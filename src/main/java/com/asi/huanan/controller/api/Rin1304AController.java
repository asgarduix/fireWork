package com.asi.huanan.controller.api;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
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

import com.asi.huanan.service.FriPolicyAdditionService;
import com.asi.huanan.service.FriPolicyDtlService;
import com.asi.huanan.service.FriPolicyPropService;
import com.asi.huanan.service.FriPolicyService;
import com.asi.huanan.service.FriTreatySharesService;
import com.asi.huanan.service.MrexpfService;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyAdditionMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyDtlMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyPropMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriTreatySharesMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicy;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyDtl;
import com.asi.huanan.service.dao.mybatis.model.Mrexpf;
import com.asi.huanan.vo.Rin1304CheckAmtPremRespVO;
import com.asi.huanan.vo.Rin1304CreateADJPolicyVO;
import com.asi.huanan.vo.Rin1304DeletePolicyAllVORes;
import com.asi.huanan.vo.Rin1304FriPolicyVOResp;
import com.asi.huanan.vo.Rin1304GetAddrNoVO;
import com.asi.huanan.vo.Rin1304InsertByOldPolicy;
import com.asi.huanan.vo.Rin1304QueryAcctFlagRespVO;
import com.asi.huanan.vo.Rin1304QueryPolicyPropVO;
import com.asi.huanan.vo.Rin1304SumAmtPremRespVO;
import com.asi.huanan.vo.Rin1304VO;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1304aapi")
@RestController
@Api(tags = { "Rin1304Aapi" })
public class Rin1304AController {

