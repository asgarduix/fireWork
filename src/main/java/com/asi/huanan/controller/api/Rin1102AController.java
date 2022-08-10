package com.asi.huanan.controller.api;

import java.math.BigDecimal;
import java.text.ParseException;
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

import com.asi.huanan.service.FriComService;
import com.asi.huanan.service.FriTreatyBrokerService;
import com.asi.huanan.service.FriTreatyRincomService;
import com.asi.huanan.service.FriTreatyService;
import com.asi.huanan.service.dao.mybatis.mapper.FriComMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyBrokerMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyRincomMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreaty;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyBroker;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyRincom;
import com.asi.huanan.vo.Rin1102ABrokerVOResp;
import com.asi.huanan.vo.Rin1102AChkEnodeVOReq;
import com.asi.huanan.vo.Rin1102AChkRinIdUsableVOReq;
import com.asi.huanan.vo.Rin1102ADeleteOneTreatyVOReq;
import com.asi.huanan.vo.Rin1102AInsertOneTreatyVOReq;
import com.asi.huanan.vo.Rin1102AQueryOneReinserVOReq;
import com.asi.huanan.vo.Rin1102AQueryOneTreatyVOReq;
import com.asi.huanan.vo.Rin1102ARincomVOResp;
import com.asi.huanan.vo.Rin1102AUpdateOneTreatyVOReq;
import com.asi.huanan.vo.Rin1102AVOResp;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.connector.MyBatisConnector;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy
@RequestMapping("rin1102aapi")
@RestController
@Api(tags= {"Rin1102Aapi"})
public class Rin1102AController {
	
	private static Logger log = LogManager.getLogger(Rin1102AController.class);
	
