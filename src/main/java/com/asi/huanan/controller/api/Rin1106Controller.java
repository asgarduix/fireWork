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

import com.asi.huanan.service.FriMunichAreaService;
import com.asi.huanan.service.dao.mybatis.model.FriMunichArea;
import com.asi.huanan.vo.Rin1106DeleteMunichVOReq;
import com.asi.huanan.vo.Rin1106InsertMunichVOReq;
import com.asi.huanan.vo.Rin1106VOReq;
import com.asi.huanan.vo.Rin1106UpdateMunichVOReq;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy

@RequestMapping("rin1106api")
@RestController
@Api(tags = { "Rin1106api" })
public class Rin1106Controller {

	private static Logger log = LogManager.getLogger(Rin1106Controller.class);

	@Autowired
	private FriMunichAreaService friMunichAreaService;

	/**
	 * 搜尋所有「慕尼黑地區設定資料」
	 *
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "搜尋所有「慕尼黑地區設定資料」", response = JsonBean.class, tags = {
			"Rin1106api" }, notes = "搜尋所有「慕尼黑地區設定資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querymunichlist")
	@ResponseBody
	public ResponseEntity<Object> queryMunichList() throws Exception {

		log.debug(">>> Rin1106Controller.queryMunichList(搜尋所有「慕尼黑地區設定資料」)");

		JsonBean jsonBean = new JsonBean();

		List<Rin1106VOReq> res = new ArrayList<>();

		try {

			res = friMunichAreaService.queryAllFor1106();

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
	 * 新增「慕尼黑地區設定資料」
	 *
	 * @param txtnatural_id
	 * @param txtcity_name
	 * @param txtmunich_id
	 * @param txtmunich_desc
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "新增「慕尼黑地區設定資料」", response = JsonBean.class, tags = { "Rin1106api" }, notes = "新增「慕尼黑地區設定資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertmunich")
	@ResponseBody
	public ResponseEntity<Object> insertMunich(@ApiParam(value = "慕尼黑地區設定資料") @RequestBody Rin1106InsertMunichVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1106Controller.insertMunich(新增「慕尼黑地區設定資料」」)");

		JsonBean jsonBean = new JsonBean();
		
		List<FriMunichArea> results = new ArrayList<>();
		
		int res = 0;

		try {
			FriMunichArea model = new FriMunichArea();
			
			model.setNaturalId(parJson.getTxtnatural_id());		//天災區域代號
			
			//查詢是否已存在資料		
			results = friMunichAreaService.queryByFriMunichArea(model);
			
			
			// 若已有，則不可新增
			if (!results.isEmpty()) {

				jsonBean.setData("");
				jsonBean.setStatus(SysEnum.statusFinish.code);
				jsonBean.setMessage("資料已存在，不可新增!");

				return new ResponseEntity<>(jsonBean, HttpStatus.OK);

			}

			
			model.setCityName(parJson.getTxtcity_name());		//縣市名稱
			model.setMunichId(parJson.getTxtmunich_id());		//慕尼黑地區代號
			model.setMunichDesc(parJson.getTxtmunich_desc());	//慕尼黑地區說明
			//執行新增
			res = friMunichAreaService.insert(model);
	
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
	 * 修改「慕尼黑地區設定資料」
	 *
	 * @param txtnatural_id
	 * @param txtcity_name
	 * @param txtmunich_id
	 * @param txtmunich_desc
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "修改「慕尼黑地區設定資料」", response = JsonBean.class, tags = {
			"Rin1106api" }, notes = "用天災區域代號當條件，修改當筆「慕尼黑地區設定資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatemunich")
	@ResponseBody
	public ResponseEntity<Object> updateMunich(@ApiParam(value = "修改「慕尼黑地區設定資料」") @RequestBody Rin1106UpdateMunichVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1106Controller.updateMunich(修改「慕尼黑地區設定資料」)");

		JsonBean jsonBean = new JsonBean();

		FriMunichArea model = new FriMunichArea();
		int res = 0;

		try {
			
			model.setNaturalId(parJson.getTxtnatural_id());		//天災區域代號
			model.setCityName(parJson.getTxtcity_name());		//縣市名稱
			model.setMunichId(parJson.getTxtmunich_id());		//慕尼黑地區代號
			model.setMunichDesc(parJson.getTxtmunich_desc());	//慕尼黑地區說明

			//執行修改
			res = friMunichAreaService.update(model);

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
	 * 刪除「慕尼黑地區設定資料」
	 * 
	 * @param txtnatural_id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除「慕尼黑地區設定資料」", response = JsonBean.class, tags = { "Rin1106api" }, notes = "用主鍵刪除多筆「慕尼黑地區設定資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deleteretain")
	@ResponseBody
	public ResponseEntity<Object> deleteRetain(@ApiParam(value = "刪除「慕尼黑地區設定資料」") @RequestBody List<Rin1106DeleteMunichVOReq> parJson)
			throws Exception {

		log.debug(">>> Rin1106Controller.deleteRetain(刪除「慕尼黑地區設定資料」)");

		JsonBean jsonBean = new JsonBean();

		int res = 0;

		try {
			//有資料才進行刪除
			if (!parJson.isEmpty()) {
				res = friMunichAreaService.deleteMunichsByPkList(parJson);
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
