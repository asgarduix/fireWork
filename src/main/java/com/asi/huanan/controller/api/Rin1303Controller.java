package com.asi.huanan.controller.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
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

import com.asi.huanan.service.FriAccountingService;
import com.asi.huanan.service.FriFacRincomService;
import com.asi.huanan.service.FriFacService;
import com.asi.huanan.service.customerize.CustomerizeService;
import com.asi.huanan.service.customerize.Rin1303Service;
import com.asi.huanan.service.dao.mybatis.mapper.FriAccountingMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacRincomMapper;
import com.asi.huanan.vo.Rin1303PrintVOReq;
import com.asi.huanan.vo.Rin1303QueryCesNoVOReq;
import com.asi.huanan.vo.Rin1303QueryCesNoVOResp;
import com.asi.huanan.vo.Rin1303QueryRinComBySlipNoVOReq;
import com.asi.huanan.vo.Rin1303QueryRinComBySlipNoVOResp;
import com.asi.huanan.vo.Rin1303UpdateStatusByNoIdVOReq;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1303api")
@RestController
@Api(tags = { "Rin1303api" })
public class Rin1303Controller {

	private static Logger log = LogManager.getLogger(Rin1303Controller.class);

	@Autowired
	private FriFacService friFacService;
	@Autowired
	private CustomerizeService customerizeService;
	@Autowired
	private FriFacRincomService friFacRincomService;
	@Autowired
	private Rin1303Service rin1303Service;
	@Autowired
	private FriAccountingService friAccountingService;
	@Autowired
	private MyBatisConnector mybatis;

	
	/**
	 * ???????????????????????????
	 *
	 * @param cessionNO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "???????????????????????????", response = JsonBean.class, tags = {
			"Rin1303api" }, notes = "???????????????????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querycessionnolist")
	@ResponseBody
	public ResponseEntity<Object> queryCessionNoList(@ApiParam(value = "???????????????") @RequestBody Rin1303QueryCesNoVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1303Controller.queryCessionNoList(???????????????????????????)");

		JsonBean jsonBean = new JsonBean();

		List<Rin1303QueryCesNoVOResp> res = new ArrayList<>();

		try {
			
			res = friFacService.queryCessionNoList(parJson.getCessionNo());

			// ????????????
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
	 * ?????????????????????????????????
	 *
	 * @param slipNo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "?????????????????????????????????", response = JsonBean.class, tags = {
			"Rin1303api" }, notes = "?????????????????????????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryrincombyslipno")
	@ResponseBody
	public ResponseEntity<Object> queryRinComBySlipNo(@ApiParam(value = "?????????") @RequestBody Rin1303QueryRinComBySlipNoVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1303Controller.queryRinComBySlipNo(?????????????????????????????????)");

		JsonBean jsonBean = new JsonBean();

		List<Rin1303QueryRinComBySlipNoVOResp> res = new ArrayList<>();

		try {
			
			res = customerizeService.queryRinComBySlipNo(parJson.getSlipNo());
			
			//???????????????????????????????????????
			for(Rin1303QueryRinComBySlipNoVOResp model: res) {
				//????????????
				if(StringUtils.isNotBlank(model.getTxtacct_flag())) {
					switch(model.getTxtacct_flag()) {
					case "Y":
						model.setTxtacct_flag("Y:?????????");
						break;
					case "N":
						model.setTxtacct_flag("N:?????????");
						break;
					default:
						break;
					}
				}
				//????????????
				if(StringUtils.isNotBlank(model.getTxtTurn_flag())) {
					switch(model.getTxtTurn_flag()){
						case "N":
							model.setTxtTurn_flag("N:?????????");
							break;
						case "H":
							model.setTxtTurn_flag("H:??????");
							break;
						case "Y":
							model.setTxtTurn_flag("Y:????????????");
							break;
						default:
							break;
					}
				}
			}

			// ????????????
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
	 * ??????????????????????????????
	 *
	 * @param bill_no_external
	 * @param transfer_status
	 * @param slip_no
	 * @param rin_com_id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "??????????????????????????????", response = JsonBean.class, tags = {
			"Rin1303api" }, notes = "?????????????????????????????????????????????????????????????????????????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatestatusbynoid")
	@ResponseBody
	public ResponseEntity<Object> updateStatusByNoId(@ApiParam(value = "??????????????????") @RequestBody Rin1303UpdateStatusByNoIdVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1303Controller.updateStatusByNoId(??????????????????????????????)");

		JsonBean jsonBean = new JsonBean();

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int result = 0;
		
		try {		
			FriFacRincomMapper friFacRincomMapper = sqlSession.getMapper(FriFacRincomMapper.class);
			FriAccountingMapper friAccountingMapper = sqlSession.getMapper(FriAccountingMapper.class);
			
			//??????fri_fac_rincom ????????????
			result = friFacRincomService.updateStatusByNoId(parJson, friFacRincomMapper);
			
			//?????????????????????????????????"???(N)"???"??????(H)"????????????fri_accounting????????????
			if(result != 0 && StringUtils.equalsAny(parJson.getTransferStatus(), "N","H")) {
				friAccountingService.deleteChangeStatusRin1303(parJson.getSlipNo(), friAccountingMapper);
			}
			
			sqlSession.commit();

			// ????????????
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("?????????"+result+"?????????");

		} catch (Exception e) {
			sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);

		}finally {
			sqlSession.close();
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	/**
	 * ????????????
	 *
	 * @param bill_no_external
	 * @param transfer_status
	 * @param slip_no
	 * @param rin_com_id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "????????????", response = JsonBean.class, tags = {
			"Rin1303api" }, notes = "????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/printrin1303report")
	@ResponseBody
	public ResponseEntity<Object> printRin1303Report(@ApiParam(value = "????????????") @RequestBody Rin1303PrintVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1303Controller.printRin1303Report(????????????)");

		JsonBean jsonBean = new JsonBean();
		
		try {		
			//????????????
			jsonBean = rin1303Service.rin1303PrintPdf(parJson);

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setMessage("??????????????????!!!?????????????????????!!!");
			jsonBean.setStatus(SysEnum.statusError.code);

		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}


}
