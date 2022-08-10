package com.asi.huanan.controller.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import com.asi.huanan.service.FriPolicyPropService;
import com.asi.huanan.service.customerize.Rin1304DService;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyAdditionMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyPropMapper;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyAddition;
import com.asi.huanan.service.dao.mybatis.model.FriPolicyProp;
import com.asi.huanan.vo.Rin1304DeletePolicyAllVORes;
import com.asi.huanan.vo.Rin1304FriPolicyPropVOResp;
import com.asi.huanan.vo.Rin1304QueryDdlPropertyListVO;
import com.asi.huanan.vo.Rin1304QueryPolicyPropVO;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1304capi")
@RestController
@Api(tags = { "Rin1304Capi" })
public class Rin1304CController {
	
	private static Logger log = LogManager.getLogger(Rin1304CController.class);

	@Autowired
	private FriPolicyPropService friPolicyPropService;
	
	@Autowired
	private FriPolicyAdditionService friPolicyAdditionService;
	
	@Autowired
	private MyBatisConnector mybatis;
	
	@Autowired
	private Rin1304DService rin1304DService;
	
	
	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」、「地址序號」查詢標的物明細檔頁面結果
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "保批單標的物明細查詢結果", response = JsonBean.class, tags = {"Rin1304Capi"}, 
			      notes = "「保單號碼」﹑「批單號碼」、「地址序號」、「標的物序號」保批單標的物明細查詢結果")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querypolicypropdtlresult")
	@ResponseBody
	public ResponseEntity<?> queryPolicyPropDtlResult(@ApiParam(value ="保批單標的物明細查詢結果")@RequestBody Rin1304QueryPolicyPropVO parJson) throws Exception {
		log.debug(">>> Rin1304CController.queryPolicyPropDtlResult(保批單標的物明細查詢結果)");
		JsonBean jsonBean = new JsonBean();
		List<Rin1304FriPolicyPropVOResp> res =new ArrayList<>();
		try {

			String policyNo=parJson.getPolicyNo();   //保單號碼
			String endorseNo=parJson.getEndorseNo(); //批單號碼
			String addrNo=parJson.getAddrNo();       //地址序號
			String propNo=parJson.getPropNo();       //標的物序號

			res=friPolicyPropService.queryPolicyPropDtlResult(policyNo,endorseNo,addrNo,propNo);
			
			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("保批單標的物明細查詢結果成功!");
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("保批單標的物明細結果失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」、「地址序號」查詢標的物明細檔頁面結果
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "下拉選單(標的物代號+標的物名稱+標的物保品名稱簡碼)", response = JsonBean.class, tags = {"Rin1304Capi"}, 
			      notes = "下拉選單(標的物代號+標的物名稱+標的物保品名稱簡碼)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryddlpropertylist")
	@ResponseBody
	public ResponseEntity<?> queryDdlPropertyList() throws Exception {
		log.debug(">>> Rin1304CController.queryDdlPropertyList(下拉選單(標的物代號+標的物名稱+標的物保品名稱簡碼))");
		JsonBean jsonBean = new JsonBean();
		List<Rin1304QueryDdlPropertyListVO> res =new ArrayList<>();
		try {
			
			res=friPolicyPropService.queryDdlPropertyList();
			
			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("標的物下拉選單查詢成功!");
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("標的物下拉選單查詢失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "新增保批單標的物明細", response = JsonBean.class, tags = {"Rin1304Capi"}, notes = "新增保批單標的物明細")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertfripolicypropdtl")
	@ResponseBody
	public ResponseEntity<?> insertFriPolicyPropDtl(@ApiParam(value ="新增保批單標的物明細")@RequestBody FriPolicyProp parJson) throws Exception {
		log.debug(">>> Rin1304FriPolicyPropDtlController.insertFriPolicyPropDtl(新增保批單標的物明細)");
		JsonBean jsonBean = new JsonBean();
		int res=0;
		try {
			res=friPolicyPropService.insert(parJson);

			//回傳結果
				jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("新增保批單標的物明細成功!");

		}catch(Exception e) {
            
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("新增保批單標的物明細失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "修改保批單標的物明細", response = JsonBean.class, tags = {"Rin1304Capi"}, notes = "修改保批單標的物明細")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatefripolicypropdtl")
	@ResponseBody
	public ResponseEntity<?>updateFriPolicyPropDtl(@ApiParam(value ="修改保批單標的物明細")@RequestBody FriPolicyProp parJson) throws Exception {
		log.debug(">>> Rin1304FriPolicyPropDtlController.updateFriPolicyPropDtl(修改保批單標的物明細)");
		JsonBean jsonBean = new JsonBean();
		int res=0;
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			FriPolicyAdditionMapper friPolicyAdditionMapper = sqlSession.getMapper(FriPolicyAdditionMapper.class);
			FriPolicyPropMapper friPolicyPropMapper = sqlSession.getMapper(FriPolicyPropMapper.class);
			
			res=friPolicyPropService.update(parJson, friPolicyPropMapper);			
			
			//取第三層(prop)下所有第四層(addition)的資料
			FriPolicyAddition additionModel = new FriPolicyAddition();
			additionModel.setPolicyNo(parJson.getPolicyNo());
			additionModel.setEndorseNo(parJson.getEndorseNo());
			additionModel.setAddrNo(parJson.getAddrNo());
			List<FriPolicyAddition> additionList = friPolicyAdditionService.queryByFriPolicyAddition(additionModel);
			
			//依據流動註記更新保額保費資料至第二(dtl)、三層(prop)
			if(!additionList.isEmpty()) {
				for(FriPolicyAddition model: additionList) {
					rin1304DService.updatePropAndDtl(model, friPolicyAdditionMapper, friPolicyPropMapper, sqlSession);
				}
			}
			
			sqlSession.commit();
			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("修改保批單標的物明細成功!");

		}catch(Exception e) {
            
			sqlSession.rollback();
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("修改保批單標的物明細失敗!");
		}finally {
			sqlSession.close();
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * Rin1304_臨分分入，刪除保批單主檔
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除保批單標的物明細", response = JsonBean.class, tags = {"Rin1304Capi"}, notes = "用「保單號碼」、「批單號碼」、「地址序號」、「標的物序號」刪除保批單標的物明細")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deletefripolicypropdtl")
	@ResponseBody
	public ResponseEntity<?> deleteFriPolicyPropDtl(@ApiParam(value ="刪除保批單標的物明細")@RequestBody Rin1304DeletePolicyAllVORes parJson) throws Exception {
		log.debug(">>> Rin1304FriPolicyPropDtlController.deleteFriPolicyPropDtl(用「保單號碼」、「批單號碼」、「地址序號」、「標的物序號」刪除保批單標的物明細)");
		JsonBean jsonBean = new JsonBean();
	
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			
			
			FriPolicyPropMapper friPolicyPropMapper=sqlSession.getMapper(FriPolicyPropMapper.class);
			FriPolicyAdditionMapper friPolicyAdditionMapper=sqlSession.getMapper(FriPolicyAdditionMapper.class);
			

			String policyNo=parJson.getPolicyNo();
			String endorseNo=parJson.getEndorseNo();
			String addrNo=parJson.getAddrNo();
			String propNo=parJson.getPropNo();
			String additionSeq= "";
			
			
			//刪除保批單標的物檔
			friPolicyPropService.deletePolicyProp(policyNo, endorseNo,addrNo, propNo,friPolicyPropMapper);
			//刪除保批單附加險檔
			friPolicyAdditionService.deletePolicyAddition(policyNo, endorseNo, addrNo, propNo,additionSeq,friPolicyAdditionMapper);
			
			

			sqlSession.commit();
			
			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("刪除保批單標的物明細成功!");
		}catch(Exception e) {
            sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("刪除保批單標的物明細失敗!");
		}finally {
			sqlSession.close();
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * Rin1304_臨分分入，用「保單號碼」、「批單號碼」、「地址序號」查詢標的物序號
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查詢標的物序號最大值", response = JsonBean.class, tags = {"Rin1304Capi"}, 
			      notes = "用「保單號碼」、「批單號碼」、「地址序號」查詢標的物序號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/getmaxpropno")
	@ResponseBody
	public ResponseEntity<?> getMaxpropNo(@ApiParam(value ="查詢標的物序號最大值")@RequestBody Rin1304QueryPolicyPropVO parJson) throws Exception {
		log.debug(">>> Rin1304CController.getMaxpropNo(查詢標的物序號最大值)");
		JsonBean jsonBean = new JsonBean();
		List<Rin1304QueryPolicyPropVO> res =new ArrayList<>();
		String res2 ="";
		try {

			String policyNo=parJson.getPolicyNo();   //保單號碼
			String endorseNo=parJson.getEndorseNo(); //批單號碼
			String addrNo=parJson.getAddrNo();       //地址序號

			res=friPolicyPropService.getMaxpropNo(policyNo,endorseNo,addrNo);
			if(res.get(0)==null) {
				res2= "1";
			}else {
				res2=res.get(0).getPropNo();
			}

			//回傳結果
			jsonBean.setData(res2);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("查詢標的物序號最大值成功!");
		}catch(Exception e) {
		
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("查詢標的物序號最大值失敗!");
		}
		
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
}
