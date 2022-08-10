package com.asi.huanan.service.customerize;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.controller.api.common.PdfReportController;
import com.asi.huanan.service.dao.mybatis.model.FriAccounting;
import com.asi.huanan.service.dao.mybatis.model.Rin120603;
import com.asi.huanan.service.dao.mybatis.model.Rin1206Brokerdetail;
import com.asi.huanan.service.repository.Rin120603Repository;
import com.asi.huanan.service.repository.Rin1206BrokerdetailRepository;
import com.asi.huanan.service.repository.cutomerize.CustomerizeRepository;
import com.asi.huanan.vo.Rin1206PdfDataVO;
import com.asi.huanan.vo.Rin1206PrintDataVO;
import com.asi.huanan.vo.Rin1206PrintReportVOReq;
import com.asi.mechanism.SpringProperty;
import com.asi.swissknife.Asiutil;
import com.ibm.icu.text.DecimalFormat;



@Service
public class Rin1206ReportService {
	private static Logger log = LogManager.getLogger(Rin1206ReportService.class);
	Asiutil asiutil = new Asiutil();

	@Autowired
	PdfReportController pdfReportController;

	@Autowired
	CustomerizeRepository customerizeRepository;

	@Autowired
	private Rin120603Repository rin120603Repository;

	@Autowired
	private Rin1206BrokerdetailRepository rin1206BrokerdetailRepository;

	/**
	 * EXCEL輸出
	 * @param printDataVO
	 * @param parJson
	 * @return
	 * @throws IOException
	 */
	public String generateExcelReport(Rin1206PrintDataVO printDataVO, Rin1206PrintReportVOReq parJson) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		Map<String, String> excelTitleMap1 = new LinkedHashMap<>();
		Map<String, String> excelTitleMap2 = new LinkedHashMap<>();
		Map<String, String> excelTitleMap3 = new LinkedHashMap<>();
		FileOutputStream fos = null;
		// 輸出位置
		String filePath = SpringProperty.localFileForExcel;

		//1.2-列印前的相關資料
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = sdFormat.format(new Date());
		String fileName = null;

		try {

			if(printDataVO.getContractDetailList() == null || printDataVO.getRin120602List() == null || printDataVO.getRin120603List() == null) {
				log.debug("報表資料異常");
				return fileName;
			}
			//1.3-轉換顯示日期
			List<Rin120603> rin120603List = printDataVO.getRin120603List();
			for(Rin120603 obj:rin120603List) {
				String dateFormat = "yyyy/MM/dd";
				obj.setTreatyMonthDate(asiutil.processDateToString(obj.getTreatyMonth(), dateFormat));
			}
			//1.4-產生excel

			// 建立sheet
			HSSFSheet sheet1 = workbook.createSheet("Rin1206明細資料");
			HSSFSheet sheet2 = workbook.createSheet("Rin120602總表");
			HSSFSheet sheet3 = workbook.createSheet("Rin120603再保人");

			//總表明細,總表,再保人
			excelTitleMap1 = this.getExcelTitleMap1();
			excelTitleMap2 = this.getExcelTitleMap2();
			excelTitleMap3 = this.getExcelTitleMap3();
			workbook = this.getWorkbook(workbook,sheet1, printDataVO.getContractDetailList(), excelTitleMap1);
			workbook = this.getWorkbook(workbook,sheet2, printDataVO.getRin120602List(), excelTitleMap2);
			workbook = this.getWorkbook(workbook,sheet3, printDataVO.getRin120603List(), excelTitleMap3);

			//1.5- excel輸出
			//判別資料夾是否存在，若不存在則建立
			File directory = new File(filePath);
			if(!directory.isFile()) {
				directory.mkdirs();
			}
			fileName = "Rin1206再保合約帳單列印_" + strDate + ".xls";
			File file = new File(filePath + fileName);
			fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.flush();
			fos.close();

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}finally {
			workbook.close();
		}

