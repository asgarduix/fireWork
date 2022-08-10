package com.asi.huanan.controller.api.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy

@RequestMapping("commonController")
@RestController
@Api(value = "Employee Management System")
public class CommonController {

	private static Logger log = LogManager.getLogger(CommonController.class);

	/**
	 * 四則運算
	 * 
	 * @param preOne
	 * @param afterOne
	 * @param operator
	 * @return
	 */
	@ApiOperation(value = "四則運算", response = JsonBean.class, tags = { "common" }, notes = "四則運算")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/getArithmetic")
	@ResponseBody
	public ResponseEntity<?> getArithmetic(@RequestParam(name = "preOne", required = true) String preOne,
			@RequestParam(name = "afterOne", required = true) String afterOne,
			@RequestParam(name = "operator", required = true) String operator,
			@RequestParam(name = "scale", required = false) String scale) {
		JsonBean jsonBean = new JsonBean();
		try {
			BigDecimal pre = new BigDecimal(preOne);
			BigDecimal after = new BigDecimal(afterOne);
			BigDecimal result = null;
			switch (operator) {
			case "+":
				result = pre.add(after);
				break;
			case "-":
				result = pre.subtract(after);
				break;
			case "*":
				result = pre.multiply(after);
				break;
			case "/":
				result = pre.divide(after, Integer.parseInt(scale), RoundingMode.HALF_UP);
				break;
			default:
				break;
			}
			jsonBean.setData(result.stripTrailingZeros().toPlainString());
			jsonBean.setStatus(SysEnum.status000.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.status999.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

}
