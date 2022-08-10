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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.service.FriTreatyShareOrderService;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyShareOrder;
import com.asi.huanan.vo.DeleteTreatyShareOrderVo;
import com.asi.huanan.vo.InsertTreatyShareOrderVo;
import com.asi.huanan.vo.QueryTreatyShareOrderListVo;
import com.asi.huanan.vo.Rin1108Vo;
import com.asi.huanan.vo.UpdateTreatyShareOrderVo;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@CrossOrigin(origins = "http://localhost:10127", maxAge = 3600)
@RequestMapping("rin1108api")
@RestController
@Api(tags = { "Rin1108api" })
public class Rin1108Controller {

	private static Logger log = LogManager.getLogger(Rin1108Controller.class);

	@Autowired
	private FriTreatyShareOrderService friTreatyShareOrderService;

	/**
	 * 搜尋「合約分保順序維護」資料
	 *
	 * @param treatyYear
	 * @param policyType
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "搜尋「合約分保順序維護」資料", response = JsonBean.class, tags = {"Rin1103api" }, notes = "用合約年度與保單種類搜尋「合約分保順序維護」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querytreatyshareorderlist")
	@ResponseBody
	public ResponseEntity<?> queryTreatyShareOrderList(@ApiParam(value = "合約分保順序查詢條件") @RequestBody QueryTreatyShareOrderListVo parJson)
			throws Exception {

		log.debug(">>> Rin1108Controller.queryTreatyShareOrderList(搜尋「合約分保順序維護」資料)");

		JsonBean jsonBean = new JsonBean();

		List<Rin1108Vo> res = new ArrayList<Rin1108Vo>();

		try {

			res = friTreatyShareOrderService.queryTreatyShareOrderList(parJson.getTreatyYear(), parJson.getPolicyType());

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
	 * 新增「合約分保順序維護」資料
	 * 
	 * @param txttreaty_year
	 * @param txtpolicy_type
	 * @param numshare_order
	 * @param txttreaty_no
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "新增「合約分保順序維護」資料", response = JsonBean.class, tags = { "Rin1108api" }, notes = "新增「合約分保順序維護」資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/inserttreatyshareorder")
	@ResponseBody
	public ResponseEntity<?> insertTreatyShareOrder(@ApiParam(value = "合約分保順序維護資料") @RequestBody InsertTreatyShareOrderVo parJson)
			throws Exception {

		log.debug(">>> Rin1108Controller.insertTreatyShareOrder(新增「合約分保順序維護」資料)");

		JsonBean jsonBean = new JsonBean();

		List<FriTreatyShareOrder> resModel = new ArrayList<FriTreatyShareOrder>();

		int res = 0;
		try {
			FriTreatyShareOrder model = new FriTreatyShareOrder();
			model.setTreatyYear(parJson.getTxttreaty_year());
			model.setPolicyType(parJson.getTxtpolicy_type());
			model.setShareOrder(parJson.getNumshare_order());
			
			//檢核是否已存在資料
			resModel = friTreatyShareOrderService.queryByFriTreatyShareOrder(model);
	System.out.println(resModel);					
			//若有取得資料則進行檢核，若無資料則直接新增
			if (resModel.size() > 0) {

				jsonBean.setData("");
				jsonBean.setStatus(SysEnum.statusFinish.code);
				jsonBean.setMessage("資料已存在，不可新增!");

				return new ResponseEntity<>(jsonBean, HttpStatus.OK);

			}
System.out.println(parJson.getTxttreaty_no());
			//執行新增
			model.setTreatyNo(parJson.getTxttreaty_no());
			res = friTreatyShareOrderService.insert(model);

	
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
	 * 修改合約代號
	 *
	 * @param txttreaty_year
	 * @param txtpolicy_type
	 * @param numshare_order
	 * @param txttreaty_no
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "修改合約代號", response = JsonBean.class, tags = {"Rin1108api" }, notes = "用合約年度、保單種類、分保順序當條件，修改當筆「合約代號」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatetreatyshareorder")
	@ResponseBody
	public ResponseEntity<?> updateTreatyShareOrder(@ApiParam(value = "「合約分保順序維護」資料") @RequestBody UpdateTreatyShareOrderVo parJson)
			throws Exception {

		log.debug(">>> Rin1108Controller.updateTreatyShareOrder(修改合約代號)");

		JsonBean jsonBean = new JsonBean();

		FriTreatyShareOrder model = new FriTreatyShareOrder();
		int res = 0;

		try {
			model.setTreatyYear(parJson.getTxttreaty_year());
			model.setPolicyType(parJson.getTxtpolicy_type());
			model.setShareOrder(parJson.getNumshare_order());
			model.setTreatyNo(parJson.getTxttreaty_no());

			//執行修改
			res = friTreatyShareOrderService.update(model);

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
	 * 刪除「合約分保順序維護」資料
	 * 
	 * @param treatyYear
	 * @param treatyNo
	 * @param commType
	 * @param serial
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除「合約分保順序維護」資料", response = JsonBean.class, tags = { "Rin1108api" }, notes = "用主鍵刪除多筆合約分保順序維護資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deletetreatyshareorder")
	@ResponseBody
	public ResponseEntity<?> deleteTreatyShareOrder(@ApiParam(value = "合約分保順序維護資料主鍵") @RequestBody List<DeleteTreatyShareOrderVo> parJson)
			throws Exception {

		log.debug(">>> Rin1108Controller.deleteTreatyShareOrder(刪除合約分保順序維護資料)");

		JsonBean jsonBean = new JsonBean();

		int res = 0;

		try {
			//有資料才進行刪除
			if (parJson.size() > 0) {
				res = friTreatyShareOrderService.deleteTreadyShareOrdersByPkList(parJson);
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
