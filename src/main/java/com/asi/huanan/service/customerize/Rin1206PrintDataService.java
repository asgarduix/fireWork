package com.asi.huanan.service.customerize;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.asi.huanan.service.dao.mybatis.mapper.ComAutonumMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriAccountingMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPayAdviceMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyBrokerMapper;
import com.asi.huanan.service.dao.mybatis.mapper.Rin120602Mapper;
import com.asi.huanan.service.dao.mybatis.mapper.Rin120603Mapper;
import com.asi.huanan.service.dao.mybatis.mapper.Rin1206BrokerdetailMapper;
import com.asi.huanan.service.dao.mybatis.mapper.Rin1206Mapper;
import com.asi.huanan.service.dao.mybatis.mapper.customerize.CustomerizeMapper;
import com.asi.huanan.service.dao.mybatis.model.FriAccounting;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyBroker;
import com.asi.huanan.service.dao.mybatis.model.Rin1206;
import com.asi.huanan.service.dao.mybatis.model.Rin120602;
import com.asi.huanan.service.dao.mybatis.model.Rin120603;
import com.asi.huanan.service.dao.mybatis.model.Rin1206Brokerdetail;
import com.asi.huanan.service.repository.ComAutonumRepository;
import com.asi.huanan.service.repository.FriAccountingRepository;
import com.asi.huanan.service.repository.FriPayAdviceRepository;
import com.asi.huanan.service.repository.FriTreatyBrokerRepository;
import com.asi.huanan.service.repository.Rin120602Repository;
import com.asi.huanan.service.repository.Rin120603Repository;
import com.asi.huanan.service.repository.Rin1206BrokerdetailRepository;
import com.asi.huanan.service.repository.Rin1206Repository;
import com.asi.huanan.service.repository.cutomerize.CustomerizeRepository;
import com.asi.huanan.vo.Rin1206AmountDataVO;
import com.asi.huanan.vo.Rin1206PrintDataVO;
import com.asi.huanan.vo.Rin1206PrintModelVO;
import com.asi.huanan.vo.Rin1206PrintReportVOReq;
import com.asi.huanan.vo.Rin1206QueryConditionVO;
import com.asi.huanan.vo.Rin1206QueryDataVO;
import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.swissknife.Asiutil;

@Service
public class Rin1206PrintDataService {
	private static Logger log = LogManager.getLogger(Rin1206PrintDataService.class);


	@Autowired
	CustomerizeRepository customerizeRepository;

	@Autowired
	private MyBatisConnector mybatis;

	@Autowired
	private FriPayAdviceRepository friPayAdviceRepository;

	@Autowired
	private Rin1206Repository rin1206Repository;

	@Autowired
	private Rin120602Repository rin120602Repository;

	@Autowired
	private Rin120603Repository rin120603Repository;

	@Autowired
	private Rin1206BrokerdetailRepository rin1206BrokerdetailRepository;

	@Autowired
	private FriTreatyBrokerRepository friTreatyBrokerRepository;

	@Autowired
	private ComAutonumRepository comAutonumRepository;

	@Autowired
	private FriAccountingRepository FriAccountingRepository;

	Asiutil asiutil = new Asiutil();

