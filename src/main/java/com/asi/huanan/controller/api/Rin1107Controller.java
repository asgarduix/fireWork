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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.service.FriRiskService;
import com.asi.huanan.service.dao.mybatis.model.FriRisk;
import com.asi.huanan.vo.Rin1107QueryRiskListVoReq;
import com.asi.huanan.vo.Rin1107Vo;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1107api")
@RestController
@Api(value = "Employee Management System" ,tags= {"Rin1107api"})
public class Rin1107Controller {

	private static Logger log = LogManager.getLogger(Rin1107Controller.class);

	@Autowired
	private FriRiskService fririskservice;

	/**
	 * 「同險編號」搜尋
	 * 
	 * @param riskNo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "「同險編號」搜尋", response = JsonBean.class, tags = { "Rin1107api" }, notes = "「同險編號」搜尋")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryrisklist")
	@ResponseBody
//	public ResponseEntity<Object> queryRiskList(@RequestParam(name = "riskNo") String riskNo) throws Exception {
	public ResponseEntity<Object> queryRiskList(@RequestBody Rin1107QueryRiskListVoReq parJason) throws Exception {

		log.debug(">>> Rin1107Controller.queryRiskList(「同險編號」搜尋)");
//		log.debug(">>> (「同險編號」搜尋) riskNo = {}", parJason.getRiskNo());

		JsonBean jsonBean = new JsonBean();

		List<Rin1107Vo> res = new ArrayList<>();

		try {

			res = fririskservice.queryRiskList(parJason.getRiskNo());
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
	 * 新增同險資料
	 * 
	 * @param riskNo
	 * @param riskName
	 * @param areaCode
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "新增同險資料", response = JsonBean.class, tags = { "Rin1107api" }, notes = "新增同險資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertriskbypk")
	public ResponseEntity<Object> insertRiskByPk(@RequestParam(name = "txtrisk_no_1") String riskNo,
			@RequestParam(name = "txtrisk_name") String riskName, @RequestParam(name = "txtarea_code") String areaCode)
			throws Exception {

		log.debug(">>> Rin1107Controller.insertRiskByPk(新增同險資料)");
		log.debug(">>> (新增同險資料) riskNo = {}", riskNo);

		JsonBean jsonBean = new JsonBean();
		
		List<FriRisk> result = new ArrayList<>();
		int res = 0;

		try {
				
				FriRisk model = new FriRisk();			
				model.setRiskNo(riskNo);			//同險編號
				
				//查詢是否已存在資料	
				result = fririskservice.queryByFriRisk(model);
				if(!result.isEmpty()) {
					
					jsonBean.setData("");
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("資料庫主鍵重複");
					
					return new ResponseEntity<>(jsonBean, HttpStatus.OK);
				}
				
				model.setRiskName(riskName);		//同險名稱
				model.setAreaCode(areaCode);		//地段代碼
				
				res = fririskservice.insertByPk(model);
				
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
	 * 修改同險資料
	 * 
	 * @param riskNo
	 * @param riskName
	 * @param areaCode
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "修改同險資料", response = JsonBean.class, tags = { "Rin1107api" }, notes = "修改同險資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updaterisk")
	public ResponseEntity<Object> updateRisk(@RequestParam(name = "riskNo") String riskNo,
			@RequestParam(name = "riskName") String riskName, @RequestParam(name = "areaCode") String areaCode)
			throws Exception {

		log.debug(">>> Rin1107Controller.updateRisk(修改同險資料)");
		log.debug(">>> (修改同險資料) riskNo = {}", riskNo);

		JsonBean jsonBean = new JsonBean();

		int res = 0;

		try {
			FriRisk model = new FriRisk();
			model.setRiskNo(riskNo);			//同險編號
			model.setRiskName(riskName);		//同險名稱
			model.setAreaCode(areaCode);		//地段代碼

			res = fririskservice.updateRisk(model);

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
	 * 刪除同險資料
	 * 
	 * @param riskNo
	 * @param riskName
	 * @param areaCode
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除同險資料", response = JsonBean.class, tags = { "Rin1107api" }, notes = "刪除同險資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deleterisk")
	public ResponseEntity<Object> deleteRisk(@RequestParam String noList) throws Exception {

		log.debug(">>> Rin1107Controller.deleteRisk(刪除同險資料)");
		log.debug(">>> (刪除同險資料) riskNo = {}", noList);

		JsonBean jsonBean = new JsonBean();
		
		int res = 0;

		try {
			//處理傳入的字串轉換成string陣列(多筆同險編號)
			String[] pkString = noList.substring(1, noList.length()-1).split(",");
			
			res = fririskservice.deletePkList(pkString);

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
