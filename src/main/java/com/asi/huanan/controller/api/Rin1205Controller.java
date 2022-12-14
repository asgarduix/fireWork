package com.asi.huanan.controller.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

import com.asi.huanan.service.FriFacSharesService;
import com.asi.huanan.service.FriTreatySharesService;
import com.asi.huanan.service.customerize.CustomerizeService;
import com.asi.huanan.service.dao.mybatis.mapper.FriFacSharesMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriTreatySharesMapper;
import com.asi.huanan.service.dao.mybatis.mapper.customerize.CustomerizeMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyShares;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1205TableFacShare;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1205TableMain;
import com.asi.huanan.vo.Rin1205QueryVOReq;
import com.asi.huanan.vo.Rin1205UpdateShareAmtPremVOReq;
import com.asi.huanan.vo.Rin1205VOResp;
import com.asi.huanan.vo.Rin1205VORespSub;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1205api")
@RestController
@Api(tags = { "Rin1205api" })
public class Rin1205Controller {

	private static Logger log = LogManager.getLogger(Rin1205Controller.class);

	@Autowired
	private CustomerizeService customerizeService;
	@Autowired
	private FriTreatySharesService friTreatySharesService;
	@Autowired
	private FriFacSharesService friFacSharesService;
	@Autowired
	private MyBatisConnector mybatis;