	public Rin1206PrintDataVO generatePrintData(Rin1206PrintReportVOReq parJson) throws Exception{
		Rin1206PrintDataVO printDateVO = new Rin1206PrintDataVO();
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

		//???????????????table mapper
		CustomerizeMapper customerizeMapper = sqlSession.getMapper(CustomerizeMapper.class);
		FriPayAdviceMapper friPayAdviceMapper = sqlSession.getMapper(FriPayAdviceMapper.class);
		Rin1206Mapper rin1206Mapper = sqlSession.getMapper(Rin1206Mapper.class);
		Rin120602Mapper rin120602Mapper = sqlSession.getMapper(Rin120602Mapper.class);
		Rin120603Mapper rin120603Mapper = sqlSession.getMapper(Rin120603Mapper.class);
		Rin1206BrokerdetailMapper brokerdetailMapper = sqlSession.getMapper(Rin1206BrokerdetailMapper.class);
		ComAutonumMapper comAutonumMapper = sqlSession.getMapper(ComAutonumMapper.class);
		FriTreatyBrokerMapper friTreatyBrokerMapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
		FriAccountingMapper friAccountingMapper = sqlSession.getMapper(FriAccountingMapper.class);

		//????????????????????????
		List<String> billNoList = new ArrayList<>();
		List<Rin1206PrintModelVO> contractDetailList = new ArrayList<>();//Rin1206????????????
		List<Rin120602> rin120602List = new ArrayList<>();//Rin120602??????
		List<Rin120603> rin120603List = new ArrayList<>();//Rin120603?????????
		List<Rin1206PrintModelVO> createRincomList = new ArrayList<>();
		List<Rin1206QueryDataVO> queryDataList1 = new ArrayList<>();
		List<Rin1206QueryDataVO> queryDataList2 = new ArrayList<>();
		List<Rin1206QueryDataVO> queryDataList3 = new ArrayList<>();
		List<Rin1206QueryDataVO> queryDataList4 = new ArrayList<>();
		List<FriTreatyBroker> brokerList = new ArrayList<>();
		List<FriAccounting> accountingList = new ArrayList<>();//for pdf

		//?????????????????????
		String billNo = null;
		//???code??????????????????+ProcessControlID,ProcessControlID??????????????????????????????
		String rptId = asiutil.processDateToString(new Date(), "yyyMMddHHmm");

		//??????????????????
		BigDecimal otherShare = BigDecimal.ZERO;
		BigDecimal totalPrem = BigDecimal.ZERO;
		BigDecimal totalPrem2 = BigDecimal.ZERO;
		BigDecimal totalPrem3 = BigDecimal.ZERO;
		BigDecimal db_rate = BigDecimal.ZERO;
		BigDecimal treatyShareRateForPrint = BigDecimal.ZERO;
		BigDecimal diff = BigDecimal.ZERO;

		//????????????
		String rdoTKind1 = parJson.getRdoTKind1();//???????????? ?????????????????????
		String txtTreatyYear = parJson.getTxtTreatyYear();
		String monthPeriod = parJson.getMonthPeriod();//????????????-????????????
		String periodNo = parJson.getPeriodNo();//????????????-????????????
		String treatyNo = parJson.getTreatyNo();//??????????????????

		try {
			//?????????????????????
			StringBuilder dateBgn = new StringBuilder();
			StringBuilder dateEnd = new StringBuilder();

			if("1".equals(monthPeriod)) {//???
				dateBgn.append(txtTreatyYear + "/" + monthPeriod + "/01");
				dateEnd.append(txtTreatyYear + "/" + monthPeriod + "/01");//?????????????????????????????????
			}else if("2".equals(monthPeriod)) {//???

				int monBgn = 1 + 3*(Integer.parseInt(periodNo) -1);
				int monthEnd = 3 * Integer.parseInt(periodNo);

				String monBgnStr = String.format("%02d", monBgn);
				String monEndStr = String.format("%02d", monthEnd);

				dateBgn.append(txtTreatyYear + "/" + monBgnStr +"/01");
				dateEnd.append(txtTreatyYear + "/" + monEndStr +"/01");


			}else if ("3".equals(monthPeriod)) {//??????
				int monBgn = 1 + 6*(Integer.parseInt(periodNo) -1);
				int monthEnd = 6 * Integer.parseInt(periodNo);

				String monBgnStr = String.format("%02d", monBgn);
				String monEndStr = String.format("%02d", monthEnd);

				dateBgn.append(txtTreatyYear + "/" + monBgnStr +"/01");
				dateEnd.append(txtTreatyYear + "/" + monEndStr +"/01");
			}else {
				log.error("??????????????????????????????");
			}

			//??????Date??????, dateEnd?????????????????????
			Date policyDprtBgn = asiutil.processStringToDate("yyyy/MM/dd", dateBgn.toString());
			Date policyDprtEnd = this.lastDay(dateEnd.toString());

			Rin1206PrintModelVO printModelVo1 = new Rin1206PrintModelVO();
			printModelVo1.setPolicyDprtBgn(policyDprtBgn);
			printModelVo1.setPolicyDprtEnd(policyDprtEnd);
			printModelVo1.setMonthPeriod(monthPeriod);
			printModelVo1.setTreatyNo(treatyNo);

			//1-????????????????????????????????????(????????????)
			contractDetailList = customerizeRepository.getContractDetail(printModelVo1, customerizeMapper);//rs2

			//2-???????????????????????????
			//rs2.Fields("treaty_year").Value), NullToSpace(rs2.Fields("treaty_no").Value), mssAccType
			Rin1206PrintModelVO printModelVo2 = new Rin1206PrintModelVO();
			printModelVo2.setTreaty_year(txtTreatyYear);
			printModelVo2.setTreaty_no(treatyNo);
			//mssAccType
			printModelVo2.setMonthPeriod(monthPeriod);//????????????-????????????(????????????-mscomMode)

			createRincomList = customerizeRepository.CreateRincom(printModelVo2, customerizeMapper);//rs3

			//??????otherShareRate & treatyShareRateForPrint
			otherShare = new BigDecimal(createRincomList.get(0).getOther_share()).divide(new BigDecimal("100"));
			if("1".equals(createRincomList.get(0).getShare_type())) {
				treatyShareRateForPrint = new BigDecimal(createRincomList.get(0).getShare_rate());
			}else {
				treatyShareRateForPrint = new BigDecimal(createRincomList.get(0).getOther_share());
			}

			//3-insertData????????????Table Rin1206
			List<Rin1206> modelList = new ArrayList<>();
			for(Rin1206PrintModelVO obj : contractDetailList) {
				//3.1-contractDetailList?????????????????????Policy??????3???????????????
				String dateFormat = "yyyy/MM/dd";
				obj.setPolicy_dateBgn(asiutil.processDateToString(obj.getPolicy_dbgn(), dateFormat));
				obj.setPolicy_dateEnd(asiutil.processDateToString(obj.getPolicy_dend(), dateFormat));
				obj.setPolicy_datePrt(asiutil.processDateToString(obj.getPolicy_dprt(), dateFormat));

				//3.2-??????db_rate
				if(!CheckIsNullSpace.isNull(obj.getShare_amt()) && obj.getShare_amt().compareTo(BigDecimal.ZERO) != 0) {
					db_rate = obj.getShare_prem().divide(obj.getShare_amt(), 2, RoundingMode.HALF_UP);
				}

				//3.3-??????insertData
				modelList.add(this.generateRin1206Data(rptId, obj, createRincomList, otherShare));

			}

			//3.4 insertData
			int rin1206Count = rin1206Repository.insertList(modelList, rin1206Mapper);

			//??????createRincomList(rs3)??????share_prem
			//Rin1206??????sum(share_prem)
			if(!modelList.isEmpty()) {
				totalPrem = modelList.stream().filter(p -> Long.parseLong(rptId) == p.getRptid())
						.map(Rin1206::getSharePrem)
						.reduce(BigDecimal.ZERO, BigDecimal::add);
			}

			totalPrem = totalPrem.setScale(0, RoundingMode.HALF_UP);
			//4-??????
			Rin1206QueryConditionVO queryConditionVO1 = new Rin1206QueryConditionVO();
			Rin1206QueryConditionVO queryConditionVO2 = new Rin1206QueryConditionVO();

			queryConditionVO1.setRptid(Long.parseLong(rptId));
			queryConditionVO1.setTreatyNo(treatyNo);
			queryConditionVO1.setPolicyMode("1");//?????????1

			queryConditionVO2.setRptid(Long.parseLong(rptId));
			queryConditionVO2.setTreatyNo(treatyNo);
			queryConditionVO2.setPolicyMode("2");//?????????2
			//			'policy_mode ='1' ??????;'2'??????
			//4.1 Rin1206?????????????????????????????????-??????
			queryDataList1 = customerizeRepository.QueryData1(queryConditionVO1, customerizeMapper);
			queryDataList2 = customerizeRepository.QueryData1(queryConditionVO2, customerizeMapper);

			//4.2 insertData2-????????????
			List<Rin120602> rin120602ModeList1 =new ArrayList<>();
			List<Rin120602> rin120602ModeList2 =new ArrayList<>();
			for(Rin1206QueryDataVO obj:queryDataList1) {//??????
				rin120602ModeList1.add(generateRin120602Data(rptId, policyDprtBgn, policyDprtEnd, obj, 1, otherShare, treatyShareRateForPrint, friPayAdviceMapper));
				int rin120602Count1 = rin120602Repository.insertList(rin120602ModeList1, rin120602Mapper);
			}
			for(Rin1206QueryDataVO obj:queryDataList2) {//??????
				rin120602ModeList2.add(generateRin120602Data(rptId, policyDprtBgn, policyDprtEnd, obj, 1, otherShare, treatyShareRateForPrint, friPayAdviceMapper));
				int rin120602Count2 = rin120602Repository.insertList(rin120602ModeList2, rin120602Mapper);
			}
			//4.3 ??????totalPrem2
			long rin120602prem1 = 0l;
			long rin120602prem2 = 0l;
			if(!rin120602ModeList1.isEmpty()) {		//?????? prem
				rin120602prem1 = rin120602ModeList1.stream().filter(p -> Long.parseLong(rptId) == p.getRptid())
						.mapToLong(Rin120602::getTreatyPrem)
						.sum();
			}
			if(!rin120602ModeList2.isEmpty()) {		//?????? prem
				rin120602prem2 = rin120602ModeList2.stream().filter(p -> Long.parseLong(rptId) == p.getRptid())
						.mapToLong(Rin120602::getTreatyPrem)
						.sum();
			}

			totalPrem2 = new BigDecimal(String.valueOf(rin120602prem1 + rin120602prem2));

			diff = totalPrem.subtract(totalPrem2);
			if(diff.compareTo(BigDecimal.ZERO) != 0) {
				//select top1
				List<Rin120602> queryTop1List = rin120602Repository.queryTop1(Long.parseLong(rptId), rin120602Mapper);
				if(!queryTop1List.isEmpty() && CheckIsNullSpace.isNullOrDefault(queryTop1List.get(0).getTreatyPrem())) {
					String treatyPrem = String.valueOf(queryTop1List.get(0).getTreatyPrem());
					totalPrem2 = new BigDecimal(treatyPrem).add(diff);
				}
			}

			//5-?????????
			//			'policy_mode ='1' ??????;'2'??????
			//5.1 Rin1206?????????????????????????????????-??????

			//sql????????????????????????queryConditionVO1 & queryConditionVO2
			queryDataList3 = customerizeRepository.QueryData2(queryConditionVO1, customerizeMapper);
			queryDataList4 = customerizeRepository.QueryData2(queryConditionVO2, customerizeMapper);

			//5.2 insertData2-????????????
			List<Rin120603> rin120603ModeList1 = new ArrayList<>();
			List<Rin120603> rin120603ModeList2 = new ArrayList<>();
			for(Rin1206QueryDataVO r2QueryObj : queryDataList3) {//??????
				for(Rin1206PrintModelVO r3RincomObj : createRincomList) {
					rin120603ModeList1.add(generateRin120603Data(rptId, policyDprtBgn, policyDprtEnd, r2QueryObj, r3RincomObj,
							1, otherShare, treatyShareRateForPrint, friPayAdviceMapper, friTreatyBrokerMapper));
				}
				int rin120603Count1 = rin120603Repository.insertList(rin120603ModeList1, rin120603Mapper);

			}

			for(Rin1206QueryDataVO r2QueryObj:queryDataList4) {//??????
				for(Rin1206PrintModelVO r3RincomObj : createRincomList) {
					rin120603ModeList2.add(generateRin120603Data(rptId, policyDprtBgn, policyDprtEnd, r2QueryObj, r3RincomObj,
							2, otherShare, treatyShareRateForPrint, friPayAdviceMapper, friTreatyBrokerMapper));
				}
				int rin120603Count2 = rin120603Repository.insertList(rin120603ModeList2, rin120603Mapper);

			}

			//5.3 ??????totalPrem3
			long rin120603prem1 = 0l;
			long rin120603prem2 = 0l;
			if(!rin120603ModeList1.isEmpty()) {
				rin120603prem1 = rin120603ModeList1.stream().filter(p -> Long.parseLong(rptId) == p.getRptid())
						.mapToLong(Rin120603::getSharePrem)
						.sum();
			}
			if(!rin120603ModeList2.isEmpty()) {
				rin120603prem2 = rin120603ModeList2.stream().filter(p -> Long.parseLong(rptId) == p.getRptid())
						.mapToLong(Rin120603::getSharePrem)
						.sum();
			}
			totalPrem3 = new BigDecimal(String.valueOf(rin120603prem1 + rin120603prem2));

			//????????????
			int serial = 0;
			String dprtBgn = asiutil.processDateToString(policyDprtBgn, "yyyy/MM/dd");
			String []dprtBgnArr = dprtBgn.split("/");
			String dprtEnd = asiutil.processDateToString(policyDprtEnd, "yyyy/MM/dd");
			String []dprtEndArr = dprtEnd.split("/");
			BigDecimal loss = BigDecimal.ZERO;
			BigDecimal cash = BigDecimal.ZERO;
			BigDecimal dBalance = BigDecimal.ZERO;
			BigDecimal dDuetoYou = BigDecimal.ZERO;
			BigDecimal dDuetoUs  = BigDecimal.ZERO;

			List<Rin1206AmountDataVO> amountList = new ArrayList<>();
			List<Rin1206PrintModelVO> createRincomList2 = new ArrayList<>();

			//????????????
			amountList = rin120603Repository.queryAmountData(rptId, rin120603Mapper);

			//????????????
			createRincomList2 = customerizeRepository.CreateRincom2(printModelVo2, customerizeMapper);//???createRincom????????????????????????
			BigDecimal agent_rate = createRincomList2.get(0).getAgent_rate();//broker_rate
			BigDecimal firrulcom_rate = createRincomList2.get(0).getFirrulcom_rate();//comm_rate
			BigDecimal businesstax_rate = createRincomList2.get(0).getBusinesstax_rate();//busstax_rate
			BigDecimal handling_rate = createRincomList2.get(0).getHandling_rate();//handling_rate
			BigDecimal stamptax_rate = createRincomList2.get(0).getStamptax_rate();//stamp_rate
			String rin_com_contract_no = createRincomList2.get(0).getRin_com_contract_no() == null?"":createRincomList2.get(0).getRin_com_contract_no();

			for(Rin1206AmountDataVO rsObj :amountList) {
				FriAccounting friAccountingModel = new FriAccounting();
				String sBroker = rsObj.getRin_com_id().trim();//rin_com_id, ex:B000006

				//???billNo
				if("1".equals(rdoTKind1)) {//????????????
					billNo = generateBillNo(txtTreatyYear, comAutonumMapper);
					billNoList.add(billNo);//??????????????????
				}else {					   //????????????
					serial ++;
					String serialNo = String.format("%04d", serial);
					billNo = txtTreatyYear + "TEST" + serialNo;
					billNoList.add(billNo);//??????????????????

				}

				//update rin120602 &rin120603 billNo
				rin120602Repository.updateBillNo(Long.parseLong(rptId), rin120602Mapper);
				rin120603Repository.updateBillNo(Long.parseLong(rptId), billNo, sBroker, rin120603Mapper);

				//??????broker(sTreaty_Year, sTreaty_no, sBroker)
				brokerList = friTreatyBrokerRepository.queryRin1206BrokerInfo(txtTreatyYear, treatyNo, sBroker, friTreatyBrokerMapper);
				for(FriTreatyBroker broker : brokerList) {
					Rin1206Brokerdetail model = new Rin1206Brokerdetail();
					model.setRptid(Long.parseLong(rptId));
					model.setBrokerId(broker.getBrokerId());
					model.setRinComId(broker.getRinComId());
					model.setRinComSname(broker.getRinComSname());
					model.setRinComShare(broker.getRinComShare());
					rin1206BrokerdetailRepository.insertModel(model, brokerdetailMapper);
				}

				if(brokerList.isEmpty()) {
					log.info("brokerList no Data!");

					loss = this.getLossPaidValue(treatyNo, policyDprtBgn, policyDprtEnd, sBroker, "", otherShare, friPayAdviceMapper);
					cash = this.getCashClaimValue(txtTreatyYear, treatyNo, policyDprtBgn, policyDprtEnd, sBroker, "", otherShare, friPayAdviceMapper);

					dBalance = rsObj.getRiprem().subtract(rsObj.getBusinesstax()).subtract(rsObj.getStamptax()).subtract(rsObj.getHandlingFee())
							.subtract(rsObj.getComm()).subtract(loss).add(cash);

					if(dBalance.compareTo(BigDecimal.ZERO) > 0) {
						dDuetoYou = dBalance;
						dDuetoUs = BigDecimal.ZERO;
					}else {
						dDuetoYou = BigDecimal.ZERO;
						dDuetoUs = dBalance.negate();
					}

					friAccountingModel.setAcctYear(dprtEndArr[0]);
					friAccountingModel.setAcctMonth(dprtEndArr[1]);
					friAccountingModel.setTreatyYear(txtTreatyYear);
					friAccountingModel.setTreatyNo(treatyNo);
					friAccountingModel.setBrokerId(sBroker);
					friAccountingModel.setRinComId("");

					friAccountingModel.setTreatyMode(monthPeriod);//???????????? (1.???2.???3.??????)
					friAccountingModel.setAmendSeq((short)0);
					friAccountingModel.setPrem(Long.parseLong(rsObj.getRiprem().setScale(0, RoundingMode.HALF_UP).toString()));
					friAccountingModel.setCashclaim(Long.parseLong(cash.setScale(0, RoundingMode.HALF_UP).toString()));
					friAccountingModel.setBrokerFee(Long.parseLong(rsObj.getAgent().setScale(0, RoundingMode.HALF_UP).toString()));

					friAccountingModel.setBrokerRate(agent_rate);
					friAccountingModel.setShiftPrem(0l);
					friAccountingModel.setShiftLoss(0l);
					friAccountingModel.setIncometax(0l);
					friAccountingModel.setIncometaxRate(BigDecimal.ZERO);
					friAccountingModel.setComm(Long.parseLong(rsObj.getComm().setScale(0, RoundingMode.HALF_UP).toString()));
					friAccountingModel.setCommRate(firrulcom_rate);
					friAccountingModel.setBusstax(Long.parseLong(rsObj.getBusinesstax().setScale(0, RoundingMode.HALF_UP).toString()));
					friAccountingModel.setBusstaxRate(businesstax_rate);
					friAccountingModel.setHandlingFee(Long.parseLong(rsObj.getHandlingFee().setScale(0, RoundingMode.HALF_UP).toString()));
					friAccountingModel.setHandlingRate(handling_rate);

					friAccountingModel.setStampFee(Long.parseLong(rsObj.getStamptax().setScale(0, RoundingMode.HALF_UP).toString()));
					friAccountingModel.setStampRate(stamptax_rate);
					friAccountingModel.setLoss(Long.parseLong(loss.setScale(0, RoundingMode.HALF_UP).toString()));
					friAccountingModel.setOutLoss(0l);

					friAccountingModel.setDuetous(Long.parseLong(dDuetoUs.setScale(0, RoundingMode.HALF_UP).toString()));
					friAccountingModel.setDuetoyou(Long.parseLong(dDuetoYou.setScale(0, RoundingMode.HALF_UP).toString()));
					friAccountingModel.setAcctDate(policyDprtEnd);
					friAccountingModel.setBillNo(billNo);
					friAccountingModel.setBillType("1");

					friAccountingModel.setYourRef(rin_com_contract_no);
					friAccountingModel.setTreatyType("01");
					friAccountingModel.setInOut("C");
					friAccountingModel.setTurnFlag("Y");

					//INSERT INTO fri_accounting
					if("1".equals(rdoTKind1)) {//????????????(??????????????????)
						int count = FriAccountingRepository.insert(friAccountingModel, friAccountingMapper);
					}

				}else {//brokerRincomFee(feein,i,brokerList)
					Rin1206Brokerdetail brokerModel = new Rin1206Brokerdetail();
					brokerModel.setBrokerId(sBroker);

					for(int i =0 ; i < brokerList.size();i++) {
						FriTreatyBroker brokerObj = brokerList.get(i);
						loss = this.getLossPaidValue(treatyNo, policyDprtBgn, policyDprtEnd, brokerList.get(i).getBrokerId(), brokerList.get(i).getRinComId(), otherShare, friPayAdviceMapper);
						cash = this.getCashClaimValue(txtTreatyYear, treatyNo, policyDprtBgn, policyDprtEnd, brokerList.get(i).getBrokerId(), brokerList.get(i).getRinComId(), otherShare, friPayAdviceMapper);

						dBalance = brokerRincomFee(rsObj.getRiprem(), i, brokerList)
								.subtract(brokerRincomFee(rsObj.getBusinesstax(), i, brokerList))
								.subtract(brokerRincomFee(rsObj.getStamptax(), i, brokerList))
								.subtract(brokerRincomFee(rsObj.getHandlingFee(), i, brokerList))
								.subtract(brokerRincomFee(rsObj.getComm(), i, brokerList))
								.subtract(loss).add(cash);

						if(dBalance.compareTo(BigDecimal.ZERO) > 0) {
							dDuetoYou = dBalance;
							dDuetoUs = BigDecimal.ZERO;
						}else {
							dDuetoYou = BigDecimal.ZERO;
							dDuetoUs = dBalance.negate();
						}

						friAccountingModel.setAcctYear(dprtEndArr[0]);
						friAccountingModel.setAcctMonth(dprtEndArr[1]);
						friAccountingModel.setTreatyYear(txtTreatyYear);
						friAccountingModel.setTreatyNo(treatyNo);
						friAccountingModel.setBrokerId(brokerObj.getBrokerId());
						friAccountingModel.setRinComId(brokerObj.getRinComId());

						friAccountingModel.setTreatyMode(monthPeriod);//???????????? (1.???2.???3.??????)
						friAccountingModel.setAmendSeq((short)0);

						BigDecimal prem = brokerRincomFee(rsObj.getRiprem(),i,brokerList);
						friAccountingModel.setPrem(Long.parseLong(prem.setScale(0, RoundingMode.HALF_UP).toString()));
						friAccountingModel.setCashclaim(Long.parseLong(cash.setScale(0, RoundingMode.HALF_UP).toString()));

						BigDecimal agent = brokerRincomFee(rsObj.getAgent(),i,brokerList);
						friAccountingModel.setBrokerFee(Long.parseLong(agent.setScale(0, RoundingMode.HALF_UP).toString()));



						friAccountingModel.setBrokerRate(brokerObj.getRinComShare());
						friAccountingModel.setShiftPrem(0l);
						friAccountingModel.setShiftLoss(0l);
						friAccountingModel.setIncometax(0l);
						friAccountingModel.setIncometaxRate(BigDecimal.ZERO);

						BigDecimal comm = brokerRincomFee(rsObj.getComm(),i,brokerList);
						BigDecimal commrate = brokerRincomFee(firrulcom_rate,i,brokerList);
						friAccountingModel.setComm(Long.parseLong(comm.setScale(0, RoundingMode.HALF_UP).toString()));
						friAccountingModel.setCommRate(commrate);

						BigDecimal busstax = brokerRincomFee(rsObj.getBusinesstax(),i,brokerList);
						BigDecimal busstaxRate = brokerRincomFee(businesstax_rate,i,brokerList);
						friAccountingModel.setBusstax(Long.parseLong(busstax.setScale(0, RoundingMode.HALF_UP).toString()));
						friAccountingModel.setBusstaxRate(busstaxRate);

						BigDecimal handlingFee = brokerRincomFee(rsObj.getHandlingFee(),i,brokerList);
						BigDecimal handlingFeeRate = brokerRincomFee(handling_rate,i,brokerList);
						friAccountingModel.setHandlingFee(Long.parseLong(handlingFee.setScale(0, RoundingMode.HALF_UP).toString()));
						friAccountingModel.setHandlingRate(handlingFeeRate);

						BigDecimal stampFee = brokerRincomFee(rsObj.getStamptax(),i,brokerList);
						BigDecimal stampRate = brokerRincomFee(stamptax_rate,i,brokerList);
						friAccountingModel.setStampFee(Long.parseLong(stampFee.setScale(0, RoundingMode.HALF_UP).toString()));
						friAccountingModel.setStampRate(stampRate);

						friAccountingModel.setLoss(Long.parseLong(loss.setScale(0, RoundingMode.HALF_UP).toString()));
						friAccountingModel.setOutLoss(0l);

						friAccountingModel.setDuetous(Long.parseLong(dDuetoUs.setScale(0, RoundingMode.HALF_UP).toString()));
						friAccountingModel.setDuetoyou(Long.parseLong(dDuetoYou.setScale(0, RoundingMode.HALF_UP).toString()));
						friAccountingModel.setAcctDate(policyDprtEnd);
						friAccountingModel.setBillNo(billNo);
						friAccountingModel.setBillType("1");

						friAccountingModel.setYourRef(rin_com_contract_no);
						friAccountingModel.setTreatyType("01");
						friAccountingModel.setInOut("C");
						friAccountingModel.setTurnFlag("Y");

						//INSERT INTO fri_accounting
						if("1".equals(rdoTKind1)) {//????????????(??????????????????)
							int count = FriAccountingRepository.insert(friAccountingModel, friAccountingMapper);
						}

					}
				}
				//???????????????list
				accountingList.add(friAccountingModel);
			}



			sqlSession.commit();

			//??????Excel????????????
			rin120602List = rin120602Repository.queryAll();
			rin120603List = rin120603Repository.queryAll();
			printDateVO.setContractDetailList(contractDetailList);//????????????
			printDateVO.setRin120602List(rin120602List);			//??????
			printDateVO.setRin120603List(rin120603List);			//?????????

			//??????Pdf????????????
			printDateVO.setRptId(rptId);
			printDateVO.setBillNoList(billNoList);
			printDateVO.setAccountingList(accountingList);
			printDateVO.setBrokerList(brokerList);
		} catch (Exception e) {
			sqlSession.rollback();
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

		}finally {
			sqlSession.close();
		}

		return printDateVO;
	}

