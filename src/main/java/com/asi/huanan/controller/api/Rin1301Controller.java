package com.asi.huanan.controller.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.service.FriAccountingService;
import com.asi.huanan.service.FriFacBrokerService;
import com.asi.huanan.service.FriFacService;
import com.asi.huanan.service.FriKindMappingService;
import com.asi.huanan.service.FriPolicyService;
import com.asi.huanan.service.LogFriFacBrokerService;
import com.asi.huanan.service.MrexpfService;
import com.asi.huanan.service.PropNameService;
import com.asi.huanan.service.customerize.Rin1301Service;
import com.asi.huanan.service.dao.mybatis.model.FriAccounting;
import com.asi.huanan.service.dao.mybatis.model.FriFac;
import com.asi.huanan.service.dao.mybatis.model.FriFacBroker;
import com.asi.huanan.service.dao.mybatis.model.FriKindMapping;
import com.asi.huanan.service.dao.mybatis.model.FriPolicy;
import com.asi.huanan.service.dao.mybatis.model.LogFriFacBroker;
import com.asi.huanan.service.dao.mybatis.model.Mrexpf;
import com.asi.huanan.service.dao.mybatis.model.PropName;
import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryMainData;
import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryPolicyData;
import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryRinserData;
import com.asi.huanan.vo.rin1301.req.Rin1301AQueryParamVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301FacRinserVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301HandleDataVOReq;
import com.asi.huanan.vo.rin1301.req.Rin1301QueryMainDataVOReq;
import com.asi.huanan.vo.rin1301.req.Rin1301QueryPolicyDetailVOReq;
import com.asi.huanan.vo.rin1301.res.Rin1301PolicyInfoVOResp;
import com.asi.huanan.vo.rin1301.res.Rin1301QuereyBrokerDetailSubVoResp;
import com.asi.huanan.vo.rin1301.res.Rin1301QueryFacPolicyVOResp;
import com.asi.huanan.vo.rin1301.res.Rin1301QueryFacPrintAccountVOResp;
import com.asi.huanan.vo.rin1301.res.Rin1301QueryPolicyDetailVOResp;
import com.asi.huanan.vo.rin1301.res.Rin1301QueryRinserDetailSubVoResp;
import com.asi.huanan.vo.rin1301.res.Rin1301SelectListVOResp;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.swissknife.Asiutil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1301api")
@RestController
@Api(value = "Employee Management System", tags = { "Rin1301api" })
public class Rin1301Controller {

	private static Logger log = LogManager.getLogger(Rin1301Controller.class);

	@Autowired
	private Rin1301Service rin1301Service;
	@Autowired
	private PropNameService propNameService;
	@Autowired
	private FriKindMappingService friKindMappingService;
	@Autowired
	private MrexpfService mrexpfService;
	@Autowired
	private FriFacBrokerService friFacBrokerService;
	@Autowired
	private FriAccountingService friAccountingService;
	@Autowired
	private FriPolicyService friPolicyService;
	@Autowired
	private LogFriFacBrokerService logFriFacBrokerService;
	@Autowired
	private FriFacService friFacService;