	/**
	 * ??????????????????????????????
	 *
	 * @param treatyYear
	 * @param treatyNo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "??????????????????????????????", response = JsonBean.class, tags = {
			"Rin1205api" }, notes = "???????????????????????????????????????????????????????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/query1205list")
	@ResponseBody
	public ResponseEntity<Object> query1205List(@ApiParam(value = "????????????????????????????????????") @RequestBody Rin1205QueryVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1205Controller.query1205List(??????????????????????????????)");

		JsonBean jsonBean = new JsonBean();

		List<Rin1205VOResp> resultList = new ArrayList<>();
		
		List<Rin1205TableMain> resMain = new ArrayList<>();
		

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

		try {
			CustomerizeMapper customerizeMapper = sqlSession.getMapper(CustomerizeMapper.class);
			FriTreatySharesMapper friTreatySharesMapper = sqlSession.getMapper(FriTreatySharesMapper.class);
			FriFacSharesMapper friFacSharesMapper = sqlSession.getMapper(FriFacSharesMapper.class);
			
			//???????????????
			resMain = customerizeService.queryRin1205MainData(parJson, customerizeMapper);
			
			if(!resMain.isEmpty()) {
				
				Rin1205VOResp result = null;
				FriTreatyShares  modelShares = null;
				List<FriTreatyShares> resRetain = null;
				Rin1205VORespSub resSub = null;
				List<Rin1205VORespSub> resShare = null;
				List<Rin1205TableFacShare> resFacShares = null;
				
				for(Rin1205TableMain model: resMain) {
					result = new Rin1205VOResp();
					result.setTxtpolicy_no(model.getPolicy_no());							//?????????
					
					//???????????????
					String endorseNo = StringUtils.isNotBlank(model.getEndorse_no())?model.getEndorse_no():"";
					
					result.setTxtendorse_no(endorseNo);										//?????????
					result.setNumaddr_no(model.getAddr_no());								//??????
					result.setDtaduty_bgn(model.getDuty_bgn());								//???????????????
					result.setDtaduty_end(model.getDuty_end());								//???????????????
					result.setTxtuseprop_name(model.getUseprop_name());						//????????????
					result.setDtapolicy_dprt(model.getPolicy_dprt());						//?????????
					result.setNumamt(model.getAmt().toString());							//?????????
					result.setNumprem(model.getShare_prem().toString());					//?????????
					result.setRiskNo(model.getRisk_no());									//????????????

					
					//???????????????0
					if(0 == model.getAmt().compareTo(BigDecimal.ZERO)) {
						
						result.setNumprem_rate("0");   										//????????????
						result.setNumshare_prem_rate("0");									//????????????
					//??????????????????0
					}else {

						//prem/amt*1000
						BigDecimal premRate =model.getPrem().divide(model.getAmt(),7,RoundingMode.HALF_UP).scaleByPowerOfTen(3);					
						result.setNumprem_rate(premRate.toString());  						//????????????
						BigDecimal sharePremRate = model.getShare_prem().divide(model.getAmt(),7,RoundingMode.HALF_UP).scaleByPowerOfTen(3);					
						result.setNumshare_prem_rate(sharePremRate.toString());				//????????????
						
					}
					 
					result.setNumlimit(model.getLimit().toString());						//????????????
					
					
					//-------????????????--------------------------------------------
					
					modelShares = new FriTreatyShares();
					modelShares.setPolicyNo(model.getPolicy_no());							//????????????
					modelShares.setEndorseNo(endorseNo);									//?????????
					modelShares.setAddrNo(Integer.valueOf(model.getAddr_no()));				//????????????
					modelShares.setTreatyYear(parJson.getTreatyYear());						//????????????
					modelShares.setRiskNo(model.getRisk_no());								//????????????
					
					
					resRetain = friTreatySharesService.getRetainResult(modelShares, friTreatySharesMapper);
					
					
					resSub = new Rin1205VORespSub();
					resSub.setTxttreaty_no_i("FAC_Retain");//????????????
					resSub.setNumshare_amt_i(resRetain.get(0).getShareAmt().toString());	//????????????
					resSub.setNumshare_prem_i(resRetain.get(0).getSharePrem().toString());	//????????????
					
					resShare = new ArrayList<>();
					resShare.add(resSub);//????????????????????????????????????????????????
					
					//-------????????????--------------------------------------------					
					resShare.addAll(friTreatySharesService.getShareResult(modelShares, friTreatySharesMapper));
					
					result.setRin1205SubVo(resShare);//???????????????
					
					//-------?????????????????????--------------------------------------------
					resFacShares = new ArrayList<>();

					resFacShares = friFacSharesService.getFacShare(model.getPolicy_no(), endorseNo, model.getAddr_no(), friFacSharesMapper);
					
					
					if(resFacShares.isEmpty()) {
						
						result.setSum_amt_1(BigDecimal.ZERO);								//????????????
						result.setNumamt_ear_1(model.getAmt_ear());							//????????????
						result.setNumamt_typ_1(model.getAmt_typ());							//????????????
						result.setNumamt_ear_2(BigDecimal.ZERO);							//????????????
						result.setNumamt_typ_2(BigDecimal.ZERO);							//????????????				
						
					}else {
						
						result.setSum_amt_1(resFacShares.get(0).getFac_amt());				//????????????
						BigDecimal amtEar1 = model.getAmt_ear().subtract(resFacShares.get(0).getFac_ear_share());
						result.setNumamt_ear_1(amtEar1);									//????????????
						BigDecimal amtTyp1 = model.getAmt_typ().subtract(resFacShares.get(0).getFac_typ_share());
						result.setNumamt_typ_1(amtTyp1);									//????????????
						result.setNumamt_ear_2(resFacShares.get(0).getFac_ear_share());		//????????????
						result.setNumamt_typ_2(resFacShares.get(0).getFac_typ_share());		//????????????
					}
					
					result.setTxtacct_flag(model.getAcct_flag());							//????????????
					result.setTxtiendorsement2(model.getIendorsement2());					//????????????
				
					
					result.setTreaty_year(model.getTreaty_year());							//????????????
					
					//???"pk"+????????????+????????????+?????????+??????????????? ???id(????????????????????????????????????id)
					if(StringUtils.isNotBlank(endorseNo)) {						
						result.setPrimaryKey("pk"+model.getPolicy_no()+model.getTreaty_year()+model.getEndorse_no()+model.getAddr_no());
					}else {
						result.setPrimaryKey("pk"+model.getPolicy_no()+model.getTreaty_year()+model.getAddr_no());
					}
				
					
					resultList.add(result);
				}
			}
			
			sqlSession.commit();
			// ????????????
			jsonBean.setData(resultList);
			jsonBean.setStatus(SysEnum.statusSuccess.code);

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
	 * ????????????????????????????????????
	 *
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "????????????????????????????????????", response = JsonBean.class, tags = {
			"Rin1205api" }, notes = "??????????????????????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updateshareamtprem")
	@ResponseBody
	public ResponseEntity<Object> updateShareAmtPrem(@ApiParam(value = "?????????????????????????????????????????????") @RequestBody List<Rin1205UpdateShareAmtPremVOReq> parJson)
			throws Exception {

		log.debug(">>> Rin1205Controller.updateShareAmtPrem(????????????????????????????????????)");

		JsonBean jsonBean = new JsonBean();
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

		int res = 0;
		
		try {
			FriTreatySharesMapper friTreatySharesMapper = sqlSession.getMapper(FriTreatySharesMapper.class);
			
			for(Rin1205UpdateShareAmtPremVOReq model : parJson) {
				Rin1205UpdateShareAmtPremVOReq updateModel = new Rin1205UpdateShareAmtPremVOReq();
				
				updateModel.setTxtpolicy_no(model.getTxtpolicy_no());								//?????????
				if(StringUtils.isNotBlank(model.getTxtendorse_no())) {					
					updateModel.setTxtendorse_no(model.getTxtendorse_no());							//?????????
				}
				updateModel.setNumaddr_no(model.getNumaddr_no());									//??????
				updateModel.setTreaty_year(model.getTreaty_year());									//????????????
				updateModel.setRiskNo(model.getRiskNo());											//????????????
				
				for(Rin1205VORespSub subModel: model.getRin1205SubVo()) {
					if(StringUtils.equals(subModel.getTxttreaty_no_i(), "FAC_Retain")) {			//????????????-??????
						updateModel.setTreaty_no("Retain");
					}else {						
						updateModel.setTreaty_no(subModel.getTxttreaty_no_i());						//????????????-??????
					}
					
					updateModel.setNumshare_amt_0(Long.valueOf(subModel.getNumshare_amt_i()));		//??????
					updateModel.setNumshare_prem_0(Long.valueOf(subModel.getNumshare_prem_i()));	//??????
						
					res += friTreatySharesService.updateShareAmtPrem(updateModel, friTreatySharesMapper);
				}
				
				
			}
			
			sqlSession.commit();
			//????????????
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);

		} catch (Exception e) {

			sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("????????????!");
		}finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}


}
