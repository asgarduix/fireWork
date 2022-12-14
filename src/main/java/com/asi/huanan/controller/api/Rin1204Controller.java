package com.asi.huanan.controller.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
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
import com.asi.huanan.controller.api.common.ExcelController;
import com.asi.huanan.service.BatchqueueService;
import com.asi.huanan.service.Rin1204Service;
import com.asi.huanan.service.customerize.CustomerizeService;
import com.asi.huanan.vo.Rin1204VOReq;
import com.asi.huanan.vo.Rin1204VOResp;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.swissknife.Asiutil;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1204api")
@RestController
@Api(tags = { "Rin1204api" })
public class Rin1204Controller {

	private static Logger log = LogManager.getLogger(Rin1204Controller.class);

	@Autowired
	private CustomerizeService customerizeService;
	@Autowired
	private BatchqueueService batchqueueService;
	@Autowired
	private BatchController batchController;
	@Autowired
	private Rin1204Service rin1204Service;
	@Autowired
	private ExcelController excelController;

	/**
	 * ??????????????????????????????
	 * @param rin1204VOReq
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "??????????????????????????????", response = JsonBean.class, tags = { "Rin1204api" }, notes = "??????????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/doAutoReinsurance")
	@ResponseBody
	public ResponseEntity<?> doAutoReinsurance(@ApiParam(value = "????????????????????????????????????") @RequestBody Rin1204VOReq rin1204VOReq)
			throws Exception {
		log.debug(">>> Rin1204Controller.doAutoReinsurance(??????????????????????????????)");
		JsonBean jsonBean = new JsonBean();
		Asiutil util = new Asiutil();
		Gson gson = new Gson();
		String msg = "";
		try {
			// ?????? ???????????? or ????????????
			if("1".equals(rin1204VOReq.getBootStatus())) {
				// ????????????
				Date submitTime = new Date();
				rin1204VOReq.setSubmitTime(util.processDateToString(submitTime, "yyyy/MM/dd HH:mm:ss"));
				String jsonParam = gson.toJson(rin1204VOReq);
				String taskId = batchController.registerBatch("Rin1204", submitTime, rin1204VOReq.getAccount(), "", jsonParam);
				try {
					batchqueueService.updateProcessStatus(taskId, (byte) 1, new Date());
					msg = rin1204Service.autoReinsurance(rin1204VOReq, taskId);
					batchqueueService.updateProcessStatus2(taskId, (byte) 2, new Date(), "");
				} catch (Exception e) {
					batchqueueService.updateProcessStatus2(taskId, (byte) 4, new Date(), "");
					throw e;
				}
			}else {
				// ????????????
				Date submitTime = util.processStringToDate("yyyy/MM/dd HH:mm:ss", rin1204VOReq.getSubmitTime());
				String jsonParam = gson.toJson(rin1204VOReq);
				batchController.registerBatch("Rin1204", submitTime, rin1204VOReq.getAccount(), "", jsonParam);				
			}
			if("3".equals(msg)) {
				msg = "???????????????";
			}
			// ????????????
			jsonBean.setData(msg);
			jsonBean.setStatus(SysEnum.status000.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.status999.code);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * ???????????????????????????
	 * @param rin1204VOReq
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "???????????????????????????", response = JsonBean.class, tags = { "Rin1204api" }, notes = "???????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryUnProcPolicy")
	@ResponseBody
	public ResponseEntity<?> queryUnProcPolicy(@ApiParam(value = "????????????????????????") @RequestBody Rin1204VOReq rin1204VOReq)
			throws Exception {
		log.debug(">>> Rin1204Controller.queryUnProcPolicy(???????????????????????????)");
		JsonBean jsonBean = new JsonBean();
		List<Rin1204VOResp> resultList = new ArrayList<>();
		try {
			// ????????????
			String ucRocDbgn = rin1204VOReq.getUcRocDbgn();
			// ????????????
			String ucRocDend = rin1204VOReq.getUcRocDend();
			// ????????????
			String riskNo = rin1204VOReq.getRiskNo();

			if (!"".equals(riskNo) && !"99999999999".equals(riskNo)) {
				resultList = customerizeService.queryUnProcPolicy1(ucRocDbgn, ucRocDend, riskNo);
			} else {
				resultList = customerizeService.queryUnProcPolicy2(ucRocDbgn, ucRocDend);
			}
			// ????????????
			jsonBean.setData(resultList);
			jsonBean.setStatus(SysEnum.status000.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.status999.code);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * ???????????????????????????(????????????)
	 * @param rin1204VOReq
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "???????????????????????????(????????????)", response = JsonBean.class, tags = { "Rin1204api" }, notes = "???????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/exportData")
	@ResponseBody
	public void exportData(@RequestBody Rin1204VOReq rin1204VOReq, HttpServletResponse response)
			throws Exception {
		log.debug(">>> Rin1204Controller.exportData(????????????)");
		JsonBean jsonBean = new JsonBean();
		List<Rin1204VOResp> resultList = new ArrayList<>();
		HSSFWorkbook workbook = new HSSFWorkbook();
		final String UNPPO = "?????????????????????";
		try {
			// ????????????
			String ucRocDbgn = rin1204VOReq.getUcRocDbgn();
			// ????????????
			String ucRocDend = rin1204VOReq.getUcRocDend();
			// ????????????
			String riskNo = rin1204VOReq.getRiskNo();
			if (!"".equals(riskNo) && !"99999999999".equals(riskNo)) {
				resultList = customerizeService.queryUnProcPolicy1(ucRocDbgn, ucRocDend, riskNo);
			} else {
				resultList = customerizeService.queryUnProcPolicy2(ucRocDbgn, ucRocDend);
			}
			if(resultList.isEmpty()) {
				workbook.createSheet("????????????");
				excelController.createExcel(workbook, response, UNPPO);
			}else {
				// ????????????sheet
				HSSFSheet sheet = workbook.createSheet(UNPPO);
				// ?????????????????????
				CellStyle cellStyle = workbook.createCellStyle();
				// ????????????????????????
				String[] headTitles = { "????????????", "????????????", "????????????" };

				HSSFRow headerRow = sheet.createRow(0);

				for (int i = 0; i < headTitles.length; i++) {
					headerRow.createCell(i).setCellStyle(cellStyle);
					headerRow.createCell(i).setCellValue(headTitles[i]);
				}
				
				// ????????????
				HSSFRow row;
				for (int i = 0; i < resultList.size(); i++) {
					row = sheet.createRow(i + 1);
					row.createCell(0).setCellStyle(cellStyle);
					row.createCell(0).setCellValue(resultList.get(i).getAreaCode());
					row.createCell(1).setCellStyle(cellStyle);
					row.createCell(1).setCellValue(resultList.get(i).getPolicyNo());
					row.createCell(2).setCellStyle(cellStyle);
					row.createCell(2).setCellValue(resultList.get(i).getEndorseNo());
				}
				excelController.createExcel(workbook, response, UNPPO);
			}
			
			workbook.close();
		} catch (Exception e) {
			workbook.close();
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.status999.code);
		}

	}
	
	/**
	 * ???????????????????????????
	 * @param rin1204VOReq
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "???????????????????????????", response = JsonBean.class, tags = { "Rin1204api" }, notes = "???????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/checkIsClose")
	@ResponseBody
	public ResponseEntity<?> checkIsClose(@ApiParam(value = "????????????????????????") @RequestBody Rin1204VOReq rin1204VOReq)
			throws Exception {
		log.debug(">>> Rin1204Controller.checkIsClose(???????????????????????????)");
		JsonBean jsonBean = new JsonBean();
		List<Rin1204VOResp> resultList = new ArrayList<>();
		Boolean isClose = false;
		try {
			// ????????????
			String ucRocDbgn = rin1204VOReq.getUcRocDbgn();
			// ????????????
			String ucRocDend = rin1204VOReq.getUcRocDend();
			// ????????????
			String riskNo = rin1204VOReq.getRiskNo();

			resultList = customerizeService.checkIsClose(ucRocDbgn, ucRocDend, riskNo);
			if(resultList.isEmpty()) {
				isClose = false;
			}else {
				isClose = true;
			}
			// ????????????
			jsonBean.setData(isClose);
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
