package com.asi.huanan.service.customerize;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.controller.api.common.BatchController;
import com.asi.huanan.controller.api.common.PdfReportController;
import com.asi.huanan.service.dao.mybatis.mapper.FriAccountingMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacBrokerMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacRincomMapper;
import com.asi.huanan.service.dao.mybatis.mapper.customerize.CustomerizeMapper;
import com.asi.huanan.service.dao.mybatis.model.FriAccounting;
import com.asi.huanan.service.dao.mybatis.model.FriFac;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1303Query2;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1303Query3;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1303Query4;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1303QueryMain;
import com.asi.huanan.service.repository.FriAccountingRepository;
import com.asi.huanan.service.repository.FriFacBrokerRepository;
import com.asi.huanan.service.repository.FriFacRepository;
import com.asi.huanan.service.repository.FriFacRincomRepository;
import com.asi.huanan.service.repository.cutomerize.CustomerizeRepository;
import com.asi.huanan.vo.FileExport;
import com.asi.huanan.vo.Rin1303PrintVO;
import com.asi.huanan.vo.Rin1303PrintVOReq;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;
import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

@Service
public class Rin1303Service {

	private Log log = LogFactory.getLog(Rin1303Service.class);

	@Autowired
	private CustomerizeRepository customerizeRepository;
	@Autowired
	private FriFacRepository friFacRepository;
	@Autowired
	private FriFacBrokerRepository friFacBrokerRepository;
	@Autowired
	private FriFacRincomRepository friFacRincomRepository;
	@Autowired
	private FriAccountingRepository friAccountingRepository;
	@Autowired
	private MyBatisConnector mybatis;
	@Autowired
	private BatchController batchController;
	@Autowired
	private PdfReportController pdfReportController;

	private static final String DATE19000101 = "1900-01-01";

	private BigDecimal totalRate = null;

//	=================================================================================================================
//		1.?????? FAC ?????? >> ??????????????????????????????????????????????????????
//		2.?????? FAC & RinCom ??????
//			A.??? RPT ???
//			B.??? ?????? ???
//				1.1 ??????????????????????????? = "N" 
//					1.1 ????????????????????? Max Amend_Seq (rst)
//					1.2	????????????????????? (rsf)
//			
//						1.2.A.??????????????? = "Y" 
//							* ??????????????????????????????(??????)??? ( RinCom is Old ; Seq = 1)
//							* ??????????????????????????? (???????????????????????????????????)
//							* ?????????????????????(??????)???  (RinCom is New ; Seq = 2) (??????????????????????????????????????????)
//			
//						1.2.B.??????????????? = "N"
//							* ????????????????????????????????????????????????????????????
//							* ?????????????????????????????????????????? ??? ??????????????? (rst)  ??? ???????????? (FAC)
//			
//			C.Update flag = "Y"
//				1.1 Update fri_fac 
//				1.2 Update fri_fac_rincom
//			
//			D. ??????????????????????????? = "N"	(????????????????????????)
//				1.1 ??? Amend_fri_fac ?????????
//	=================================================================================================================