	public Rin1206 generateRin1206Data(String rptid, Rin1206PrintModelVO printModelVo, List<Rin1206PrintModelVO> printModelVoList, BigDecimal otherShare) {
		Rin1206 rin1206 = new Rin1206();
		BigDecimal sharePrem = BigDecimal.ZERO;
		BigDecimal db_businesstax = BigDecimal.ZERO;
		BigDecimal db_handling_fee = BigDecimal.ZERO;
		BigDecimal db_stamptax = BigDecimal.ZERO;
		BigDecimal db_agent = BigDecimal.ZERO;
		BigDecimal db_comm = BigDecimal.ZERO;

		//1-??????db_businesstax,db_handling_fee,db_stamptax,db_agent,db_comm
		if(printModelVo.getShare_prem() != null) {
			sharePrem = printModelVo.getShare_prem();
			db_businesstax = sharePrem.multiply(new BigDecimal("0.01")).multiply(otherShare).setScale(0, RoundingMode.HALF_UP);
			db_handling_fee = sharePrem.multiply(printModelVoList.get(0).getHandling_rate())
					.multiply(new BigDecimal("0.01")).multiply(otherShare).setScale(0, RoundingMode.HALF_UP);
			db_stamptax = sharePrem.multiply(printModelVoList.get(0).getStamptax_rate())
					.multiply(new BigDecimal("0.01")).multiply(otherShare).setScale(0, RoundingMode.HALF_UP);
			db_agent = sharePrem.multiply(printModelVoList.get(0).getAgent_rate())
					.multiply(new BigDecimal("0.01")).multiply(otherShare).setScale(0, RoundingMode.HALF_UP);
			db_comm = sharePrem.multiply(printModelVoList.get(0).getFirrulcom_rate())
					.multiply(new BigDecimal("0.01")).multiply(otherShare).setScale(0, RoundingMode.HALF_UP);

		}
		//2-??????insert to Rin1206 data
		rin1206.setRptid(Long.parseLong(rptid));
		rin1206.setTreatyYear(Integer.parseInt(printModelVo.getTreaty_year()));
		rin1206.setTreatyNo(printModelVo.getTreaty_no());
		rin1206.setRinComId("");
		rin1206.setRinComShare(Double.parseDouble("0"));
		rin1206.setBusinesstax(db_businesstax);
		rin1206.setStamptax(db_stamptax);
		rin1206.setHandlingFee(db_handling_fee);
		rin1206.setAgent(db_agent);

		rin1206.setPolicyNo(printModelVo.getPolicy_no());
		rin1206.setEndorseNo(printModelVo.getEndorse_no());
		rin1206.setSeqNo(printModelVo.getAddr_no());
		rin1206.setLimitNo(printModelVo.getLimit_no());
		rin1206.setAmount(printModelVo.getAmt());
		rin1206.setPremium(printModelVo.getPrem());
		rin1206.setComm(db_comm);

		rin1206.setDstart(printModelVo.getPolicy_dbgn());
		rin1206.setDend(printModelVo.getPolicy_dend());

		String dprtMonth = asiutil.processDateToString(printModelVo.getPolicy_dprt(), "yyyy/MM/dd");

		rin1206.setDprtMonth(dprtMonth.split("/")[1]);//???Month() //???????????
		rin1206.setShareAmt(printModelVo.getShare_amt());
		rin1206.setSharePrem(printModelVo.getShare_prem().multiply(otherShare));
		rin1206.setRiamt(BigDecimal.ZERO);
		rin1206.setRiprem(BigDecimal.ZERO);
		rin1206.setRipaid(BigDecimal.ZERO);
		rin1206.setRate(Double.parseDouble("0"));
		rin1206.setRinComEname("");

		rin1206.setTreatyName(printModelVoList.get(0).getTreaty_name() == null? "": printModelVoList.get(0).getTreaty_name());
		rin1206.setAcctFlag(printModelVo.getAcct_flag());
		rin1206.setSharePremOrg(printModelVo.getShare_prem());

		return rin1206;
	}