	@ApiOperation(value = "臨分資料維護主頁_查詢", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "臨分資料維護主頁查詢，查詢條件為保單號、合約編號、知會/更正號擇一")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryFacPolicy")
	@ResponseBody
	public ResponseEntity<?> queryFacPolicy(@ApiParam(value = "主頁查詢條件") @RequestBody Rin1301QueryMainDataVOReq param)
			throws Exception {
		log.debug(">>> Rin1301Controller.queryFacPolicy( 臨分資料維護主頁_查詢 )");
		JsonBean jsonBean = new JsonBean();
		List<Rin1301QueryFacPolicyVOResp> res = new ArrayList<>();

		try {
			// 若未列印及暫緩或不轉之臨分起訖日皆空值，而且保單號、合約編號、知會/更正號其中一個有值，呼叫queryFacPolicy方法查詢
			if ((!StringUtils.isNotBlank(param.getPolicyDprtBgn()) && !StringUtils.isNotBlank(param.getPolicyDprtEnd()))
					&& (StringUtils.isNotBlank(param.getPolicyNo()) || StringUtils.isNotBlank(param.getCessionNo())
							|| StringUtils.isNotBlank(param.getSlipNo()))) {
				List<Rin1301QueryMainData> results = rin1301Service.queryFacPolicy(param);

				res = results.stream().map(result -> {
					Rin1301QueryFacPolicyVOResp targetBean = new Rin1301QueryFacPolicyVOResp();
					BeanUtils.copyProperties(result, targetBean);
					if (StringUtils.equals(targetBean.getPrintType(), "N")) {
						targetBean.setConversionStatus("");
					}
					return targetBean;
				}).collect(Collectors.toList());

			}

			if (res.isEmpty() && StringUtils.isNotBlank(param.getPolicyNo())) {
				jsonBean.setMessage("無此保單號碼臨分相關資料");
			}

			if (res.isEmpty() && StringUtils.isNotBlank(param.getSlipNo())) {
				jsonBean.setMessage("無此知會/更正號臨分相關資料");
			}

			if (res.isEmpty() && StringUtils.isNotBlank(param.getCessionNo())) {
				jsonBean.setMessage("無此合約號臨分相關資料");
			}

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "臨分資料維護主頁_查詢", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "臨分資料維護主頁查詢，查詢條件為未列印及暫緩或不轉之臨分期間(時間區間)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryFacPrintAccount")
	@ResponseBody
	public ResponseEntity<?> queryFacPrintAccount(
			@ApiParam(value = "主頁查詢條件") @RequestBody Rin1301QueryMainDataVOReq param) throws Exception {
		log.debug(">>> Rin1301Controller.queryFacPrintAccount( 臨分資料維護主頁_查詢 )");
		JsonBean jsonBean = new JsonBean();
		List<Rin1301QueryFacPrintAccountVOResp> res = new ArrayList<>();

		try {
			if (StringUtils.isNotBlank(param.getPolicyDprtBgn())) {
				param.setPolicyDprtBgn(param.getPolicyDprtBgn().strip() + " 00:00:00");
			} else {
				param.setPolicyDprtBgn("1900/1/1" + " 00:00:00");
			}
			if (StringUtils.isNotBlank(param.getPolicyDprtEnd())) {
				param.setPolicyDprtEnd(param.getPolicyDprtEnd().strip() + " 23:59:59");
			} else {
				param.setPolicyDprtEnd("9999/12/31" + " 23:59:59");
			}

			// 若未列印及暫緩或不轉之臨分起訖日其中一個有值，而且保單號、合約編號、知會/更正號皆空值，呼叫queryFacPrintAccount方法查詢
			if ((StringUtils.isNotBlank(param.getPolicyDprtBgn()) || StringUtils.isNotBlank(param.getPolicyDprtEnd()))
					&& (!StringUtils.isNotBlank(param.getPolicyNo()) && !StringUtils.isNotBlank(param.getCessionNo())
							&& !StringUtils.isNotBlank(param.getSlipNo()))) {
				List<Rin1301QueryMainData> results = rin1301Service.queryFacPrintAccount(param);

				res = results.stream().map(result -> {
					Rin1301QueryFacPrintAccountVOResp targetBean = new Rin1301QueryFacPrintAccountVOResp();
					BeanUtils.copyProperties(result, targetBean);
					return targetBean;
				}).collect(Collectors.toList());

			}

			if (res.isEmpty()) {
				jsonBean.setMessage("查無相關資料");
			}

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "臨分資料維護內頁_使用同險代號查詢保批單", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "臨分資料維護內頁_根據同險代號查詢臨分保單清單_保批單資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryPolicyDetailBySameRiskCode")
	@ResponseBody
	public ResponseEntity<?> queryPolicyDetailBySameRiskCode(
			@ApiParam(value = "臨分保單清單查詢條件") @RequestBody Rin1301AQueryParamVoReq param) throws Exception {

		log.debug(">>> Rin1301Controller.queryPolicyDetailBySameRiskCode( 臨分資料維護內頁_使用同險代號查詢保批單 )");
		JsonBean jsonBean = new JsonBean();
		String errorMessage = "";
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		try {

			if (param.getRiskNos() == null || param.getRiskNos().isEmpty()) {
				errorMessage += "請輸入同險代號。\n";
			}

			if (StringUtils.isBlank(param.getTreatyDBgn())) {
				errorMessage += "請輸入生效起日。\n";
			}

			if (StringUtils.isBlank(param.getTreatyDEnd())) {
				errorMessage += "請輸入生效迄日。\n";
			}

			if (StringUtils.isNotEmpty(errorMessage)) {
				jsonBean.setData("");
				jsonBean.setMessage(errorMessage);
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}

			param.setTreatyDBgn(param.getTreatyDBgn().strip() + " 00:00:00");
			param.setTreatyDEnd(param.getTreatyDEnd().strip() + " 23:59:59");
			results = rin1301Service.queryPolicyDetailBySameRiskCode(param);

			List<Rin1301PolicyInfoVOResp> res = results.stream().map(result -> {
				Rin1301PolicyInfoVOResp targetBean = new Rin1301PolicyInfoVOResp();
				BeanUtils.copyProperties(result, targetBean);
				return targetBean;
			}).collect(Collectors.toList());

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "臨分資料維護內頁_檢核保批單境外分入註記", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "臨分資料維護內頁_檢核保批單境外分入註記")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/checkPolEndorseByMkovse")
	@ResponseBody
	public ResponseEntity<?> checkPolEndorseByMkovse(
			@ApiParam(value = "檢核條件") @RequestBody List<Rin1301QueryPolicyDetailVOReq> params) throws Exception {

		log.debug(">>> Rin1301Controller.checkPolEndorseByMkovse( 臨分資料維護內頁_檢核保批單境外分入註記 )");
		JsonBean jsonBean = new JsonBean();
		String errorMessage = "";
		try {

			if (params.stream().filter(item -> StringUtils.isBlank(item.getPolicyNo())).count() > 0) {
				errorMessage += "保單號碼不得為空。\n";
			}

			if (StringUtils.isNotEmpty(errorMessage)) {
				jsonBean.setData("");
				jsonBean.setMessage(errorMessage);
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}

			List<FriPolicy> resultList = new ArrayList<>();
			for (Rin1301QueryPolicyDetailVOReq param : params) {
				var friPolicyModel = new FriPolicy();
				BeanUtils.copyProperties(param, friPolicyModel);
				List<FriPolicy> result = friPolicyService.queryByFriPolicy(friPolicyModel);
				if (result != null && !result.isEmpty()) {
					resultList.add(result.get(0));
				}
			}

			var mkovseYList = resultList.stream().filter(item -> StringUtils.equals("Y", item.getMkovse()))
					.collect(Collectors.toList());

			var mkovseNList = resultList.stream()
					.filter(item -> StringUtils.equals("N", item.getMkovse()) || StringUtils.isBlank(item.getMkovse()))
					.collect(Collectors.toList());

			if ((mkovseYList != null && !mkovseYList.isEmpty()) && (mkovseNList != null && !mkovseNList.isEmpty())) {
				var errorMsg = "保單號碼";
				for (FriPolicy item : mkovseYList) {
					errorMsg += item.getPolicyNo() + ",";
				}

				errorMsg += ("境外分入註記為Y與其他所選保單境外分入註記不同，請確認");
				jsonBean.setMessage(errorMsg);
			}

			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "臨分資料維護內頁_檢核保批單作業序號是否被使用", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "臨分資料維護內頁_檢核保批單作業序號是否被使用")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/checkPolicyNoSeqExist")
	@ResponseBody
	public ResponseEntity<?> checkPolicyNoSeqExist(@ApiParam(value = "檢核條件") @RequestBody Rin1301HandleDataVOReq param)
			throws Exception {

		log.debug(">>> Rin1301Controller.checkPolicyNoSeqExist( 臨分資料維護內頁_檢核保批單作業序號是否被使用 )");
		JsonBean jsonBean = new JsonBean();
		String errorMessage = "";
		try {

			if (StringUtils.isBlank(param.getCessionNo())) {
				errorMessage += "合約號不得為空。\n";
			}

			if (StringUtils.isBlank(param.getPolicyNoSeq())) {
				errorMessage += "保批單作業序號不得為空。\n";
			}

			if (StringUtils.isNotEmpty(errorMessage)) {
				jsonBean.setMessage(errorMessage);
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}

			var friFacModel = new FriFac();
			friFacModel.setCessionNo(param.getCessionNo());
			friFacModel.setPolicyNoSeq(param.getPolicyNoSeq());

			List<FriFac> results = friFacService.queryByFriFac(friFacModel);

			if (results != null && !results.isEmpty()) {
				jsonBean.setMessage("本合約號+作業序號已被使用, 如要重複使用本作業序號請按 [確定] , 修改為其他號碼請按 [取消]");
			}

			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "臨分資料維護內頁_檢核是否保批單被其他合約使用", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "臨分資料維護內頁_檢核是否保批單被其他合約使用")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/checkPolEndorseByOtherCessionUse")
	@ResponseBody
	public ResponseEntity<?> checkPolEndorseByOtherCessionUse(
			@ApiParam(value = "檢核條件") @RequestBody List<Rin1301QueryPolicyDetailVOReq> params) throws Exception {

		log.debug(">>> Rin1301Controller.checkPolEndorseByOtherCessionUse( 臨分資料維護內頁_檢核是否保批單被其他合約使用 )");
		JsonBean jsonBean = new JsonBean();
		String errorMessage = "";
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		try {

			if (params.stream().filter(item -> StringUtils.isBlank(item.getPolicyNo())).count() > 0) {
				errorMessage += "保單號碼不得為空。\n";
			}

			if (StringUtils.isNotEmpty(errorMessage)) {
				jsonBean.setMessage(errorMessage);
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}

			results = rin1301Service.checkPolEndorseByOtherCessionUse(params);

			List<Rin1301PolicyInfoVOResp> res = results.stream().map(result -> {
				Rin1301PolicyInfoVOResp targetBean = new Rin1301PolicyInfoVOResp();
				BeanUtils.copyProperties(result, targetBean);
				return targetBean;
			}).collect(Collectors.toList());

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "臨分資料維護內頁資料明細查詢 ", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "根據條件查詢臨分資料維護內頁資料明細 ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryPolicyDetail")
	@ResponseBody
	public ResponseEntity<?> queryPolicyDetail(
			@ApiParam(value = "資料明細的查詢條件") @RequestBody List<Rin1301QueryPolicyDetailVOReq> params) throws Exception {

		log.debug(">>> Rin1301Controller.queryPolicyDetail( 臨分資料維護內頁資料明細查詢 )");
		JsonBean jsonBean = new JsonBean();
		try {

			if (params == null || params.isEmpty()) {
				jsonBean.setMessage("請輸入保單號碼");
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}
			List<Rin1301QueryPolicyDetailVOReq> blankPolicyParamList = params.stream()
					.filter(param -> StringUtils.isBlank(param.getPolicyNo())).collect(Collectors.toList());
			if (blankPolicyParamList != null && !blankPolicyParamList.isEmpty()) {
				jsonBean.setMessage("保單號碼不得為空");
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}

			jsonBean = rin1301Service.queryPolicyDetail(params);


		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);

	}

	@ApiOperation(value = "臨分資料維護內頁_標的物代號下拉式選單", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "臨分資料維護內頁_標的物代號下拉式選單")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryPropNoList")
	@ResponseBody
	public ResponseEntity<?> queryPropNoList() throws Exception {
		log.debug(">>> Rin1301Controller.queryPropNoList( 臨分資料維護內頁_標的物代號下拉式選單 )");
		JsonBean jsonBean = new JsonBean();
		List<Rin1301SelectListVOResp> res = new ArrayList<>();

		try {
			List<PropName> results = propNameService.queryAll();
			res = results.stream().map(result -> {
				Rin1301SelectListVOResp respVo = new Rin1301SelectListVOResp();
				respVo.setOptionKey(result.getPropNo());
				respVo.setOptionValue(result.getEname());
				return respVo;
			}).collect(Collectors.toList());

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "臨分資料維護內頁_險種類別下拉式選單", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "臨分資料維護內頁_險種類別下拉式選單")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryPropInsKindList")
	@ResponseBody
	public ResponseEntity<?> queryPropInsKindList() throws Exception {
		log.debug(">>> Rin1301Controller.queryPropInsKindList( 臨分資料維護內頁_險種類別下拉式選單 )");
		JsonBean jsonBean = new JsonBean();
		List<Rin1301SelectListVOResp> res = new ArrayList<>();

		try {
			List<FriKindMapping> results = friKindMappingService.queryinsKindList();

			res = results.stream().map(result -> {
				Rin1301SelectListVOResp respVo = new Rin1301SelectListVOResp();
				respVo.setOptionKey(result.getKindName());
				respVo.setOptionValue(result.getKindId());
				return respVo;
			}).collect(Collectors.toList());

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "臨分資料維護內頁_匯率資料", response = JsonBean.class, tags = { "Rin1301api" }, notes = "臨分資料維護內頁_匯率資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryExChangeList")
	@ResponseBody
	public ResponseEntity<?> queryExChangeList() throws Exception {
		log.debug(">>> Rin1301Controller.queryPropInsKindList( 臨分資料維護內頁_匯率資料 )");
		JsonBean jsonBean = new JsonBean();
		List<Rin1301SelectListVOResp> res = new ArrayList<>();

		try {
			List<Mrexpf> results = mrexpfService.queryByCrtdat();
			res = results.stream().map(result -> {
				Rin1301SelectListVOResp respVo = new Rin1301SelectListVOResp();
				respVo.setOptionKey(result.getCurncy());
				respVo.setOptionValue(result.getExrate().toString());
				return respVo;
			}).collect(Collectors.toList());

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "臨分資料維護內頁再保人明細查詢 ", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "根據條件查詢臨分資料維護內頁再保人明細")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryRinserDetail")
	@ResponseBody
	public ResponseEntity<?> queryRinserDetail(
			@ApiParam(value = "資料明細的查詢條件") @RequestBody Rin1301QueryPolicyDetailVOReq param) throws Exception {

		log.debug(">>> Rin1301Controller.queryPolicyDetail( 臨分資料維護內頁再保人明細查詢 )");
		JsonBean jsonBean = new JsonBean();
		Rin1301QueryPolicyDetailVOResp resVo = new Rin1301QueryPolicyDetailVOResp();
		List<Rin1301QueryRinserData> result = null;
		try {

			if (StringUtils.isBlank(param.getSlipNo())) {
				jsonBean.setMessage("請輸入知會/更正號");
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}
			if (StringUtils.isBlank(param.getLogSeq())) {
				result = rin1301Service.queryRinserDetail(param);
			} else {
				result = rin1301Service.queryRinserDetailForLogSeq(param);
			}

			List<Rin1301QueryRinserDetailSubVoResp> res = result.stream().map(item -> {
				Rin1301QueryRinserDetailSubVoResp targetBean = new Rin1301QueryRinserDetailSubVoResp();
				BeanUtils.copyProperties(item.getFriFacRincom(), targetBean);
				BeanUtils.copyProperties(item, targetBean);
				return targetBean;
			}).collect(Collectors.toList());

			resVo.setRinserDetail(res);
			jsonBean.setData(resVo);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	

	}

	@ApiOperation(value = "臨分資料維護內頁經紀人明細查詢 ", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "根據條件查詢臨分資料維護內頁經紀人明細")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryBrokerDetail")
	@ResponseBody
	public ResponseEntity<?> queryBrokerDetail(
			@ApiParam(value = "資料明細的查詢條件") @RequestBody Rin1301QueryPolicyDetailVOReq param) throws Exception {

		log.debug(">>> Rin1301Controller.queryPolicyDetail( 臨分資料維護內頁經紀人明細查詢 )");
		JsonBean jsonBean = new JsonBean();
		Rin1301QueryPolicyDetailVOResp resVo = new Rin1301QueryPolicyDetailVOResp();
		List<Rin1301QuereyBrokerDetailSubVoResp> res = null;

		try {

			if (StringUtils.isBlank(param.getSlipNo())) {
				jsonBean.setMessage("請輸入知會/更正號");
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}

			if (StringUtils.isBlank(param.getLogSeq())) {
				FriFacBroker model = new FriFacBroker();
				model.setSlipNo(param.getSlipNo());
				List<FriFacBroker> result = friFacBrokerService.queryByFriFacBroker(model);
				res = result.stream().map(item -> {
					Rin1301QuereyBrokerDetailSubVoResp targetBean = new Rin1301QuereyBrokerDetailSubVoResp();
					BeanUtils.copyProperties(item, targetBean);
					return targetBean;
				}).collect(Collectors.toList());
			} else {
				LogFriFacBroker model = new LogFriFacBroker();
				model.setSlipNo(param.getSlipNo());
				List<LogFriFacBroker> result = logFriFacBrokerService.queryByLogFriFacBroker(model);
				res = result.stream().map(item -> {
					Rin1301QuereyBrokerDetailSubVoResp targetBean = new Rin1301QuereyBrokerDetailSubVoResp();
					BeanUtils.copyProperties(item, targetBean);
					return targetBean;
				}).collect(Collectors.toList());
			}

			resVo.setBrokerDetail(res);
			jsonBean.setData(resVo);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "臨分資料維護內頁保批單清單查詢 ", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "臨分資料維護內頁保批單清單查詢 ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryFacSameRiskList")
	@ResponseBody
	public ResponseEntity<?> queryFacSameRiskList(
			@ApiParam(value = "資料明細的查詢條件") @RequestBody Rin1301QueryPolicyDetailVOReq param) throws Exception {

		log.debug(">>> Rin1301Controller.queryFacSameRiskList( 臨分資料維護內頁保批單清單查詢)");
		JsonBean jsonBean = new JsonBean();
		try {

			if (StringUtils.isBlank(param.getSlipNo())) {
				jsonBean.setMessage("請輸入知會/更正號");
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}

			jsonBean = rin1301Service.queryFacSameRiskList(param);
		

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "新增臨分資料 ", response = JsonBean.class, tags = { "Rin1301api" }, notes = "新增臨分資料 ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/createRinData")
	@ResponseBody
	public ResponseEntity<?> createRinData(@ApiParam(value = "新增臨分資料內容") @RequestBody Rin1301HandleDataVOReq param)
			throws Exception {

		log.debug(">>> Rin1301Controller.createRinData(新增臨分資料 )");
		JsonBean jsonBean = new JsonBean();
		try {
			// #todo:檢核

			jsonBean = rin1301Service.createRinData(param);
			

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "查詢修改臨分資料 ", response = JsonBean.class, tags = { "Rin1301api" }, notes = "查詢修改臨分資料 ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryRinData")
	@ResponseBody
	public ResponseEntity<?> queryRinData(
			@ApiParam(value = "查詢修改臨分資料") @RequestBody Rin1301QueryPolicyDetailVOReq param) throws Exception {

		log.debug(">>> Rin1301Controller.queryRinData(查詢修改臨分資料 )");
		JsonBean jsonBean = new JsonBean();
		try {
			// #todo:檢核
			if (StringUtils.isBlank(param.getSlipNo())) {
				jsonBean.setMessage("請輸入知會/更正號");
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}
			if (StringUtils.isBlank(param.getLogSeq())) {
				jsonBean = rin1301Service.queryRinData(param);
			} else {
				jsonBean = rin1301Service.queryRinDataByLogSeq(param);
			}

			

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "修改臨分資料 ", response = JsonBean.class, tags = { "Rin1301api" }, notes = "修改臨分資料內容 ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/editRinData")
	@ResponseBody
	public ResponseEntity<?> editRinData(@ApiParam(value = "修改臨分資料內容") @RequestBody Rin1301HandleDataVOReq param)
			throws Exception {

		log.debug(">>> Rin1301Controller.editRinData(修改臨分資料 )");
		JsonBean jsonBean = new JsonBean();
		try {
			// #todo:檢核
			if (StringUtils.isBlank(param.getSlipNo())) {
				jsonBean.setMessage("請輸入知會/更正號");
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}
			// TODO (待確認是否只有"T"才是insert) 客戶說不是覆寫資料，而是重新 insert 一筆，且修改註記壓02
			if("T".equals(param.getConversionStatus())) {
				param.setLogSeq("02");
			}
			jsonBean = rin1301Service.editRinData(param);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);

	}

	@ApiOperation(value = "刪除臨分資料 ", response = JsonBean.class, tags = { "Rin1301api" }, notes = "刪除臨分資料 ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/delRinData")
	@ResponseBody
	public ResponseEntity<?> delRinData(@ApiParam(value = "刪除臨分資料") @RequestBody Rin1301QueryPolicyDetailVOReq param)
			throws Exception {

		log.debug(">>> Rin1301Controller.editRinData(刪除臨分資料 )");
		JsonBean jsonBean = new JsonBean();
		try {

			if (StringUtils.isBlank(param.getSlipNo())) {
				jsonBean.setMessage("請輸入知會/更正號");
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}
			List<FriAccounting> result = friAccountingService.queryForRin1303No4(param.getSlipNo());

			if (result != null && !result.isEmpty()) {
				var util = new Asiutil();
				var closeDateStr = util.processDateToString(result.get(0).getCloseDate(), "yyyy/MM/dd");
				jsonBean.setMessage("已列印帳單並已轉入會計系統 (轉入時間:" + closeDateStr + "), 不可刪除!");
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);

			}

			jsonBean = rin1301Service.delRinData(param);
			

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "臨分資料維護內頁再保人檢核 ", response = JsonBean.class, tags = {
			"Rin1301api" }, notes = "根據條件檢核臨分資料維護內頁再保人")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/chkRinser")
	@ResponseBody
	public ResponseEntity<?> chkRinser(@ApiParam(value = "須檢核之再保人資料") @RequestBody List<Rin1301FacRinserVoReq> params)
			throws Exception {

		log.debug(">>> Rin1301Controller.chkRinser(臨分資料維護內頁再保人檢核 )");
		var errorMsg = "";
		JsonBean jsonBean = new JsonBean();

		try {
			for(Rin1301FacRinserVoReq param:params) {
				
				if (StringUtils.isBlank(param.getRinComId())) {
					errorMsg += "請輸入再保人代號\n";
				}
				
				if (StringUtils.isBlank(param.getTreatyDBgn())) {
					errorMsg += "請輸入生效起日\n";
				}
			}

			if (!StringUtils.isBlank(errorMsg)) {
				jsonBean.setMessage(errorMsg);
				jsonBean.setStatus(SysEnum.status001.code);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}

			jsonBean = rin1301Service.chkRinser(params);
			
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);

	}

	
	@ApiOperation(value = "計算臨分保額保費 ", response = JsonBean.class, tags = { "Rin1301api" }, notes = "計算臨分保額保費")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/calRinPremAndAmt")
	@ResponseBody
	public ResponseEntity<?> calRinPremAndAmt(@ApiParam(value = "計算臨分保額保費") @RequestBody Rin1301HandleDataVOReq param)
			throws Exception {

		log.debug(">>> Rin1301Controller.calRinPremAndAmt(計算臨分保額保費)");
		JsonBean jsonBean = new JsonBean();
		try {
			// #todo:檢核

			jsonBean = rin1301Service.calRinPremAndAmt(param);
			

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setMessage(e.getMessage());
			jsonBean.setStatus(SysEnum.statusError.code);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);

	}

}