	/**
	 * ????????????
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yiting 2021/12/09
	 */
	public JsonBean rin1303PrintPdf(Rin1303PrintVOReq parJson) throws Exception {

		JsonBean jsonBean = new JsonBean();

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

		Date nowDate = Calendar.getInstance().getTime(); // ????????????
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = sd.format(nowDate);

		//????????????
		DecimalFormat df2 = new DecimalFormat("#,##0.00");
		DecimalFormat df4 = new DecimalFormat("0.0000");

		String fileName = "RIN130303";
		String pdfName = fileName + strDate + ".pdf";

		try {

			CustomerizeMapper customerizeMapper = sqlSession.getMapper(CustomerizeMapper.class);
			FriFacBrokerMapper brokermapper = sqlSession.getMapper(FriFacBrokerMapper.class);
			FriFacRincomMapper friFacRincomMapper = sqlSession.getMapper(FriFacRincomMapper.class);
			FriAccountingMapper friAccountingMapper = sqlSession.getMapper(FriAccountingMapper.class);
			FriFacMapper friFacMapper = sqlSession.getMapper(FriFacMapper.class);

			List<Rin1303PrintVO> result = new ArrayList<>();//???????????????????????????
			Rin1303PrintVO obj = null;						//??????????????????

			String rinComId = parJson.getRinComId();		//???????????????
			String cessionNo = parJson.getCessionNo(); 		//?????????
			String slipNo = parJson.getSlipNo(); 			//?????????(?????????)
			
			List<FriFac> facList = null;					//??????????????????????????????
			List<Rin1303QueryMain> mainList = null;			//???????????????????????????
			Rin1303QueryMain main = null;					//????????????????????????
			List<Rin1303Query2> secondList = null;			//??????????????????????????????
			Rin1303Query2 second = null;					//???????????????????????????
			List<Rin1303Query3> thirdList = null;			//????????????????????????

			BigDecimal hundred = null;			//100????????????
			BigDecimal commRate = null;			//?????????
			BigDecimal taxRate = null;			//????????????
			BigDecimal brokerRate = null;		//?????????
			BigDecimal discountRate = null;		//?????????
			BigDecimal handlingRate = null;		//?????????
			
			BigDecimal orgprem = null;			//?????????
			BigDecimal orgcomm = null;			//??????
			BigDecimal orgtax = null;			//?????????
			BigDecimal broker = null;			//??????
			BigDecimal discount = null;			//??????
			BigDecimal proc = null;				//??????
			
			BigDecimal balanceDue = null;		//??????
			BigDecimal subTotalDeb = null;		//????????????
			BigDecimal subTotalCred = null;		//????????????


			facList = new ArrayList<>();
			mainList = new ArrayList<>();

			String treatyDate = "";
			String treatyYear = "";
			String[] dateArray = null;			//????????????????????????

			// ????????????????????????================================================
			mainList = customerizeRepository.queryRin1303PrintMain(cessionNo, slipNo, rinComId, customerizeMapper);
			if (!mainList.isEmpty()) {
				main = mainList.get(0);
				obj = new Rin1303PrintVO();
				log.debug(">>> Rin1303Controller.printRin1303Report(??????????????????)");

				// ?????????????????????--------------------------------------
				dateArray = main.getTreatyDate().split("-");

				//???????????????
				if (StringUtils.isNotBlank(dateArray[0])) {
					treatyYear = dateArray[0];
				}
				if (StringUtils.isBlank(treatyYear)) {
					treatyYear = cessionNo.substring(0, 4);//???????????????
				}
				if (StringUtils.isBlank(treatyYear)) {
					treatyYear = slipNo.substring(0, 4);//????????????(?????????)???
				}
				
				//??????????????????????????????+???????????????
				switch (dateArray[1]) {
				case "01":
					treatyDate = "January , " + treatyYear;
					break;
				case "02":
					treatyDate = "February , " + treatyYear;
					break;
				case "03":
					treatyDate = "March , " + treatyYear;
					break;
				case "04":
					treatyDate = "April , " + treatyYear;
					break;
				case "05":
					treatyDate = "May , " + treatyYear;
					break;
				case "06":
					treatyDate = "June , " + treatyYear;
					break;
				case "07":
					treatyDate = "July , " + treatyYear;
					break;
				case "08":
					treatyDate = "August , " + treatyYear;
					break;
				case "09":
					treatyDate = "September , " + treatyYear;
					break;
				case "10":
					treatyDate = "October , " + treatyYear;
					break;
				case "11":
					treatyDate = "November , " + treatyYear;
					break;
				case "12":
					treatyDate = "December , " + treatyYear;
					break;
				default:
					break;
				}
				// ?????????(5)
				obj.setPeriod(treatyDate);

			} else {
				
				//????????????????????????????????????????????????
				jsonBean.setStatus(SysEnum.statusFinish.code);
				return jsonBean;
			}

			// ????????????????????????(21)
			obj.setPeriodRange(main.getTreatyDbgn() + " to " + main.getTreatyDend() + ", " + main.getDays() + " days");
			// ??????????????????(20)
			obj.setInsurant(main.getInsurant());
			// ????????????(22)
			obj.setPolicyNo(main.getPolicyNo());

			// ?????????????????????================================================
			FriFac modelFac = new FriFac();
			modelFac.setSlipNo(slipNo);
			facList = friFacRepository.queryByFriFac(modelFac);

			// ????????????or?????????or?????????0??????????????????;???????????????????????????
			String currency = "";
			BigDecimal currencyExchangeRate = null;
			if (facList.isEmpty() || StringUtils.isBlank(facList.get(0).getCurrency())
					|| facList.get(0).getCurrencyexchangerate().compareTo(BigDecimal.ZERO) == 0) {
				currency = "NTD";
				currencyExchangeRate = new BigDecimal(1);
			} else {
				currency = facList.get(0).getCurrency();

				currencyExchangeRate = new BigDecimal(1).divide(facList.get(0).getCurrencyexchangerate(), 4,
						RoundingMode.HALF_UP);
			}

			// ??????(26)
			obj.setCurrency(currency);
			// Slip??????(4)
			obj.setSlipNo(slipNo);

			// ??????????????????????????????================================================
			secondList = customerizeRepository.queryRin1303Print2(slipNo, rinComId, customerizeMapper);

			if (!secondList.isEmpty()) {
				second = secondList.get(0);

				// RefNo(1)
				obj.setRefNo(second.getRefNo());
				// ????????????(?????????)(6)??????????????????
//				obj.setShareRate(second.getShareRate() + " %");
				// ????????????(2)
				obj.setEname(second.getEname());

				// cession_no(?????????+???????????????+??????????????????(3))
				if (StringUtils.isNotBlank(main.getPolicyNoSeq())) {
					obj.setCessionNo(cessionNo + rinComId + "-" + main.getPolicyNoSeq());
				} else {
					obj.setCessionNo(cessionNo + rinComId);
				}

				// ????????????????????????
				hundred = new BigDecimal(100);								//100????????????
				BigDecimal zero = BigDecimal.ZERO;							//0????????????
				subTotalDeb = BigDecimal.ZERO;								//????????????					
				subTotalCred = BigDecimal.ZERO;								//????????????				
				

				// ?????????(9)
				commRate = new BigDecimal(second.getCommRate());			
				obj.setCommRate(commRate.toString() + " %");
				// ????????????(12)
				taxRate = new BigDecimal(second.getTaxRate());				
				obj.setTaxRate(taxRate.toString() + " %");
				// ?????????(15)
				brokerRate = new BigDecimal(second.getBrokerRate());		
				obj.setBrokerRate(brokerRate.toString() + " %");
				// ?????????(16)
				discountRate = new BigDecimal(second.getDiscountRate());	
				obj.setDiscountRate(discountRate.toString() + " %");
				// ?????????(27)
				handlingRate = new BigDecimal(second.getHandlingRate());	
				obj.setHandlingRate(df4.format(handlingRate) + " %");				
				

				// ??????
				if ("NT".equalsIgnoreCase(currency.substring(0, 2))) {

					// ?????????(7)(8)
					if (null != second.getOrgprem()	&& 0 != zero.compareTo(second.getOrgprem())) {
						orgprem = second.getOrgprem();
					} else {
						orgprem = new BigDecimal(second.getCedePrem());
					}

					if (orgprem.compareTo(BigDecimal.ZERO) < 0) {
						obj.setPremiumDeb(df2.format(orgprem.abs().setScale(0, RoundingMode.HALF_UP)));
						obj.setPremiumCred("0.00");
	
						subTotalDeb = subTotalDeb.add(orgprem.abs());
					} else {
						obj.setPremiumDeb("0.00");
						obj.setPremiumCred(df2.format(orgprem.setScale(0, RoundingMode.HALF_UP)));

						subTotalCred = subTotalCred.add(orgprem);
					}

					// ??????(10)(11)
					if (null != second.getOrgcomm()	&& 0 != zero.compareTo(second.getOrgcomm())) {
						orgcomm = second.getOrgcomm();
					} else {
						orgcomm = orgprem.multiply(commRate).divide(hundred, 0, RoundingMode.HALF_UP);
					}

					if (orgcomm.compareTo(BigDecimal.ZERO) < 0) {
						obj.setCommissionDeb("");
						obj.setCommissionCred(df2.format(orgcomm.abs()));
				
						subTotalCred = subTotalCred.add(orgcomm.abs());
					} else {
						obj.setCommissionDeb(df2.format(orgcomm));
						obj.setCommissionCred("");

						subTotalDeb = subTotalDeb.add(orgcomm);
					}

					// ?????????(13)(14)
					if (null != second.getOrgtax() && 0 != zero.compareTo(second.getOrgtax())) {
						orgtax = second.getOrgtax();
					} else {
						orgtax = orgprem.multiply(taxRate).divide(hundred, 0, RoundingMode.HALF_UP);
					}
		
					if (orgtax.compareTo(BigDecimal.ZERO) < 0) {
						obj.setBusinessTaxDeb("");
						obj.setBusinessTaxCred(df2.format(orgtax.abs()));
						
						subTotalCred = subTotalCred.add(orgtax.abs());
					} else if (orgtax.compareTo(BigDecimal.ZERO) > 0) {
						obj.setBusinessTaxDeb(df2.format(orgtax));
						obj.setBusinessTaxCred("");

						subTotalDeb = subTotalDeb.add(orgtax);
					} else {
						obj.setBusinessTaxDeb("");
						obj.setBusinessTaxCred("");
					}

					// ??????(28)(29)
					broker = orgprem.multiply(brokerRate).divide(hundred, 0, RoundingMode.HALF_UP);
					if (broker.compareTo(BigDecimal.ZERO) < 0) {
						obj.setBrokerFeeDeb("");
						obj.setBrokerFeeCred(df2.format(broker.abs()));
						
						subTotalCred = subTotalCred.add(broker.abs());
					} else if (broker.compareTo(BigDecimal.ZERO) > 0) {
						obj.setBrokerFeeDeb(df2.format(broker));
						obj.setBrokerFeeCred("");

						subTotalDeb = subTotalDeb.add(broker);
					} else {
						obj.setBrokerFeeDeb("");
						obj.setBrokerFeeCred("");
					}

					// ??????(30)(31)
					discount = orgprem.multiply(discountRate).divide(hundred, 0, RoundingMode.HALF_UP);
					if (discount.compareTo(BigDecimal.ZERO) < 0) {
						obj.setDiscountFeeDeb("");
						obj.setDiscountFeeCred(df2.format(discount.abs()));

						subTotalCred = subTotalCred.add(discount.abs());
					} else if (discount.compareTo(BigDecimal.ZERO) > 0) {
						obj.setDiscountFeeDeb(df2.format(discount));
						obj.setDiscountFeeCred("");
						
						subTotalDeb = subTotalDeb.add(discount);
					} else {
						obj.setDiscountFeeDeb("");
						obj.setDiscountFeeCred("");
					}
		
					// ??????(32)(33)
					proc = orgprem.multiply(handlingRate).divide(hundred, 0, RoundingMode.HALF_UP);
					if (proc.compareTo(BigDecimal.ZERO) < 0) {
						obj.setHandlingFeeDeb("");
						obj.setHandlingFeeCred(df2.format(proc.abs()));

						subTotalCred = subTotalCred.add(proc.abs());
					} else if (proc.compareTo(BigDecimal.ZERO) > 0) {
						obj.setHandlingFeeDeb(df2.format(proc));
						obj.setHandlingFeeCred("");

						subTotalDeb = subTotalDeb.add(proc);
					} else {
						obj.setHandlingFeeDeb("");
						obj.setHandlingFeeCred("");
					}

				// ????????????
				} else {
					// ?????????(7)(8)
					if (null != second.getOrgprem()	&& 0 != zero.compareTo(second.getOrgprem())) {
						orgprem = second.getOrgprem();
					} else {
						orgprem = new BigDecimal(second.getCedePrem()).multiply(currencyExchangeRate);
					}

					if (orgprem.compareTo(BigDecimal.ZERO) < 0) {
						obj.setPremiumDeb(df2.format(orgprem.abs()));
						obj.setPremiumCred("");

						subTotalDeb = subTotalDeb.add(orgprem.abs());
					} else {
						obj.setPremiumDeb("");
						obj.setPremiumCred(df2.format(orgprem));

						subTotalCred = subTotalCred.add(orgprem);
					}

					// ??????(10)(11)
					if (null != second.getOrgcomm()	&& 0 != zero.compareTo(second.getOrgcomm())) {
						orgcomm = second.getOrgcomm();
					} else {
						orgcomm = orgprem.multiply(commRate).divide(hundred, 2, RoundingMode.HALF_UP);
					}
				
					if (orgcomm.compareTo(BigDecimal.ZERO) < 0) {
						obj.setCommissionDeb("");
						obj.setCommissionCred(df2.format(orgcomm.abs()));

						subTotalCred = subTotalCred.add(orgcomm.abs());
					} else {
						obj.setCommissionDeb(df2.format(orgcomm));
						obj.setCommissionCred("");

						subTotalDeb = subTotalDeb.add(orgcomm);
					}

					// ?????????(13)(14)
					if (null != second.getOrgtax() && 0 != zero.compareTo(second.getOrgtax())) {
						orgtax = second.getOrgtax();
					} else {
						orgtax = orgprem.multiply(taxRate).divide(hundred, 2, RoundingMode.HALF_UP);
					}
			
					if (orgtax.compareTo(BigDecimal.ZERO) < 0) {
						obj.setBusinessTaxDeb("");
						obj.setBusinessTaxCred(df2.format(orgtax.abs()));
					
						subTotalCred = subTotalCred.add(orgtax.abs());
					} else if (orgtax.compareTo(BigDecimal.ZERO) > 0) {
						obj.setBusinessTaxDeb(df2.format(orgtax));
						obj.setBusinessTaxCred("");
						
						subTotalDeb = subTotalDeb.add(orgtax);
					} else {
						obj.setBusinessTaxDeb("");
						obj.setBusinessTaxCred("");
					}

					// ??????(28)(29)
					broker = orgprem.multiply(brokerRate).divide(hundred, 2, RoundingMode.HALF_UP);
					if (broker.compareTo(BigDecimal.ZERO) < 0) {
						obj.setBrokerFeeDeb("");
						obj.setBrokerFeeCred(df2.format(broker.abs()));

						subTotalCred = subTotalCred.add(broker.abs());
					} else if (broker.compareTo(BigDecimal.ZERO) > 0) {
						obj.setBrokerFeeDeb(df2.format(broker));
						obj.setBrokerFeeCred("");

						subTotalDeb = subTotalDeb.add(broker);
					} else {
						obj.setBrokerFeeDeb("");
						obj.setBrokerFeeCred("");
					}

					// ??????(30)(31)
					discount = orgprem.multiply(discountRate).divide(hundred, 2, RoundingMode.HALF_UP);
					if (discount.compareTo(BigDecimal.ZERO) < 0) {
						obj.setDiscountFeeDeb("");
						obj.setDiscountFeeCred(df2.format(discount.abs()));

						subTotalCred = subTotalCred.add(discount.abs());
					} else if (discount.compareTo(BigDecimal.ZERO) > 0) {
						obj.setDiscountFeeDeb(df2.format(discount));
						obj.setDiscountFeeCred("");

						subTotalDeb = subTotalDeb.add(discount);
					} else {
						obj.setDiscountFeeDeb("");
						obj.setDiscountFeeCred("");
					}
					
					// ??????(32)(33)
					proc = orgprem.multiply(handlingRate).divide(hundred, 2, RoundingMode.HALF_UP);
					if (proc.compareTo(BigDecimal.ZERO) < 0) {
						obj.setHandlingFeeDeb("");
						obj.setHandlingFeeCred(df2.format(proc.abs()));

						subTotalCred = subTotalCred.add(proc.abs());
					} else if (proc.compareTo(BigDecimal.ZERO) > 0) {
						obj.setHandlingFeeDeb(df2.format(proc));
						obj.setHandlingFeeCred("");

						subTotalDeb = subTotalDeb.add(proc);
					} else {
						obj.setHandlingFeeDeb("");
						obj.setHandlingFeeCred("");
					}
				}

				// ??????(17)(35)(34)(??????=?????????-??????-?????????-?????????-?????????-?????????)
				balanceDue = orgprem.subtract(orgcomm).subtract(orgtax).subtract(proc).subtract(broker)
						.subtract(discount);
				if (balanceDue.compareTo(BigDecimal.ZERO) < 0) {
					obj.setBalanceDueDeb("");
					obj.setBalanceDueCred(df2.format(balanceDue.abs()));
					
					subTotalCred = subTotalCred.add(balanceDue.abs());					
					obj.setBalanceDue("Balance due to us");
				} else {
					obj.setBalanceDueDeb(df2.format(balanceDue));
					obj.setBalanceDueCred("");
					
					subTotalDeb = subTotalDeb.add(balanceDue);					
					obj.setBalanceDue("Balance due to you");
				}

				// ???????????????(18)(19)
				obj.setSubTotalCred(df2.format(subTotalCred));
				obj.setSubTotalDeb(df2.format(subTotalDeb));
				// ???????????????(25)
				obj.setPaidDateExpect(second.getPaidDateExpect());

				// ?????????????????????================================================
				thirdList = friFacBrokerRepository.queryRin1303Print3(slipNo, rinComId, brokermapper);
				
				String rinComSname = "";		// ?????????????????????
				String brokerShareRate = "";	// ?????????????????????
				// ??????????????????
				if (!thirdList.isEmpty()) {
					for (Rin1303Query3 subThird : thirdList) {
						// ?????????????????????(23)
						rinComSname += subThird.getRinComSname()+"\n";
						// ?????????????????????(24)
						brokerShareRate += df2.format(new BigDecimal(subThird.getBrokerShareRate())) + " %"+"\n";
					}
				} 
					
				obj.setRinComSname(rinComSname);
				obj.setBrokerShareRate(brokerShareRate);
				result.add(obj);
			
			}

			// ???????????????================================================
			short amendSeq = 0;
			short newAmendSeq = 0;
			List<FriAccounting> resultAccount = new ArrayList<>();
			List<Rin1303Query4> resultBroker = new ArrayList<>();


			// ??????????????????????????????, ??????????????????????????? Amend
			resultAccount = friAccountingRepository.queryForRin1303No1(slipNo, friAccountingMapper);
			// ???????????????
			if (!resultAccount.isEmpty()) {

				this.friBillAmendProcess(slipNo, resultAccount.get(0).getBrokerId(), friAccountingMapper);
			}
			// ?????????????????????
			amendSeq = this.friBillAmendProcess(slipNo, rinComId, friAccountingMapper);

			newAmendSeq = (short) (amendSeq + 1);

			resultBroker = this.setBrokerInfo(slipNo, rinComId, brokermapper);

			FriAccounting modelAcct = null;

			//?????????????????????
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date oldDate = sdf.parse(DATE19000101);

			// ????????????????????????
			if (resultBroker.isEmpty()) {
				modelAcct = new FriAccounting();

				modelAcct.setAcctYear(treatyYear); 			// ????????????
				modelAcct.setAcctMonth(dateArray[1]); 		// ????????????
				modelAcct.setTreatyYear(treatyYear); 		// ????????????
				modelAcct.setTreatyNo(cessionNo); 			// ????????????
				modelAcct.setRinComId(""); 					// ???????????????
				modelAcct.setTreatyMode("5"); 				// ????????????
				modelAcct.setCashclaim(0L); 				// ????????????
				modelAcct.setShiftPrem(0L); 				// ?????????????????????
				modelAcct.setShiftLoss(0L); 				// ???????????????????????????
				modelAcct.setIncometax(0L); 				// ?????????????????????
				modelAcct.setIncometaxRate(BigDecimal.ZERO);// ?????????????????????income tax%
				modelAcct.setStampFee(0L); 					// ?????????
				modelAcct.setStampRate(BigDecimal.ZERO); 	// ????????????
				modelAcct.setLoss(0L); 						// ????????????
				modelAcct.setOutLoss(0L); 					// ????????????
				modelAcct.setAcctDate(nowDate); 			// ?????????
				modelAcct.setCloseFlag(""); 				// ??????????????????
				modelAcct.setCloseDate(oldDate); 			// ???????????????
				modelAcct.setBillNo(slipNo); 				// ????????????
				modelAcct.setBillType("2"); 				// ????????????
				modelAcct.setYourRef(second.getRefNo()); 	// ???????????????(??????)
				modelAcct.setTreatyType("09"); 				// ????????????
				modelAcct.setInOut("C"); 					// ????????????
				modelAcct.setAmendSeq(newAmendSeq); 		// amend??????
				modelAcct.setPrem(orgprem.longValue()); 	// ?????????
				modelAcct.setBrokerFee(broker.longValue()); // ???????????????
				modelAcct.setBrokerRate(brokerRate); 		// ??????????????????
				modelAcct.setComm(orgcomm.longValue()); 	// ????????????
				modelAcct.setCommRate(commRate); 			// ???????????????
				modelAcct.setBusstax(orgtax.longValue()); 	// ??????????????????
				modelAcct.setBusstaxRate(taxRate); 			// ??????????????????
				modelAcct.setHandlingFee(proc.longValue()); // ????????????
				modelAcct.setHandlingRate(handlingRate); 	// ?????????
				// balance us/you
				if (balanceDue.compareTo(BigDecimal.ZERO) > 0) {
					modelAcct.setDuetous(0L);
					modelAcct.setDuetoyou(balanceDue.longValue());
				} else {
					modelAcct.setDuetous(balanceDue.abs().longValue());
					modelAcct.setDuetoyou(0L);
				}

				modelAcct.setBrokerId(rinComId); 			// ???????????????
				modelAcct.setCurrency(currency); 			// ??????
				modelAcct.setMkovse(main.getMkovse()); 		// ??????????????????
				modelAcct.setCurrencyexchangerate(currencyExchangeRate);// ??????
				modelAcct.setTurnFlag("Y"); 				// ?????????

				modelAcct.setRinComIdOld("");
				modelAcct.setBrokerIdOld("");

				friAccountingRepository.insert(modelAcct, friAccountingMapper);
			//????????????????????????
			} else {
				int no = 0;

				for (Rin1303Query4 modelQ4 : resultBroker) {
					modelAcct = new FriAccounting();

					modelAcct.setAcctYear(treatyYear);
					modelAcct.setAcctMonth(dateArray[1]);
					modelAcct.setTreatyYear(treatyYear);
					modelAcct.setTreatyNo(cessionNo);
					modelAcct.setRinComId(modelQ4.getRin_com_id());
					modelAcct.setTreatyMode("5");
					modelAcct.setCashclaim(0L);
					modelAcct.setShiftPrem(0L);
					modelAcct.setShiftLoss(0L);
					modelAcct.setIncometax(0L);
					modelAcct.setIncometaxRate(BigDecimal.ZERO);
					modelAcct.setStampFee(0L);
					modelAcct.setStampRate(BigDecimal.ZERO);
					modelAcct.setLoss(0L);
					modelAcct.setOutLoss(0L);
					modelAcct.setAcctDate(nowDate);
					modelAcct.setCloseFlag("");
					modelAcct.setCloseDate(oldDate);
					modelAcct.setBillNo(slipNo);
					modelAcct.setBillType("2");
					modelAcct.setYourRef(second.getRefNo());
					modelAcct.setTreatyType("09");
					modelAcct.setInOut("C");
					modelAcct.setAmendSeq(newAmendSeq);
					modelAcct.setPrem(this.brokerRincomFee(orgprem, no, resultBroker).longValue());
					modelAcct.setBrokerFee(this.brokerRincomFee(broker, no, resultBroker).longValue());
					modelAcct.setBrokerRate(modelQ4.getRin_com_share());
					modelAcct.setComm(this.brokerRincomFee(orgcomm, no, resultBroker).longValue());
					modelAcct.setCommRate(commRate);
					modelAcct.setBusstax(this.brokerRincomFee(orgtax, no, resultBroker).longValue());
					modelAcct.setBusstaxRate(taxRate);
					modelAcct.setHandlingFee(this.brokerRincomFee(proc, no, resultBroker).longValue());
					modelAcct.setHandlingRate(handlingRate);

					BigDecimal resultNt = this.brokerRincomFee(balanceDue, no, resultBroker);
					if (resultNt.compareTo(BigDecimal.ZERO) > 0) {
						modelAcct.setDuetous(0L);
						modelAcct.setDuetoyou(resultNt.longValue());
					} else {
						modelAcct.setDuetous(resultNt.abs().longValue());
						modelAcct.setDuetoyou(0L);
					}

					modelAcct.setBrokerId(modelQ4.getBrokerId());
					modelAcct.setCurrency(currency);
					modelAcct.setMkovse(main.getMkovse());
					modelAcct.setCurrencyexchangerate(currencyExchangeRate);
					modelAcct.setTurnFlag("Y");

					modelAcct.setRinComIdOld("");
					modelAcct.setBrokerIdOld("");

					friAccountingRepository.insert(modelAcct, friAccountingMapper);

					no++;
				}
			}

			// Update fri_fac_rincom ??????acct_flag='Y'???transfer_status = 'Y'
			// ????????????????????????????????????????????????????????????????????????
			if ("1".equals(parJson.getBillType())) {
				friFacRincomRepository.updateRin1303AcctFlag(new Date(), slipNo, rinComId, friFacRincomMapper);
			} else {
				friFacRincomRepository.updateRin1303AcctFlag(null, slipNo, rinComId, friFacRincomMapper);
			}

			// Update fri_fac ??????acct_flag='Y'
			friFacRepository.updateRin1303AcctFlag(slipNo, friFacMapper);

			// ??????????????????
			Map<String, Object> parameter = new HashedMap<>();
			FileExport fe = new FileExport();
			fe.setFileName(fileName);
			byte[] pdfByteArr = null;

			// ??????base64
			pdfByteArr = pdfReportController.getPdfBytes(parameter, result, fileName, "");
			String pdfBase64Encode = Base64.getEncoder().encodeToString(pdfByteArr);
			fe.setFileBase64Encode(pdfBase64Encode);

			pdfReportController.printReport(parameter, result, fileName, "", pdfName);
			batchController.printNow("Rin1303P", parJson.getAccount(), pdfName, (byte) 2);
			sqlSession.commit();

			jsonBean.setData(fe);
			jsonBean.setStatus(SysEnum.statusSuccess.code);

		} catch (Exception e) {
			sqlSession.rollback();
			batchController.printNow("Rin1303P", parJson.getAccount(), pdfName, (byte) 4);
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setMessage("??????????????????!!!?????????????????????!!!");
			jsonBean.setStatus(SysEnum.statusError.code);

		} finally {
			sqlSession.close();
		}
		return jsonBean;
	}