	public Rin120602 generateRin120602Data(String rptid, Date dprtBgn, Date dprtEnd, Rin1206QueryDataVO queryDataVO, int i, BigDecimal otherShare, BigDecimal treatyShareRateForPrint, FriPayAdviceMapper friPayAdviceMapper) throws Exception {
		Rin120602 rin120602 = new Rin120602();

		BigDecimal treatyPrem = BigDecimal.ZERO;
		BigDecimal busstax = BigDecimal.ZERO;
		BigDecimal comm = BigDecimal.ZERO;
		BigDecimal lossPaid = BigDecimal.ZERO;
		BigDecimal cashClaim = BigDecimal.ZERO;
//		BigDecimal loss = BigDecimal.ZERO;

		//1-??????treatyPrem,busstax,comm,lossPaid,cashClaim
		treatyPrem = queryDataVO.getTreaty_prems();
		busstax = treatyPrem.multiply(new BigDecimal("0.01")).setScale(0, RoundingMode.HALF_UP);
		comm = treatyPrem.multiply(queryDataVO.getFirrulcom_rate()).divide(new BigDecimal("100"), 0, RoundingMode.HALF_UP);
		lossPaid = getLossPaidValue(queryDataVO.getTreaty_no(), dprtBgn, dprtEnd, "", "", otherShare, friPayAdviceMapper);
		cashClaim = getCashClaimValue(queryDataVO.getTreaty_year().toString(), queryDataVO.getTreaty_no(), dprtBgn, dprtEnd, "", "", otherShare, friPayAdviceMapper);

		//2-??????insert to Rin120602 data
		rin120602.setRptid(Long.parseLong(rptid));
		rin120602.setBillNo("");
		rin120602.setTreatyName(queryDataVO.getTreaty_name() + "-" + queryDataVO.getTreaty_year().toString());
		rin120602.setTreatyRate(treatyShareRateForPrint);
		rin120602.setTreatyMode(queryDataVO.getTreaty_sname());


		String dprtBgnString = asiutil.processDateToString(dprtBgn, "yyyy/MM/dd");
		String treatyMonth = dprtBgnString.split("/")[0] + "/" + queryDataVO.getDprt_month() + "/01";
		rin120602.setTreatyMonth(asiutil.processStringToDate("yyyy/MM/dd", treatyMonth));
		rin120602.setTreatyPrem(Long.parseLong(treatyPrem.setScale(0, RoundingMode.HALF_UP).toString()));
		rin120602.setCommRate(Double.parseDouble(queryDataVO.getFirrulcom_rate().toString()));
		rin120602.setComm(comm);
		rin120602.setBusstaxRate(Double.parseDouble("1"));
		rin120602.setBusstax(busstax);
		rin120602.setPolicyMode("1");
		rin120602.setLoss(lossPaid);


		rin120602.setCashrefund(cashClaim);

		return rin120602;
	}

