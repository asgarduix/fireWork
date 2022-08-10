package com.asi.huanan.controller.api;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.asi.huanan.service.FriComCreditService;
import com.asi.huanan.service.customerize.CustomerizeService;
import com.asi.huanan.service.dao.mybatis.model.FriComCredit;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1101FricomJoinRicmpf1;
import com.asi.huanan.vo.Rin1101AQueryCreditVOReq;
import com.asi.huanan.vo.Rin1101AQueryOneReinerVOReq;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1101aapi")
@RestController
@Api(tags= {"Rin1101Aapi"})
public class Rin1101AController {
	
	private static Logger log = LogManager.getLogger(Rin1101AController.class);
	
	@Autowired
	private CustomerizeService customerizeService;
	@Autowired
	private FriComCreditService friComCreditService;
	
	
	
	
	/**
	 * 用「再保人代號」取得再保人
	 * @param rinComId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "用「再保人代號」取得再保人", response = JsonBean.class, tags = {"Rin1101Aapi"}, notes = "用「再保人代號」取得此再保人的完整資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryonereiner")
	@ResponseBody
	public ResponseEntity<Object> queryOneReiner(@ApiParam(value ="再保人代號")@RequestBody Rin1101AQueryOneReinerVOReq parJson) throws Exception {

		log.debug(">>> Rin1101AController.queryOneReiner(用「再保人代號」取得再保人)");
		log.debug(">>> (用「再保人代號」取得再保人) rinComId = {}", parJson.getRinComId());

		JsonBean jsonBean = new JsonBean();
		
		List<Rin1101FricomJoinRicmpf1> res = new ArrayList<>();

		try {
			
			res = customerizeService.queryOneReiner(parJson.getRinComId());

			//回傳結果
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
	 * 查詢「評等資料」
	 * @param rinComId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢「評等資料」", response = JsonBean.class, tags = {"Rin1101Aapi"}, notes = "用再保人資料查詢其「評等資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querycredit")
	@ResponseBody
	public ResponseEntity<Object> queryCredit(@ApiParam(value ="再保人代號")@RequestBody Rin1101AQueryCreditVOReq parJson) throws Exception {

		log.debug(">>> Rin1101AController.querycredit(查詢「評等資料」)");
		log.debug(">>> (查詢「評等資料」) rinComId = {}", parJson.getRinComId());

		JsonBean jsonBean = new JsonBean();
		
		List<FriComCredit> res = new ArrayList<>();

		try {
			
			res = friComCreditService.queryCredit(parJson.getRinComId());

			//回傳結果
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
	
	

}