	private short friBillAmendProcess(String amendBillNo, String brokerId, FriAccountingMapper friAccountingMapper)
			throws Exception {

		String amendFlag = "N";
		short iMaxAmendSeq = 0;
		short friBillAmendProcess = 0;

		List<FriAccounting> result1 = new ArrayList<>();
		List<FriAccounting> result2 = new ArrayList<>();
		try {
			result1 = friAccountingRepository.queryForRin1303No2(amendBillNo, brokerId, friAccountingMapper);

			if (result1.isEmpty()) {
				iMaxAmendSeq = -1;
			} else {
				if (null != result1.get(0).getAmendSeq()) {
					iMaxAmendSeq = result1.get(0).getAmendSeq();
				}
				amendFlag = result1.get(0).getAmendFlag();
			}

			if ("Y".equals(amendFlag)) {
				friBillAmendProcess = iMaxAmendSeq;
				return friBillAmendProcess;
			}

			result2 = friAccountingRepository.queryForRin1303No3(amendBillNo, brokerId, iMaxAmendSeq,
					friAccountingMapper);

			if (!result2.isEmpty()) {
				iMaxAmendSeq = result2.get(0).getAmendSeq();

				// ???????????????????????????
				Boolean toDelete = false;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String closeDate = sdf.format(result2.get(0).getCloseDate());

				String turnFlag = result2.get(0).getTurnFlag();
				// ???????????????????????????????????????
				if (DATE19000101.equals(closeDate) || ((!"T".equals(turnFlag)) && (!"H".equals(turnFlag)))) {
					toDelete = true;
				}

				// ????????????????????????????????????
				if (Boolean.TRUE.equals(toDelete)) {
					friAccountingRepository.deleteForRin1303(amendBillNo, iMaxAmendSeq, brokerId, friAccountingMapper);
					iMaxAmendSeq -= 1;
				} else {
					iMaxAmendSeq += 1;

					FriAccounting model = null;
					Date nowDate = Calendar.getInstance().getTime();
					Date oldDate = sdf.parse(DATE19000101);
					for (FriAccounting acct : result2) {
						model = new FriAccounting();

						BeanUtils.copyProperties(acct, model);

						model.setAmendSeq(iMaxAmendSeq); 	// amendSeq
						model.setAmendFlag("Y"); 			// amend??????(Y:??????N:???)
						model.setAcctDate(nowDate); 		// ?????????
						model.setCloseDate(oldDate); 		// ???????????????
						model.setCloseFlag(""); 			// ??????????????????
						model.setTurnFlag("Y"); 			// ?????????(Y???H???T)
						model.setLastupdatedate(oldDate); 	// ???????????????

						if (acct.getPrem() != null) {
							model.setPrem(acct.getPrem() * -1);
						}
						if (acct.getBrokerFee() != null) {
							model.setBrokerFee(acct.getBrokerFee() * -1);
						}
						if (acct.getComm() != null) {
							model.setComm(acct.getComm() * -1);
						}
						if (acct.getBusstax() != null) {
							model.setBusstax(acct.getBusstax() * -1);
						}
						if (acct.getHandlingFee() != null) {
							model.setHandlingFee(acct.getHandlingFee() * -1);
						}
						if (acct.getDuetous() != null) {
							model.setDuetous(acct.getDuetous() * -1);
						}
						if (acct.getDuetoyou() != null) {
							model.setDuetoyou(acct.getDuetoyou() * -1);
						}

						model.setLastupdatedate(nowDate);
						model.setLastupdatedateReal(nowDate);

						friAccountingRepository.insert(model, friAccountingMapper);
					}
				}

			}

			friBillAmendProcess = iMaxAmendSeq;

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sub -> log.debug(sub.toString()));
		}

