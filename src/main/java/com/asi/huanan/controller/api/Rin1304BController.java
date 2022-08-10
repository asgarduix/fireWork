package com.asi.huanan.controller.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import com.asi.huanan.service.FriRetainLimitService;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyAdditionMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyDtlMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyPropMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyDtl;
import com.asi.huanan.vo.R1304QueryPolicyDtlVO;
import com.asi.huanan.vo.Rin1304DeletePolicyAllVORes;
import com.asi.huanan.vo.Rin1304FriPolicyAdditionVOResp;
import com.asi.huanan.vo.Rin1304FriPolicyDtlVOResp;
import com.asi.huanan.vo.Rin1304GetUsePropVO;
import com.asi.huanan.vo.Rin1304PolicyDtlUpdateAddrNoResVo;
import com.asi.huanan.vo.Rin1304QueryLimitVO;
import com.asi.huanan.vo.Rin1304UpdateAddrNoOriResVO;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1304bapi")
@RestController
@Api(tags = { "Rin1304Bapi" })
public class Rin1304BController {
	private static Logger log = LogManager.getLogger(Rin1304BController.class);
	
	
	@Autowired
	private FriPolicyDtlService friPolicyDtlService;
	
	@Autowired
	private FriPolicyPropService friPolicyPropService;
	
	@Autowired
	private FriPolicyAdditionService friPolicyAdditionService;
	
	@Autowired
	private FriRetainLimitService friRetainLimitService;
	
