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
	 * 搜尋「分保同險資料」
	 *
	 * @param treatyYear
	 * @param treatyNo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "搜尋「分保同險資料」", response = JsonBean.class, tags = {
			"Rin1205api" }, notes = "用同險編號、合約年度、有效日期搜尋「分保同險資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/query1205list")
	@ResponseBody
	public ResponseEntity<Object> query1205List(@ApiParam(value = "搜尋「分保同險資料」條件") @RequestBody Rin1205QueryVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1205Controller.query1205List(搜尋「分保同險資料」)");

		JsonBean jsonBean = new JsonBean();

		List<Rin1205VOResp> resultList = new ArrayList<>();
		
		List<Rin1205TableMain> resMain = new ArrayList<>();
		

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

		try {
			CustomerizeMapper customerizeMapper = sqlSession.getMapper(CustomerizeMapper.class);
			FriTreatySharesMapper friTreatySharesMapper = sqlSession.getMapper(FriTreatySharesMapper.class);
			FriFacSharesMapper friFacSharesMapper = sqlSession.getMapper(FriFacSharesMapper.class);
			
			//執行主查詢
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
					result.setTxtpolicy_no(model.getPolicy_no());							//保單號
					
					//處理批單號
					String endorseNo = StringUtils.isNotBlank(model.getEndorse_no())?model.getEndorse_no():"";
					
					result.setTxtendorse_no(endorseNo);										//批單號
					result.setNumaddr_no(model.getAddr_no());								//序號
					result.setDtaduty_bgn(model.getDuty_bgn());								//生效日期起
					result.setDtaduty_end(model.getDuty_end());								//生效日期迄
					result.setTxtuseprop_name(model.getUseprop_name());						//使用性質
					result.setDtapolicy_dprt(model.getPolicy_dprt());						//印單日
					result.setNumamt(model.getAmt().toString());							//總保額
					result.setNumprem(model.getShare_prem().toString());					//總保費
					result.setRiskNo(model.getRisk_no());									//同險編號

					
					//若總保額為0
					if(0 == model.getAmt().compareTo(BigDecimal.ZERO)) {
						
						result.setNumprem_rate("0");   										//保單費率
						result.setNumshare_prem_rate("0");									//分保費率
					//若總保額不為0
					}else {

						//prem/amt*1000
						BigDecimal premRate =model.getPrem().divide(model.getAmt(),7,RoundingMode.HALF_UP).scaleByPowerOfTen(3);					
						result.setNumprem_rate(premRate.toString());  						//保單費率
						BigDecimal sharePremRate = model.getShare_prem().divide(model.getAmt(),7,RoundingMode.HALF_UP).scaleByPowerOfTen(3);					
						result.setNumshare_prem_rate(sharePremRate.toString());				//分保費率
						
					}
					 
					result.setNumlimit(model.getLimit().toString());						//保單限額
					
					
					//-------取得自留--------------------------------------------
					
					modelShares = new FriTreatyShares();
					modelShares.setPolicyNo(model.getPolicy_no());							//保單號碼
					modelShares.setEndorseNo(endorseNo);									//批單號
					modelShares.setAddrNo(Integer.valueOf(model.getAddr_no()));				//地址序號
					modelShares.setTreatyYear(parJson.getTreatyYear());						//合約年度
					modelShares.setRiskNo(model.getRisk_no());								//同險代號
					
					
					resRetain = friTreatySharesService.getRetainResult(modelShares, friTreatySharesMapper);
					
					
					resSub = new Rin1205VORespSub();
					resSub.setTxttreaty_no_i("FAC_Retain");//自留代號
					resSub.setNumshare_amt_i(resRetain.get(0).getShareAmt().toString());	//自留保額
					resSub.setNumshare_prem_i(resRetain.get(0).getSharePrem().toString());	//自留保費
					
					resShare = new ArrayList<>();
					resShare.add(resSub);//將自留資料放入子表格的第一筆資料
					
					//-------取得分出--------------------------------------------					
					resShare.addAll(friTreatySharesService.getShareResult(modelShares, friTreatySharesMapper));
					
					result.setRin1205SubVo(resShare);//子表格資料
					
					//-------取得臨分分保額--------------------------------------------
					resFacShares = new ArrayList<>();

					resFacShares = friFacSharesService.getFacShare(model.getPolicy_no(), endorseNo, model.getAddr_no(), friFacSharesMapper);
					
					
					if(resFacShares.isEmpty()) {
						
						result.setSum_amt_1(BigDecimal.ZERO);								//臨分保額
						result.setNumamt_ear_1(model.getAmt_ear());							//地震自留
						result.setNumamt_typ_1(model.getAmt_typ());							//颱風自留
						result.setNumamt_ear_2(BigDecimal.ZERO);							//地震臨分
						result.setNumamt_typ_2(BigDecimal.ZERO);							//颱風臨分				
						
					}else {
						
						result.setSum_amt_1(resFacShares.get(0).getFac_amt());				//臨分保額
						BigDecimal amtEar1 = model.getAmt_ear().subtract(resFacShares.get(0).getFac_ear_share());
						result.setNumamt_ear_1(amtEar1);									//地震自留
						BigDecimal amtTyp1 = model.getAmt_typ().subtract(resFacShares.get(0).getFac_typ_share());
						result.setNumamt_typ_1(amtTyp1);									//颱風自留
						result.setNumamt_ear_2(resFacShares.get(0).getFac_ear_share());		//地震臨分
						result.setNumamt_typ_2(resFacShares.get(0).getFac_typ_share());		//颱風臨分
					}
					
					result.setTxtacct_flag(model.getAcct_flag());							//帳單註記
					result.setTxtiendorsement2(model.getIendorsement2());					//原批單號
				
					
					result.setTreaty_year(model.getTreaty_year());							//合約年度
					
					//「"pk"+用保單號+合約年度+批單號+地址序號」 當id(前端子表格用，因資料沒有id)
					if(StringUtils.isNotBlank(endorseNo)) {						
						result.setPrimaryKey("pk"+model.getPolicy_no()+model.getTreaty_year()+model.getEndorse_no()+model.getAddr_no());
					}else {
						result.setPrimaryKey("pk"+model.getPolicy_no()+model.getTreaty_year()+model.getAddr_no());
					}
				
					
					resultList.add(result);
				}
			}
			
			sqlSession.commit();
			// 回傳結果
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
	 * 修改「合約的保額、保費」
	 *
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "修改「合約的保額、保費」", response = JsonBean.class, tags = {
			"Rin1205api" }, notes = "修改保單所有的合約保額與保費")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updateshareamtprem")
	@ResponseBody
	public ResponseEntity<Object> updateShareAmtPrem(@ApiParam(value = "修改合約保額與保費的條件與內容") @RequestBody List<Rin1205UpdateShareAmtPremVOReq> parJson)
			throws Exception {

		log.debug(">>> Rin1205Controller.updateShareAmtPrem(修改「合約的保額、保費」)");

		JsonBean jsonBean = new JsonBean();
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

		int res = 0;
		
		try {
			FriTreatySharesMapper friTreatySharesMapper = sqlSession.getMapper(FriTreatySharesMapper.class);
			
			for(Rin1205UpdateShareAmtPremVOReq model : parJson) {
				Rin1205UpdateShareAmtPremVOReq updateModel = new Rin1205UpdateShareAmtPremVOReq();
				
				updateModel.setTxtpolicy_no(model.getTxtpolicy_no());								//保單號
				if(StringUtils.isNotBlank(model.getTxtendorse_no())) {					
					updateModel.setTxtendorse_no(model.getTxtendorse_no());							//批單號
				}
				updateModel.setNumaddr_no(model.getNumaddr_no());									//序號
				updateModel.setTreaty_year(model.getTreaty_year());									//合約年度
				updateModel.setRiskNo(model.getRiskNo());											//同險代號
				
				for(Rin1205VORespSub subModel: model.getRin1205SubVo()) {
					if(StringUtils.equals(subModel.getTxttreaty_no_i(), "FAC_Retain")) {			//合約代號-自留
						updateModel.setTreaty_no("Retain");
					}else {						
						updateModel.setTreaty_no(subModel.getTxttreaty_no_i());						//合約代號-分保
					}
					
					updateModel.setNumshare_amt_0(Long.valueOf(subModel.getNumshare_amt_i()));		//保額
					updateModel.setNumshare_prem_0(Long.valueOf(subModel.getNumshare_prem_i()));	//保費
						
					res += friTreatySharesService.updateShareAmtPrem(updateModel, friTreatySharesMapper);
				}
				
				
			}
			
			sqlSession.commit();
			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);

		} catch (Exception e) {

			sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("修改失敗!");
		}finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}


}
