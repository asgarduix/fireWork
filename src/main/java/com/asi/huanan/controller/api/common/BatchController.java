package com.asi.huanan.controller.api.common;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.service.BatchparameterService;
import com.asi.huanan.service.BatchqueueService;
import com.asi.huanan.service.dao.mybatis.model.Batchparameter;
import com.asi.huanan.service.dao.mybatis.model.Batchqueue;
import com.asi.mechanism.SpringProperty;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ibm.icu.util.Calendar;

@RestController
public class BatchController {
	private static Logger log = LogManager.getLogger(BatchController.class);

	@Autowired
	private BatchqueueService batchqueueService;
	
	@Autowired
	private BatchparameterService batchparameterService;
	
	/**
	 * 註冊排程
	 * @param batchID
	 * @param submitTime
	 * @param account
	 * @param batchReprotAccessPath
	 * @param jsonParam
	 */
	public String registerBatch(String batchID, Date submitTime, String account, String batchReprotAccessPath, String jsonParam) {
		Gson gson = new Gson();
		String taskId = "";
		try {
			// 產生taskId
			taskId = batchqueueService.genTaskID();
			
			// insert batchqueue
			Batchqueue batchqueue = new Batchqueue();
			batchqueue.setTaskid(taskId);
			batchqueue.setBatchid(batchID);
			batchqueue.setSubmittime(submitTime);
			batchqueue.setAccount(account);
			batchqueue.setBatchreprotaccesspath(batchReprotAccessPath);
			// 0:尚未執行， 1:執行中 ，2:執行完成 ，3:使用者終止，4:執行失敗
			batchqueue.setProcessstatus((byte) 0);
			batchqueueService.insert(batchqueue);
			
			// insert batchparameter
			// 將json參數轉成map存入DB
			Map<String, String> map = new HashMap<String, String>();
			map = gson.fromJson(jsonParam, new TypeToken<HashMap<String, String>>() {
			}.getType());
			for (Entry<String, String> entry : map.entrySet()) {
				String paramName = entry.getKey();
				String paramValue = entry.getValue();
//				if(StringUtils.isBlank(paramValue)) {
//					// 若value值為空，就不存此參數
//					continue;
//				}
				Batchparameter batchparameter = new Batchparameter();
				batchparameter.setTaskid(taskId);
				batchparameter.setBatchid(batchID);
				batchparameter.setDataname(paramName);
				batchparameter.setDatavalue(paramValue);
				batchparameterService.insert(batchparameter);
			}
			
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}
		return taskId;
	}

//	public void instantBatch(String batchID, Date submitTime, String account, String batchReprotAccessPath, String jsonParam) {
//		try {
//			// 產生taskId
//			String taskId = batchqueueService.genTaskID();
//			// insert batchqueue
//			Batchqueue batchqueue = new Batchqueue();
//			batchqueue.setBatchid(batchID);
//			batchqueue.setSubmittime(submitTime);
//			batchqueue.setAccount(account);
//			batchqueue.setBatchreprotaccesspath(batchReprotAccessPath);
//			
//			// TODO 寫完DB後直接呼叫程式
//		} catch (Exception e) {
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
//		}
//	}
	
	
	
	
	
	/**
	 * 立即列印PDF
	 * @param batchID(功能代號)
	 * @param account(使用者帳號)
	 * @param pdfName(檔案名稱+副檔名)
	 * @param processStatus(執行狀態 2:執行完成、4:執行失敗)
	 */
	public String printNow(String batchID, String account, String pdfName, byte processStatus) {

		String taskId = "";
		try {
			// 產生taskId
			taskId = batchqueueService.genTaskID();
			
			Date nowDate = Calendar.getInstance().getTime();		//系統時間
			
			// insert batchqueue
			Batchqueue batchqueue = new Batchqueue();
			batchqueue.setTaskid(taskId);
			batchqueue.setBatchid(batchID);
			batchqueue.setSubmittime(nowDate);
			batchqueue.setStarttime(nowDate);
			batchqueue.setEndtime(nowDate);
			batchqueue.setProcessstatus(processStatus);			
			batchqueue.setAccount(account);			
			batchqueue.setBatchreprotaccesspath(SpringProperty.localFileForPdf+pdfName);
			batchqueueService.insert(batchqueue);
			
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}
		return taskId;
	}
}