	@Autowired
	private MyBatisConnector mybatis;
	
	
	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」做為保批單主檔查詢結果
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "保批單明細檔查詢結果", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "「保單號碼」﹑「批單號碼」、「地址序號」保批單明細檔查詢結果")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querypolicydtlbyprimarykey")
	@ResponseBody
	public ResponseEntity<?> queryPolicyDtlByPrimaryKey(@ApiParam(value ="保批單明細檔查詢結果")@RequestBody R1304QueryPolicyDtlVO parJson) throws Exception {
		log.debug(">>> Rin1304BController.queryPolicyDtlByPrimaryKey(保批單明細檔查詢結果)");
		JsonBean jsonBean = new JsonBean();
		List<Rin1304FriPolicyDtlVOResp> res = new ArrayList<>();
		List<Rin1304FriPolicyAdditionVOResp> res2 = new ArrayList<>();
		List<Rin1304FriPolicyDtlVOResp> res3 = new ArrayList<>();
		Rin1304FriPolicyDtlVOResp result = null;
		try {

			String policyNo=parJson.getPolicyNo();   //保單號碼
			String endorseNo=parJson.getEndorseNo(); //批單號碼
			String addrNo=parJson.getAddrNo();  //地址序號

			res=friPolicyDtlService.queryPolicyDtlByPrimaryKey(policyNo,endorseNo,addrNo);
			res2=friPolicyAdditionService.queryPolicyAdditionResult(policyNo,endorseNo,addrNo,"","","");
			

				result = new Rin1304FriPolicyDtlVOResp();
				// 將資料複製
				BeanUtils.copyProperties(res.get(0), result);
				
				if(res2.size()>=2 || res2.size()<=0) {
					//當附加險>1 or 沒有附加險 30險種代碼為空
					result.setTxtmercno("");           
				}else if(res2.size()==1) {
					result.setTxtmercno(res2.get(0).getTxtmercno());
				}
	
			res3.add(result);
			
			//回傳結果
			jsonBean.setData(res3);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("保批單明細檔查詢結果成功!");
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("保批單明細檔查詢結果失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」做為保批單主檔查詢結果
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢該明細累積保額", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "「保單號碼」、「地址序號」查詢該明細累積保額")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/sumpolicyendorseamt")
	@ResponseBody
	public ResponseEntity<?> sumPolicyEndorseAmt(@ApiParam(value ="查詢該明細累積保額")@RequestBody R1304QueryPolicyDtlVO parJson) throws Exception {
		log.debug(">>> Rin1304BController.sumPolicyEndorseAmt(查詢該明細累積保額)");
		JsonBean jsonBean = new JsonBean();
		List<Rin1304FriPolicyDtlVOResp> res = new ArrayList<>();
		try {

			String policyNo=parJson.getPolicyNo();   //保單號碼
			String addrNo=parJson.getAddrNo();       //地址序號

			res=friPolicyDtlService.sumPolicyEndorseAmt(policyNo,addrNo);
			
			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("查詢該明細累積保額成功!");
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢該明細累積保額失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
//	/**
//	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」做為保批單主檔查詢結果
//	 * @param parJson
//	 * @return
//	 * @throws Exception
//	 */
//	@ApiOperation(value = "取得保批單明細檔查詢結果", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "「保單號碼」﹑「批單號碼」、「地址序號」保批單明細檔查詢結果")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
//	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
//	@PostMapping(value = "/queryPolicyDtl")
//	@ResponseBody
//	public ResponseEntity<?> queryPolicyDtl(@ApiParam(value ="保批單明細檔查詢結果")@RequestBody R1304QueryPolicyDtlVO parJson) throws Exception {
//		log.debug(">>> Rin1304BController.queryPolicyDtl(保批單明細檔查詢結果)");
//		JsonBean jsonBean = new JsonBean();
//		List<Rin1304FriPolicyDtlVOResp> res = new ArrayList<>();
//		try {
//
//			String policyNo=parJson.getTxtpolicy_no();   //保單號碼
//			String endorseNo=parJson.getTxtendorse_no(); //批單號碼
//	
//			res=friPolicyDtlService.queryPolicyDtl(policyNo,endorseNo);
//			
//			//回傳結果
//			jsonBean.setData(res);
//			jsonBean.setStatus(SysEnum.statusSuccess.code);
//			jsonBean.setMessage("保批單明細檔查詢結果成功!");
//		}catch(Exception e) {
//		
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
//
//			jsonBean.setData("");
//			jsonBean.setStatus(SysEnum.statusError.code);
//			jsonBean.setMessage("保批單明細檔查詢結果失敗!");
//		}
//		
//		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
//	}
	
	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」取保單地址序號
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢是否有保單地址序號", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "用「保單號碼」、「批單號碼」、「保單地址序號」取保單地址序號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryaddrno")
	@ResponseBody
	public ResponseEntity<?> queryaddrNo(@ApiParam(value ="查詢是否有保單地址序號")@RequestBody R1304QueryPolicyDtlVO parJson) throws Exception {
		log.debug(">>> Rin1304BController.queryaddrNo(查詢是否有保單地址序號)");
		JsonBean jsonBean = new JsonBean();
		List<R1304QueryPolicyDtlVO> res=null;
		try {

			String policyNo=parJson.getPolicyNo();   //保單號碼
			String endorseNo=parJson.getEndorseNo(); //批單號碼
			String addrNoOri=parJson.getAddrNoOri();//地址序號

			res=friPolicyDtlService.queryaddrNo(policyNo,endorseNo,addrNoOri);
			
			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("查詢是否有保單地址序號成功!");
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢是否有保單地址序號失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "新增保批單明細檔", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "新增保批單明細")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertfripolicydtl")
	@ResponseBody
	public ResponseEntity<?> insertFriPolicyDtl(@ApiParam(value ="新增保批單明細")@RequestBody FriPolicyDtl parJson) throws Exception {
		log.debug(">>> Rin1304BController.insertFriPolicyDtl(新增保批單明細)");
		JsonBean jsonBean = new JsonBean();
	    int res=0;
		try {
			parJson.setAmt((long) 0);
			parJson.setPrem((long)0);
			parJson.setAmtFlt((long) 0);
			parJson.setPremFlt((long) 0);
			parJson.setAmtFix((long) 0);
			parJson.setPremFix((long) 0);
			parJson.setAmtTyp((long) 0);
			parJson.setPremTyp((long)0);
			parJson.setAmtEar((long) 0);
			parJson.setPremEar((long) 0);

			res=friPolicyDtlService.insert(parJson);

			    //回傳結果
				jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("新增保批單明細檔成功!");

		}catch(Exception e) {
            
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("新增保批單明細檔失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "修改保批單明細檔", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "修改保批單明細檔")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatefripolicydtl")
	@ResponseBody
	public ResponseEntity<?> updateFriPolicyDtl(@ApiParam(value ="修改保批單明細檔")@RequestBody FriPolicyDtl parJson) throws Exception {
		log.debug(">>> Rin1304BController.updateFriPolicyDtl(修改保批單明細檔)");
		JsonBean jsonBean = new JsonBean();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	int res;
		try {
			Date txtPolicyDbgn=parJson.getPolicyDbgn();
			String policyDbgn = dateFormat.format(txtPolicyDbgn);
			parJson.setPolicyDbgn(dateFormat.parse(policyDbgn));
			
	
			res=friPolicyDtlService.update(parJson);

			//回傳結果
				jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("修改保批單明細檔成功!");

		}catch(Exception e) {
            
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("修改保批單明細檔失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "更新保單地址序號", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "更新保單地址序號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updateaddrnoori")
	@ResponseBody
	public ResponseEntity<?> updateAddrNoOri(@ApiParam(value ="更新保單地址序號")@RequestBody Rin1304UpdateAddrNoOriResVO parJson) throws Exception {
		log.debug(">>> Rin1304BController.updateAddrNoOri(更新保單地址序號)");
		JsonBean jsonBean = new JsonBean();
	    int res=0;
	    SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			FriPolicyDtlMapper friPolicyDtlMapper=sqlSession.getMapper(FriPolicyDtlMapper.class);
			
			String policyNo=parJson.getPolicyNo();   //保單號碼
			String endorseNo=parJson.getEndorseNo(); //批單號碼
			String addrNoOri=parJson.getAddrNoOri(); //保單地址序號

			friPolicyDtlService.updateAddrNoOriPlusOne(policyNo,endorseNo,addrNoOri,friPolicyDtlMapper);
			res=friPolicyDtlService.updateAddrNoOriAll(policyNo,endorseNo,friPolicyDtlMapper);
			
			sqlSession.commit();
			    //回傳結果
				jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("更新保單地址序號成功!");

		}catch(Exception e) {
			 sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("更新保單地址序號失敗!");
		}
		finally {
			sqlSession.close();
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	/**
	 * Rin1304_臨分分入，刪除保批單明細檔
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除保批單明細檔", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "用「保單號碼」和「批單號碼」刪除保批單明細檔")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deletefripolicydtl")
	@ResponseBody
	public ResponseEntity<?> deleteFriPolicyDtl(@ApiParam(value ="刪除保批單明細檔")@RequestBody Rin1304DeletePolicyAllVORes parJson) throws Exception {
		log.debug(">>> Rin1304Controller.deleteFriPolicyDtl(用「保單號碼」和「批單號碼」刪除保批單明細檔)");
		JsonBean jsonBean = new JsonBean();
	
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			
	
			FriPolicyDtlMapper friPolicyDtlMapper=sqlSession.getMapper(FriPolicyDtlMapper.class);
			FriPolicyPropMapper friPolicyPropMapper=sqlSession.getMapper(FriPolicyPropMapper.class);
			FriPolicyAdditionMapper friPolicyAdditionMapper=sqlSession.getMapper(FriPolicyAdditionMapper.class);
			


			String policyNo=parJson.getPolicyNo();
			String endorseNo=parJson.getEndorseNo();
			String addrNo=parJson.getAddrNo();
			String propNo="";
			String additionSeq="";
			
			
			//刪除保批單明細檔
			friPolicyDtlService.deletePolicyDtl(policyNo, endorseNo,addrNo, friPolicyDtlMapper);
			//刪除保批單標的物檔
			friPolicyPropService.deletePolicyProp(policyNo, endorseNo,addrNo, propNo,friPolicyPropMapper);
			//刪除保批單附加險檔
			friPolicyAdditionService.deletePolicyAddition(policyNo, endorseNo, addrNo, propNo,additionSeq,friPolicyAdditionMapper);
			
			

			sqlSession.commit();
			
			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("刪除保批單明細檔成功!");
		}catch(Exception e) {
            sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("刪除保批單明細檔失敗!");
		}finally {
			sqlSession.close();
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * Rin1304_臨分分入，用「使用性質代號」帶出「使用性質名稱」
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢保批單明細檔_地址序號&保單地址序號", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "用「保單號碼」查詢保批單明細檔_地址序號&保單地址序號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/qrerypolicyaddrno")
	@ResponseBody
	public ResponseEntity<?> qreryPolicyAddrNo(@ApiParam(value ="查詢保批單明細檔_地址序號&保單地址序號")@RequestBody FriPolicyDtl parJson) throws Exception {
		log.debug(">>> Rin1304BController.qreryPolicyAddrNo(用「保單號碼」查詢保批單明細檔_地址序號&保單地址序號)");
		JsonBean jsonBean = new JsonBean();
		List<FriPolicyDtl> res = new ArrayList<>();
		try {
			String policyNo=parJson.getPolicyNo();
			String endorseNo=parJson.getEndorseNo();
			
			res=friPolicyDtlService.qreryPolicyAddrNo(policyNo,endorseNo);
		
			//回傳結果
				jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("查詢保批單明細檔_地址序號&保單地址序號成功!");
			
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢保批單明細檔_地址序號&保單地址序號失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * Rin1304_臨分分入，用「使用性質代號」帶出「使用性質名稱」
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢使用性質名稱", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "用「使用性質代號」查詢使用性質名稱")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/getuseprop")
	@ResponseBody
	public ResponseEntity<?> getUseProp(@ApiParam(value ="查詢使用性質名稱")@RequestBody Rin1304GetUsePropVO parJson) throws Exception {
		log.debug(">>> Rin1304BController.getUseProp(用「使用性質代號」查詢使用性質名稱)");
		JsonBean jsonBean = new JsonBean();
		List<Rin1304GetUsePropVO> res = new ArrayList<>();
		try {

			String usePropId=parJson.getUsePropId();//使用性質代號

			res=friPolicyDtlService.getUseProp(usePropId);
			
			//回傳結果
			if(!res.isEmpty()) {
				jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("查詢使用性質名稱成功!");
			}else {
				jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.status100.code);
				jsonBean.setMessage("查詢使用性質名稱不存在!");
			}
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢使用性質名稱失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * Rin1304_臨分分入，輸入完「樓層數」和「建築等級英文代號」會自動帶出 限額代號、限額、限額比率、保單限額 
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢保單限額、限額代號、限額", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "用「樓層數」和「建築等級英文代號」會自動帶出 限額代號、限額、限額比率、保單限額 ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querylimit")
	@ResponseBody
	public ResponseEntity<?> queryLimit(@ApiParam(value ="查詢保單限額、限額代號、限額")@RequestBody Rin1304QueryLimitVO parJson) throws Exception {
		log.debug(">>> Rin1304BController.querylimit(用「樓層數」和「建築等級英文代號」會自動帶出 限額代號、限額、限額比率、保單限額 )");
		JsonBean jsonBean = new JsonBean();
		List<Rin1304QueryLimitVO> res = new ArrayList<>();
		try {

			//保單列印日'年'
			String policyYear=parJson.getPolicyYear().substring(0, 4);
			//使用性質代號
            String propCode=parJson.getPropCode();
			//建築等級英文代號
            String constClass=parJson.getConstClass();
            
            if(!propCode.isEmpty()&& !constClass.isEmpty()) {
            	res=friRetainLimitService.queryLimit(policyYear,propCode,constClass);
            }
      
            
			    //回傳結果
            	jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("查詢保單限額、限額代號、限額成功!");
            
					
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢保單限額、限額代號、限額失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	@ApiOperation(value = "更正地址序號", response = JsonBean.class, tags = {"Rin1304Bapi"}, notes = "更正地址序號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updateaddrno")
	@ResponseBody
	public ResponseEntity<?> updateAddrNo(@ApiParam(value ="更正地址序號")@RequestBody Rin1304PolicyDtlUpdateAddrNoResVo parJson) throws Exception {
		log.debug(">>> Rin1304BController.updateAddrNo(更正地址序號)");
		JsonBean jsonBean = new JsonBean();
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

		try {
			
			FriPolicyDtlMapper friPolicyDtlMapper=sqlSession.getMapper(FriPolicyDtlMapper.class);
			FriPolicyPropMapper friPolicyPropMapper=sqlSession.getMapper(FriPolicyPropMapper.class);
			FriPolicyAdditionMapper friPolicyAdditionMapper=sqlSession.getMapper(FriPolicyAdditionMapper.class);
			
			String oldAddrNo=parJson.getOldAddrNo();                        //舊地址序號
			String policyNo=parJson.getPolicyDtlList().getPolicyNo();       //保單號碼
			String endorseNo=parJson.getPolicyDtlList().getEndorseNo();     //批單號碼
			String addrNo=parJson.getPolicyDtlList().getAddrNo().toString();//地址序號
			
			//更新保批單明細地址序號
			friPolicyDtlService.updateAddrNo(parJson.getPolicyDtlList(),oldAddrNo,friPolicyDtlMapper);
			//更新標的物明細檔地址序號
			friPolicyPropService.updatePolicyPropAddrNo(addrNo,policyNo,endorseNo,oldAddrNo,friPolicyPropMapper);
			//更新附加險明細檔地址序號
			friPolicyAdditionService.updatePolicyAdditionAddrNo(addrNo,policyNo,endorseNo,oldAddrNo,friPolicyAdditionMapper);
			
			sqlSession.commit();
			//回傳結果
				jsonBean.setData("");
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("更正地址序號成功!");

		}catch(Exception e) {
            sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("更正地址序號失敗!");
		}finally {
			sqlSession.close();
		}
		
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
}



