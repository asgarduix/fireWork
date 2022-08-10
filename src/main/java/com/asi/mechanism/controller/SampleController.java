package com.asi.mechanism.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.vo.SampleVoChild;
import com.asi.huanan.vo.SampleVoSimple;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.security.jwtref.JwtTokenUtil;
import com.asi.mechanism.service.connector.DBConnectionSqlite;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@CrossOrigin(origins = "http://localhost:10121", maxAge = 3600)
@RequestMapping("common")
@RestController
@Api(value = "api_value")
public class SampleController {

	private static Logger log = LogManager.getLogger(SampleController.class);

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private DBConnectionSqlite sqlite;

	/**
	 * 檢核字串是否有符合email格式
	 * 
	 * @return
	 */
	@ApiOperation(value = "檢核字串是否有符合email格式", response = JsonBean.class, tags = {
			"common" }, notes = "檢核字串是否有符合email格式")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list", response = JsonBean.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/checkemail/v3", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public ResponseEntity<?> backPublicCheckEmailV3(@ApiParam(value = "SampleVo") @RequestBody SampleVoSimple sample) {
		JsonBean jsonBean = new JsonBean();
		List<Map<String, String>> messageList = new ArrayList<Map<String, String>>();
		String regex = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
		Boolean isEmailCorrect = false;

		try {
			List<SampleVoChild> dataChildList = new ArrayList<SampleVoChild>();

			String value = sample.getEmail();

			if (value.toLowerCase().matches(regex)) {
				isEmailCorrect = true;
				Map<String, String> messageMap = new HashMap<String, String>();
				messageList.add(messageMap);
			}

			for (SampleVoChild dataChild : dataChildList) {
				String key = dataChild.getKey();

			}

			if (isEmailCorrect) {
				jsonBean.setData("");
				jsonBean.setStatus(HttpStatus.OK.toString());
				jsonBean.setMessage("temporary");
//				jsonBean.setMessage("temporary");
			} else {
				jsonBean.setData(messageList);
				jsonBean.setStatus(HttpStatus.BAD_REQUEST.toString());
				jsonBean.setMessage("temporary");
			}

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 檢核字串是否有符合email格式
	 * 
	 * @return
	 */
	@ApiOperation(value = "檢核字串是否有符合email格式", response = JsonBean.class, tags = {
			"common" }, notes = "檢核字串是否有符合email格式")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list", response = JsonBean.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/checkemail/v2")
	@ResponseBody
	public ResponseEntity<?> backPublicCheckEmailV2(@RequestParam(name = "email", required = true) String email) {
		JsonBean jsonBean = new JsonBean();
		List<Map<String, String>> messageList = new ArrayList<Map<String, String>>();
		String regex = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
		Boolean isEmailCorrect = false;

		try {
			List<SampleVoChild> dataChildList = new ArrayList<SampleVoChild>();

			String value = email;

			if (value.toLowerCase().matches(regex)) {
				isEmailCorrect = true;
				Map<String, String> messageMap = new HashMap<String, String>();
				messageList.add(messageMap);
			}

			for (SampleVoChild dataChild : dataChildList) {
				String key = dataChild.getKey();

			}

			if (isEmailCorrect) {
				jsonBean.setData("");
				jsonBean.setStatus(HttpStatus.OK.toString());
				jsonBean.setMessage("temporary");
//				jsonBean.setMessage("temporary");
			} else {
				jsonBean.setData(messageList);
				jsonBean.setStatus(HttpStatus.BAD_REQUEST.toString());
				jsonBean.setMessage("temporary");
			}

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 檢核字串是否有符合email格式
	 * 
	 * @return
	 */
	@ApiOperation(value = "檢核字串是否有符合email格式", response = JsonBean.class, tags = {
			"common" }, notes = "檢核字串是否有符合email格式")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved list", response = JsonBean.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/checkemail")
	@ResponseBody
	public ResponseEntity<?> backPublicCheckEmail(
			@ApiParam(value = "SampleVo") @RequestBody(required = true) SampleVoSimple sampleRequest) {
		JsonBean jsonBean = new JsonBean();
		List<Map<String, String>> messageList = new ArrayList<Map<String, String>>();
		String regex = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
		Boolean isEmailCorrect = false;

		try {
			List<SampleVoChild> dataChildList = new ArrayList<SampleVoChild>();

			String value = sampleRequest.getEmail();

			if (value.toLowerCase().matches(regex)) {
				isEmailCorrect = true;
				Map<String, String> messageMap = new HashMap<String, String>();
				messageList.add(messageMap);
			}

			for (SampleVoChild dataChild : dataChildList) {
				String key = dataChild.getKey();

			}

			if (isEmailCorrect) {
				jsonBean.setData("");
				jsonBean.setStatus(HttpStatus.OK.toString());
				jsonBean.setMessage("temporary");
//				jsonBean.setMessage("temporary");
			} else {
				jsonBean.setData(messageList);
				jsonBean.setStatus(HttpStatus.BAD_REQUEST.toString());
				jsonBean.setMessage("temporary");
			}

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 檢核字串是否有符合email格式(參數為list)
	 * 
	 * @return
	 */
	@ApiOperation(value = "檢核字串是否有符合email格式(參數為array)", response = JsonBean.class, tags = {
			"common" }, notes = "檢核字串是否有符合email格式(參數為array)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/backPublicCheckEmailList")
	@ResponseBody
	public ResponseEntity<?> backPublicCheckEmailList(
			@RequestParam(name = "email", required = true) List<String> emailList) {
		JsonBean jsonBean = new JsonBean();
		String regex = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
		Boolean isEmailCorrect = true;
		List<String> messageList = new ArrayList<String>();
		try {
			for (String email : emailList) {
				if (!email.toLowerCase().matches(regex)) {
					isEmailCorrect = false;
					messageList.add(email);
				}
			}
			if (isEmailCorrect) {
				jsonBean.setData("");
				jsonBean.setStatus(HttpStatus.OK.toString());
				jsonBean.setMessage("temporary");
			} else {
				jsonBean.setData(messageList);
				jsonBean.setStatus(HttpStatus.BAD_REQUEST.toString());
				jsonBean.setMessage("temporary");
			}

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

}