		return fileName;
	}

	/**
	 * PDF資料查詢 & 輸出(季度合約)
	 * @param printDataVO
	 * @param parJson
	 */
	public String generatePdfReport(Rin1206PrintDataVO printDataVO, Rin1206PrintReportVOReq parJson) {
		Map<String, Object> parameters = new HashedMap<>();
		String subreportPath = "templates/ireport/jasper/";//子報表路徑
		parameters.put("SUBREPORT_DIR", subreportPath);
		List<Rin1206PdfDataVO> pdfList = new ArrayList<>();
		List<Rin1206Brokerdetail> brokerdetailList = null;
		String fileName = null;
		List<String> billNoList = printDataVO.getBillNoList();//帳單號碼清單
		String rptId = printDataVO.getRptId();//列印號碼
		String txtTreatyYear = parJson.getTxtTreatyYear();
		String periodNo = parJson.getPeriodNo();//列印狀態-日期區間

		if (billNoList == null) {
			return fileName;
		}
		try {
			//1.前置資料處理
			//1.1處理pdf顯示時間
			String today = this.getDateNow();//取系統顯示時間ex:Jul 21 ,2021
			parameters.put("today", today);
			//季區間(顯示Date用)
			String period = this.getPeriod(periodNo, txtTreatyYear);
			parameters.put("period", period);

			//1.2此次列印下的Rin1206DATA
			List<Rin120603> rin120603List = new ArrayList<>();
			if (rptId != null) {
				rin120603List = rin120603Repository.queryDataByRptId(Long.parseLong(rptId));
			}

			//2.pdf資料塞值
			//先找每份帳單
			for(String billNo : billNoList) {
				Rin1206PdfDataVO pdfDataVo = new Rin1206PdfDataVO();

				//取rin120603單一帳單下的資料
				List<Rin120603> rin120603BillNoList = new ArrayList<>();
				rin120603BillNoList = rin120603List.stream().filter(p -> p.getBillNo().equals(billNo))
						.collect(Collectors.toList());
				//rinComidDataList如為一季應內含 有六項保批單各季三個月的資料


				//2.1 pdf表頭資料(rin120603)
				pdfDataVo.setRinComSname(rin120603BillNoList.get(0).getRinComSname());
				pdfDataVo.setBillNo(billNo);
				pdfDataVo.setTreatyName(rin120603BillNoList.get(0).getTreatyName());
				pdfDataVo.setRinComShare(rin120603BillNoList.get(0).getRinComShare().toString() + "%");

				//分出比率
				BigDecimal treatyRate = rin120603BillNoList.get(0).getTreatyRate().setScale(0, RoundingMode.HALF_UP);

				pdfDataVo.setTreatyRate(treatyRate.toString() +"%" + rin120603BillNoList.get(0).getTreatyMode());

				//保批單區間
				switch (periodNo) {
				case "1":
					pdfDataVo.setpMonth1("Jan");
					pdfDataVo.setpMonth2("Feb");
					pdfDataVo.setpMonth3("March");
					break;
				case "2":
					pdfDataVo.setpMonth1("Apr");
					pdfDataVo.setpMonth2("May");
					pdfDataVo.setpMonth3("Jun");
					break;
				case "3":
					pdfDataVo.setpMonth1("July");
					pdfDataVo.setpMonth2("Aug");
					pdfDataVo.setpMonth3("Sep");
					break;
				case "4":
					pdfDataVo.setpMonth1("Oct");
					pdfDataVo.setpMonth2("Nov");
					pdfDataVo.setpMonth3("Dec");
					break;
				default:
					break;
				}

				//2.2 pdf保單資料(rin120603)
				List<Rin120603> policyList = rin120603BillNoList.stream().filter(p -> p.getPolicyMode().equals("1"))
						.collect(Collectors.toList());
				Long pSum = 0l;
				if(policyList.size() == 1) {
					Long pTreatyPrem1 = policyList.get(0).getTreatyPrem();
					pdfDataVo.setpTotalPrem1(coverCashValue(pTreatyPrem1));
					pdfDataVo.setpTotalPrem2("0");
					pdfDataVo.setpTotalPrem3("0");
					Long pSharePrem1 = policyList.get(0).getSharePrem();
					pdfDataVo.setpSharePrem1(coverCashValue(pSharePrem1));
					pdfDataVo.setpSharePrem2("0");
					pdfDataVo.setpSharePrem3("0");
					pSum = pSharePrem1;
				}else {
					Long pTreatyPrem1 = policyList.get(0).getTreatyPrem();
					Long pTreatyPrem2 = policyList.get(1).getTreatyPrem();
					Long pTreatyPrem3 = policyList.get(2).getTreatyPrem();
					pdfDataVo.setpTotalPrem1(coverCashValue(pTreatyPrem1));
					pdfDataVo.setpTotalPrem2(coverCashValue(pTreatyPrem2));
					pdfDataVo.setpTotalPrem3(coverCashValue(pTreatyPrem3));

					Long pSharePrem1 = policyList.get(0).getSharePrem();
					Long pSharePrem2 = policyList.get(1).getSharePrem();
					Long pSharePrem3 = policyList.get(2).getSharePrem();
					pSum = pSharePrem1 + pSharePrem2 + pSharePrem3;
					pdfDataVo.setpSharePrem1(coverCashValue(pSharePrem1));
					pdfDataVo.setpSharePrem2(coverCashValue(pSharePrem2));
					pdfDataVo.setpSharePrem3(coverCashValue(pSharePrem3));
				}
				pdfDataVo.setpSum(coverCashValue(pSum));


				//2.3 pdf批單資料(rin120603)
				List<Rin120603> endorseList = rin120603BillNoList.stream().filter(p -> p.getPolicyMode().equals("2"))
						.collect(Collectors.toList());
				Long eSum = 0l;
				if(endorseList.size() == 1) {
					Long eTreatyPrem1 = endorseList.get(0).getTreatyPrem();
					pdfDataVo.seteTotalPrem1(coverCashValue(eTreatyPrem1));
					pdfDataVo.seteTotalPrem2("0");
					pdfDataVo.seteTotalPrem3("0");
					Long eSharePrem1 = endorseList.get(0).getSharePrem();
					pdfDataVo.seteSharePrem1(coverCashValue(eSharePrem1));
					pdfDataVo.seteSharePrem2("0");
					pdfDataVo.seteSharePrem3("0");
					eSum = eSharePrem1;
				}else {
					Long eTreatyPrem1 = endorseList.get(0).getTreatyPrem();
					Long eTreatyPrem2 = endorseList.get(1).getTreatyPrem();
					Long eTreatyPrem3 = endorseList.get(2).getTreatyPrem();
					pdfDataVo.seteTotalPrem1(coverCashValue(eTreatyPrem1));
					pdfDataVo.seteTotalPrem2(coverCashValue(eTreatyPrem2));
					pdfDataVo.seteTotalPrem3(coverCashValue(eTreatyPrem3));

					Long eSharePrem1 = endorseList.get(0).getSharePrem();
					Long eSharePrem2 = endorseList.get(1).getSharePrem();
					Long eSharePrem3 = endorseList.get(2).getSharePrem();
					pdfDataVo.seteSharePrem1(coverCashValue(eSharePrem1));
					pdfDataVo.seteSharePrem2(coverCashValue(eSharePrem2));
					pdfDataVo.seteSharePrem3(coverCashValue(eSharePrem3));
					eSum = eSharePrem1 + eSharePrem2 + eSharePrem3;

				}
				pdfDataVo.seteSum(coverCashValue(eSum));

				//2.4理賠資料()
				List<FriAccounting> accountingList = printDataVO.getAccountingList();//帳單資料
				List<FriAccounting> billAccountingList = new ArrayList<>();

				billAccountingList =
						accountingList.stream().filter(s -> s.getBillNo().equals(billNo))
						.collect(Collectors.toList());

				for(FriAccounting accountData : billAccountingList) {
					Long busstax = accountData.getBusstax();
					Long comm = accountData.getComm();
					Long loss = accountData.getLoss();
					pdfDataVo.setBusinesstaxRate(rin120603BillNoList.get(0).getBusstaxRate() + "%");
					pdfDataVo.setBusstax(coverCashValue(busstax));

					pdfDataVo.setComm(coverCashValue(comm));
					pdfDataVo.setCommRate(accountData.getCommRate().setScale(0, RoundingMode.HALF_UP).toString() + "%");

					BigDecimal totalLoss = rin120603BillNoList.get(0).getTotalLoss().setScale(0, RoundingMode.HALF_UP);
					pdfDataVo.setTotalLoss(coverCashValue(Long.parseLong(totalLoss.toString())));
					pdfDataVo.setLoss(coverCashValue(loss));

					Long dueToYou = accountData.getDuetoyou();
					Long dueToUs  = accountData.getDuetous();
					pdfDataVo.setDuetoYouLong(dueToYou);//pdf判斷使用
					pdfDataVo.setDuetoYou(coverCashValue(dueToYou));
					pdfDataVo.setDuetoUs(coverCashValue(dueToUs));

					//總和
					Long debitTotalSum = busstax + comm + loss + dueToYou;
					Long creditTotalSum = pSum + eSum + dueToUs;
					pdfDataVo.setDebitTotalSum(coverCashValue(debitTotalSum));
					pdfDataVo.setCreditTotalSum(coverCashValue(creditTotalSum));
				}
				//帳單經紀人 & 再保人們
				String sbroker = rin120603BillNoList.get(0).getRinComId();
				Rin1206Brokerdetail model = new Rin1206Brokerdetail();
				model.setRptid(Long.parseLong(rptId));
				model.setBrokerId(sbroker);
				brokerdetailList = rin1206BrokerdetailRepository.queryByRptIdBrokerId(model);
				if(!brokerdetailList.isEmpty()) {
					for(Rin1206Brokerdetail brokerdetail :brokerdetailList) {
						brokerdetail.setRinComShareStr(brokerdetail.getRinComShare().setScale(2, RoundingMode.HALF_UP).toString() + " %");
					}
					pdfDataVo.setBrokerdetailList(brokerdetailList);
				}
				pdfList.add(pdfDataVo);

			}
			//3.PDF輸出
			if(!billNoList.isEmpty()) {
				SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				String strDate = sdFormat.format(new Date());
				fileName = "Rin1206再保合約帳單列印_" + strDate + ".pdf";
				String jrxmlName = "Rin1206";
				pdfReportController.printReport(parameters, pdfList, jrxmlName, "", fileName);
			}



		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}
		return fileName;
	}


	/**
	 * 將標頭名稱與輸出資料組合，生成excel
	 * @param <E>
	 * @param fileName
	 * @param resultsList
	 * @param titleMap
	 * @return
	 * @throws Exception
	 */
	public <E> HSSFWorkbook getWorkbook(HSSFWorkbook workbook, HSSFSheet sheet, List<E> resultsList, Map<String, String> titleMap) throws Exception {

		try {

			// 建立單元格樣式
			CellStyle cellStyle = workbook.createCellStyle();

			HSSFRow headerRow = sheet.createRow(0);
			if (!titleMap.isEmpty()) {
				int count = 0;
				for(Map.Entry<String, String> titleEntry : titleMap.entrySet()) {
					headerRow.createCell(count).setCellStyle(cellStyle);
					headerRow.createCell(count).setCellValue(titleEntry.getValue());
					count += 1;
				}

				HSSFRow row;
				for (int i = 0; i < resultsList.size(); i++) {
					row = sheet.createRow(i + 1);

					count = 0;
					Field[] fields = resultsList.get(i).getClass().getDeclaredFields();
					String fieldName = null;	//資料物件的某變數名
					for (Map.Entry<String, String> titleEntry : titleMap.entrySet()) {
						for (Field field : fields) {
							field.setAccessible(true);
							fieldName = field.getName();
							if (titleEntry.getKey().equals(fieldName)) {
								Object value = field.get(resultsList.get(i));			//資料物件的某變數值
								row.createCell(count).setCellStyle(cellStyle);
								row.createCell(count).setCellValue(null == value?"null":value.toString());
								count += 1;
								break;
							}
						}
					}
				}
			} else {
				throw new RuntimeException("excel 生成失敗，由於無標頭行。");
			}

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}

		return workbook;
	}

	public Map<String, String> getExcelTitleMap1(){
		LinkedHashMap<String, String> excelTitleMap = new LinkedHashMap<>();// ：key為物件屬性名稱，value值為對應之表頭文字
		excelTitleMap.put("policy_no","policy_no");
		excelTitleMap.put("endorse_no","endorse_no");
		excelTitleMap.put("policy_dateBgn","policy_dbgn");
		excelTitleMap.put("policy_dateEnd","policy_dend");
		excelTitleMap.put("policy_datePrt","policy_dprt");
		excelTitleMap.put("addr_no","addr_no");
		excelTitleMap.put("amt","amt");
		excelTitleMap.put("prem","prem");
		excelTitleMap.put("limit_no","limit_no");
		excelTitleMap.put("treaty_year","treaty_year");
		excelTitleMap.put("treaty_no","treaty_no");
		excelTitleMap.put("share_amt","share_amt");
		excelTitleMap.put("share_prem","share_prem");
		excelTitleMap.put("acct_flag","acct_flag");

		return excelTitleMap;
	}

	public Map<String, String> getExcelTitleMap2(){
		LinkedHashMap<String, String> excelTitleMap = new LinkedHashMap<>();// ：key為物件屬性名稱，value值為對應之表頭文字

		excelTitleMap.put("seq","seq");
		excelTitleMap.put("rptid","rptid");
		excelTitleMap.put("billNo","bill_no");
		excelTitleMap.put("treatyName","treaty_name");
		excelTitleMap.put("treatyMode","treaty_mode");
		excelTitleMap.put("policyMode","policy_mode");
		excelTitleMap.put("treatyRate","treaty_rate");
		excelTitleMap.put("treatyPrem","treaty_prem");
		excelTitleMap.put("treatyMonth","treaty_month");
		excelTitleMap.put("prem","prem");
		excelTitleMap.put("loss","loss");
		excelTitleMap.put("commRate","comm_rate");

		excelTitleMap.put("comm","common");

		excelTitleMap.put("busstaxRate","busstax_rate");
		excelTitleMap.put("busstax","busstax");

		excelTitleMap.put("brokerRate","broker_rate");
		excelTitleMap.put("brokerFee","broker_fee");

		excelTitleMap.put("stampRate","stamt_rate");
		excelTitleMap.put("stampFee","stamt_fee");

		excelTitleMap.put("handlingRate","handling_rate");
		excelTitleMap.put("handlingFee","handling_fee");
		excelTitleMap.put("duetoyou","duetoyou");
		excelTitleMap.put("duetous","duetous");
		excelTitleMap.put("rinComSname","rin_com_sname");
		excelTitleMap.put("cashrefund","cashRefund");

		return excelTitleMap;
	}

	public Map<String, String> getExcelTitleMap3(){
		LinkedHashMap<String, String> excelTitleMap = new LinkedHashMap<>();// ：key為物件屬性名稱，value值為對應之表頭文字

		excelTitleMap.put("rptid","rptid");
		excelTitleMap.put("billNo","bill_no");
		excelTitleMap.put("treatyName","treaty_name");
		excelTitleMap.put("rinComShare","rin_com_share");
		excelTitleMap.put("rinComSname","rin_com_sname");
		excelTitleMap.put("treatyRate","treaty_rate");
		excelTitleMap.put("treatyMode","treaty_mode");
		excelTitleMap.put("treatyMonthDate","treaty_month");//treatyMonthDate替換date型別的treaty_month
		excelTitleMap.put("treatyPrem","treaty_prem");
		excelTitleMap.put("sharePrem","share_prem");
		excelTitleMap.put("commRate","comm_rate");
		excelTitleMap.put("comm","comm");
		excelTitleMap.put("busstaxRate","busstax_rate");
		excelTitleMap.put("busstax","busstax");

		excelTitleMap.put("policyMode","policy_mode");
		excelTitleMap.put("totalLoss","total_loss");
		excelTitleMap.put("loss","loss");

		excelTitleMap.put("cashrefund","cashRefund");

		excelTitleMap.put("rinComId","rin_com_id");
		excelTitleMap.put("treatyYear","treaty_year");
		excelTitleMap.put("treatyNo","treaty_no");
		excelTitleMap.put("isbroker","IsBroker");

		return excelTitleMap;
	}

	public String getMonthStr(String month) {
		switch (month) {
		case "01":
			month = "Jan";
			break;
		case "02":
			month = "Feb";
			break;
		case "03":
			month = "Mar";
			break;
		case "04":
			month = "Apr";
			break;
		case "05":
			month = "May";
			break;
		case "06":
			month = "Jun";
			break;
		case "07":
			month = "Jul";
			break;
		case "08":
			month = "Aug";
			break;
		case "09":
			month = "Sep";
			break;
		case "10":
			month = "Oct";
			break;
		case "11":
			month = "Nov";
			break;
		case "12":
			month = "Dec";
			break;
		default:
			break;
		}
		return month;
	}

	public String getDateNow() {//顯示"Jul 21,2021"
		String dateNow = asiutil.processDateToString(new Date(), "yyyy/MM/dd");
		String [] dateArr = dateNow.split("/");
		String monthStr = dateArr[1];
		switch (monthStr) {
		case "01":
			monthStr = "Jan";
			break;
		case "02":
			monthStr = "Feb";
			break;
		case "03":
			monthStr = "Mar";
			break;
		case "04":
			monthStr = "Apr";
			break;
		case "05":
			monthStr = "May";
			break;
		case "06":
			monthStr = "Jun";
			break;
		case "07":
			monthStr = "Jul";
			break;
		case "08":
			monthStr = "Aug";
			break;
		case "09":
			monthStr = "Sep";
			break;
		case "10":
			monthStr = "Oct";
			break;
		case "11":
			monthStr = "Nov";
			break;
		case "12":
			monthStr = "Dec";
			break;
		default:
			break;
		}
		String dateShow = monthStr +" " + dateArr[2] +", "+ dateArr[0];
		return dateShow;
	}

	public String getPeriod(String periodNo, String treatyYear) {
		StringBuilder sbPeriod = new StringBuilder();
		switch (periodNo) {
		case "1":
			sbPeriod.append("Jan.-Mar.");
			break;

		case "2":
			sbPeriod.append("Apr.-Jun.");
			break;
		case "3":
			sbPeriod.append("Jul.-Sep.");
			break;
		case "4":
			sbPeriod.append("Oct.-Dec.");
			break;
		default:
			break;
		}
		sbPeriod.append(" "+treatyYear);

		return sbPeriod.toString();
	}

	//千分位處理 & 保費為負值加上"("__")"
	public String coverCashValue(Long prem) {
		DecimalFormat df = new DecimalFormat("#,###");
		String premString = df.format(prem);
		if(prem < 0 ) {//保加總負值加上"("__")"

			return "("+ premString +")";
		}else {

			return premString;
		}
	}

}