	private static Logger log = LogManager.getLogger(Rin1304AController.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private FriPolicyService friPolicyService;

	@Autowired
	private FriPolicyDtlService friPolicyDtlService;

	@Autowired
	private FriPolicyPropService friPolicyPropService;

	@Autowired
	private FriPolicyAdditionService friPolicyAdditionService;

	@Autowired
	private FriTreatySharesService friTreatySharesService;
	
	@Autowired
	private MrexpfService mrexpfService;

	@Autowired
	private MyBatisConnector mybatis;

	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」做為保批單主檔查詢結果
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "保批單主檔查詢結果", response = JsonBean.class, tags = {
			"Rin1304Aapi" }, notes = "「保單號碼」﹑「批單號碼」保批單主檔查詢結果")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querypolicy")
	@ResponseBody
	public ResponseEntity<?> queryPolicyByPrimaryKey(@ApiParam(value = "保批單主檔查詢結果") @RequestBody Rin1304VO parJson)
			throws Exception {
		log.debug(">>> Rin1304AController.queryPolicyByPrimaryKey(保批單主檔查詢結果)");
		JsonBean jsonBean = new JsonBean();

		List<Rin1304FriPolicyVOResp> res = new ArrayList<>();
		List<Rin1304FriPolicyVOResp> res2 = new ArrayList<>();
		Rin1304FriPolicyVOResp result = null;
		try {

			String policyNoQuery = parJson.getPolicyNo(); // 保單號碼頁面查詢
			String endorseNoQuery = parJson.getEndorseNo(); // 批單號碼頁面查詢

			res = friPolicyService.queryPolicyByPrimaryKey(policyNoQuery, endorseNoQuery);

			// 查詢共保累積保額和共保累積保費
			res2 = friPolicyService.querySumAllAmtPremByPolicyNo(policyNoQuery);

			if (!res.isEmpty()) {
				result = new Rin1304FriPolicyVOResp();

				// 將資料複製
				BeanUtils.copyProperties(res.get(0), result);

				BigDecimal amt1Eq = BigDecimal.ZERO; // 共保保額地震險
				BigDecimal amt2Ty = BigDecimal.ZERO; // 共保保額颱風洪水險
				BigDecimal amt3Bi = BigDecimal.ZERO; // 共保保額營業中斷險

				BigDecimal amt1EqHn = BigDecimal.ZERO; // 華南保額地震險
				BigDecimal amt2TyHn = BigDecimal.ZERO; // 華南保額颱風洪水險
				BigDecimal amt3BiHn = BigDecimal.ZERO; // 華南保額營業中斷險

				BigDecimal prem1Reins = BigDecimal.ZERO; // 共保保費火險及其他附加險
				BigDecimal prem2Eq = BigDecimal.ZERO; // 共保保費地震險
				BigDecimal prem3Ty = BigDecimal.ZERO; // 共保保費颱風洪水險
				BigDecimal prem4Bi = BigDecimal.ZERO; // 共保保費營業中斷險

				BigDecimal prem1ReinsHn = BigDecimal.ZERO; // 華南保費火險及其他附加險
				BigDecimal prem2EqHn = BigDecimal.ZERO; // 華南保費地震險
				BigDecimal prem3TyHn = BigDecimal.ZERO; // 華南保費颱風洪水險
				BigDecimal prem4BiHn = BigDecimal.ZERO; // 華南保費營業中斷險
				double zerofive = 0.5;
				int hundred = 100;
				BigDecimal addZerofive = BigDecimal.valueOf(zerofive); // 計算用加0.5

				for (Rin1304FriPolicyVOResp model : res) {
					String additionNo = model.getNumAddition_no(); // 附加險代號

					BigDecimal coinsRate = model.getTxtcoins_rate(); // 共保比率
					int coinsRate2 = coinsRate.intValue(); // 共保比率(整數)

					BigDecimal amt = model.getNumamt(); // 保額
					BigDecimal prem = model.getNumprem(); // 保費
					BigDecimal commRate = model.getNumcomm_rate(); // 佣金率
					BigDecimal prepayRate = model.getNumprepay_rate(); // 預收比率

					BigDecimal countAmt = null; // (保額/共保比率)+0.5
					BigDecimal countPrem = null; // (保費/共保比率)+0.5
					BigDecimal countAmtInt = null; // (保額/共保比率)+0.5 取整數
					BigDecimal countPremInt = null; // (保費/共保比率)+0.5 取整數
					BigDecimal countAmt2 = null; // (保額/共保比率)
					BigDecimal countPrem2 = null; // (保費/共保比率)
					BigDecimal divideHundred = new BigDecimal(hundred); // /100
					BigDecimal countCoinsRate = model.getTxtcoins_rate().divide(divideHundred); // 共保比率/100
					if (amt == null) {
						model.setNumamt(new BigDecimal(0));
						countAmt = new BigDecimal(0);
					} else {
						amt = model.getNumamt();
						countAmt = amt.divide(countCoinsRate, 1, RoundingMode.HALF_UP).add(addZerofive); // (保額/共保比率)+0.5
						countAmtInt = countAmt.setScale(0, RoundingMode.HALF_UP); // (保額/共保比率)+0.5 取整數
						countAmt2 = amt.divide(countCoinsRate, 0, RoundingMode.HALF_UP);

					}

					if (prem == null) {
						model.setNumprem(new BigDecimal(0));
						countPrem = new BigDecimal(0);
					} else {
						prem = model.getNumprem();
						countPrem = prem.divide(countCoinsRate, 1, RoundingMode.HALF_UP).add(addZerofive); // (保費/共保比率)+0.5
						countPremInt = countPrem.setScale(0, RoundingMode.HALF_UP); // (保費/共保比率)+0.5 取整數
						countPrem2 = prem.divide(countCoinsRate, 0, RoundingMode.HALF_UP);

					}

					if (StringUtils.isNotBlank(additionNo)) {
						String subAdditionNo = additionNo.substring(0, 1);
						if (subAdditionNo.equals("2")) {
//							if (coinsRate2 == hundred || coinsRate2 == 0) {
//
//								amt1Eq = amt1Eq.add(countAmt2);
//								result.setNumamt_1_eq(amt1Eq);        // 共保保額地震險
//
//								amt1EqHn = amt1EqHn.add(amt);
//								result.setNumamt_1_eq_hn(amt1EqHn);   // 華南保額地震險
//
//								prem2Eq = prem2Eq.add(countPrem2);
//								result.setNumprem_2_eq(prem2Eq);      // 共保保費地震險
//
//								prem2EqHn = prem2EqHn.add(prem);
//								result.setNumprem_2_eq_hn(prem2EqHn); // 華南保費地震險
//
//							} else {
							amt1Eq = amt1Eq.add(countAmt2);
							result.setNumamt_1_eq(amt1Eq); // 共保保額地震險

							amt1EqHn = amt1EqHn.add(amt);
							result.setNumamt_1_eq_hn(amt1EqHn); // 華南保額地震險

							prem2Eq = prem2Eq.add(prem);
							result.setNumprem_2_eq(prem2Eq); // 共保保費地震險

							prem2EqHn = prem2EqHn.add(prem);
							result.setNumprem_2_eq_hn(prem2EqHn); // 華南保費地震險

//								amt1Eq = amt1Eq.add(countAmtInt);
//								result.setNumamt_1_eq(amt1Eq);        // 共保保額地震險
//
//								amt1EqHn = amt1EqHn.add(amt);
//								result.setNumamt_1_eq_hn(amt1EqHn);   // 華南保額地震險
//
//								prem2Eq = prem2Eq.add(countPremInt);
//								result.setNumprem_2_eq(prem2Eq);      // 共保保費地震險
//
//								prem2EqHn = prem2EqHn.add(prem);
//								result.setNumprem_2_eq_hn(prem2EqHn); // 華南保費地震險
//							}

						} else if (subAdditionNo.equals("3")) {
//							if (coinsRate2 == hundred || coinsRate2 == 0) {
//								amt2Ty = amt2Ty.add(countAmt2);
//								result.setNumamt_2_ty(amt2Ty);        // 共保保額颱風洪水險
//
//								amt2TyHn = amt2TyHn.add(amt);
//								result.setNumamt_2_ty_hn(amt2TyHn);   // 華南保額颱風洪水險
//
//								prem3Ty = prem3Ty.add(countPrem2);
//								result.setNumprem_3_ty(prem3Ty);      // 共保保費颱風洪水險
//
//								prem3TyHn = prem3TyHn.add(prem);
//								result.setNumprem_3_ty_hn(prem3TyHn); // 華南保費颱風洪水險
//							} else {
							amt2Ty = amt2Ty.add(countAmt2);
							result.setNumamt_2_ty(amt2Ty); // 共保保額颱風洪水險

							amt2TyHn = amt2TyHn.add(amt);
							result.setNumamt_2_ty_hn(amt2TyHn); // 華南保額颱風洪水險

							prem3Ty = prem3Ty.add(prem);
							result.setNumprem_3_ty(prem3Ty); // 共保保費颱風洪水險

							prem3TyHn = prem3TyHn.add(prem);
							result.setNumprem_3_ty_hn(prem3TyHn); // 華南保費颱風洪水險
//								
//								amt2Ty = amt2Ty.add(countAmtInt);
//								result.setNumamt_2_ty(amt2Ty);        // 共保保額颱風洪水險
//
//								amt2TyHn = amt2TyHn.add(amt);
//								result.setNumamt_2_ty_hn(amt2TyHn);   // 華南保額颱風洪水險
//
//								prem3Ty = prem3Ty.add(countPremInt);
//								result.setNumprem_3_ty(prem3Ty);      // 共保保費颱風洪水險
//
//								prem3TyHn = prem3TyHn.add(prem);
//								result.setNumprem_3_ty_hn(prem3TyHn); // 華南保費颱風洪水險
//							}

						} else if (subAdditionNo.equals("8") && !additionNo.equals("82") && !additionNo.equals("83")) {

//							if (coinsRate2 == hundred || coinsRate2 == 0) {
//								amt3Bi = amt3Bi.add(countAmt2);
//								result.setNumamt_3_bi(amt3Bi);        // 共保保額營業中斷險
//
//								amt3BiHn = amt3BiHn.add(amt);
//								result.setNumamt_3_bi_hn(amt3BiHn);   // 華南保額營業中斷險
//
//								prem4Bi = prem4Bi.add(countPrem2);
//								result.setNumprem_4_bi(prem4Bi);      // 共保保費營業中斷險
//
//								prem4BiHn = prem4BiHn.add(prem);
//								result.setNumprem_4_bi_hn(prem4BiHn); // 華南保額營業中斷險
//							} else {
							amt3Bi = amt3Bi.add(countAmt2);
							result.setNumamt_3_bi(amt3Bi); // 共保保額營業中斷險

							amt3BiHn = amt3BiHn.add(amt);
							result.setNumamt_3_bi_hn(amt3BiHn); // 華南保額營業中斷險

							prem4Bi = prem4Bi.add(prem);
							result.setNumprem_4_bi(prem4Bi); // 共保保費營業中斷險

							prem4BiHn = prem4BiHn.add(prem);
							result.setNumprem_4_bi_hn(prem4BiHn); // 華南保額營業中斷
							
							
							

//								amt3Bi = amt3Bi.add(countAmtInt);
//								result.setNumamt_3_bi(amt3Bi);        // 共保保額營業中斷險
//
//								amt3BiHn = amt3BiHn.add(amt);
//								result.setNumamt_3_bi_hn(amt3BiHn);   // 華南保額營業中斷險
//
//								prem4Bi = prem4Bi.add(countPremInt);
//								result.setNumprem_4_bi(prem4Bi);      // 共保保費營業中斷險
//
//								prem4BiHn = prem4BiHn.add(prem);
//								result.setNumprem_4_bi_hn(prem4BiHn); // 華南保額營業中斷險
//							}

						} else if (!subAdditionNo.equals("2") && !subAdditionNo.equals("3")
								&& !subAdditionNo.equals("8")) {

//							if (coinsRate2 == hundred || coinsRate2 == 0) {
//								prem1Reins = prem1Reins.add(countPrem2);
//								result.setNumprem_1_reins(prem1Reins);      // 共保保費火險及其他附加險
//
//								prem1ReinsHn = prem1ReinsHn.add(prem);
//								result.setNumprem_1_reins_hn(prem1ReinsHn); // 華南保費火險及其他附加險
//							} else {
							prem1Reins = prem1Reins.add(prem);

							result.setNumprem_1_reins(prem1Reins); // 共保保費火險及其他附加險

							prem1ReinsHn = prem1ReinsHn.add(prem);
							result.setNumprem_1_reins_hn(prem1ReinsHn); // 華南保費火險及其他附加險
//								prem1Reins = prem1Reins.add(countPremInt);
//								result.setNumprem_1_reins(prem1Reins);      // 共保保費火險及其他附加險
//
//								prem1ReinsHn = prem1ReinsHn.add(prem);
//								result.setNumprem_1_reins_hn(prem1ReinsHn); // 華南保費火險及其他附加險
//							}

						} else if (additionNo.equals("82")) {
							prem2Eq = prem2Eq.add(prem);
							result.setNumprem_2_eq(prem2Eq); // 共保保費地震險

							prem2EqHn = prem2EqHn.add(prem);
							result.setNumprem_2_eq_hn(prem2EqHn); // 華南保費地震險

						} else if (additionNo.equals("83")) {
							prem3Ty = prem3Ty.add(prem);
							result.setNumprem_3_ty(prem3Ty); // 共保保費颱風洪水險

							prem3TyHn = prem3TyHn.add(prem);
							result.setNumprem_3_ty_hn(prem3TyHn); // 華南保費颱風洪水險
						}
					}
					result.setNumprem_1_reins(prem1Reins.divide(countCoinsRate, 0, RoundingMode.HALF_UP)); // 共保保費火險及其他附加險
					result.setNumprem_2_eq(prem2Eq.divide(countCoinsRate, 0, RoundingMode.HALF_UP)); // 共保保費地震險
					result.setNumprem_3_ty(prem3Ty.divide(countCoinsRate, 0, RoundingMode.HALF_UP)); // 共保保費颱風洪水險
					result.setNumprem_4_bi(prem4Bi.divide(countCoinsRate, 0, RoundingMode.HALF_UP)); // 共保保費營業中斷險
				}

//				result.setNumall_amt(res.get(0).getNumall_amt());
//				result.setNumall_prem(res.get(0).getNumall_prem());

//				result.setNumamt_1_eq(amt1Eq.add(addZerofive));
//				result.setNumprem_2_eq(prem2Eq.add(addZerofive));
//				result.setNumamt_2_ty(amt2Ty.add(addZerofive));        // 共保保額颱風洪水險
//				result.setNumprem_3_ty(prem3Ty.add(addZerofive));      // 共保保費颱風洪水險
//				result.setNumamt_3_bi(amt3Bi.add(addZerofive)); 
//				result.setNumprem_4_bi(prem4Bi.add(addZerofive));

				result.setTotalAmt(res2.get(0).getTotalAmt());
				result.setTotalPrem(res2.get(0).getTotalPrem());
				
			}
			
			

			// 回傳結果
			jsonBean.setData(result);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("保批單主檔查詢成功!");
		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(result);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("保批單主檔查詢失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」做為保批單主檔查詢結果
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "是否已列印正式帳單", response = JsonBean.class, tags = {
			"Rin1304Aapi" }, notes = "「保單號碼」﹑「批單號碼」是否已列印正式帳單")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/checkacctflagisy")
	@ResponseBody
	public ResponseEntity<?> checkAcctfFlagIsY(
			@ApiParam(value = "是否已列印正式帳單") @RequestBody Rin1304QueryAcctFlagRespVO parJson) throws Exception {
		log.debug(">>> Rin1304AController.checkAcctfFlagIsY(是否已列印正式帳單)");
		JsonBean jsonBean = new JsonBean();

		List<Rin1304QueryAcctFlagRespVO> res = new ArrayList<>();

		try {

			String policyNo = parJson.getPolicyNo(); // 保單號碼頁面查詢
			String endorseNo = parJson.getEndorseNo(); // 批單號碼頁面查詢

			res = friPolicyService.checkAcctfFlagIsY(policyNo, endorseNo);

			// 回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("是否已列印正式帳單查詢成功!");
		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("是否已列印正式帳單查詢失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	@ApiOperation(value = "新增續保單號資料", response = JsonBean.class, tags = { "Rin1304Aapi" }, notes = "新增保批單主檔")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertpolicyforoldpolicy")
	@ResponseBody
	public ResponseEntity<?> insertPolicyForOldPolicy(
			@ApiParam(value = "新增續保單號資料") @RequestBody Rin1304InsertByOldPolicy parJson) throws Exception {
		log.debug(">>> Rin1304AController.insertPolicyForOldPolicy(新增續保單號資料)");
		JsonBean jsonBean = new JsonBean();
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			String policyNo = parJson.getPolicyNo();   // 保單號碼
			String endorseNo = parJson.getEndorseNo(); // 批單號碼
			String oldPolicy = parJson.getOldPolicy(); // 續保單號
			String getPolicyNo;

			if (!oldPolicy.equals("")) {
				getPolicyNo = oldPolicy;
			} else {
				getPolicyNo = policyNo;
				oldPolicy = "";
			}

			FriPolicyMapper friPolicyMapper = sqlSession.getMapper(FriPolicyMapper.class);
			FriPolicyDtlMapper friPolicyDtlMapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			FriPolicyPropMapper friPolicyPropMapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			FriPolicyAdditionMapper friPolicyAdditionMapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);

			//新增保批單主檔 by 續保單號
			friPolicyService.insertFriPolicyByOldPolicyNo(policyNo, endorseNo, oldPolicy, getPolicyNo, friPolicyMapper);
			//新增保批單明細檔 by 續保單號
			friPolicyDtlService.insertFriPolicyDtlForOldPolicy(policyNo, endorseNo, getPolicyNo, friPolicyDtlMapper);
			//新增保批單標的物檔 by 續保單號
			friPolicyPropService.insertFriPolicyPropForOldPolicy(policyNo, endorseNo, getPolicyNo, friPolicyPropMapper);
			//新增保批單附加險明細檔 by 續保單號
			friPolicyAdditionService.insertFriPolicyAdditionForOldPolicy(policyNo, endorseNo, getPolicyNo,
					friPolicyAdditionMapper);

			sqlSession.commit();
			// 回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("新增續保單號資料成功!");

		} catch (Exception e) {
			sqlSession.rollback();
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("新增續保單號資料失敗!");
		} finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	

	@ApiOperation(value = "新增保批單主檔", response = JsonBean.class, tags = { "Rin1304Aapi" }, notes = "新增保批單主檔")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertfripolicy")
	@ResponseBody
	public ResponseEntity<?> insertFriPolicy(@ApiParam(value = "新增保批單主檔") @RequestBody FriPolicy parJson)
			throws Exception {
		log.debug(">>> Rin1304AController.insertFriPolicy(新增保批單主檔)");
		JsonBean jsonBean = new JsonBean();
		int res = 0;
		try {

			res = friPolicyService.insert(parJson);

			// 回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("新增保批單主檔成功!");

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("新增保批單主檔失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "自動產生調整批單(ADJ)", response = JsonBean.class, tags = { "Rin1304Aapi" }, notes = "自動產生調整批單(ADJ)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/createADJpolicy")
	@ResponseBody
	public ResponseEntity<?> createADJPolicy(
			@ApiParam(value = "自動產生調整批單(ADJ)") @RequestBody Rin1304CreateADJPolicyVO parJson) throws Exception {
		log.debug(">>> Rin1304AController.createADJPolicy(自動產生調整批單(ADJ))");
		JsonBean jsonBean = new JsonBean();
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			String policyNo = parJson.getPolicyNo();        // 保單號碼
			String endorseNo = parJson.getEndorseNo();      // 批單號碼
			String policyNoADJ =parJson.getPolicyNoADJ();   // 新保單號碼
			String endorseNoADJ =parJson.getEndorseNoADJ(); // 新批單號碼
			
			String today=dateFormat.format(new Date());
			String policyDprt =today;     // 保單列印日

			

			FriPolicyMapper friPolicyMapper = sqlSession.getMapper(FriPolicyMapper.class);
			FriPolicyDtlMapper friPolicyDtlMapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			FriPolicyPropMapper friPolicyPropMapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			FriPolicyAdditionMapper friPolicyAdditionMapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);

			//新增保批單主檔 by 新保批單號碼
			friPolicyService.createADJpolicy(policyNo, endorseNo,policyDprt, policyNoADJ, endorseNoADJ, friPolicyMapper);
			//新增保批單明細檔 by 新保批單號碼
			friPolicyDtlService.createADJpolicyDtl(policyNo, endorseNo, policyNoADJ, endorseNoADJ, friPolicyDtlMapper);
			//新增保批單標的物檔 by 新保批單號碼
			friPolicyPropService.createADJpolicyProp(policyNo, endorseNo, policyNoADJ, endorseNoADJ, friPolicyPropMapper);
			//新增保批單附加險明細檔 by 新保批單號碼
			friPolicyAdditionService.createADJpolicyAddition(policyNo, endorseNo, policyNoADJ, endorseNoADJ,friPolicyAdditionMapper);

			sqlSession.commit();
			// 回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("自動產生調整批單(ADJ)成功!");

		} catch (Exception e) {
			sqlSession.rollback();
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("自動產生調整批單(ADJ)失敗!");
		} finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * Rin1304_臨分分入，更正保批單主檔
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "更正保批單主檔", response = JsonBean.class, tags = { "Rin1304Aapi" }, notes = "更正保批單主檔")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatefripolicy")
	@ResponseBody
	public ResponseEntity<?> updateFriPolicy(@ApiParam(value = "更正保批單主檔") @RequestBody FriPolicy parJson)
			throws Exception {
		log.debug(">>> Rin1304AController.updateFriPolicy(更正保批單主檔)");
		JsonBean jsonBean = new JsonBean();
		int res = 0;

		try {

			res = friPolicyService.update(parJson);

			// 回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("更正保批單主檔成功!");
		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("更正保批單主檔失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * Rin1304_臨分分入，查詢是否有保批單相關明細
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢是否有保批單相關明細", response = JsonBean.class, tags = {
			"Rin1304Aapi" }, notes = "用「保單號碼」和「批單號碼」查詢是否有保批單相關明細")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryfripolicyall")
	@ResponseBody
	public ResponseEntity<?> queryFriPolicyAll(@ApiParam(value = "「") @RequestBody Rin1304VO parJson) throws Exception {
		log.debug(">>> Rin1304AController.queryFriPolicyAll(用「保單號碼」和「批單號碼」查詢是否有保批單相關明細)");
		JsonBean jsonBean = new JsonBean();

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		boolean queryall = false;
		try {

			FriPolicyDtlMapper friPolicyDtlMapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			FriPolicyPropMapper friPolicyPropMapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			FriPolicyAdditionMapper friPolicyAdditionMapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);

			List<Rin1304VO> resDtl = null;
			List<Rin1304VO> resProp = null;
			List<Rin1304VO> resAddition = null;

			String policyNo = parJson.getPolicyNo(); // 保單號碼
			String endorseNo = parJson.getEndorseNo(); // 批單號碼

			// 先查詢是否有保批單明細檔、保批單標的物檔、保批單附加險檔
			resDtl = friPolicyDtlService.queryPolicyDtl(policyNo, endorseNo, friPolicyDtlMapper);
			resProp = friPolicyPropService.queryPolicyPropDtl(policyNo, endorseNo, friPolicyPropMapper);
			resAddition = friPolicyAdditionService.queryPolicyAddition(policyNo, endorseNo, friPolicyAdditionMapper);

			sqlSession.commit();

			// 回傳結果
			if (!resDtl.isEmpty() || !resProp.isEmpty() || !resAddition.isEmpty()) {
				queryall = true;

				jsonBean.setData(queryall);
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("查詢相關明細成功!");
			}

		} catch (Exception e) {
			sqlSession.rollback();

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢相關明細失敗!");
		} finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」、「地址序號」、「標的物序號」查詢附加險檔頁面結果
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "幣別下拉選單", response = JsonBean.class, tags = { "Rin1304Aapi" }, notes = "幣別下拉選單")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryddlcurncylist")
	@ResponseBody
	public ResponseEntity<?> queryDdlCurncyList() throws Exception {
		log.debug(">>> Rin1304AController.queryDdlCurncyList(幣別下拉選單)");
		JsonBean jsonBean = new JsonBean();
		List<Mrexpf> res = new ArrayList<>();
		try {

			res = mrexpfService.queryByCrtdat();

			// 回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("幣別下拉選單成功!");
		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("幣別下拉選單失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * 查詢幣別匯率
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢幣別匯率", response = JsonBean.class, tags = { "Rin1304Aapi" }, notes = "查詢幣別匯率")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryCurrency")
	@ResponseBody
	public ResponseEntity<?> queryCurrency(@RequestBody Rin1304FriPolicyVOResp parJson) throws Exception {
		log.debug(">>> Rin1304AController.queryCurrency(查詢幣別匯率)");
		JsonBean jsonBean = new JsonBean();
		List<Mrexpf> res = new ArrayList<>();
		try {
			res = mrexpfService.queryCurrency(parJson.getTxtcurrency());
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("查詢幣別匯率成功!");
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢幣別匯率失敗!");
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * Rin1304_臨分分入，刪除保批單主檔
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除保批單主檔", response = JsonBean.class, tags = {
			"Rin1304Aapi" }, notes = "用「保單號碼」和「批單號碼」刪除保批單主檔")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deletefripolicy")
	@ResponseBody
	public ResponseEntity<?> deleteFriPolicy(
			@ApiParam(value = "「保單號碼」和「批單號碼」刪除保批單主檔") @RequestBody Rin1304DeletePolicyAllVORes parJson)
			throws Exception {
		log.debug(">>> Rin1304AController.deleteFriPolicy(用「保單號碼」和「批單號碼」刪除保批單主檔");
		JsonBean jsonBean = new JsonBean();

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {

			FriPolicyMapper friPolicyMapper = sqlSession.getMapper(FriPolicyMapper.class);
			FriPolicyDtlMapper friPolicyDtlMapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			FriPolicyPropMapper friPolicyPropMapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			FriPolicyAdditionMapper friPolicyAdditionMapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
			FriTreatySharesMapper friTreatySharesMapper = sqlSession.getMapper(FriTreatySharesMapper.class);

//			List<Rin1304VO> resDtl = null;
//			List<Rin1304VO> resProp = null;
//			List<Rin1304VO> resAddition = null;

			String policyNo = parJson.getPolicyNo(); // 保單號碼
			String endorseNo = parJson.getEndorseNo(); // 批單號碼
			String addrNo = ""; // 地址序號
			String propNo = ""; // 標的物序號
			String additionSeq = ""; // 附加險序號

//			// 先查詢是否有保批單明細檔、保批單標的物檔、保批單附加險檔
//			resDtl = friPolicyDtlService.queryPolicyDtl(policyNo, endorseNo, friPolicyDtlMapper);
//			resProp = friPolicyPropService.queryPolicyPropDtl(policyNo, endorseNo, friPolicyPropMapper);
//			resAddition = friPolicyAdditionService.queryPolicyAddition(policyNo, endorseNo, friPolicyAdditionMapper);
       if(!policyNo.isEmpty()) {
    	   
    	   // 刪除保批單主檔
    	   friPolicyService.deletePolicyByPrimaryKey(policyNo, endorseNo, friPolicyMapper);
    	   // 刪除保批單明細檔
    	   friPolicyDtlService.deletePolicyDtl(policyNo, endorseNo, addrNo, friPolicyDtlMapper);
    	   // 刪除保批單標的物檔
    	   friPolicyPropService.deletePolicyProp(policyNo, endorseNo, addrNo, propNo, friPolicyPropMapper);
    	   // 刪除保批單附加險檔
    	   friPolicyAdditionService.deletePolicyAddition(policyNo, endorseNo, addrNo, propNo, additionSeq,
    			   friPolicyAdditionMapper);
    	   // 刪除合約分保檔
    	   friTreatySharesService.deletefriTreatyShares(policyNo, endorseNo, friTreatySharesMapper);
       }
            
            

			sqlSession.commit();

			// 回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("刪除保批單主檔成功!");

		} catch (Exception e) {
			sqlSession.rollback();

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("刪除保批單主檔失敗!");
		} finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	/**
	 * Rin1304_臨分分入，將明細保期更改同主檔保期
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "將明細保期更改同主檔保期", response = JsonBean.class, tags = { "Rin1304Aapi" }, notes = "將明細保期更改同主檔保期")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatepolicydates")
	@ResponseBody
	public ResponseEntity<?> updatePolicyDates(@ApiParam(value = "將明細保期更改同主檔保期") @RequestBody FriPolicyDtl parJson)
			throws Exception {
		log.debug(">>> Rin1304AController.updatePolicyDates(用「起保日期」、「到期日期」、「保單號碼」、「批單號碼」將明細保期更改同主檔保期)");
		JsonBean jsonBean = new JsonBean();
		int res = 0;

		try {
			Date policyDbgn=parJson.getPolicyDbgn();
			Date policyDend=parJson.getPolicyDend();
			String policyNo=parJson.getPolicyNo();
			String endorseNo=parJson.getEndorseNo();

			res = friPolicyDtlService.updatePolicyDates(policyDbgn,policyDend,policyNo,endorseNo);

			// 回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("將明細保期更改同主檔保期成功!");
		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("將明細保期更改同主檔保期失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * Rin1304_臨分分入，反算_附加險總額=華南保費保額
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "反算_查詢附加險總額和華南保額/保費", response = JsonBean.class, tags = {
			"Rin1304Aapi" }, notes = "反算_查詢附加險總額和華南保額/保費")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/checkamtprem")
	@ResponseBody
	public ResponseEntity<?> checkAmtPrem(@ApiParam(value = "反算_查詢附加險總額和華南保額/保費") @RequestBody Rin1304CheckAmtPremRespVO parJson) throws Exception {
		log.debug(">>> Rin1304AController.checkAmt(反算_查詢附加險總額和華南保額/保費)");
		JsonBean jsonBean = new JsonBean();

		List<Rin1304CheckAmtPremRespVO> res = new ArrayList<>();
		Rin1304CheckAmtPremRespVO result = null;
		result = new Rin1304CheckAmtPremRespVO();
		try {

			
			String policyNo = parJson.getPolicyNo();   // 保單號碼
			String endorseNo = parJson.getEndorseNo(); // 批單號碼
			BigDecimal amt = BigDecimal.ZERO;          // 總保額
			BigDecimal prem = BigDecimal.ZERO;         // 總保費
			BigDecimal amtDtl = BigDecimal.ZERO;       // 明細總保額
			BigDecimal premDtl = BigDecimal.ZERO;      // 明細總保費
			
			res = friPolicyService.checkAmtPrem(policyNo, endorseNo);
			for(Rin1304CheckAmtPremRespVO model : res) {
				amt = amt.add(model.getAmt());
				result.setAmt(amt);
				prem = prem.add(model.getPrem());
				result.setPrem(prem);
				amtDtl = amtDtl.add(model.getAmtDtl());
				result.setAmtDtl(amtDtl);
				premDtl = premDtl.add(model.getPremDtl());
				result.setPremDtl(premDtl);
			}
			// 回傳結果
			jsonBean.setData(result);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("反算_查詢附加險總額和華南保額/保費成功!");
		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("反算_查詢附加險總額和華南保額/保費失敗!");
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	

	/**
	 * Rin1304_臨分分入，反算_附加險總額=華南保費保額
	 * 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "反算用_保額/保費加總", response = JsonBean.class, tags = {
			"Rin1304Aapi" }, notes = "反算用_保額/保費加總")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/sumamtprem")
	@ResponseBody
	public ResponseEntity<?> sumAmtPrem(@ApiParam(value = "反算用_保額/保費加總") @RequestBody Rin1304SumAmtPremRespVO parJson) throws Exception {
		log.debug(">>> Rin1304AController.sumAmtPrem(反算用_保額/保費加總)");
		JsonBean jsonBean = new JsonBean();
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		
		List<Rin1304GetAddrNoVO> res = new ArrayList<>();
		List<Rin1304QueryPolicyPropVO> res2=new ArrayList<>(); 
		List<Rin1304SumAmtPremRespVO> res3=new ArrayList<>(); 
		String policyNo=parJson.getPolicyNo();
		String endorseNo=parJson.getEndorseNo();
		
		
		try {
			
			FriPolicyDtlMapper friPolicyDtlMapper = sqlSession.getMapper(FriPolicyDtlMapper.class);
			FriPolicyPropMapper friPolicyPropMapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			FriPolicyAdditionMapper friPolicyAdditionMapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
			//取得地址序號
			res =friPolicyDtlService.getAddrNo(policyNo,endorseNo,friPolicyDtlMapper);
			
			
			for(Rin1304GetAddrNoVO model : res) {
				String addrNo=model.getAddrNo();
				res2=friPolicyPropService.getPropNo(policyNo,endorseNo,addrNo,friPolicyPropMapper);
				
				for(Rin1304QueryPolicyPropVO model2 : res2){
					String propNo=model2.getPropNo();
					res3=friPolicyAdditionService.sumAmtPrem(policyNo,endorseNo,addrNo,propNo,friPolicyAdditionMapper);
					BigDecimal amt = BigDecimal.ZERO;      // 保額
					BigDecimal prem = BigDecimal.ZERO;     // 保費
					BigDecimal amtTyp = BigDecimal.ZERO;  // 颱洪保額
					BigDecimal premTyp = BigDecimal.ZERO; // 颱洪保費
					BigDecimal amtEar = BigDecimal.ZERO;  // 地震保額
					BigDecimal premEar = BigDecimal.ZERO; // 地震保費
					
					
					for(Rin1304SumAmtPremRespVO model3:res3) {
						BigDecimal getAmt = model3.getAmt();      // 保額
						BigDecimal getPrem = model3.getPrem();     // 保費
						BigDecimal getAmtTyp = model3.getAmtTyp();  // 颱洪保額
						BigDecimal getPremTyp = model3.getPremTyp(); // 颱洪保費
						BigDecimal getAmtEar = model3.getAmtEar();  // 地震保額
						BigDecimal getPremEar = model3.getPremEar(); // 地震保費
						if(getAmt==null) {
							getAmt= new BigDecimal(0);
							}
						if(getPrem==null) {
							getPrem= new BigDecimal(0);
							}
						if(getAmtTyp==null) {
							getAmtTyp= new BigDecimal(0);
							}
						if(getPremTyp==null) {
							getPremTyp= new BigDecimal(0);
							}
						if(getAmtEar==null) {
							getAmtEar= new BigDecimal(0);
							}
						if(getPremEar==null) {
							getPremEar= new BigDecimal(0);
							}
						amt=amt.add(getAmt);
						prem = prem.add(getPrem);				
						amtTyp = amtTyp.add(getAmtTyp);
						premTyp = premTyp.add(getPremTyp);
						amtEar = amtEar.add(getAmtEar);
						premEar = premEar.add(getPremEar);
					}
					friPolicyPropService.updatePropAmtPrem(prem,amt,policyNo,endorseNo,addrNo,propNo,friPolicyPropMapper);
					friPolicyDtlService.updatePolicyDtlAmtPrem(policyNo,endorseNo,addrNo,amtTyp,premTyp,amtEar,premEar,friPolicyDtlMapper);
				}
			}

			
			
			sqlSession.commit();
			// 回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("反算用_保額/保費加總成功!");
		} catch (Exception e) {
			sqlSession.rollback();
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("反算用_保額/保費加總失敗!");
		}finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

}
