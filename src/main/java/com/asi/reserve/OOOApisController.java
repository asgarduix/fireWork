package com.asi.reserve;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.security.jwtref.JwtTokenUtil;
import com.asi.mechanism.service.SysUserPersonalService;
import com.asi.mechanism.service.dao.mybatis.model.SysUserPersonal;
import com.asi.swissknife.Asiutil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy

@RequestMapping("{template_controller}")
@RestController
@Api(value = "{content_from_sa_doc}")
public class OOOApisController {
	/**
	 * TODO 單檔CRUD自動產生?
	 * 
	 */

	private static Logger log = LogManager.getLogger(OOOApisController.class);

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private SysUserPersonalService sysUserPersonalService;

	/**
	 * 
	 * template_code
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "{your_description}", response = JsonBean.class, tags = {
			"appHoliday_crud" }, notes = "{your_description}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/updateAppHoliday")
	@ResponseBody
	public ResponseEntity<Object> updateAppHoliday(@RequestParam(name = "uuid", required = true) String uuid) {
		JsonBean jsonBean = new JsonBean();
		int succVal = 0;
		Asiutil util = new Asiutil();

		try {
			boolean boo = false;

			// ...do template

			if (boo == false) {
				log.debug("something error");
				jsonBean.setStatus(SysEnum.status100.code);
				jsonBean.setMessage(SysEnum.status100.context);
				return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.status999.code);
			jsonBean.setMessage(SysEnum.status999.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 範例
	 * 
	 * APP_HOLIDAY 查詢
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "查詢假日檔資料", response = JsonBean.class, tags = {
			"appHoliday_crud" }, notes = "假日檔單檔維護<br>(可開發程式碼產生器)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/queryAppHoliday")
	@ResponseBody
	public ResponseEntity<?> queryAppHoliday(@RequestParam(name = "uuid", required = false) String uuid) {
		// List<AppHoliday> resList = new ArrayList<>();
		//
		// try {
		// if (uuid != null) {
		// AppHoliday appHoliday = new AppHoliday();
		// appHoliday.setUuid(uuid);
		// resList = appHolidayService.queryByAppHoliday(appHoliday);
		// } else {
		// resList = appHolidayService.queryAll();
		// }
		//
		// // jsonBean.setStatus(SysEnum.ok.value);
		// } catch (Exception e) {
		// log.debug(e.toString());
		// Arrays.asList(e.getStackTrace()).stream().forEach(sube ->
		// log.debug(sube.toString()));
		// return new ResponseEntity<>(new ArrayList<String>(),
		// HttpStatus.INTERNAL_SERVER_ERROR);
		// }

		JsonBean jsonBean = new JsonBean();
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 範例
	 * 
	 * APP_HOLIDAY 新增
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "新增假日檔資料", response = JsonBean.class, tags = {
			"appHoliday_crud" }, notes = "假日檔單檔維護<br>(可開發程式碼產生器)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/insertAppHoliday")
	@ResponseBody
	public ResponseEntity<?> insertAppHoliday(@RequestParam(name = "ad01", required = true) String ad01,
			@RequestParam(name = "ad02", required = true) String ad02,
			@RequestParam(name = "ad03", required = true) String ad03,
			@RequestParam(name = "ad04", required = true) String ad04) {

		JsonBean jsonBean = new JsonBean();
		int succVal = 0;
		Asiutil util = new Asiutil();

		try {
			SysUserPersonal sysUserPersonal = new SysUserPersonal();
			// sysUserPersonal.setCrtUserid("...");
			// ...
			succVal = sysUserPersonalService.insert(sysUserPersonal);

			jsonBean.setStatus(SysEnum.statusSuccess.context);
			jsonBean.setMessage("success value : " + succVal);
			jsonBean.setData(succVal);

			// 依照業務處理流程作決定
			// return new ResponseEntity<>(jsonBean, HttpStatus.OK);
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 範例
	 * 
	 * APP_HOLIDAY 新增
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "刪除假日檔資料", response = JsonBean.class, tags = {
			"appHoliday_crud" }, notes = "假日檔單檔維護<br>(可開發程式碼產生器)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@DeleteMapping("/deleteAppHoliday")
	@ResponseBody
	public ResponseEntity<?> deleteAppHoliday(@RequestParam(name = "uuid", required = true) String uuid) {
		JsonBean jsonBean = new JsonBean();
		int succVal = 0;

		try {
			succVal = sysUserPersonalService.deleteByKey(uuid);

			jsonBean.setStatus(SysEnum.statusSuccess.context);
			jsonBean.setMessage("success value : " + succVal);
			jsonBean.setData(succVal);
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
}
