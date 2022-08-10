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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.service.FriComService;
import com.asi.huanan.vo.Rin1102ApopVOResp;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1102apopapi")
@RestController
@Api(tags = { "Rin1102Apopapi" })
public class Rin1102ApopController {

	private static Logger log = LogManager.getLogger(Rin1102ApopController.class);

	@Autowired
	private FriComService friComService;

	/**
	 * 搜尋所有「再保人代號資料」
	 *
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "搜尋所有「再保人代號資料」", response = JsonBean.class, tags = {
			"Rin1102Apopapi" }, notes = "搜尋所有「再保人代號資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryallfricom")
	@ResponseBody
	public ResponseEntity<Object> queryAllFriCom() throws Exception {

		log.debug(">>> Rin1102ApopController.queryAllFriCom(搜尋所有「再保人代號資料」)");

		JsonBean jsonBean = new JsonBean();

		List<Rin1102ApopVOResp> res = new ArrayList<>();

		try {

			res = friComService.queryAllFriCom();
			
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

	
}
