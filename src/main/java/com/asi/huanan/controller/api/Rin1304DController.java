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

import com.asi.huanan.service.FriPolicyAdditionService;
import com.asi.huanan.service.customerize.Rin1304DService;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyAdditionMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyPropMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyAddition;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyAdditionKey;
import com.asi.huanan.vo.Rin1304DeletePolicyAllVORes;
import com.asi.huanan.vo.Rin1304FriPolicyAdditionVOResp;
import com.asi.huanan.vo.Rin1304QueryDdlAdditionListVO;
import com.asi.huanan.vo.Rin1304QueryPolicyAdditionVO;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1304dapi")
@RestController
@Api(tags = { "Rin1304Dapi" })
public class Rin1304DController {
	
	private static Logger log = LogManager.getLogger(Rin1304DController.class);

	@Autowired
	private FriPolicyAdditionService friPolicyAdditionService;
	
	@Autowired
	private MyBatisConnector mybatis;
	
	@Autowired
	private Rin1304DService rin1304DService;
	
	
	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」、「地址序號」、「標的物序號」查詢附加險檔頁面結果
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢附加險檔頁面結果", response = JsonBean.class, tags = {"Rin1304Dapi"}, 
			      notes = "「保單號碼」、「批單號碼」、「地址序號」、「標的物序號」查詢附加險檔頁面結果")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querypolicyadditionresult")
	@ResponseBody
	public ResponseEntity<?> queryPolicyAdditionResult(@ApiParam(value ="查詢附加險檔頁面結果")@RequestBody Rin1304QueryPolicyAdditionVO parJson) throws Exception {
		log.debug(">>> Rin1304DController.queryPolicyAdditionResult(查詢附加險檔頁面結果)");
		JsonBean jsonBean = new JsonBean();
		List<Rin1304FriPolicyAdditionVOResp> res = new ArrayList<>();
		try {

			String policyNo=parJson.getPolicyNo();      //保單號碼
			String endorseNo=parJson.getEndorseNo();    //批單號碼
			String addrNo=parJson.getAddrNo();          //地址序號
			String propNo=parJson.getPropNo();          //標的物序號
			String additionSeq=parJson.getAdditionSeq();//附加險序號
			String additionNo=parJson.getAdditionNo();  //附加險代號

			res=friPolicyAdditionService.queryPolicyAdditionResult(policyNo,endorseNo,addrNo,propNo,additionSeq,additionNo);
			
			
			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("查詢附加險檔結果頁面成功!");
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢附加險檔結果頁面失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」、「地址序號」、「標的物序號」查詢附加險檔頁面結果
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "附加險代號下拉選單", response = JsonBean.class, tags = {"Rin1304Dapi"}, 
			      notes = "附加險代號下拉選單")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryddladditionlist")
	@ResponseBody
	public ResponseEntity<?> queryDdlAdditionList() throws Exception {
		log.debug(">>> Rin1304DController.queryDdlAdditionList(附加險代號下拉選單)");
		JsonBean jsonBean = new JsonBean();
		List<Rin1304QueryDdlAdditionListVO> res = new ArrayList<>();
		try {

			res=friPolicyAdditionService.queryDdlAdditionList();
			
			
			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("附加險代號下拉選單成功!");
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("附加險代號下拉選單失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	@ApiOperation(value = "新增保批單附加險明細檔", response = JsonBean.class, tags = {"Rin1304Dapi"}, notes = "新增保批單附加險明細檔")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertfripolicyadditiondtl")
	@ResponseBody
	public ResponseEntity<?> insertFriPolicyAdditionDtl(@ApiParam(value ="新增保批單附加險明細檔")@RequestBody FriPolicyAddition parJson) throws Exception {
		log.debug(">>> Rin1304DController.insertFriPolicyAdditionDtl(新增保批單附加險明細檔)");
		JsonBean jsonBean = new JsonBean();
	    int res=0;
	    
	    SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			FriPolicyAdditionMapper friPolicyAdditionMapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
			FriPolicyPropMapper friPolicyPropMapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			// 火險保額 < 附加險保額，應跳出檢核
			String msg = checkAmt(parJson);
			if(StringUtils.isNotBlank(msg)) {
				// 該附加險保額大於純火險保額!!
				jsonBean.setData(msg);
				jsonBean.setStatus("004");
				jsonBean.setMessage(msg);
			}else {
				
				//新增fri_policy_addition資料
				res=friPolicyAdditionService.insert(parJson, friPolicyAdditionMapper);
				//連動更新fri_policy_prop與fri_policy_dtl資料
				rin1304DService.updatePropAndDtl(parJson, friPolicyAdditionMapper,friPolicyPropMapper, sqlSession);
				
				sqlSession.commit();
				
				//回傳結果
				jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("新增保批單附加險明細檔成功!");
			}
		}catch(Exception e) {
			sqlSession.rollback();
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("新增保批單附加險明細檔失敗!");
		}finally {
			sqlSession.close();
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "修改保批單附加險明細檔", response = JsonBean.class, tags = {"Rin1304Dapi"}, notes = "修改保批單附加險明細檔")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatefripolicyadditiondtl")
	@ResponseBody
	public ResponseEntity<?> updateFriPolicyAdditionDtl(
			@ApiParam(value = "修改保批單附加險明細檔") @RequestBody FriPolicyAddition parJson) throws Exception {
		log.debug(">>> Rin1304DController.updateFriPolicyAdditionDtl(修改保批單附加險明細檔)");
		JsonBean jsonBean = new JsonBean();
		int res;
	    
	    SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			FriPolicyAdditionMapper friPolicyAdditionMapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
			FriPolicyPropMapper friPolicyPropMapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			// 火險保額<附加險保額，應跳出檢核
			String msg = checkAmt(parJson);
			if(StringUtils.isNotBlank(msg)) {
				// 該附加險保額大於純火險保額!!
				jsonBean.setData(msg);
				jsonBean.setStatus("004");
				jsonBean.setMessage(msg);
			}else {
				//更新fri_policy_addition資料
				res = friPolicyAdditionService.updatePolicyAddition(parJson, friPolicyAdditionMapper);
				//連動更新fri_policy_prop與fri_policy_dtl資料
				rin1304DService.updatePropAndDtl(parJson, friPolicyAdditionMapper,friPolicyPropMapper, sqlSession);
				
				sqlSession.commit();
				
				// 回傳結果
				jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("修改保批單附加險明細檔成功!");
			}
		} catch (Exception e) {
			sqlSession.rollback();
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("修改保批單附加險明細檔失敗!");
		}finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	private String checkAmt(FriPolicyAddition parJson) {
		String msg = "";
		try {
			if("OF".equals(parJson.getAdditionNo())) {
				// 需查出其他附加險的保額做比較
				FriPolicyAddition model = new FriPolicyAddition();
				// 保單號
				model.setPolicyNo(parJson.getPolicyNo());
				// 批單號
				model.setEndorseNo(parJson.getEndorseNo());
				// 地址序號
				model.setAddrNo(parJson.getAddrNo());
				// 標的物序號
				model.setPropNo(parJson.getPropNo());
				List<FriPolicyAddition> friPolicyAdditionList = friPolicyAdditionService.queryByFriPolicyAddition(model);
				// 可能查到多筆
				if(!friPolicyAdditionList.isEmpty()) {
					for (FriPolicyAddition friPolicyAddition : friPolicyAdditionList) {
						// 純火險，則不用此檢核
						if (!"OF".equals(friPolicyAddition.getAdditionNo())
								&& friPolicyAddition.getAmt() > parJson.getAmt()) {
							msg = "該附加險保額大於純火險保額!!";
							break;
						}
					}
				}
			}else {
				// 查出純火險保額
				FriPolicyAddition model = new FriPolicyAddition();
				// 保單號
				model.setPolicyNo(parJson.getPolicyNo());
				// 批單號
				model.setEndorseNo(parJson.getEndorseNo());
				// 地址序號
				model.setAddrNo(parJson.getAddrNo());
				// 標的物序號
				model.setPropNo(parJson.getPropNo());
				// 附加險代號(純火險: OF)
				model.setAdditionNo("OF");
				List<FriPolicyAddition> friPolicyAdditionList = friPolicyAdditionService.queryByFriPolicyAddition(model);
				// 理論上只會查到1筆
				if(!friPolicyAdditionList.isEmpty() && (parJson.getAmt() > friPolicyAdditionList.get(0).getAmt())) {
					msg = "該附加險保額大於純火險保額!!";
				}
			}
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
		}
		
		return msg;
	}

	/**
	 * Rin1304_臨分分入，刪除保批單附加險檔
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除保批單附加險檔", response = JsonBean.class, tags = {"Rin1304Dapi"}, notes = "用「保單號碼」、「批單號碼」、「地址序號」、「標的物序號」、「附加險序號」刪除保批單附加險檔")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deletefripolicyaddition")
	@ResponseBody
	public ResponseEntity<?> deleteFriPolicyAddition(@ApiParam(value ="刪除保批單附加險檔")@RequestBody Rin1304DeletePolicyAllVORes parJson) throws Exception {
		log.debug(">>> Rin1304DController.deleteFriPolicyAddition(用「保單號碼」、「批單號碼」、「地址序號」、「標的物序號」、「附加險序號」刪除保批單附加險檔)");
		JsonBean jsonBean = new JsonBean();
	
		int res=0;
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			
			
			FriPolicyAdditionMapper friPolicyAdditionMapper=sqlSession.getMapper(FriPolicyAdditionMapper.class);
			

			String policyNo=parJson.getPolicyNo();
			String endorseNo=parJson.getEndorseNo();
			String addrNo=parJson.getAddrNo();
			String propNo=parJson.getPropNo();
			String additionSeq=parJson.getAdditionSeq();
			
			
			//刪除保批單標的物檔
			res=friPolicyAdditionService.deletePolicyAddition(policyNo, endorseNo,addrNo, propNo,additionSeq,friPolicyAdditionMapper);
			
			

			sqlSession.commit();
			
			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("刪除保批單附加險檔成功!");
		}catch(Exception e) {
            sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("刪除保批單附加險檔失敗!");
		}finally {
			sqlSession.close();
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」、「地址序號」、「標的物序號」查詢附加險檔頁面結果
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢附加險序號最大值", response = JsonBean.class, tags = {"Rin1304Dapi"}, 
			      notes = "「保單號碼」、「批單號碼」、「地址序號」、「標的物序號」查詢附加險序號最大值")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/getmaxadditionseq")
	@ResponseBody
	public ResponseEntity<?> getMaxAdditionSeq(@ApiParam(value ="查詢附加險序號最大值")@RequestBody Rin1304QueryPolicyAdditionVO parJson) throws Exception {
		log.debug(">>> Rin1304DController.getMaxAdditionSeq(查詢附加險序號最大值)");
		JsonBean jsonBean = new JsonBean();
		List<FriPolicyAdditionKey> res = new ArrayList<>();
		String res2= "";
		try {

			String policyNo=parJson.getPolicyNo();      //保單號碼
			String endorseNo=parJson.getEndorseNo();    //批單號碼
			String addrNo=parJson.getAddrNo();          //地址序號
			String propNo=parJson.getPropNo();          //標的物序號


			res=friPolicyAdditionService.getMaxAdditionSeq(policyNo,endorseNo,addrNo,propNo);
			if(res.get(0)==null) {
				res2= "1";
			}else {
				res2=res.get(0).getAdditionSeq().toString();
			}
			
			//回傳結果
			jsonBean.setData(res2);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("查詢附加險序號最大值成功!");
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢附加險序號最大值失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	
}
