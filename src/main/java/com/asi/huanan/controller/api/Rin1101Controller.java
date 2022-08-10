package com.asi.huanan.controller.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.service.FriComService;
import com.asi.huanan.service.dao.mybatis.model.FriCom;
import com.asi.huanan.service.dao.mybatis.model.customerize.FricomJoinRicmpf1;
import com.asi.huanan.vo.AutoTenRcidVo;
import com.asi.huanan.vo.QueryReinersVo;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@CrossOrigin(origins = "http://localhost:10127", maxAge = 3600)
@RequestMapping("rin1101api")
@RestController
@Api(tags= {"Rin1101api"})
public class Rin1101Controller {
	
	private static Logger log = LogManager.getLogger(Rin1101Controller.class);
	
	@Autowired
	private FriComService friComService;
	
	
	/**
	 * 用「再保人代號」搜尋
	 * @param rinComId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "用「再保人代號」搜尋", response = JsonBean.class, tags = {"Rin1101api"}, notes = "用「再保人代號」做模糊查詢")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryreiners")
	@ResponseBody
	public ResponseEntity<?> queryReiners(@ApiParam(value ="再保人代號")@RequestBody QueryReinersVo parJson) throws Exception {
		log.debug(">>> Rin1101Controller.queryReiners(用「再保人代號」搜尋)");
		log.debug(">>> (用「再保人代號」搜尋) rinComId = " + parJson.getRinComId());

		JsonBean jsonBean = new JsonBean();
		
		List<FricomJoinRicmpf1> res = new ArrayList<FricomJoinRicmpf1>();

		try {
			
			res = friComService.queryReiners(parJson.getRinComId());
			
			//處理時間格式(YYYY/MM/DD)
			String originDate = "";
			for(int i = 0; i < res.size(); i++) {
				if(StringUtils.isNotBlank(res.get(i).getUsemrk())) {
					
					originDate = res.get(i).getUsemrk();
					res.get(i).setUsemrk(originDate.substring(0,4)+"/"+originDate.substring(5,7)+"/"+originDate.substring(8,10));
					
				}
			}

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
	 * 用「再保人代號」autocomplete
	 * @param rinComId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "用「再保人代號」autocomplete", response = JsonBean.class, tags = {"Rin1101api"}, notes = "用「再保人代號」做autocomplete，取前10筆")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/autotenrcid")
	@ResponseBody
	public ResponseEntity<?> autoTenRcid(@ApiParam(value ="再保人代號")@RequestBody AutoTenRcidVo parJson) throws Exception {

		log.debug(">>> Rin1101Controller.autoTenRcid(用「再保人代號」autocomplete");
		log.debug(">>> (用「再保人代號」autocomplete) rinComId = " + parJson.getRinComId());

		JsonBean jsonBean = new JsonBean();
		
		List<FriCom> res = new ArrayList<FriCom>();

		try {
			
			res = friComService.autoTenRcid(parJson.getRinComId());
			
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
