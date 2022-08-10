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

import com.asi.huanan.service.BatchlogService;
import com.asi.huanan.service.dao.mybatis.model.Batchlog;
import com.asi.huanan.vo.Batch002AVo;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("batch002aapi")
@RestController
@Api(tags = { "Batch002Aapi" })
public class Batch002AController {
	
	private static Logger log = LogManager.getLogger(Batch002AController.class);
	
	@Autowired
	private BatchlogService batchlogService;
	
	/**
	 * 作業細項說明
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	 
	@ApiOperation(value = "作業細項說明", response = JsonBean.class, tags = {"Batch002Aapi" }, notes = "作業細項說明")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querylog")
	@ResponseBody
	public ResponseEntity<?> queryUseAccount(@ApiParam(value = "作業細項說明") @RequestBody Batch002AVo parJson)
			throws Exception {

		log.debug(">>> Batch002AController.queryUseAccount(作業細項說明)");

		JsonBean jsonBean = new JsonBean();

		List<Batchlog> res = new ArrayList<>();

		try {

			res = batchlogService.queryLogUseID(parJson.getTaskid());
			
			for (Batchlog obj : res) {

				if (null != obj.getSeq()) {
					long seq = obj.getSeq();
					String seqString = String.valueOf(seq);
					obj.setSeqString(seqString);
				}
			}
			
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
	
	//搜尋
	@ApiOperation(value = "作業細項說明_模糊查詢", response = JsonBean.class, tags = {"Batch002Aapi" }, notes = "作業細項說明_模糊查詢")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querybycontent")
	@ResponseBody
	public ResponseEntity<?> queryByContent(@ApiParam(value = "作業細項說明_模糊查詢") @RequestBody Batch002AVo parJson)
			throws Exception {
		
		log.debug(">>> Batch002AController.queryByContent(作業細項說明_模糊查詢)");

		JsonBean jsonBean = new JsonBean();
		
		List<Batchlog> res = new ArrayList<>();
		
		try {
			res = batchlogService.queryByContent(parJson.getKeyword(), parJson.getTaskid());
			
			for (Batchlog obj : res) {

				if (null != obj.getSeq()) {
					long seq = obj.getSeq();
					String seqString = String.valueOf(seq);
					obj.setSeqString(seqString);
				}
			}
			
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
