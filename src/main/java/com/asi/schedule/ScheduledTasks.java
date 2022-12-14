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
	 * ??????
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
	 * Rin1203_??????????????????
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "${environments.schedule.func.rin1203}")
	@ResponseBody
	public void schedulInsertToFriTemparea() throws Exception {
		StackTraceElement threadObj = Thread.currentThread().getStackTrace()[1];
		log.info("===" + threadObj.getClassName() + "." + threadObj.getMethodName() + " process on time" + "===");
		log.info("R1203_????????????????????????:??????????????????,????????????????????????");
		log.info("Current Thread : {}", Thread.currentThread().getName());
		log.info("Fixed Rate Task : The time is now {}", dateFormat.format(new Date()));
		JsonBean jsonBean = new JsonBean();

		// ??????????????????????????????
		try {
			customerizeService.deleteAllFriTemparea();

			// ?????????????????????????????????N????????????
			Integer setYear = SpringProperty.SetYear;
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.YEAR, -(setYear));
			Date time = c.getTime();
			String policyDprtS = dateFormat.format(time);
			String policyDprtE = dateFormat.format(new Date());

			// ???????????????????????????N?????????
			customerizeService.insertToFriTemparea(policyDprtS, policyDprtE);

			jsonBean.setData(setYear);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("Rin1203_??????????????????????????????!");
			log.info("Rin1203_??????????????????????????????");
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("Rin1203_??????????????????????????????!");
		}

	}

	/**
	 * Rin1302 ????????????????????????_????????????
	 *
	 * @throws Exception
	 */
	@Scheduled(fixedRateString  = "${environments.schedule.func.rin1302}")
	public void rin1302ExecReportWithFixedRate() throws Exception {
		log.info("Rin1302 ????????????????????????_???????????? ???10??????????????????......");
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
	 * Rin1204 ????????????????????? 
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedRateString  = "${environments.schedule.func.rin1204}")
	public void rin1204BatchqueueScheduler() throws Exception {
		log.info("Rin1204 ????????????????????????10??????????????????......");
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
					// ??????batchqueue processstatus??????
					// status 0:??????????????? 1:????????? ???2:???????????? ???3:??????????????????4:????????????
					batchqueueService.updateProcessStatus(taskId, (byte) 1, new Date());

					// ??? taskId ????????????
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
					// ????????????????????????
					String msg = rin1204Service.autoReinsurance(rin1204VOReq, taskId);
					if(!"3".equals(msg)) {
						// 2:????????????
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
	 * Rin1206 ????????????/????????????????????????
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedRateString  = "${environments.schedule.func.rin1206}")
	public void rin1206BatchqueueScheduler() throws Exception {
		log.info("Rin1206 ????????????/??????????????????????????????10??????????????????......");
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

					// ??????batchqueue processstatus??????
					// status 0:??????????????? 1:????????? ???2:???????????? ???3:??????????????????4:????????????
					batchqueueService.updateProcessStatus(taskId, (byte) 1, new Date());

					// ??? taskId ????????????
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

					// ?????????rin1206,rin120602,rin120603?????????table??????
					rin1206Repository.truncateTable();
					rin120602Repository.truncateTable();
					rin120603Repository.truncateTable();
					// ??????????????????

					Rin1206PrintDataVO printDataVO = new Rin1206PrintDataVO();
					printDataVO = rin1206PrintDataService.generatePrintData(rin1206VOReq);

					// ??????Excel
					if ("EXCEL".equals(batchreprotaccesspath)) {
						String excelFileName = rin1206ReportService.generateExcelReport(printDataVO, rin1206VOReq);
						if (!CheckIsNullSpace.isNullOrBlank(excelFileName)) {
							batchreprotaccesspath = SpringProperty.localFileForExcel + excelFileName;

							// ????????????
							batchqueueService.updateProcessStatus2(taskId, (byte) 2, new Date(), batchreprotaccesspath);
						} else {
							batchqueueService.updateProcessStatus2(taskId, (byte) 4, new Date(), batchreprotaccesspath);
						}
					}

					// ??????PDF
					if ("PDF".equals(batchreprotaccesspath)) {
						String pdfFileName = rin1206ReportService.generatePdfReport(printDataVO, rin1206VOReq);
						if (!CheckIsNullSpace.isNullOrBlank(pdfFileName)) {
							batchreprotaccesspath = SpringProperty.localFileForPdf + pdfFileName;

							// ????????????
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
