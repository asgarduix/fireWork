package com.asi.huanan.controller.api;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.service.FriPolicyService;
import com.asi.huanan.service.Rin1304Service;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1304QueryTree0;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1304QueryTree1;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1304QueryTree2;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1304QueryTree3;
import com.asi.huanan.vo.Rin1304QueryTreeVoReq;
import com.asi.huanan.vo.Rin1304VO;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1304api")
@RestController
@Api(tags = { "Rin1304api" })
public class Rin1304Controller {

	private static Logger log = LogManager.getLogger(Rin1304Controller.class);

	@Autowired
	private FriPolicyService friPolicyService;

	@Autowired
	private Rin1304Service rin1304Service;

	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」判斷是否有資料
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "判斷是否有資料", response = JsonBean.class, tags = { "Rin1304api" }, notes = "「保單號碼」、「批單號碼」判斷是否有資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryrin1304")
	@ResponseBody
	public ResponseEntity<?> queryRin1304(@ApiParam(value = "判斷是否有資料") @RequestBody Rin1304VO parJson)
			throws Exception {
		log.debug(">>> Rin1304Controller.queryRin1304(判斷是否有資料)");
		JsonBean jsonBean = new JsonBean();
		List<Rin1304VO> res = null;
		try {

			String policyNo = parJson.getPolicyNo(); // 保單號碼查詢
			String endorseNo = parJson.getEndorseNo(); // 批單號碼查詢

			res = friPolicyService.queryrin1304(policyNo, endorseNo);

			// 回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("查詢Rin1304主頁結果成功!");
		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢Rin1304主頁結果失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * Rin1304臨分分入畫面中的樹狀表資料API
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "判斷是否有資料", response = JsonBean.class, tags = { "Rin1304api" }, notes = "「保單號碼」、「批單號碼」判斷是否有資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryrin1304/tree/layer/{layerNo}")
	@ResponseBody
	public ResponseEntity<Object> queryRin1304Tree(@PathVariable(value = "layerNo", required = true) int layerNo,
			@RequestBody Rin1304QueryTreeVoReq voReq) throws Exception {
		log.debug("Rin1304Controller-treeLayer-queryapi");
		JsonBean jsonBean = new JsonBean();
		
		log.debug("???" + "p_no" + " " + voReq.getPolicyNo());
		log.debug("???" + "e_no" + " " + voReq.getEndorseNo());
		log.debug("???" + "a_no" + " " + voReq.getAddrNo());
		log.debug("???" + "pr_no" + " " + voReq.getPropNo());

		try {

			switch (layerNo) {
			case 0:
				// layer 0
				List<Rin1304QueryTree0> list0 = rin1304Service.qeuryTree(voReq, 0);
				log.debug(list0.size());
				jsonBean.setData(list0);
				break;
			case 1:
				// layer 1
				List<Rin1304QueryTree1> list1 = rin1304Service.qeuryTree(voReq, 1);
				log.debug(list1.size());
				jsonBean.setData(list1);
				break;
			case 2:
				// layer 2
				List<Rin1304QueryTree2> list2 = rin1304Service.qeuryTree(voReq, 2);
				log.debug(list2.size());
				jsonBean.setData(list2);
				break;
			case 3:
				// layer 3
				List<Rin1304QueryTree3> list3 = rin1304Service.qeuryTree(voReq, 3);
				log.debug(list3.size());
				jsonBean.setData(list3);
				break;

			default:
				break;
			}



			// 回傳結果
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("查詢Rin1304主頁結果成功!");
		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢Rin1304主頁結果失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

}
