package com.asi.huanan.controller.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.exceptions.PersistenceException;
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

import com.asi.huanan.service.ComAutonumService;
import com.asi.huanan.service.FriPolicyDtlService;
import com.asi.huanan.service.FriRiskService;
import com.asi.huanan.service.FriTempareaService;
import com.asi.huanan.service.UsedAreaService;
import com.asi.huanan.service.customerize.CustomerizeService;
import com.asi.huanan.service.dao.mybatis.mapper.ComAutonumMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriRiskMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriTempareaMapper;
import com.asi.huanan.service.dao.mybatis.mapper.UsedAreaMapper;
import com.asi.huanan.vo.QueryAreaPolicyVo;
import com.asi.huanan.vo.QueryByNumbeCodeVo;
import com.asi.huanan.vo.QueryRiskListVo;
import com.asi.huanan.vo.Rin1203AVOResp;
import com.asi.huanan.vo.UpdateFriPolicyDel;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.SpringProperty;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * @author sophia_hung
 *
 */
@Lazy
@RequestMapping("rin1203aapi")
@RestController
@Api(tags = { "Rin1203Aapi" })
public class Rin1203AController {

	private static Logger log = LogManager.getLogger(Rin1203AController.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private CustomerizeService customerizeService;
	
	@Autowired
	private MyBatisConnector mybatis;
	
	@Autowired
	private UsedAreaService usedAreaService;
	
	@Autowired
	private FriTempareaService friTempareaService;
	
	@Autowired
	private FriPolicyDtlService friPolicyDtlService;
	
	@Autowired
	private FriRiskService friRiskService;
	
	@Autowired
	private ComAutonumService comAutonumService;
	
	
	
	
	/**
	 * 用「印單日期起迄」、「地段代號」取得同險設定資料
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "用「印單日期起迄」、「地段代號」取得同險設定資料", response = JsonBean.class, tags = {"Rin1203Aapi"}, notes = "用「印單日期起迄」、「地段代號」取得同險設定完整資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryareapolicy")
	@ResponseBody
	public ResponseEntity<?> queryAreaPolicy(@ApiParam(value ="取得同險設定資料條件")@RequestBody QueryAreaPolicyVo parJson) throws Exception {

		log.debug(">>> Rin1203AController.queryAreaPolicy(用「印單日期起迄」、「地段代號」取得同險設定資料)");
		
		JsonBean jsonBean = new JsonBean();
		
		List<Rin1203AVOResp> result = new ArrayList<>();
		String policyDprtS="";
		String policyDprtE="";
		String queryDprtS=parJson.getPolicy_dprtS();
		String queryDprtE=parJson.getPolicy_dprtE();
		
		
		try {
			//系統時間、使用者設定的N年前時間
			 Integer setYear=SpringProperty.SetYear;
			 Calendar c = Calendar.getInstance();
			 c.setTime(new Date());
			 c.add(Calendar.YEAR, -(setYear));
			 Date time = c.getTime();
			 String twoYearago=dateFormat.format(time);
			 String today=dateFormat.format(new Date());
			 
			 int before = queryDprtS.compareTo(twoYearago);
			 int after  = queryDprtE.compareTo(today);

			 
			 if(queryDprtS.equals("")||before<0) {
				 policyDprtS = twoYearago;
			 }else {
				 policyDprtS=queryDprtS;
			 }
			 
			 if(queryDprtE.equals("")||after>0) {
				 policyDprtE= today;
			 }else {
				 policyDprtE=queryDprtE;
			 }
			 
			 result = customerizeService.queryAreaPolicy(policyDprtS, policyDprtE, parJson.getTxtarea_code());
			 //給資料id
			 for(int i = 0; i < result.size(); i++) {
				result.get(i).setId(i+1);
			 }	
			
			//回傳結果
			jsonBean.setData(result);
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
	 * 處理其他地段
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "處理其他地段代號", response = JsonBean.class, tags = {"Rin1203Aapi"}, notes = "處理其他地段代號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deleteareacode")
	@ResponseBody
	public ResponseEntity<?> deleteAreaCode(@ApiParam(value ="處理其他地段代號")@RequestBody  Rin1203AVOResp  parJson) throws Exception {

		log.debug(">>> Rin1203AController.deleteAreaCode(處理其他地段代號)");

		JsonBean jsonBean = new JsonBean();
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
				
		try {
			
			FriTempareaMapper friTempareaMapper =sqlSession.getMapper(FriTempareaMapper.class);
			UsedAreaMapper usedAreaMapper = sqlSession.getMapper(UsedAreaMapper.class);
			
			
			
			String areaCode = parJson.getTxtarea_code();
			int procCount =parJson.getProcCount();
			
			//當列表中處理狀態其中一個為N,fri_temparea.proc_count =0 
			friTempareaService.updateTemparea(procCount,areaCode,friTempareaMapper);
			
			
			//解鎖
			if(StringUtils.isNotEmpty(areaCode)) {
			usedAreaService.deleteByKey(areaCode,usedAreaMapper);
			}
			
			sqlSession.commit();

			
			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("處理其他地段代號刪除成功!");
			

		} catch (Exception e) {
			
			sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("處理其他地段代號刪除失敗!");

		}finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	
	/**
	 * 更正地段
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "更正地段", response = JsonBean.class, tags = {"Rin1203Aapi"}, notes = "更正地段")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updareacode")
	@ResponseBody
	public ResponseEntity<?> updAreaCode(@ApiParam(value ="更正地段")@RequestBody  Rin1203AVOResp  parJson) throws Exception {

		log.debug(">>> Rin1203AController.updAreaCode(更正地段)");

		JsonBean jsonBean = new JsonBean();

		try {
			
			String areaCode = parJson.getTxtarea_code();
			String policyNo = parJson.getTxtpolicy_no();
			String endorseNo =parJson.getTxtendorse_no();
			Integer addrNo=parJson.getNumaddr_no();
			
			friPolicyDtlService.updateAreaCode(areaCode,policyNo,endorseNo,addrNo);


			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("更正地段成功!");
			

		} catch(PersistenceException sqlException) {
			
			log.debug(sqlException.toString());
			Arrays.asList(sqlException.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			
			jsonBean.setMessage("地段代號資料錯誤!");
			
		}catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("更正地段失敗!");
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	
	/**
	 * 更正地址
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "更正地址", response = JsonBean.class, tags = {"Rin1203Aapi"}, notes = "更正地址")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updateaddress")
	@ResponseBody
	public ResponseEntity<?> updateAddress(@ApiParam(value ="更正地址")@RequestBody  Rin1203AVOResp  parJson) throws Exception {

		log.debug(">>> Rin1203AController.updateAddress(更正地址)");

		JsonBean jsonBean = new JsonBean();

		try {
			
			String propAddr = parJson.getTxtprop_addr();
			String policyNo = parJson.getTxtpolicy_no();
			String endorseNo =parJson.getTxtendorse_no();
			Integer addrNo=parJson.getNumaddr_no();
			
			friPolicyDtlService.updateAddress(propAddr,policyNo,endorseNo,addrNo);


			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("更正地址成功!");
			

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("更正地址失敗!");
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	
	/**
	 * 更正同險號
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "更正同險號", response = JsonBean.class, tags = {"Rin1203Aapi"}, notes = "更正同險號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updateriskno")
	@ResponseBody
	public ResponseEntity<?> updateRiskNo(@ApiParam(value ="更正同險號")@RequestBody  Rin1203AVOResp  parJson) throws Exception {

		log.debug(">>> Rin1203AController.updateRiskNo(更正同險號)");

		JsonBean jsonBean = new JsonBean();

		try {
			
			
			String riskNo =parJson.getTxtrisk_no();
			String riskName =parJson.getTxtrisk_name();
			String policyNo = parJson.getTxtpolicy_no();
			String endorseNo =parJson.getTxtendorse_no();
			Integer addrNo=parJson.getNumaddr_no();
			
			friPolicyDtlService.updateRiskNo(riskNo,riskName,policyNo,endorseNo,addrNo);


			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("更正同險號成功!");
			

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("更正同險號失敗!");
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	
	}
	
	
	
	/**
	 * 刪除同險
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除同險", response = JsonBean.class, tags = {"Rin1203Aapi"}, notes = "刪除同險")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/delateriskno")
	@ResponseBody
	public ResponseEntity<?> deteleRiskNo(@ApiParam(value ="刪除同險")@RequestBody  Rin1203AVOResp  parJson) throws Exception {

		log.debug(">>> Rin1203AController.deteleRiskNo(刪除同險)");

		JsonBean jsonBean = new JsonBean();
		int res = 0;
		int serial=0;
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			
			FriRiskMapper friRiskMapper=sqlSession.getMapper(FriRiskMapper.class);
			ComAutonumMapper comAutonumMapper=sqlSession.getMapper(ComAutonumMapper.class);
			String riskNo =parJson.getTxtrisk_no();
			String areaCode=parJson.getTxtarea_code();
			
			if(StringUtils.isNotEmpty(riskNo)) {
				
				res=friRiskService.deleteByKey(riskNo,friRiskMapper);
				
				//刪除同險後,序號-1
		    	comAutonumService.minusComAutonumSerial(areaCode,comAutonumMapper);
		    	
		    	
		    	//查詢流水號
		    	serial=comAutonumService.getRiskNoSerial(areaCode,comAutonumMapper);
		    	
		    	
		    	//若是序號<=0，則整筆資料刪除
		    	if(serial<=0) {
		    		comAutonumService.deleteOneComAutonumList(areaCode,comAutonumMapper);
		    	}
			}
			
			sqlSession.commit();
		
			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("刪除同險成功!");
			

		} catch (Exception e) {
			sqlSession.rollback();
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("刪除同險失敗!");
		}finally {
			
			sqlSession.close();
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	
	
	/**
	 * 新增同險
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "新增同險資料", response = JsonBean.class, tags = { "Rin1203api" }, notes = "新增同險")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertfririskbypk")
	public ResponseEntity<?> insertFriRiskByPk(@ApiParam(value ="新增同險")@RequestBody  Rin1203AVOResp  parJson)
			throws Exception {

		log.debug(">>> Rin1203AController.insertFriRiskByPk(新增同險)");

		JsonBean jsonBean = new JsonBean();

		
		int res = 0;
	    int resList=0;
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			FriRiskMapper friRiskMapper=sqlSession.getMapper(FriRiskMapper.class);
			ComAutonumMapper comAutonumMapper=sqlSession.getMapper(ComAutonumMapper.class);
				
			    String riskNo =parJson.getTxtrisk_no();
			    String riskName =parJson.getTxtrisk_name();
			    String areaCode =parJson.getTxtarea_code();
				
			        //新增同險
			    	res = friRiskService.insertRiskNoByPk(riskNo,riskName,areaCode,friRiskMapper);
			    	
			    	//查詢有無這筆資料
			    	resList=comAutonumService.getRiskNoSerial(areaCode,comAutonumMapper);
			    	
			    	if(resList<=0) {
			    		comAutonumService.insertOneListByNumberCode(areaCode,comAutonumMapper);
			    	}
			    	//新增完後序號+1
			    	comAutonumService.plusComAutonumSerial(areaCode,comAutonumMapper);
			    	
			    
			    sqlSession.commit();
				// 回傳結果
				jsonBean.setData(res);
				jsonBean.setStatus(SysEnum.statusSuccess.code);


		} catch (Exception e) {
			sqlSession.rollback();

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("新增同險資料失敗!");


		}finally {
			sqlSession.close();
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	
	
	
	/**
	 * 確認同險按鈕
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "確認同險", response = JsonBean.class, tags = {"Rin1203Aapi"}, notes = "確認同險(多筆)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatepolicydellist")
	@ResponseBody
	public ResponseEntity<?> updatePolicyDelList(@ApiParam(value = "確認同險") @RequestBody UpdateFriPolicyDel parJson) throws Exception {

		log.debug(">>> Rin1203AController.updatePolicyDelList(確認同險)");

		JsonBean jsonBean = new JsonBean();
		

		try {
			

          friPolicyDtlService.updatePolicyDelList(parJson.getRiskDdlList(),parJson.getRiskNo(),parJson.getRiskName());

		
			// 回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("確認同險更新成功!");
			

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("確認同險失敗!");
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	
	}
	
	
	
	/**
	 * 產生同險代號(查詢流水號)
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "產生同險代號(查詢流水號)", response = JsonBean.class, tags = {"Rin1203Aapi"}, notes = "產生同險代號(查詢流水號)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/getrisknoserial")
	@ResponseBody
	public ResponseEntity<?> getRiskNoSerial(@ApiParam(value = "產生同險代號(查詢流水號)") @RequestBody QueryByNumbeCodeVo parJson) throws Exception {

		log.debug(">>> Rin1203AController.getRiskNoSerial(產生同險代號(查詢流水號))");

		JsonBean jsonBean = new JsonBean();
		int res = 0;
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			
			ComAutonumMapper comAutonumMapper=sqlSession.getMapper(ComAutonumMapper.class);
			//查詢流水號
			res=comAutonumService.getRiskNoSerial(parJson.getNumber_code(),comAutonumMapper);
			
			//沒資料就給1
			if(res==0) {
				res = 1;
				
			//判別有資料就序號+1
			}else {
				res+=1;
			}
			
			// 回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("產生同險代號(查詢流水號)成功!");
			

		} catch (Exception e) {
			sqlSession.rollback();
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("產生同險代號(查詢流水號)失敗!");
		}finally {
			sqlSession.close();
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	
	}
	

	/**
	 * 用「地段代號」取得同險資料(下拉選單)
	 * @param parJson
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "用「地段代號」取得同險資料", response = JsonBean.class, tags = {"Rin1203Aapi"}, notes = "用「地段代號」取得同險資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryddlrisklist")
	@ResponseBody
	public ResponseEntity<?> queryDdlRiskList(@ApiParam(value ="取得同險資料條件")@RequestBody QueryRiskListVo parJson) throws Exception {

		log.debug(">>> Rin1203AController.queryDdlRiskList(用「地段代號」取得同險資料)");

		JsonBean jsonBean = new JsonBean();
		
		List<QueryRiskListVo> res = new ArrayList<>();
		
		try {
			
			res = friRiskService.queryDdlRiskList(parJson.getTxtarea_code());

	
			//回傳結果
			jsonBean.setData(res);
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("取得同險資料成功!");
			

		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);

		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	

	@ApiOperation(value = "更改舊同險號", response = JsonBean.class, tags = {"Rin1203Aapi"}, notes = "更改舊同險號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updateoldriskno")
	@ResponseBody
	public ResponseEntity<?> updateOldRiskNo(@ApiParam(value ="更改舊同險號")@RequestBody  Rin1203AVOResp  parJson) throws Exception {

		log.debug(">>> Rin1203AController.updateOldRiskNo(更改舊同險號)");

		JsonBean jsonBean = new JsonBean();

		try {
			
			
			String riskNo =parJson.getTxtrisk_no();
			String riskName =parJson.getTxtrisk_name();
			String policyNo = parJson.getTxtpolicy_no();
			String endorseNo =parJson.getTxtendorse_no();
			String propAddr =parJson.getTxtprop_addr();
			
			friPolicyDtlService.updateOldRiskNo(riskNo,riskName,policyNo,endorseNo,propAddr);


			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("更改舊同險號成功!");
			

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("更改舊同險號失敗");
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	
	}
}