		return friBillAmendProcess;
	}

	private List<Rin1303Query4> setBrokerInfo(String slipNo, String brokerId, FriFacBrokerMapper mapper)
			throws Exception {

		this.totalRate = BigDecimal.ZERO; // ?????????????????????totalRate????????????????????????????????????????????????????????????totalRate
		List<Rin1303Query4> result = new ArrayList<>();
		try {
			result = friFacBrokerRepository.queryRin1303BrokerInfo(slipNo, brokerId, mapper);
			for (Rin1303Query4 model : result) {
				this.totalRate = this.totalRate.add(model.getRin_com_share());
				model.setBrokerId(brokerId);
			}

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sub -> log.debug(sub.toString()));

		}

		return result;
	}

	private BigDecimal brokerRincomFee(BigDecimal feeIn, int no, List<Rin1303Query4> brokerList) throws Exception {

		BigDecimal brokerRincomFee = null;
		BigDecimal feeTotal = null;
		BigDecimal[] fee = new BigDecimal[brokerList.size()];
		BigDecimal feeRest = null;

		if (feeIn == null) {
			brokerRincomFee = BigDecimal.ZERO;
			return brokerRincomFee;
		}

		if (this.totalRate.compareTo(BigDecimal.ZERO) == 0) {
			brokerRincomFee = BigDecimal.ZERO;
		} else {
			feeTotal = BigDecimal.ZERO;

			for (int i = 0; i < brokerList.size(); i++) {
				fee[i] = feeIn
						.multiply(brokerList.get(i).getRin_com_share().divide(this.totalRate, 0, RoundingMode.HALF_UP));
				feeTotal = feeTotal.add(fee[i]);
			}
			feeRest = feeIn.subtract(feeTotal);
			fee[0] = fee[0].add(feeRest);
		}

		brokerRincomFee = fee[no];

		return brokerRincomFee;
	}

}