	public Rin120603 generateRin120603Data(String rptid, Date dprtBgn, Date dprtEnd, Rin1206QueryDataVO rsQueryDataVO, Rin1206PrintModelVO rs2RincomObj, int i, BigDecimal otherShare, BigDecimal treatyShareRateForPrint, FriPayAdviceMapper friPayAdviceMapper, FriTreatyBrokerMapper friTreatyBrokerMapper) throws Exception {
		Rin120603 rin120603 = new Rin120603();

		BigDecimal treatyPrem = BigDecimal.ZERO;
		BigDecimal loss = BigDecimal.ZERO;
		BigDecimal riShare = BigDecimal.ZERO;
		BigDecimal busstax = BigDecimal.ZERO;
		BigDecimal comm = BigDecimal.ZERO;

		BigDecimal lossPaid = BigDecimal.ZERO;
		BigDecimal cashClaim = BigDecimal.ZERO;
		BigDecimal totallossPaid = BigDecimal.ZERO;
		List<FriTreatyBroker> brokerList = new ArrayList<>();
		String isBroker = null;

//		createRincomList????????????
		Integer treatyYear = rsQueryDataVO.getTreaty_year();
		String treatyNo = rsQueryDataVO.getTreaty_no();
		String rinComId = rs2RincomObj.getRin_com_id();
		String rinComShare = rs2RincomObj.getRin_com_share();
		String rinComTax = rs2RincomObj.getRin_com_tax();
		BigDecimal firrulcomRate = rs2RincomObj.getFirrulcom_rate();


		//1-??????treatyPrem,loss,comm,totallossPaid,lossPaid,cashClaim,riShare,busstax,comm
		treatyPrem = rsQueryDataVO.getTreaty_prems().setScale(0, RoundingMode.HALF_UP);

		loss = rsQueryDataVO.getRipaids().multiply(new BigDecimal(rinComShare)).divide(new BigDecimal("100"), 0, RoundingMode.HALF_UP);

		totallossPaid = getLossPaidValue(treatyNo, dprtBgn, dprtEnd, "", "", otherShare, friPayAdviceMapper);
		lossPaid = getLossPaidValue(treatyNo, dprtBgn, dprtEnd, rinComId, "", otherShare, friPayAdviceMapper);
		cashClaim = getCashClaimValue(treatyYear.toString(), treatyNo
				, dprtBgn, dprtEnd, rinComId, "", otherShare, friPayAdviceMapper);

		riShare = treatyPrem.multiply(new BigDecimal(rinComShare)).divide(new BigDecimal("100"), 0, RoundingMode.HALF_UP);
		busstax = riShare.multiply(new BigDecimal(rinComTax)).divide(new BigDecimal("100"), 0, RoundingMode.HALF_UP);
		comm = riShare.multiply(firrulcomRate).divide(new BigDecimal("100"), 0, RoundingMode.HALF_UP);

		//2-??????Fri_Treaty_Broker ??????isBroker???
		brokerList = friTreatyBrokerRepository.queryRin1206BrokerInfo(treatyYear.toString(), treatyNo, rinComId, friTreatyBrokerMapper);
		if(!brokerList.isEmpty()) {
			isBroker = "Y";
		}else {
			isBroker = "N";
		}

		//3-??????insert to Rin120603 data
		String dprtBgnString = asiutil.processDateToString(dprtBgn, "yyyy/MM/dd");

		rin120603.setRptid(Long.parseLong(rptid));
		rin120603.setBillNo("");
		rin120603.setTreatyName(rsQueryDataVO.getTreaty_name() + "-" + rsQueryDataVO.getTreaty_year());
		rin120603.setRinComShare(new BigDecimal(rinComShare));
		rin120603.setRinComSname(rs2RincomObj.getEname());
		rin120603.setTreatyRate(treatyShareRateForPrint);
		rin120603.setTreatyMode(rsQueryDataVO.getTreaty_sname());
		rin120603.setTreatyNo(treatyNo);
		String treatyMonth = dprtBgnString.split("/")[0] + "/" + rsQueryDataVO.getDprt_month() + "/01";
		rin120603.setTreatyMonth(asiutil.processStringToDate("yyyy/MM/dd", treatyMonth));
		rin120603.setTreatyPrem(Long.parseLong(treatyPrem.toString()));
		rin120603.setSharePrem(Long.parseLong(riShare.toString()));
		rin120603.setCommRate(Double.parseDouble(firrulcomRate.toString()));
		rin120603.setComm(comm);
		rin120603.setBusstaxRate(Double.parseDouble(rinComTax));
		rin120603.setBusstax(busstax);
		rin120603.setPolicyMode(String.valueOf(i));

		rin120603.setTotalLoss(totallossPaid);
		rin120603.setLoss(lossPaid);
		rin120603.setCashrefund(cashClaim);
		rin120603.setRinComId(rinComId);
		rin120603.setTreatyYear(String.valueOf(treatyYear));
		rin120603.setTreatyNo(treatyNo);
		rin120603.setIsbroker(isBroker);

		return rin120603;
	}
	// ????????????
	private Date lastDay(String parDate) throws ParseException {

		Asiutil util = new Asiutil();
		Date resDate = util.processStringToDate("yyyy/MM/dd HH:mm:ss", parDate + " 23:59:59");

		Calendar cal = new GregorianCalendar();
		cal.setTime(resDate);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));

		return cal.getTime();
	}

	/**
	 * ????????????
	 * @param dateBgn	??????????????????
	 * @param dateEnd	??????????????????
	 * @param treaty_no	??????????????????
	 * @param brokerID	??????????????????????????????????????????
	 * @param inComId	??????????????????????????????????????????
	 * @param friPayAdviceMapper
	 * @return
	 * @throws Exception
	 */
	private BigDecimal getLossPaidValue(String treatyNo, Date dateBgn, Date dateEnd, String brokerID,String inComId, BigDecimal otherShare, FriPayAdviceMapper friPayAdviceMapper) throws Exception{
		BigDecimal lossPaid = BigDecimal.ZERO;

		//????????????  from fri_pay_advice
		long sumLossPaid = friPayAdviceRepository.getLossPaidValue(treatyNo, dateBgn, dateEnd, brokerID, inComId, friPayAdviceMapper);

		//???????????? FFQSS ???, ??????????????????
		if("FFQSS".equals(treatyNo)) {
			lossPaid = new BigDecimal(String.valueOf(sumLossPaid)).setScale(0, RoundingMode.HALF_UP);
		}else {
			lossPaid = new BigDecimal(String.valueOf(sumLossPaid)).divide(otherShare, 0, RoundingMode.HALF_UP);
		}

		return lossPaid;
	}

	/**
	 *
	 * @param treatyYear	??????????????????
	 * @param treatyNo		??????????????????
	 * @param dateBgn
	 * @param dateEnd
	 * @param brokerID		??????????????????????????????????????????
	 * @param inComId		??????????????????????????????????????????
	 * @return
	 * @throws Exception
	 */
	public BigDecimal getCashClaimValue(String treatyYear, String treatyNo, Date dateBgn, Date dateEnd, String brokerID,String inComId, BigDecimal otherShare, FriPayAdviceMapper friPayAdviceMapper) throws Exception{
		BigDecimal cashClaim = BigDecimal.ZERO;

		//????????????  from fri_pay_advice
		long sumCashClaim = friPayAdviceRepository.getCashClaimValue(treatyYear, treatyNo, dateBgn, dateEnd, brokerID, inComId, friPayAdviceMapper);

		cashClaim = new BigDecimal(String.valueOf(sumCashClaim)).multiply(otherShare).setScale(0, RoundingMode.HALF_UP);

		return cashClaim;
	}


	public String generateBillNo(String num_year, ComAutonumMapper comAutonumMapper) throws Exception {
//		com_autonum
		String number_code= "FTB";

		int serial = comAutonumRepository.getBillNoMaxSerial(num_year, number_code, comAutonumMapper);
		//??????????????????
		if(serial > 0){
			//???????????????+1
			serial += 1;
			//Update serialNumber
			comAutonumRepository.updateBillNoSerial(serial, num_year, number_code);
		}else {
			//insert new Autonum
			comAutonumRepository.insertNewAutonum(num_year, number_code);
			serial++;
		}

		String serialNo = String.format("%04d", serial);
		String billNo = num_year + number_code + serialNo;

		return billNo;
	}

	private BigDecimal brokerRincomFee(BigDecimal feeIn, int no, List<FriTreatyBroker> brokerList) {

		BigDecimal brokerRincomFee = BigDecimal.ZERO;
		BigDecimal totalRate = BigDecimal.ZERO;
		BigDecimal feeTotal =  BigDecimal.ZERO;
		BigDecimal[] fee = new BigDecimal[brokerList.size()];
		BigDecimal feeRest = BigDecimal.ZERO;;

		if (feeIn == null) {
			brokerRincomFee = BigDecimal.ZERO;
			return brokerRincomFee;
		}

		for(FriTreatyBroker broker :brokerList) {
			totalRate = totalRate.add(broker.getRinComShare());
		}
		if (totalRate.compareTo(BigDecimal.ZERO) == 0) {
			brokerRincomFee = BigDecimal.ZERO;
		} else {

			for (int i = 0; i < brokerList.size(); i++) {
				fee[i] = feeIn
						.multiply(brokerList.get(i).getRinComShare().divide(totalRate, 0, RoundingMode.HALF_UP));
				feeTotal = feeTotal.add(fee[i]);
			}
			feeRest = feeIn.subtract(feeTotal);
			fee[0] = fee[0].subtract(feeTotal);


		}
		brokerRincomFee = fee[no];

		return brokerRincomFee;
	}

}
