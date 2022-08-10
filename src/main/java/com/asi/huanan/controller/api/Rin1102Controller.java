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

import com.asi.huanan.service.FriTreatyService;
import com.asi.huanan.vo.Rin1102QueryTreatysVOReq;
import com.asi.huanan.vo.Rin1102VOResp;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1102api")
@RestController
@Api(tags= {"Rin1102api"})
public class Rin1102Controller {
	
	private static Logger log = LogManager.getLogger(Rin1102Controller.class);
	
	@Autowired
	private FriTreatyService friTreatyService;
	
	
	/**
	 * 搜尋「再保合約資料」
	 * @param treatyYear
	 * @param treatyNo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "搜尋「再保合約資料」", response = JsonBean.class, tags = {"Rin1101api"}, notes = "用「合約年度」﹑「合約代號」做「再保合約資料」模糊查詢")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querytreatys")
	@ResponseBody
	public ResponseEntity<Object> queryTreatys(@ApiParam(value ="「再保合約資料」搜尋條件")@RequestBody Rin1102QueryTreatysVOReq parJson) throws Exception {
		log.debug(">>> Rin1102Controller.queryTreatys(搜尋「再保合約資料」)");

		JsonBean jsonBean = new JsonBean();
		
		List<Rin1102VOResp> res = new ArrayList<>();

		try {
			
			res = friTreatyService.queryTreatys(parJson);

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
