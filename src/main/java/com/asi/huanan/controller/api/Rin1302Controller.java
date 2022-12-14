package com.asi.huanan.controller.api;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.controller.api.common.ExcelController;
import com.asi.huanan.service.BatchparameterService;
import com.asi.huanan.service.BatchqueueService;
import com.asi.huanan.service.customerize.CustomerizeService;
import com.asi.huanan.service.customerize.Rin1302Service;
import com.asi.huanan.service.dao.mybatis.mapper.BatchparameterMapper;
import com.asi.huanan.service.dao.mybatis.mapper.BatchqueueMapper;
import com.asi.huanan.service.dao.mybatis.model.Batchparameter;
import com.asi.huanan.service.dao.mybatis.model.Batchqueue;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1302Table;
import com.asi.huanan.vo.FileExport;
import com.asi.huanan.vo.Rin1302Vo;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.SpringProperty;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.swissknife.Asiutil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1302api")
@RestController
@Api(value = "Employee Management System", tags = { "Rin1302api" })
public class Rin1302Controller {

	private static Logger log = LogManager.getLogger(Rin1302Controller.class);

	@Autowired
	private CustomerizeService customerizeService;
	@Autowired
	private ExcelController excelController;
	@Autowired
	private BatchqueueService batchqueueService;
	@Autowired
	private BatchparameterService batchparameterService;
	@Autowired
	private MyBatisConnector mybatis;

	@Autowired
	private Rin1302Service rin1302Service;
	
	private static final DateTimeFormatter fileNameDateFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	/**
	 * ????????????_??????
	 * 
	 * @param usePropId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "????????????_??????", response = JsonBean.class, tags = { "Rin1302api" }, notes = "????????????_??????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/exportexcel")
	@ResponseBody
	public JsonBean exportExcel(@ApiParam(value = "??????????????????????????????_????????????") @RequestBody Rin1302Vo parJson) throws Exception {

		log.debug(">>> Rin1302Controller.exportExcel(????????????_??????)");
		log.debug(">>> (????????????_??????) usePropId = " + parJson.getTreaty_dend());

		JsonBean jsonBean = new JsonBean();

		String[] bgnDateArr = parJson.getTreaty_dend().split("/");
		String uctreaty_dend_Bgn_CE = (Integer.parseInt(bgnDateArr[0]) - 1911) + "/" + bgnDateArr[1] + "/" + "01";
		String uctreaty_dend_End_CE = (Integer.parseInt(bgnDateArr[0]) - 1911) + "/" + bgnDateArr[1] + "/" + "01";

		List<Rin1302Table> rin1302TableList = customerizeService.getRin1302MainData(firstDay(uctreaty_dend_Bgn_CE),
				lastDay(uctreaty_dend_End_CE));

		String taskid = null;

		String parentDirPath = SpringProperty.localFileForExcel;
		File parent = new File(parentDirPath);
		if (!parent.exists()) {
			parent.mkdirs();
		}

		// excel?????????
		String fileName = "Rin1302_" + LocalDateTime.now().format(fileNameDateFormatter) + ".xls";
		String pathName = parentDirPath + fileName;
		try (HSSFWorkbook workbook = rin1302Service.createWorkbook(rin1302TableList);
				FileOutputStream fos = new FileOutputStream(pathName);) {

			// ??????taskid???????????????table????????????
			taskid = batchqueueService.genTaskID();

			// batchqueue ?????????
			Batchqueue model = new Batchqueue();
			model.setSubmittime(new Date());
			model.setStarttime(new Date());
			model.setTaskid(taskid);
			model.setProcessstatus((byte) 2);
			model.setAccount(parJson.getAccount());
			model.setBatchreprotaccesspath(pathName);
			batchqueueService.insertBatchFriFacQueue(model);

			FileExport fe = new FileExport();
			if (!rin1302TableList.isEmpty()) {
				// excel ??????
				byte[] excelByteArr = excelController.getExcelBytes(workbook);

				// ??????base64
				if (excelByteArr != null) {
					String fileBase64Encode = Base64.getEncoder().encodeToString(excelByteArr);
					fe.setFileName(fileName);
					fe.setFileBase64Encode(fileBase64Encode);
				}

				workbook.write(fos);
				fos.flush();
			}

			// ???????????????
			batchqueueService.updateProcessStatus0(taskid, new Date());

			jsonBean.setData(fe);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {

			// batchqueue???update???4:????????????
			batchqueueService.updateProcessStatus4(taskid, (byte) 4, new Date());

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
		}

		return jsonBean;
	}

	// ???????????????
	private Date firstDay(String parDate) throws ParseException {

		Asiutil util = new Asiutil();
		Date resDate = util.processTWStringToDate("yyyy/MM/dd HH:mm:ss", parDate + " 23:59:59");

		Calendar cal = new GregorianCalendar();
		cal.setTime(resDate);
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));

		return cal.getTime();
	}

	// ????????????
	private Date lastDay(String parDate) throws ParseException {

		Asiutil util = new Asiutil();
		Date resDate = util.processTWStringToDate("yyyy/MM/dd HH:mm:ss", parDate + " 23:59:59");

		Calendar cal = new GregorianCalendar();
		cal.setTime(resDate);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));

		return cal.getTime();
	}

	/**
	 * ??????????????????????????????_????????????
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */

