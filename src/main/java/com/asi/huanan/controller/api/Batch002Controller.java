package com.asi.huanan.controller.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.service.BatchqueueService;
import com.asi.huanan.service.customerize.CustomerizeService;
import com.asi.huanan.service.dao.mybatis.model.Batchqueue;
import com.asi.huanan.service.dao.mybatis.model.customerize.BatchqueueJoinBatchlist;
import com.asi.huanan.vo.Batch002Vo;
import com.asi.huanan.vo.FileExport;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.SysAccountRoleService;
import com.asi.mechanism.service.dao.mybatis.model.SysAccountRole;
import com.asi.swissknife.Asiutil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("batch002api")
@RestController
@Api(tags = { "Batch002api" })
public class Batch002Controller {

	private static Logger log = LogManager.getLogger(Batch002Controller.class);

	@Autowired
	private CustomerizeService customerizeService;
	@Autowired
	private BatchqueueService batchqueueService;
	@Autowired
	private SysAccountRoleService sysAccountRoleService;

	/**
	 * 批次作業監視器_資料搜尋_初始畫面
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */

	@ApiOperation(value = "批次作業監視器_資料搜尋_初始畫面", response = JsonBean.class, tags = {
			"Batch002api" }, notes = "批次作業監視器_資料搜尋_初始畫面")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryuseaccount")
	@ResponseBody
	public ResponseEntity<?> queryUseAccount(@ApiParam(value = "批次作業監視器_資料搜尋_初始畫面") @RequestBody Batch002Vo parJson)
			throws Exception {

		log.debug(">>> Batch002Controller.queryUseAccount(批次作業監視器_資料搜尋_初始畫面)");

		JsonBean jsonBean = new JsonBean();
		Asiutil util = new Asiutil();

		List<BatchqueueJoinBatchlist> res = new ArrayList<>();

		try {
			// 查詢 sys_account_role ，確認是否為 IT 人員 "ITmanager"
			SysAccountRole model = new SysAccountRole();
			model.setAkaId(parJson.getAccount());
			List<SysAccountRole> sysAccountRoleList = sysAccountRoleService.queryBySysAccountRole(model);
			if(!sysAccountRoleList.isEmpty() && "ITmanager".equals(sysAccountRoleList.get(0).getUserRole())) {
				// IT人員要看到全部的資料(fire_reins)
				res = customerizeService.queryUseAccount("fire_reins");
			}else {
				res = customerizeService.queryUseAccount(parJson.getAccount());
			}

			for (BatchqueueJoinBatchlist obj : res) {

				if (null != obj.getStarttime()) {
					Date starttime = obj.getStarttime();
					String startDateString = util.processDateToString(starttime, "yyyy/MM/dd HH:mm:ss");
					obj.setStartDateString(startDateString);
				}
			}

			for (BatchqueueJoinBatchlist obj : res) {
				if (null != obj.getEndtime()) {
					Date endtime = obj.getEndtime();
					String endDateString = util.processDateToString(endtime, "yyyy/MM/dd HH:mm:ss");
					obj.setEndDateString(endDateString);
				}
			}

			for (BatchqueueJoinBatchlist obj : res) {
				Byte processstatus = obj.getProcessstatus();

				switch (processstatus) {
				case 0:
					obj.setProcessstatusString("尚未執行");
					break;
				case 1:
					obj.setProcessstatusString("執行中");
					break;
				case 2:
					obj.setProcessstatusString("執行完成");
					break;
				case 3:
					obj.setProcessstatusString("使用者終止");
					break;
				default:
					obj.setProcessstatusString("執行失敗");
					break;
				}
			}

			// 回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);

		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 批次作業監視器_強制中止
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "批次作業監視器_強制中止", response = JsonBean.class, tags = { "Batch002api" }, notes = "批次作業監視器_強制中止")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatebatchqueuebyterminate")
	@ResponseBody
	public ResponseEntity<?> updateBatchqueueByTerminate(
			@ApiParam(value = "批次作業監視器_強制中止") @RequestBody Batch002Vo parJson) throws Exception {
		log.debug(">>> Batch002Controller.updateBatchqueueByTerminate(批次作業監視器_強制中止)");

		JsonBean jsonBean = new JsonBean();

		try {

			batchqueueService.updateBatchqueueByTerminate(parJson.getTaskid(), new Date(), (byte) 3);

			// 回傳結果
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 批次作業監視器_資料搜尋_報表選項
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "批次作業監視器_資料搜尋_報表選項", response = JsonBean.class, tags = {
			"Batch002api" }, notes = "批次作業監視器_資料搜尋_報表選項")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryuseaccountandbatchid")
	@ResponseBody
	public ResponseEntity<?> queryUseAccountAndBatchid(
			@ApiParam(value = "批次作業監視器_資料搜尋_報表選項") @RequestBody Batch002Vo parJson) throws Exception {

		log.debug(">>> Batch002Controller.queryUseAccountAndBatchid(批次作業監視器_資料搜尋_報表選項)");

		JsonBean jsonBean = new JsonBean();
		Asiutil util = new Asiutil();

		List<BatchqueueJoinBatchlist> res = new ArrayList<>();

		try {

			// 判斷前端報表選項取到的下拉value值
			switch (parJson.getReportType()) {
			case "1": //合約帳單(pdf)
				parJson.setBatchid("Rin1206P");
				break;
			case "2": //臨分到期(xls)
				parJson.setBatchid("Rin1302P");
				break;
			case "3": //合約帳單列印明細(xls)
				parJson.setBatchid("Rin1206P");
				break;
			case "4": //臨分帳單/通知單(pdf)
				parJson.setBatchid("Rin1303P");
				break;
			default:
				parJson.setBatchid("");
				break;
			}
			
			if (parJson.getReportType().equals("1") || parJson.getReportType().equals("4")) {
				res = customerizeService.queryUseAccountAndBatchid2(parJson.getAccount(), parJson.getBatchid());
			} else {
				res = customerizeService.queryUseAccountAndBatchid(parJson.getAccount(), parJson.getBatchid());
			}
			

			// Date型別轉字串
			for (BatchqueueJoinBatchlist obj : res) {

				if (null != obj.getStarttime()) {
					Date starttime = obj.getStarttime();
					String startDateString = util.processDateToString(starttime, "yyyy/MM/dd HH:mm:ss");
					obj.setStartDateString(startDateString);
				}
			}

			// Date型別轉字串
			for (BatchqueueJoinBatchlist obj : res) {
				if (null != obj.getEndtime()) {
					Date endtime = obj.getEndtime();
					String endDateString = util.processDateToString(endtime, "yyyy/MM/dd HH:mm:ss");
					obj.setEndDateString(endDateString);
				}
			}

			for (BatchqueueJoinBatchlist obj : res) {
				Byte processstatus = obj.getProcessstatus();

				switch (processstatus) {
				case 0:
					obj.setProcessstatusString("尚未執行");
					break;
				case 1:
					obj.setProcessstatusString("執行中");
					break;
				case 2:
					obj.setProcessstatusString("執行完成");
					break;
				case 3:
					obj.setProcessstatusString("使用者終止");
					break;
				default:
					obj.setProcessstatusString("執行失敗");
					break;
				}
			}

			// 回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);

		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);

	}

	/**
	 * 批次作業監視器_開啟報表
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "批次作業監視器_開啟報表", response = JsonBean.class, tags = { "Batch002api" }, notes = "批次作業監視器_開啟報表")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querybatchreprotaccesspath")
	@ResponseBody
	public ResponseEntity<?> queryBatchReprotAccessPath(
			@ApiParam(value = "批次作業監視器_開啟報表") @RequestBody Batch002Vo parJson) throws Exception {

		log.debug(">>> Batch002Controller.queryBatchReprotAccessPath(批次作業監視器_開啟報表)");

		JsonBean jsonBean = new JsonBean();
		List<Batchqueue> res = new ArrayList<>();
		FileExport fe = new FileExport();
		String fileBase64Encode = null;

		try {
			res = batchqueueService.queryBatchReprotAccessPath(parJson.getTaskid());

			for (Batchqueue obj : res) {

				if (null != obj.getBatchreprotaccesspath()) {
					String batchreprotaccesspath = obj.getBatchreprotaccesspath();
					String[] fileNameArr = batchreprotaccesspath.split("/");
					String fileName = fileNameArr[fileNameArr.length-1];
					fe.setFileName(fileName);//下載檔的檔名

					//用路徑讀取報表檔案
					File file = new File(batchreprotaccesspath);
					try(InputStream in = new FileInputStream(file)) {						
						byte[] excelArr = in.readAllBytes();
						fileBase64Encode = Base64.getEncoder().encodeToString(excelArr);
						fe.setFileBase64Encode(fileBase64Encode);
					}catch(Exception e){
						throw e;
					}
				}
			}

			// 回傳結果
			jsonBean.setData(fe);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
		} 
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

}
