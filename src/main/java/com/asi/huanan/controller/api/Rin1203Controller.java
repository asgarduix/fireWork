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

import com.asi.huanan.service.FriTempareaService;
import com.asi.huanan.service.UsedAreaService;
import com.asi.huanan.service.dao.mybatis.mapper.UsedAreaMapper;
import com.asi.huanan.service.dao.mybatis.model.UsedArea;
import com.asi.huanan.vo.DeleteUsedArea;
import com.asi.huanan.vo.QueryFriTempareaVo;
import com.asi.huanan.vo.Rin1203Vo;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.SpringProperty;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1203api")
@RestController
@Api(tags = { "Rin1203api" })
public class Rin1203Controller {
	private static Logger log = LogManager.getLogger(Rin1203Controller.class);
	
	@Autowired
	private FriTempareaService friTempareaService;
	
	@Autowired
	private MyBatisConnector mybatis;
	
	@Autowired
	private UsedAreaService usedAreaService;
	
	
	/**
	 * 搜尋「同險設定資料」
	 * @param 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "搜尋「同險設定資料」", response = JsonBean.class, tags = {"Rin1203api"}, notes = "用「印單日期起迄」﹑「地段代號」﹑「範圍」做「同險設定資料」模糊查詢")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryfritemparea")
	@ResponseBody
	public ResponseEntity<?> queryFriTtemparea(@ApiParam(value ="「同險設定資料」搜尋條件")@RequestBody QueryFriTempareaVo parJson) throws Exception {
		log.debug(">>> Rin1203Controller.queryFriTtemparea(搜尋「同險設定資料」)");
		JsonBean jsonBean = new JsonBean();
		
		List<Rin1203Vo> res = new ArrayList<Rin1203Vo>();

		try {
			
			res = friTempareaService.queryTemparea(parJson.getTxtarea_code(),parJson.getRisk_flag());

			//回傳結果
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
	 * 檢核地段代號是否上鎖
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "檢核地段代號是否上鎖", response = JsonBean.class, tags = {"Rin1203api"}, notes = "檢核地段代號是否上鎖")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryareacodeislock")
	@ResponseBody
	public ResponseEntity<?> queryAreaCodeIsLock(@ApiParam(value ="檢核地段代號是否上鎖")@RequestBody  UsedArea  parJson) throws Exception {

		log.debug(">>> Rin1203Controller.queryAreaCodeIsLock(檢核地段代號是否上鎖)");

		JsonBean jsonBean = new JsonBean();
		int result = 0;
		
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
				
		try {
			
			UsedAreaMapper usedAreaMapper = sqlSession.getMapper(UsedAreaMapper.class);
			String areaCode=parJson.getAreaCode();
			
			UsedArea model = new UsedArea();
			model.setAreaCode(areaCode);
			
			//查詢資料庫是否已上鎖
			result=usedAreaService.queryAreaCodeIsLock(model,usedAreaMapper).size();
		
			if(result>0) {
				//回傳結果
				jsonBean.setData("");
				jsonBean.setStatus(SysEnum.statusFinish.code);
				jsonBean.setMessage("此地區處理中!不可點選");
				
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}
			usedAreaService.insert(model, usedAreaMapper);
			sqlSession.commit();

			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("紀錄上鎖資料新增成功!");
				
			
		} catch (Exception e) {
			
			sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("檢核地段代號是否上鎖失敗!");

		}finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	
	/**
	 * 清除地段代號
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "清除地段代號", response = JsonBean.class, tags = {"Rin1203Aapi"}, notes = "清除地段代號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deleteusedareain")
	@ResponseBody
	public ResponseEntity<?> deleteUsedAreaIn(@ApiParam(value ="清除地段代號")@RequestBody  List<DeleteUsedArea> parJson) throws Exception {

		log.debug(">>> Rin1203Controller.deleteUsedAreaIn(清除地段代號)");

		JsonBean jsonBean = new JsonBean();

		try {
			
			
		if(!parJson.isEmpty()){
			
	          usedAreaService.deleteUsedAreaIn(parJson);
			
		}
	
			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("清除地段代號成功!");
			

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("清除地段代號失敗!");
		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	/**
	 * 取得.yml使用者設定的年
	 * @param 
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "取得.yml使用者設定的年", response = JsonBean.class, tags = {"Rin1203api"}, notes = "取得.yml使用者設定的年")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryusersetyear")
	@ResponseBody
	public ResponseEntity<?> queryUserSetYear() throws Exception {
		log.debug(">>> Rin1203Controller.queryUserSetYear(取得.yml使用者設定的年)");
		JsonBean jsonBean = new JsonBean();
        
		try {
            Integer setYear=SpringProperty.SetYear;
			//回傳結果
			jsonBean.setData(setYear);
			jsonBean.setData(2);
			jsonBean.setStatus(SysEnum.statusSuccess.code);

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);

		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
}
