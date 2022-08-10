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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.service.FriComService;
import com.asi.huanan.service.FriUseLimitService;
import com.asi.huanan.service.dao.mybatis.model.FriRisk;
import com.asi.huanan.service.dao.mybatis.model.FriUseLimit;
import com.asi.huanan.vo.DeleteTreatyVo;
import com.asi.huanan.vo.Rin1107Vo;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@CrossOrigin(origins = "http://localhost:10127", maxAge = 3600)
@RequestMapping("rin1104api")
@RestController
@Api(value = "Employee Management System" ,tags= {"Rin1104api"})
public class Rin1104Controller {
	
	private static Logger log = LogManager.getLogger(Rin1104Controller.class);
	
	@Autowired
	private FriUseLimitService friUseLimitService;
	
	/**
	 * 「使用性質代號」搜尋
	 * 
	 * @param usePropId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "「使用性質代號」搜尋", response = JsonBean.class, tags = { "Rin1104api" }, notes = "「使用性質代號」搜尋")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryByUsePropId")
	public ResponseEntity<?> queryByUsePropId(@RequestParam(name = "usePropId") String usePropId) throws Exception {

		log.debug(">>> Rin1104Controller.queryByUsePropId(「使用性質代號」搜尋)");
		log.debug(">>> (「使用性質代號」搜尋) usePropId = " + usePropId);

		JsonBean jsonBean = new JsonBean();

		List<FriUseLimit> res = new ArrayList<FriUseLimit>();

		try {

			res = friUseLimitService.queryByUsePropId(usePropId);

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
	 * 新增自留限額資料
	 * 
	 * @param usePropId
	 * @param special1Limit
	 * @param special2Limit
	 * @param firstLimit
	 * @param secondLimit
	 * @param thirdLimit
	 * @param outsideLimit
	 * @param usePropName
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "新增自留限額資料", response = JsonBean.class, tags = { "Rin1104api" }, notes = "新增自留限額資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertFriUseLimit")
	public ResponseEntity<?> insertFriUseLimit(@RequestParam(name = "usePropId") String usePropId,
			@RequestParam(name = "special1Limit") String special1Limit, @RequestParam(name = "special2Limit") String special2Limit,
			@RequestParam(name = "firstLimit") String firstLimit, @RequestParam(name = "secondLimit") String secondLimit,
			@RequestParam(name = "thirdLimit") String thirdLimit, @RequestParam(name = "outsideLimit") String outsideLimit,
			@RequestParam(name = "usePropName") String usePropName)
			throws Exception {

		log.debug(">>> Rin1104Controller.insertFriUseLimit(新增自留限額資料)");
		log.debug(">>> (新增自留限額資料) usePropId = " + usePropId);

		JsonBean jsonBean = new JsonBean();
		
		List<FriUseLimit> result = new ArrayList<FriUseLimit>();
		int res = 0;

		try {
				
			FriUseLimit model = new FriUseLimit();			
				model.setUsePropId(usePropId);
				
				
				result = friUseLimitService.queryByFriUseLimit(model);
				if(result.size() > 0) {
					
					jsonBean.setData("");
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("資料庫主鍵重複");
					
					return new ResponseEntity<>(jsonBean, HttpStatus.OK);
				}
				
				model.setSpecial1Limit(special1Limit);
				model.setSpecial2Limit(special2Limit);
				model.setFirstLimit(firstLimit);
				model.setSecondLimit(secondLimit);
				model.setThirdLimit(thirdLimit);
				model.setOutsideLimit(outsideLimit);
				model.setUsePropName(usePropName);
				
				res = friUseLimitService.insertByPk(model);
				
				// 回傳結果
				jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.statusSuccess.code);


		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("新增同險資料失敗，請聯絡管理人");


		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * 修改自留限額資料
	 * 
	 * @param usePropId
	 * @param special1Limit
	 * @param special2Limit
	 * @param firstLimit
	 * @param secondLimit
	 * @param thirdLimit
	 * @param outsideLimit
	 * @param usePropName
	 * @return
	 * @throws Exception
	 */
	 
	@ApiOperation(value = "修改自留限額資料", response = JsonBean.class, tags = { "Rin1104api" }, notes = "修改自留限額資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updateFriUseLimit")
	public ResponseEntity<?> updateFriUseLimit(@RequestParam(name = "usePropId") String usePropId,
			@RequestParam(name = "special1Limit") String special1Limit, @RequestParam(name = "special2Limit") String special2Limit,
			@RequestParam(name = "firstLimit") String firstLimit, @RequestParam(name = "secondLimit") String secondLimit,
			@RequestParam(name = "thirdLimit") String thirdLimit, @RequestParam(name = "outsideLimit") String outsideLimit,
			@RequestParam(name = "usePropName") String usePropName)
			throws Exception {

		log.debug(">>> Rin1104Controller.updateRisk(修改自留限額資料)");
		log.debug(">>> (修改自留限額資料) usePropId = " + usePropId);

		JsonBean jsonBean = new JsonBean();

		List<FriUseLimit> result = new ArrayList<FriUseLimit>();
		int res = 0;

		try {
			FriUseLimit model = new FriUseLimit();
			model.setUsePropId(usePropId);
			model.setSpecial1Limit(special1Limit);
			model.setSpecial2Limit(special2Limit);
			model.setFirstLimit(firstLimit);
			model.setSecondLimit(secondLimit);
			model.setThirdLimit(thirdLimit);
			model.setOutsideLimit(outsideLimit);
			model.setUsePropName(usePropName);

			res = friUseLimitService.updateFriUseLimit(model);

			// 回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);

		}catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * 刪除自留限額資料
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除自留限額資料", response = JsonBean.class, tags = { "Rin1104api" }, notes = "刪除自留限額資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deleteFriUseLimit")
	@ResponseBody
	public ResponseEntity<?> deleteFriUseLimit(@ApiParam(value = "刪除自留限額資料") @RequestBody List<FriUseLimit> parJson) throws Exception {

		log.debug(">>> Rin1104Controller.deleteFriUseLimit(刪除自留限額資料)");

		JsonBean jsonBean = new JsonBean();
		
		int res = 0;

		try {
			//有資料才進行刪除
			if (parJson.size() > 0) {
				res = friUseLimitService.deleteFriUseLimit(parJson);
			}
//			res = friUseLimitService.deleteFriUseLimit(pkString);

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