	@ApiOperation(value = "??????????????????????????????_????????????", response = JsonBean.class, tags = {
			"Rin1302api" }, notes = "??????????????????????????????_????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertscheduleexcel")
	@ResponseBody
	public JsonBean insertScheduleExcel(@ApiParam(value = "??????????????????????????????_????????????") @RequestBody Rin1302Vo parJson)
			throws Exception {

		log.debug(">>> Rin1302Controller.insertScheduleExcel(??????????????????????????????_????????????)");

		JsonBean jsonBean = new JsonBean();
		Asiutil util = new Asiutil();

		String resTaskID;

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {

			BatchqueueMapper batchqueueMapper = sqlSession.getMapper(BatchqueueMapper.class);
			BatchparameterMapper batchparameterMapper = sqlSession.getMapper(BatchparameterMapper.class);

			// ??????taskid???????????????table????????????
			resTaskID = batchqueueService.genTaskID(batchqueueMapper);

			// batchqueue ??????

			String dtaStart = parJson.getDtaStart();

			String ddlHour = parJson.getDdlHour();

			String ddlMin = parJson.getDdlMin();

			String ddlddlMin = parJson.getDdlddlMin();

			String dateString = dtaStart + " " + ddlHour + ":" + ddlMin + ":" + ddlddlMin;

			Date submittime = util.processStringToDate("yyyy/MM/dd HH:mm:ss", dateString);

			String taskid = resTaskID;

			Byte processstatus = 0;

			String account = parJson.getAccount();

			// batchqueue model ?????????
			Batchqueue model = new Batchqueue();
			model.setSubmittime(submittime);
			model.setTaskid(taskid);
			model.setProcessstatus(processstatus);
			model.setAccount(account);

			// batchparameter ??????
			String[] bgnDateArr = parJson.getTreaty_dend().split("/");
			String uctreaty_dend_Bgn_CE = (Integer.parseInt(bgnDateArr[0]) - 1911) + "/" + bgnDateArr[1] + "/" + "01";
			String uctreaty_dend_End_CE = (Integer.parseInt(bgnDateArr[0]) - 1911) + "/" + bgnDateArr[1] + "/" + "01";

			Date treaty_dend_Bgn = this.firstDay(uctreaty_dend_Bgn_CE);
			Date treaty_dend_End = this.lastDay(uctreaty_dend_End_CE); // ??????????????????

			String date1Value = util.processDateToString(treaty_dend_Bgn, "yyyy/MM/dd");
			String date2Value = util.processDateToString(treaty_dend_End, "yyyy/MM/dd");

			// batchparameter model ?????????

			Batchparameter modelParam = new Batchparameter();

			modelParam.setTaskid(taskid);
			modelParam.setDate1Value(date1Value);
			modelParam.setDate2Value(date2Value);

			// batchqueue
			batchqueueService.insertBatchFriFacQueue(model, batchqueueMapper);

			// batchparameter
			batchparameterService.insertBatchFriFacParam1(modelParam, batchparameterMapper);
			batchparameterService.insertBatchFriFacParam2(modelParam, batchparameterMapper);

			sqlSession.commit();

			// ????????????
			jsonBean.setStatus(SysEnum.statusSuccess.code);

		} catch (Exception e) {

			sqlSession.rollback();

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);

		} finally {
			sqlSession.close();
		}

		return jsonBean;
	}
}
