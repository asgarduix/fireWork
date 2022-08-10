package com.asi.huanan.controller.api;

import java.util.Arrays;
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

import com.asi.huanan.controller.api.common.BatchController;
import com.asi.huanan.service.BatchqueueService;
import com.asi.huanan.service.FriTreatyService;
import com.asi.huanan.service.customerize.CheckIsNullSpace;
import com.asi.huanan.service.customerize.Rin1206PrintDataService;
import com.asi.huanan.service.customerize.Rin1206ReportService;
import com.asi.huanan.service.dao.mybatis.model.FriTreaty;
import com.asi.huanan.service.repository.Rin120602Repository;
import com.asi.huanan.service.repository.Rin120603Repository;
import com.asi.huanan.service.repository.Rin1206Repository;
import com.asi.huanan.vo.FileExport;
import com.asi.huanan.vo.Rin1206PrintDataVO;
import com.asi.huanan.vo.Rin1206PrintReportVOReq;
import com.asi.huanan.vo.Rin1206QueryTreatyNoVOReq;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.SpringProperty;
import com.asi.mechanism.common.SysEnum;
import com.asi.swissknife.Asiutil;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1206api")
@RestController
@Api(tags = { "Rin1206api" })
public class Rin1206Controller {

	private static Logger log = LogManager.getLogger(Rin1206Controller.class);

	@Autowired
	private FriTreatyService friTreatyService;

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
	private BatchController batchController;

	@Autowired
	private BatchqueueService batchqueueService;

	/**
	 * 查詢合約代號
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢「合約代號」", response = JsonBean.class, tags = {"Rin1206api" }, notes = "用合約年度查詢合約代號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryfritreatybytreatyyear")
	@ResponseBody
	public ResponseEntity<?> queryTreatyNoByTreatyYear(@ApiParam(value = "搜尋「合約代號」條件") @RequestBody Rin1206QueryTreatyNoVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1206Controller.queryfriTreatyByTreatyYear(查詢「合約代號」)");

		List<FriTreaty> results = null;
		JsonBean jsonBean = new JsonBean();
		String treatyYear = parJson.getTreaty_year();//合約年度
		try {

			if(!CheckIsNullSpace.isNullOrBlank(treatyYear)) {
				results = friTreatyService.queryTreatyNoByTreatyYear(treatyYear);
			}

			//回傳結果
			jsonBean.setData(results);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}


	/**
	 * 即時列印「合約帳單/合約明細列印」
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "即時列印「合約帳單/合約明細列印」", response = JsonBean.class, tags = {"Rin1206api" }, notes = "即時列印「合約帳單/合約明細列印」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/printrin1206report")
	@ResponseBody
	public ResponseEntity<?> printRin1206Report(@ApiParam(value = "列印「合約帳單/合約明細列印」條件") @RequestBody Rin1206PrintReportVOReq parJson)
			throws Exception {
		Asiutil util = new Asiutil();
		Gson gson = new Gson();
		JsonBean jsonBean = new JsonBean();
		FileExport fe = new FileExport();
		String message = "";

		try {
			//列印前rin1206,rin120602,rin120603暫存檔table清除
			rin1206Repository.truncateTable();
			rin120602Repository.truncateTable();
			rin120603Repository.truncateTable();

			Rin1206PrintDataVO printDataVO = new Rin1206PrintDataVO();
			//1-取得合約帳單列印資料
			printDataVO = rin1206PrintDataService.generatePrintData(parJson);

			//2-列印Excel
			String excelFileName = rin1206ReportService.generateExcelReport(printDataVO, parJson);
			log.debug("excelFileName = " + excelFileName);
			//3.PDF
			String pdfFileName = rin1206ReportService.generatePdfReport(printDataVO, parJson);
			log.debug("pdfFileName = " + pdfFileName);
			log.debug("立即執行寫入");


			// 立即執行寫入batchqueue(移至2.3完成前保留)
			Date submitTime = new Date();
			parJson.setSubmitTime(util.processDateToString(submitTime, "yyyy/MM/dd HH:mm:ss"));
			String jsonParam = gson.toJson(parJson);

			//excel
			String excelTaskId = batchController.registerBatch("Rin1206P", submitTime, parJson.getAccount(), "", jsonParam);
			if(!CheckIsNullSpace.isNullOrBlank(excelFileName)) {
				String excelPath = SpringProperty.localFileForExcel;
				try {
					batchqueueService.updateProcessStatus(excelTaskId, (byte) 2,new Date(), new Date(), excelPath + excelFileName);
				} catch (Exception e) {
					batchqueueService.updateProcessStatus(excelTaskId, (byte) 4,new Date(), new Date(), "");
					throw e;
				}
			}else {
				message += "Excel列印失敗;";
				batchqueueService.updateProcessStatus(excelTaskId, (byte) 4,new Date(), new Date(), "");
			}

			//pdf
			String pdfTaskId = batchController.registerBatch("Rin1206P", submitTime, parJson.getAccount(), "", jsonParam);
			if (!CheckIsNullSpace.isNullOrBlank(pdfFileName)) {
				String pdfPath = SpringProperty.localFileForPdf;
				try {
					batchqueueService.updateProcessStatus(pdfTaskId, (byte) 2,new Date(), new Date(), pdfPath + pdfFileName);

				} catch (Exception e) {
					batchqueueService.updateProcessStatus(pdfTaskId, (byte) 4,new Date(), new Date(), "");
					throw e;
				}
			}else {
				message += "pdf列印失敗;";
				batchqueueService.updateProcessStatus(pdfTaskId, (byte) 4,new Date(), new Date(), "");
			}

			//回傳結果
			fe.setMessage(message);
			jsonBean.setData(fe);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}


	@ApiOperation(value = "合約帳單/合約明細列印_排程執行", response = JsonBean.class, tags = {
	"Rin1206api" }, notes = "合約帳單/合約明細列印_排程執行")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertscheduleforrin1206report")
	@ResponseBody
	public ResponseEntity<?> insertScheduleForRin1206Report(@ApiParam(value = "合約帳單/合約明細列印_排程執行") @RequestBody Rin1206PrintReportVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1206Controller.insertScheduleForRin1206Report(合約帳單/合約明細列印_排程執行)");
		JsonBean jsonBean = new JsonBean();
		Asiutil util = new Asiutil();
		Gson gson = new Gson();
		try {

			// 排程執行
			Date submitTime = util.processStringToDate("yyyy/MM/dd HH:mm:ss", parJson.getSubmitTime());
			String jsonParam = gson.toJson(parJson);
			//寫入PDF&EXCEL兩筆task入排程
			batchController.registerBatch("Rin1206P", submitTime, parJson.getAccount(), "EXCEL", jsonParam);
			batchController.registerBatch("Rin1206P", submitTime, parJson.getAccount(), "PDF", jsonParam);

			// 回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.status000.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.status999.code);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

}
