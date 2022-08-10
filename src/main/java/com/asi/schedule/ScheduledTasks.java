package com.asi.schedule;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asi.huanan.service.BatchparameterService;
import com.asi.huanan.service.BatchqueueService;
import com.asi.huanan.service.Rin1204Service;
import com.asi.huanan.service.customerize.CheckIsNullSpace;
import com.asi.huanan.service.customerize.CustomerizeService;
import com.asi.huanan.service.customerize.Rin1206PrintDataService;
import com.asi.huanan.service.customerize.Rin1206ReportService;
import com.asi.huanan.service.customerize.Rin1302Service;
import com.asi.huanan.service.dao.mybatis.model.Batchparameter;
import com.asi.huanan.service.dao.mybatis.model.Batchqueue;
import com.asi.huanan.service.repository.Rin120602Repository;
import com.asi.huanan.service.repository.Rin120603Repository;
import com.asi.huanan.service.repository.Rin1206Repository;
import com.asi.huanan.vo.Rin1204VOReq;
import com.asi.huanan.vo.Rin1206PrintDataVO;
import com.asi.huanan.vo.Rin1206PrintReportVOReq;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.SpringProperty;
import com.asi.mechanism.common.SysEnum;

@Component
//@EnableAsync
@RequestMapping("scheduledtasks")
public class ScheduledTasks {
	private static Logger log = LogManager.getLogger(ScheduledTasks.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//    private final String setRunTime = SpringProperty.SetRunTime ;
	@Autowired
	private CustomerizeService customerizeService;
	@Autowired
	private BatchqueueService batchqueueService;
	@Autowired
	private BatchparameterService batchparameterService;
	@Autowired
	private Rin1204Service rin1204Service;
	@Autowired
	private Rin1206PrintDataService rin1206PrintDataService;
	@Autowired
	private Rin1206ReportService rin1206ReportService;
	@Autowired
	private Rin1206Repository rin1206Repository;
	@Autowired
	private Rin120602Repository rin120602Repository;
	@Autowired
	private Rin120603Repository rin120603Repository;

	@Autowired
	private Rin1302Service rin1302Service;

	/**
	 *
	 * @throws Exception
	 */
//	@Scheduled(cron = "0/15 0/1 17 * * ?")
	public void scheduleJob() throws Exception {
		StackTraceElement threadObj = Thread.currentThread().getStackTrace()[1];
		log.info("===" + threadObj.getClassName() + "." + threadObj.getMethodName() + " process on time" + "===");

		try {
			// ...
			log.debug("do something");
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
		}
	}

	/**
	 * 範例
	 *
	 * @throws Exception
	 */
//	@Async
//	@Scheduled(fixedDelay = 1000)
//	@Scheduled(fixedRate = 1000)
	@Scheduled(cron = "0/15 0/1 17 * * ?")
	public void scheduleJobSample() throws Exception {
		StackTraceElement threadObj = Thread.currentThread().getStackTrace()[1];
		log.info("===" + threadObj.getClassName() + "." + threadObj.getMethodName() + " process on time" + "===");

		try {
			// ...
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
		}
	}

	/**
	 * Rin1203_同險設定排程
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "${environments.schedule.func.rin1203}")
	@ResponseBody
	public void schedulInsertToFriTemparea() throws Exception {
		StackTraceElement threadObj = Thread.currentThread().getStackTrace()[1];
		log.info("===" + threadObj.getClassName() + "." + threadObj.getMethodName() + " process on time" + "===");
		log.info("R1203_同險設定排程開始:每日執行一次,依使用者設定參數");
		log.info("Current Thread : {}", Thread.currentThread().getName());
		log.info("Fixed Rate Task : The time is now {}", dateFormat.format(new Date()));
		JsonBean jsonBean = new JsonBean();

		// 每日先刪除舊有的資料
		try {
			customerizeService.deleteAllFriTemparea();

			// 系統時間、使用者設定的N年前時間
			Integer setYear = SpringProperty.SetYear;
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.YEAR, -(setYear));
			Date time = c.getTime();
			String policyDprtS = dateFormat.format(time);
			String policyDprtE = dateFormat.format(new Date());

			// 新增當天系統日期前N年資料
			customerizeService.insertToFriTemparea(policyDprtS, policyDprtE);

			jsonBean.setData(setYear);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("Rin1203_同險設定排程執行結束!");
			log.info("Rin1203_同險設定排程執行結束");
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("Rin1203_同險設定排程執行失敗!");
		}

	}

	/**
	 * Rin1302 臨分到期通知列印_排程執行
	 *
	 * @throws Exception
	 */
	@Scheduled(fixedRateString  = "${environments.schedule.func.rin1302}")
	public void rin1302ExecReportWithFixedRate() throws Exception {
		log.info("Rin1302 臨分到期通知列印_排程執行 每10分鐘執行ㄧ次......");
		log.info("Current Thread : {}", Thread.currentThread().getName());
		log.info("Fixed Rate Task : The time is now {}", dateFormat.format(new Date()));

		try {
			rin1302Service.rin1302PrintExcel();
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));

		}

	}

	/**
	 * Rin1204 自動分保排程器 
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedRateString  = "${environments.schedule.func.rin1204}")
	public void rin1204BatchqueueScheduler() throws Exception {
		log.info("Rin1204 自動分保排程，每10分鐘執行ㄧ次......");
		log.info("Current Thread : {}", Thread.currentThread().getName());
		log.info("Fixed Rate Task : The time is now {}", dateFormat.format(new Date()));
		String taskId = "";
		String batchreprotaccesspath = "";
		try {
			Batchqueue batchqueue = new Batchqueue();
			batchqueue.setBatchid("Rin1204");
			batchqueue.setProcessstatus((byte) 0);
			List<Batchqueue> batchqueueList = batchqueueService.queryByBatchqueue(batchqueue);
			for (int i = 0; i < batchqueueList.size(); i++) {
				Date submitTime = batchqueueList.get(i).getSubmittime();
				Date nowTime = new Date();

				if (submitTime.before(nowTime)) {
					taskId = batchqueueList.get(i).getTaskid();
					// 修改batchqueue processstatus狀態
					// status 0:尚未執行， 1:執行中 ，2:執行完成 ，3:使用者終止，4:執行失敗
					batchqueueService.updateProcessStatus(taskId, (byte) 1, new Date());

					// 以 taskId 查詢參數
					Batchparameter batchparameter = new Batchparameter();
					batchparameter.setTaskid(taskId);
					List<Batchparameter> batchparameterList = batchparameterService
							.queryByBatchparameter(batchparameter);

					batchreprotaccesspath = batchqueueList.get(i).getBatchreprotaccesspath();
					Rin1204VOReq rin1204VOReq = new Rin1204VOReq();
					for (int j = 0; j < batchparameterList.size(); j++) {
						if ("treatyYear".equals(batchparameterList.get(j).getDataname())) {
							rin1204VOReq.setTreatyYear(batchparameterList.get(j).getDatavalue());
						}
						if ("ucRocDbgn".equals(batchparameterList.get(j).getDataname())) {
							rin1204VOReq.setUcRocDbgn(batchparameterList.get(j).getDatavalue());
						}
						if ("ucRocDend".equals(batchparameterList.get(j).getDataname())) {
							rin1204VOReq.setUcRocDend(batchparameterList.get(j).getDatavalue());
						}
						if ("riskNo".equals(batchparameterList.get(j).getDataname())) {
							rin1204VOReq.setRiskNo(batchparameterList.get(j).getDatavalue());
						}
					}
					// 呼叫自動分保程式
					String msg = rin1204Service.autoReinsurance(rin1204VOReq, taskId);
					if(!"3".equals(msg)) {
						// 2:執行完成
						batchqueueService.updateProcessStatus2(taskId, (byte) 2, new Date(), batchreprotaccesspath);
					}
				}
			}
		} catch (Exception e) {
			batchqueueService.updateProcessStatus2(taskId, (byte) 4, new Date(), batchreprotaccesspath);
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
		}

	}

	/**
	 * Rin1206 合約帳單/合約明細列印排程
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedRateString  = "${environments.schedule.func.rin1206}")
	public void rin1206BatchqueueScheduler() throws Exception {
		log.info("Rin1206 合約帳單/合約明細列印排程，每10分鐘執行ㄧ次......");
		log.info("Current Thread : {}", Thread.currentThread().getName());
		log.info("Fixed Rate Task : The time is now {}", dateFormat.format(new Date()));
		String taskId = "";
		String batchreprotaccesspath = "";
		try {
			Batchqueue batchqueue = new Batchqueue();
			batchqueue.setBatchid("Rin1206P");
			batchqueue.setProcessstatus((byte) 0);
			List<Batchqueue> batchqueueList = batchqueueService.queryByBatchqueue(batchqueue);
			for (int i = 0; i < batchqueueList.size(); i++) {
				Date submitTime = batchqueueList.get(i).getSubmittime();
				Date nowTime = new Date();

				if (submitTime.before(nowTime)) {
					taskId = batchqueueList.get(i).getTaskid();

					// 修改batchqueue processstatus狀態
					// status 0:尚未執行， 1:執行中 ，2:執行完成 ，3:使用者終止，4:執行失敗
					batchqueueService.updateProcessStatus(taskId, (byte) 1, new Date());

					// 以 taskId 查詢參數
					Batchparameter batchparameter = new Batchparameter();
					batchparameter.setTaskid(taskId);
					List<Batchparameter> batchparameterList = batchparameterService
							.queryByBatchparameter(batchparameter);

					batchreprotaccesspath = batchqueueList.get(i).getBatchreprotaccesspath();

					Rin1206PrintReportVOReq rin1206VOReq = new Rin1206PrintReportVOReq();
					for (int j = 0; j < batchparameterList.size(); j++) {
						if ("txtTreatyYear".equals(batchparameterList.get(j).getDataname())) {
							rin1206VOReq.setTxtTreatyYear(batchparameterList.get(j).getDatavalue());
						}
						if ("treatyNo".equals(batchparameterList.get(j).getDataname())) {
							rin1206VOReq.setTreatyNo(batchparameterList.get(j).getDatavalue());
						}
						if ("monthPeriod".equals(batchparameterList.get(j).getDataname())) {
							rin1206VOReq.setMonthPeriod(batchparameterList.get(j).getDatavalue());
						}
						if ("rdoTKind1".equals(batchparameterList.get(j).getDataname())) {
							rin1206VOReq.setRdoTKind1(batchparameterList.get(j).getDatavalue());
						}
						if ("periodNo".equals(batchparameterList.get(j).getDataname())) {
							rin1206VOReq.setPeriodNo(batchparameterList.get(j).getDatavalue());
						}
					}

					// 列印前rin1206,rin120602,rin120603暫存檔table清除
					rin1206Repository.truncateTable();
					rin120602Repository.truncateTable();
					rin120603Repository.truncateTable();
					// 呼叫列印程式

					Rin1206PrintDataVO printDataVO = new Rin1206PrintDataVO();
					printDataVO = rin1206PrintDataService.generatePrintData(rin1206VOReq);

					// 列印Excel
					if ("EXCEL".equals(batchreprotaccesspath)) {
						String excelFileName = rin1206ReportService.generateExcelReport(printDataVO, rin1206VOReq);
						if (!CheckIsNullSpace.isNullOrBlank(excelFileName)) {
							batchreprotaccesspath = SpringProperty.localFileForExcel + excelFileName;

							// 執行完成
							batchqueueService.updateProcessStatus2(taskId, (byte) 2, new Date(), batchreprotaccesspath);
						} else {
							batchqueueService.updateProcessStatus2(taskId, (byte) 4, new Date(), batchreprotaccesspath);
						}
					}

					// 列印PDF
					if ("PDF".equals(batchreprotaccesspath)) {
						String pdfFileName = rin1206ReportService.generatePdfReport(printDataVO, rin1206VOReq);
						if (!CheckIsNullSpace.isNullOrBlank(pdfFileName)) {
							batchreprotaccesspath = SpringProperty.localFileForPdf + pdfFileName;

							// 執行完成
							batchqueueService.updateProcessStatus2(taskId, (byte) 2, new Date(), batchreprotaccesspath);
						} else {
							batchqueueService.updateProcessStatus2(taskId, (byte) 4, new Date(), batchreprotaccesspath);
						}
					}
				}
			}
		} catch (Exception e) {
			batchqueueService.updateProcessStatus2(taskId, (byte) 4, new Date(), batchreprotaccesspath);
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
		}
	}
}
