package com.asi.huanan.controller.api;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.service.FriTreatyCommService;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyComm;
import com.asi.huanan.vo.DeleteTreatyVo;
import com.asi.huanan.vo.InsertTreatyCommVo;
import com.asi.huanan.vo.QueryTreatyCommListVo;
import com.asi.huanan.vo.Rin1103Vo;
import com.asi.huanan.vo.UpdateTreatyCommVo;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@CrossOrigin(origins = "http://localhost:10127", maxAge = 3600)
@RequestMapping("rin1103api")
@RestController
@Api(tags = { "Rin1103api" })
public class Rin1103Controller {

	private static Logger log = LogManager.getLogger(Rin1103Controller.class);

	@Autowired
	private FriTreatyCommService friTreatyCommService;

	/**
	 * 搜尋「梯次佣金資料」
	 *
	 * @param treatyYear
	 * @param treatyNo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "搜尋「梯次佣金資料」", response = JsonBean.class, tags = {
			"Rin1103api" }, notes = "用合約年度與合約編號搜尋「梯次佣金資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querytreatylist")
	@ResponseBody
	public ResponseEntity<?> queryTreatyList(@ApiParam(value = "合約內容") @RequestBody QueryTreatyCommListVo parJson)
			throws Exception {

		log.debug(">>> Rin1103Controller.queryTreatyList(搜尋「梯次佣金資料」)");

		JsonBean jsonBean = new JsonBean();

		List<Rin1103Vo> res = new ArrayList<Rin1103Vo>();

		try {

			res = friTreatyCommService.queryTreatyList(parJson.getTreatyYear(), parJson.getTreatyNo());

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
	 * 
	 *
	 * @param treatyYear
	 * @param treatyNo
	 * @param commType
	 * @param upperLimit
	 * @param lowerLimit
	 * @param commRate
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "新增「梯次佣金資料」", response = JsonBean.class, tags = { "Rin1103api" }, notes = "新增「梯次佣金資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/inserttreaty")
	@ResponseBody
	public ResponseEntity<?> insertTreaty(@ApiParam(value = "梯次佣金資料") @RequestBody InsertTreatyCommVo parJson)
			throws Exception {

		log.debug(">>> Rin1103Controller.insertTreaty(新增「梯次佣金資料」)");

		JsonBean jsonBean = new JsonBean();

		List<FriTreatyComm> resModel = new ArrayList<FriTreatyComm>();
		short serial = 0;

		try {
			//取得"合約年度"、"合約編號"、"梯次佣金類別"相同的所有資料
			resModel = friTreatyCommService.checkInsertUpLow(parJson.getTxttreaty_year(), parJson.getTxttreaty_no(),
					parJson.getTxtcomm_type());
			
			
			//若有取得資料則進行檢核，若無資料則直接新增
			if(resModel.size() > 0) {
				
				//檢核上下限是否重疊
				for (FriTreatyComm model : resModel) {
					
					BigDecimal compareA = parJson.getNumlower_limit().max(model.getLowerLimit());
					BigDecimal compareB = parJson.getNumupper_limit().min(model.getUpperLimit());
					
					//重疊則不可新增
					if (compareA.compareTo(compareB) <= 0) {
						
						jsonBean.setData("");
						jsonBean.setStatus(SysEnum.statusFinish.code);
						jsonBean.setMessage("上下限重疊!");
						
						return new ResponseEntity<>(jsonBean, HttpStatus.OK);
						
					}
					
				}
			}

			//執行新增，且取得自生序號
			serial = friTreatyCommService.insertTreadyReturnSerial(parJson);

			//將序號回傳前端
			jsonBean.setData(serial);
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
	 * 修改「梯次佣金資料」
	 *
	 * @param treatyYear
	 * @param treatyNo
	 * @param commType
	 * @param upperLimit
	 * @param lowerLimit
	 * @param commRate
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "修改「梯次佣金資料」", response = JsonBean.class, tags = {
			"Rin1103api" }, notes = "用原始的合約年度、合約編號、梯次佣金類別、序號當條件，修改當筆「梯次佣金資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatetreaty")
	@ResponseBody
	public ResponseEntity<?> updateTreaty(@ApiParam(value = "梯次佣金資料的新、舊資料") @RequestBody UpdateTreatyCommVo parJson)
			throws Exception {

		log.debug(">>> Rin1103Controller.updateTreaty(修改「梯次佣金資料」)");

		JsonBean jsonBean = new JsonBean();

		List<FriTreatyComm> resModel = new ArrayList<FriTreatyComm>();
		int res = 0;

		try {
			//取得"合約年度"、"合約編號"、"梯次佣金類別"相同的所有資料(且用"序號"排除自己本身的資料)
			resModel = friTreatyCommService.checkUpdateUpLow(parJson.getTxttreaty_year(), parJson.getTxttreaty_no(),
					parJson.getTxtcomm_type(), parJson.getSerial());
			
			
			//若有取得資料則進行檢核，若無資料則直接修改
			if (resModel.size() > 0) {

				//檢核上下限是否重疊
				for (FriTreatyComm model : resModel) {

					BigDecimal compareA = parJson.getNumlower_limit().max(model.getLowerLimit());
					BigDecimal compareB = parJson.getNumupper_limit().min(model.getUpperLimit());

					//重疊則不可新增
					if (compareA.compareTo(compareB) <= 0) {

						jsonBean.setData("");
						jsonBean.setStatus(SysEnum.statusFinish.code);
						jsonBean.setMessage("上下限重疊!");

						return new ResponseEntity<>(jsonBean, HttpStatus.OK);

					}
				}
			}

			//執行修改
			res = friTreatyCommService.updateByOldPk(parJson);

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
	 * 刪除梯次佣金資料
	 * 
	 * @param treatyYear
	 * @param treatyNo
	 * @param commType
	 * @param serial
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除梯次佣金資料", response = JsonBean.class, tags = { "Rin1103api" }, notes = "用主鍵刪除多筆梯次佣金資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deletetreaty")
	@ResponseBody
	public ResponseEntity<?> deleteTreaty(@ApiParam(value = "梯次佣金資料主鍵") @RequestBody List<DeleteTreatyVo> parJson)
			throws Exception {

		log.debug(">>> Rin1103Controller.deleteTreaty(刪除梯次佣金資料)");

		JsonBean jsonBean = new JsonBean();

		int res = 0;

		try {
			//有資料才進行刪除
			if (parJson.size() > 0) {
				res = friTreatyCommService.deleteTreadysByPkList(parJson);
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
