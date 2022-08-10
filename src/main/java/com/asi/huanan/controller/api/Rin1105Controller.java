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

import com.asi.huanan.service.FriRetainLimitService;
import com.asi.huanan.service.dao.mybatis.model.FriRetainLimit;
import com.asi.huanan.vo.Rin1105DeleteRetainVOReq;
import com.asi.huanan.vo.Rin1105InsertRetainVOReq;
import com.asi.huanan.vo.Rin1105QueryRetainListVOReq;
import com.asi.huanan.vo.Rin1105VOResp;
import com.asi.huanan.vo.Rin1105UpdateRetainVOReq;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1105api")
@RestController
@Api(tags = { "Rin1105api" })
public class Rin1105Controller {

	private static Logger log = LogManager.getLogger(Rin1105Controller.class);

	@Autowired
	private FriRetainLimitService friRetainLimitService;

	/**
	 * 搜尋「限額資料」
	 *
	 * @param treatyYear
	 * @param limitId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "搜尋「限額資料」", response = JsonBean.class, tags = {
			"Rin1105api" }, notes = "用合約年度與限額代號搜尋「限額資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryretainlist")
	@ResponseBody
	public ResponseEntity<Object> queryRetainList(@ApiParam(value = "限額資料條件") @RequestBody Rin1105QueryRetainListVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1105Controller.queryRetainList(搜尋「限額資料」)");

		JsonBean jsonBean = new JsonBean();

		List<Rin1105VOResp> res = new ArrayList<>();

		try {

			res = friRetainLimitService.queryRetainList(parJson.getTreatyYear(), parJson.getLimitId());

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
	 * 新增「限額資料」
	 *
	 * @param txttreaty_year
	 * @param txtlimit_id
	 * @param numlimit_amount
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "新增「限額資料」", response = JsonBean.class, tags = { "Rin1105api" }, notes = "新增「限額資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertretain")
	@ResponseBody
	public ResponseEntity<Object> insertRetain(@ApiParam(value = "限額資料") @RequestBody Rin1105InsertRetainVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1105Controller.insertRetain(新增「限額資料」)");

		JsonBean jsonBean = new JsonBean();
		
		List<FriRetainLimit> results = new ArrayList<>();
		
		int res = 0;

		try {
			FriRetainLimit model = new FriRetainLimit();
			
			//檢核是否已存在資料		
			model.setTreatyYear(parJson.getTxttreaty_year());	//合約年度
			model.setLimitId(parJson.getTxtlimit_id());			//限額代號
			results = friRetainLimitService.queryByFriRetainLimit(model);
			
			
			// 若已有，則不可新增
			if (!results.isEmpty()) {

				jsonBean.setData("");
				jsonBean.setStatus(SysEnum.statusFinish.code);
				jsonBean.setMessage("資料已存在，不可新增!");

				return new ResponseEntity<>(jsonBean, HttpStatus.OK);

			}

			model.setLimitAmount(parJson.getNumlimit_amount());	//限額
			//執行新增
			res = friRetainLimitService.insert(model);

			//將序號回傳前端
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("新增失敗!");

		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	
	/**
	 * 修改「限額資料」
	 *
	 * @param txttreaty_year
	 * @param txtlimit_id
	 * @param numlimit_amount
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "修改「限額資料」", response = JsonBean.class, tags = {
			"Rin1105api" }, notes = "用合約年度、限額代號當條件，修改當筆「限額資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updateretain")
	@ResponseBody
	public ResponseEntity<Object> updateRetain(@ApiParam(value = "修改「限額資料」") @RequestBody Rin1105UpdateRetainVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1105Controller.updateRetain(修改「限額資料」)");

		JsonBean jsonBean = new JsonBean();

		FriRetainLimit model = new FriRetainLimit();
		int res = 0;

		try {
			
			model.setTreatyYear(parJson.getTxttreaty_year());	//合約年度
			model.setLimitId(parJson.getTxtlimit_id());			//限額代號
			model.setLimitAmount(parJson.getNumlimit_amount());	//限額

			//執行修改
			res = friRetainLimitService.update(model);

			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("修改失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 刪除「限額資料」
	 * 
	 * @param treatyYear
	 * @param treatyNo
	 * @param commType
	 * @param serial
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除「限額資料」", response = JsonBean.class, tags = { "Rin1105api" }, notes = "用主鍵刪除多筆「限額資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deleteretain")
	@ResponseBody
	public ResponseEntity<Object> deleteRetain(@ApiParam(value = "「限額資料」主鍵") @RequestBody List<Rin1105DeleteRetainVOReq> parJson)
			throws Exception {

		log.debug(">>> Rin1105Controller.deleteRetain(刪除「限額資料」)");

		JsonBean jsonBean = new JsonBean();

		int res = 0;

		try {
			//有資料才進行刪除
			if (!parJson.isEmpty()) {
				res = friRetainLimitService.deleteRetainsByPkList(parJson);
			}

			// 回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("刪除失敗!");

		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
}