	@Autowired
	private FriTreatyService friTreatyService;
	@Autowired
	private FriTreatyBrokerService friTreatyBrokerService;
	@Autowired
	private FriTreatyRincomService friTreatyRincomService;
	@Autowired
	private FriComService friComService;
	@Autowired
	private MyBatisConnector mybatis;
	
	
	
	
	/**
	 * 用「合約年度」、「合約代號」取得再保合約資料
	 * @param treatyYear
	 * @param treatyNo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "用「合約年度」、「合約代號」取得再保合約資料", response = JsonBean.class, tags = {"Rin1102Aapi"}, notes = "用「合約年度」、「合約代號」取得此再保合約完整資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryonetreaty")
	@ResponseBody
	public ResponseEntity<Object> queryOneTreaty(@ApiParam(value ="取得再保合約資料條件")@RequestBody Rin1102AQueryOneTreatyVOReq parJson) throws Exception {

		log.debug(">>> Rin1102AController.queryOneTreaty(用「合約年度」、「合約代號」取得再保合約資料)");

		JsonBean jsonBean = new JsonBean();
		
		Rin1102AVOResp res = new Rin1102AVOResp();
		
		List<FriTreaty> resTreaty = new ArrayList<>();
		List<Rin1102ARincomVOResp> resTreatyRincoms = new ArrayList<>();
		List<Rin1102ABrokerVOResp> resTreatyBrokers = new ArrayList<>();

		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		try {
			
			FriTreatyMapper friTreatyMapper = sqlSession.getMapper(FriTreatyMapper.class);
			FriTreatyRincomMapper friTreatyRincomMapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
			FriTreatyBrokerMapper friTreatyBrokerMapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
			
			//查詢合約資料
			resTreaty = friTreatyService.queryOneTreaty(parJson, friTreatyMapper);
			//查詢再保人資料
			resTreatyRincoms = friTreatyRincomService.queryTreatyRincoms(parJson, friTreatyRincomMapper);
			//查詢經紀人資料
			resTreatyBrokers = friTreatyBrokerService.queryTreatyBrokers(parJson, friTreatyBrokerMapper);
			
			sqlSession.commit();

			res.setFriTreaty(resTreaty.get(0));
			res.setFriTreatyRincomList(resTreatyRincoms);
			res.setFriTreatyBrokerList(resTreatyBrokers);
			//回傳結果
			jsonBean.setData(res);
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
	 * 新增再保合約資料
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "新增再保合約資料", response = JsonBean.class, tags = {"Rin1102Aapi"}, notes = "新增一筆再保合約資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/insertonetreaty")
	@ResponseBody
	public ResponseEntity<Object> insertOneTreaty(@ApiParam(value ="再保合約資料")@RequestBody Rin1102AInsertOneTreatyVOReq parJson) throws Exception {

		log.debug(">>> Rin1102AController.insertOneTreaty(新增再保合約資料)");

		JsonBean jsonBean = new JsonBean();
		int result = 0;
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
				
		try {
			
			FriTreatyMapper friTreatyMapper = sqlSession.getMapper(FriTreatyMapper.class);
			FriTreatyRincomMapper friTreatyRincomMapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
			FriTreatyBrokerMapper friTreatyBrokerMapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
			
			String treatyYear = parJson.getFriTreaty().getTreatyYear();	//合約年度
			String treatyNo = parJson.getFriTreaty().getTreatyNo();		//合約代號
			
			//參數處理(欄位預設值)
			FriTreaty model = this.fieldFriTreaty(parJson.getFriTreaty());
			
			//檢核資料庫是否已存在此筆資料
			result = friTreatyService.queryByFriTreaty(model, friTreatyMapper).size();
			if(result > 0) {
				jsonBean.setData("");
				jsonBean.setStatus(SysEnum.statusFinish.code);
				jsonBean.setMessage("此筆資料已存在!");
				
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}
			//執行新增合約資料
			friTreatyService.insert(model, friTreatyMapper);
			
			//參數處理
			List<FriTreatyRincom> rincomList = new ArrayList<>();
			FriTreatyRincom rincomModel = null;
			
			if(!parJson.getFriTreatyRincomList().isEmpty()) {
				
				for(Rin1102ARincomVOResp rincom : parJson.getFriTreatyRincomList()) {
					rincomModel = new FriTreatyRincom();

					rincomModel.setTreatyYear(treatyYear);										//合約年度
					rincomModel.setTreatyNo(treatyNo);											//合約代號
					rincomModel.setRinComId(rincom.getTxtrin_com_id1());						//再保人代號
					rincomModel.setRinComSeq(Short.valueOf(rincom.getNumrin_com_seq()));		//序號
					rincomModel.setRinComSname(rincom.getTxtrin_com_sname1());					//再保人名稱
					rincomModel.setRinComShare(new BigDecimal(rincom.getNumrin_com_share1()));	//分攤比例
					rincomModel.setRinComTax(new BigDecimal(rincom.getNumrin_com_tax()));		//代扣營業稅
						
					rincomList.add(rincomModel);
				}
				//執行新增再保人資料
				friTreatyRincomService.insertList(rincomList, friTreatyRincomMapper);
			}
			
			//參數處理
			List<FriTreatyBroker> brokerList = new ArrayList<>();
			FriTreatyBroker brokerModel = null;
			
			if(!parJson.getFriTreatyBrokerList().isEmpty()) {
				
				for(Rin1102ABrokerVOResp broker : parJson.getFriTreatyBrokerList()) {
					brokerModel = new FriTreatyBroker();
					
					brokerModel.setTreatyYear(treatyYear);										//合約年度
					brokerModel.setTreatyNo(treatyNo);											//合約代號
					brokerModel.setBrokerId(broker.getTxtBroker_id());							//經紀人代號
					brokerModel.setBrokerSname(broker.getTxtBroker_sname());					//經紀人名稱
					brokerModel.setRinComId(broker.getTxtrin_com_id2());						//再保人代號
					brokerModel.setRinComSname(broker.getTxtrin_com_sname2());					//再保人名稱
					brokerModel.setRinComShare(new BigDecimal(broker.getNumrin_com_share2()));	//再保人分攤比率
					
					brokerList.add(brokerModel);
				}
				//執行新增經紀人資料
				friTreatyBrokerService.insertList(brokerList, friTreatyBrokerMapper);
			}
				
			sqlSession.commit();

			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("再保合約資料新增成功!");
			

		} catch (Exception e) {
			
			sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("再保合約資料新增失敗!請聯絡管理人員!");

		}finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	/**
	 * 修改再保合約資料
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "修改再保合約資料", response = JsonBean.class, tags = {"Rin1102Aapi"}, notes = "修改一筆再保合約資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updateonetreaty")
	@ResponseBody
	public ResponseEntity<Object> updateOneTreaty(@ApiParam(value ="再保合約資料")@RequestBody Rin1102AUpdateOneTreatyVOReq parJson) throws Exception {

		log.debug(">>> Rin1102AController.updateOneTreaty(修改再保合約資料)");

		JsonBean jsonBean = new JsonBean();
		int result = 0;
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
				
		try {
			
			FriTreatyMapper friTreatyMapper = sqlSession.getMapper(FriTreatyMapper.class);
			FriTreatyRincomMapper friTreatyRincomMapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
			FriTreatyBrokerMapper friTreatyBrokerMapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
			
			String treatyYear = parJson.getFriTreaty().getTreatyYear();	//合約年度
			String treatyNo = parJson.getFriTreaty().getTreatyNo();		//合約代號
			//參數處理(欄位預設值)
			FriTreaty model = this.fieldFriTreaty(parJson.getFriTreaty());
			
			//檢核資料庫是否有此筆資料供修改
			result = friTreatyService.queryByFriTreaty(model, friTreatyMapper).size();
			if(result < 1) {
				jsonBean.setData("");
				jsonBean.setStatus(SysEnum.statusFinish.code);
				jsonBean.setMessage("此筆資料不存在!無法修改!");
				
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}
			
			//修改主檔資料
			friTreatyService.update(model, friTreatyMapper);
			
			//刪除兩個副表資料
			friTreatyRincomService.deleteByYearNo(treatyYear, treatyNo, friTreatyRincomMapper);
			friTreatyBrokerService.deleteByYearNo(treatyYear, treatyNo, friTreatyBrokerMapper);
			
			//參數處理
			List<FriTreatyRincom> rincomList = new ArrayList<>();
			FriTreatyRincom rincomModel = null;
			if(!parJson.getFriTreatyRincomList().isEmpty()) {
				for(Rin1102ARincomVOResp rincom : parJson.getFriTreatyRincomList()) {
					rincomModel = new FriTreatyRincom();
					
					rincomModel.setTreatyYear(treatyYear);										//合約年度
					rincomModel.setTreatyNo(treatyNo);											//合約代號	
					rincomModel.setRinComId(rincom.getTxtrin_com_id1());						//再保人代號
					rincomModel.setRinComSeq(Short.valueOf(rincom.getNumrin_com_seq()));		//序號
					rincomModel.setRinComSname(rincom.getTxtrin_com_sname1());					//再保人名稱
					rincomModel.setRinComShare(new BigDecimal(rincom.getNumrin_com_share1()));	//分攤比例
					rincomModel.setRinComTax(new BigDecimal(rincom.getNumrin_com_tax()));		//代扣營業稅
					
					rincomList.add(rincomModel);
				}
				//執行新增再保人資料
				friTreatyRincomService.insertList(rincomList, friTreatyRincomMapper);
			}
			
			List<FriTreatyBroker> brokerList = new ArrayList<>();
			FriTreatyBroker brokerModel = null;
			
			if(!parJson.getFriTreatyBrokerList().isEmpty()) {
				
				for(Rin1102ABrokerVOResp broker : parJson.getFriTreatyBrokerList()) {
					brokerModel = new FriTreatyBroker();
					
					brokerModel.setTreatyYear(treatyYear);										//合約年度
					brokerModel.setTreatyNo(treatyNo);											//合約代號
					brokerModel.setBrokerId(broker.getTxtBroker_id());							//經紀人代號
					brokerModel.setBrokerSname(broker.getTxtBroker_sname());					//經紀人名稱
					brokerModel.setRinComId(broker.getTxtrin_com_id2());						//再保人代號
					brokerModel.setRinComSname(broker.getTxtrin_com_sname2());					//再保人名稱
					brokerModel.setRinComShare(new BigDecimal(broker.getNumrin_com_share2()));	//再保人分攤比率
					
					brokerList.add(brokerModel);
				}
				//執行新增經紀人資料
				friTreatyBrokerService.insertList(brokerList, friTreatyBrokerMapper);
			}
			
			sqlSession.commit();

			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("再保合約資料修改成功!");
			

		} catch (Exception e) {
			
			sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("再保合約資料修改失敗!請聯絡管理人員!");

		}finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 刪除再保合約資料
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "刪除再保合約資料", response = JsonBean.class, tags = {"Rin1102Aapi"}, notes = "刪除一筆再保合約資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/deleteonetreaty")
	@ResponseBody
	public ResponseEntity<Object> deleteOneTreaty(@ApiParam(value ="再保合約資料")@RequestBody Rin1102ADeleteOneTreatyVOReq parJson) throws Exception {

		log.debug(">>> Rin1102AController.deleteOneTreaty(刪除再保合約資料)");

		JsonBean jsonBean = new JsonBean();
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
				
		try {
			
			FriTreatyMapper friTreatyMapper = sqlSession.getMapper(FriTreatyMapper.class);
			FriTreatyRincomMapper friTreatyRincomMapper = sqlSession.getMapper(FriTreatyRincomMapper.class);
			FriTreatyBrokerMapper friTreatyBrokerMapper = sqlSession.getMapper(FriTreatyBrokerMapper.class);
			
			String treatyYear = parJson.getTreatyYear();
			String treatyNo = parJson.getTreatyNo();
			
			//刪除合約資料
			friTreatyService.deleteByYearNo(treatyYear, treatyNo, friTreatyMapper);
			//刪除再保人資料
			friTreatyRincomService.deleteByYearNo(treatyYear, treatyNo, friTreatyRincomMapper);
			//刪除經紀人資料
			friTreatyBrokerService.deleteByYearNo(treatyYear, treatyNo, friTreatyBrokerMapper);
			
			sqlSession.commit();

			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("再保合約資料刪除成功!");
			

		} catch (Exception e) {
			
			sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("再保合約資料刪除失敗!請聯絡管理人員!");

		}finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	/**
	 * 檢核再保人註銷與適格
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "檢核再保人註銷與適格", response = JsonBean.class, tags = {"Rin1102Aapi"}, notes = "檢核再保人是否已註銷與適格姓")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/chkcomidusable")
	@ResponseBody
	public ResponseEntity<Object> chkComIdUsable(@ApiParam(value ="檢核再保人註銷與適格的條件")@RequestBody Rin1102AChkRinIdUsableVOReq parJson) throws Exception {

		log.debug(">>> Rin1102AController.chkComIdUsable(檢核再保人註銷與適格)");

		JsonBean jsonBean = new JsonBean();
		
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
				
		try {
			String msg = "";//檢核訊息
			int result = 0;
			
			FriComMapper friComMapper = sqlSession.getMapper(FriComMapper.class);
		
			
			//1.檢核註銷
			result = friComService.chkRemark(parJson, friComMapper);
			if(result > 0) {
				if("broker".equals(parJson.getType())) {
					msg = "本經紀人已註銷，不可選擇";
				}else if("reinser".equals(parJson.getType())){					
					msg = "本再保人已註銷，不可選擇";
				}
				
				jsonBean.setData("");
				jsonBean.setStatus(SysEnum.statusFinish.code);
				jsonBean.setMessage(msg);
				
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}
			
			//2.檢核適格性
			msg = friComService.chkRinQua(parJson, friComMapper);
			if(StringUtils.isNotBlank(msg)) {
				jsonBean.setData("");
				jsonBean.setStatus(SysEnum.statusFinish.code);
				jsonBean.setMessage(msg);
				
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			}			
			
			sqlSession.commit();

			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("");
			

		} catch (Exception e) {
			
			sqlSession.rollback();
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("再保人檢核失敗!請聯絡管理人員!");

		}finally {
			sqlSession.close();
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	

	/**
	 * 檢核最終支付公司
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "檢核最終支付公司", response = JsonBean.class, tags = {"Rin1102Aapi"}, notes = "檢核再保人經紀人最終支付公司")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/chkenode")
	@ResponseBody
	public ResponseEntity<Object> chkEnode(@ApiParam(value ="檢核最終支付公司的條件")@RequestBody Rin1102AChkEnodeVOReq parJson) throws Exception {

		log.debug(">>> Rin1102AController.chkEnode(檢核最終支付公司)");

		JsonBean jsonBean = new JsonBean();
				
		try {
			String rinComId = "";	//再保人代號
			String enode = "";		
			StringBuilder groupC = new StringBuilder();//已註銷的再保人
			StringBuilder groupN = new StringBuilder();//非最終支付公司的再保人
			String resultMsg = "";
			
			for(int i  = 0; i < parJson.getRinComIdList().size(); i++) {
				rinComId = parJson.getRinComIdList().get(i);
				enode = friComService.chkEnode(rinComId);
			
				if(StringUtils.isNotBlank(enode)) {					
					switch(enode) {
					case "Y":
						break;				
					case "C"://已註銷
						groupC.append(rinComId).append(",");
						break;
					case "N"://非最終支付公司
						if(!parJson.getBrokerIdList().contains(rinComId)) {
							groupN.append(rinComId).append(",");
						}
						break;
					default:
						break;					
					}
				}
			}
		
			//處理訊息
			if(groupC.length() > 0) {
				resultMsg += "寫入失敗!再保人"+StringUtils.chop(groupC.toString())+"已註銷，請修正後再試!\n";			
			}
			if(groupN.length() > 0) {
				resultMsg += "寫入失敗!再保人"+StringUtils.chop(groupN.toString())+"非為最終支付公司，必須維護第二層再保人資料，請修正後再試!";
			}
			
			//回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage(resultMsg);
			

		} catch (Exception e) {
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);
			jsonBean.setMessage("檢核最終支付公司失敗!請聯絡管理人員!");

		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	
	/**
	 * 用「再保人/經紀人代號」取得再保人/經紀人名稱
	 * @param reinser
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "用「再保人/經紀人代號」取得再保人/經紀人名稱", response = JsonBean.class, tags = {"Rin1102Aapi"}, notes = "用「再保人/經紀人代號」取得再保人/經紀人名稱")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryonereinser")
	@ResponseBody
	public ResponseEntity<Object> queryOneReinser(@ApiParam(value ="「再保人/經紀人代號」")@RequestBody Rin1102AQueryOneReinserVOReq parJson) throws Exception {

		log.debug(">>> Rin1102AController.queryOneReinser(用「再保人/經紀人代號」取得再保人/經紀人名稱");

		JsonBean jsonBean = new JsonBean();
		
		String ename = "";//再保人/經紀人名稱

		try {
			
			ename = friComService.queryOneReinser(parJson.getReinser());
	
			//回傳結果
			jsonBean.setData(ename);
			jsonBean.setStatus(SysEnum.statusSuccess.code);		

		} catch (Exception e) {
			
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusError.code);

		}
		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}
	
	//新增、修改時，合約資料(fritreaty欄位)必填欄位沒給值的話，給預設值
	private FriTreaty fieldFriTreaty(FriTreaty model) throws ParseException{
		
		BigDecimal zero = BigDecimal.ZERO;
		short zeroShort = 0;
		
		// --------------共用區塊--------------
		//虧損轉移年數
		if(model.getLoseKeepYear() == null) {
			model.setLoseKeepYear(zeroShort);			
		}
		//未滿期保費計算
		if(StringUtils.isBlank(model.getNpremcalType())) {
			model.setNpremcalType("1");
		}
		// --------------利率及稅率--------------
		//未滿期保費利息利率
		if(model.getNpreminstRate() == null) {
			model.setNpreminstRate(zero);
		}
		//未滿期保費利息稅率
		if(model.getNpremtaxRate() == null) {
			model.setNpremtaxRate(zero);
		}
		//未決賠款計算比例
		if(model.getNripaycalRate() == null) {
			model.setNripaycalRate(zero);
		}
		//代扣營業稅率
		if(model.getBusinesstaxRate() == null) {
			model.setBusinesstaxRate(zero);
		}
		//印花稅率
		if(model.getStamptaxRate() == null) {
			model.setStamptaxRate(zero);
		}
		//管理率
		if(model.getHandlingRate() == null) {
			model.setHandlingRate(zero);
		}
		//代理率
		if(model.getAgentRate() == null) {
			model.setAgentRate(zero);
		}
		//再保費提存率
		if(model.getRipremRate() == null) {
			model.setRipremRate(zero);
		}
		//再保費提存利息利率
		if(model.getRipreminstRate() == null) {
			model.setRipreminstRate(zero);
		}
		//再保費提存代扣稅率
		if(model.getRipremtaxRate() == null) {
			model.setRipremtaxRate(zero);
		}
		//賠款提存率
		if(model.getRipayRate() == null) {
			model.setRipayRate(zero);
		}
		//賠款提存利息利率
		if(model.getRipayinstRate() == null) {
			model.setRipayinstRate(zero);
		}
		//賠款提存代扣稅率
		if(model.getRipaytaxRate() == null) {
			model.setRipaytaxRate(zero);
		}
		//預估保費收入(EST.P.I)
		if(model.getExceedestPI() == null) {
			model.setExceedestPI(0L);
		}
		//責任額
		if(model.getExceedduty() == null) {
			model.setExceedduty(0L);
		}
		//起賠
		if(model.getExceedripaybegin() == null) {
			model.setExceedripaybegin(0L);
		}
		//費率%
		if(model.getExceedestEp() == null) {
			model.setExceedestEp(0L);
		}
		//預估再保費(Est.EP)
		if(model.getExceedestEp() == null) {
			model.setExceedestEp(0L);
		}
		//扣款後預估再保費(M&D)
		if(model.getExceedestEpMd() == null) {
			model.setExceedestEpMd(0L);
		}
		//復效次數(Reinstateme)
		if(model.getExceedreinstateme() == null) {
			model.setExceedreinstateme(0L);
		}
		//責任費率%(R.O.L)
		if(model.getExceedrOL() == null) {
			model.setExceedrOL(zero);
		}
		//29險種03分配比例
		if(model.getClass2903rate() == null) {
			model.setClass2903rate(zero);
		}
		//29險種25分配比例
		if(model.getClass2925rate() ==  null) {
			model.setClass2925rate(zero);
		}
		//29險種28分配比例
		if(model.getClass2928rate() == null) {
			model.setClass2928rate(zero);
		}
		
		
		// --------------限額及除外業務--------------
		//共保比率
		if(model.getCoinsRate() == null) {
			model.setCoinsRate(zero);
		}
		//自留額倍數
		if(model.getRetainTimes() == null) {
			model.setRetainTimes(zero);
		}
		//出險通知
		if(model.getAccidentNotice() == null) {
			model.setAccidentNotice(0L);
		}
		//現金攤賠
		if(model.getCashCall() == null) {
			model.setCashCall(0L);
		}
		//TIA
		if(model.getLimitTia() == null) {
			model.setLimitTia(0L);
		}
		//純火規章佣金率
		if(model.getFirrulcomRate() == null) {
			model.setFirrulcomRate(zero);
		}
		//附加規章佣金率
		if(model.getAddrulcomRate() == null) {
			model.setAddrulcomRate(zero);
		}
		//地震規章佣金率
		if(model.getEarrulcomRate() == null) {
			model.setEarrulcomRate(zero);
		}
		//颱洪規章佣金率
		if(model.getTyprulcomRate() == null) {
			model.setTyprulcomRate(zero);
		}
		//純火專案佣金率
		if(model.getFirprjcomRate() == null) {
			model.setFirprjcomRate(zero);
		}
		//附加專案佣金率
		if(model.getAddprjcomRate() == null) {
			model.setAddprjcomRate(zero);
		}
		//地震專案佣金率
		if(model.getEarprjcomRate() == null) {
			model.setEarprjcomRate(zero);
		}
		//颱洪專案佣金率
		if(model.getTypprjcomRate() == null) {
			model.setTypprjcomRate(zero);
		}
		//純火非規章佣金率
		if(model.getFirnrucomRate() == null) {
			model.setFirnrucomRate(zero);
		}
		//附加非規章佣金率
		if(model.getAddnrucomRate() == null) {
			model.setAddnrucomRate(zero);
		}
		//地震非規章佣金率
		if(model.getEarnrucomRate() == null) {
			model.setEarnrucomRate(zero);
		}
		//颱洪非規章佣金率
		if(model.getTypnrucomRate() == null) {
			model.setTypnrucomRate(zero);
		}
		//
		
		return model;
	}
}
