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
	 * 搜尋「合約號資料」
	 *
	 * @param cessionNO
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "搜尋「合約號資料」", response = JsonBean.class, tags = {
			"Rin1303api" }, notes = "用合約號搜尋「合約號資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/querycessionnolist")
	@ResponseBody
	public ResponseEntity<Object> queryCessionNoList(@ApiParam(value = "合約號條件") @RequestBody Rin1303QueryCesNoVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1303Controller.queryCessionNoList(搜尋「合約號資料」)");

		JsonBean jsonBean = new JsonBean();

		List<Rin1303QueryCesNoVOResp> res = new ArrayList<>();

		try {
			
			res = friFacService.queryCessionNoList(parJson.getCessionNo());

			// 回傳結果
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
	 * 搜尋「臨分再保人資料」
	 *
	 * @param slipNo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "搜尋「臨分再保人資料」", response = JsonBean.class, tags = {
			"Rin1303api" }, notes = "用更正號搜尋「臨分再保人資料」")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/queryrincombyslipno")
	@ResponseBody
	public ResponseEntity<Object> queryRinComBySlipNo(@ApiParam(value = "更正號") @RequestBody Rin1303QueryRinComBySlipNoVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1303Controller.queryRinComBySlipNo(搜尋「臨分再保人資料」)");

		JsonBean jsonBean = new JsonBean();

		List<Rin1303QueryRinComBySlipNoVOResp> res = new ArrayList<>();

		try {
			
			res = customerizeService.queryRinComBySlipNo(parJson.getSlipNo());
			
			//處理傳送回前端欲顯示的文字
			for(Rin1303QueryRinComBySlipNoVOResp model: res) {
				//已列印否
				if(StringUtils.isNotBlank(model.getTxtacct_flag())) {
					switch(model.getTxtacct_flag()) {
					case "Y":
						model.setTxtacct_flag("Y:已列印");
						break;
					case "N":
						model.setTxtacct_flag("N:未列印");
						break;
					default:
						break;
					}
				}
				//轉檔狀況
				if(StringUtils.isNotBlank(model.getTxtTurn_flag())) {
					switch(model.getTxtTurn_flag()){
						case "N":
							model.setTxtTurn_flag("N:不轉檔");
							break;
						case "H":
							model.setTxtTurn_flag("H:暫緩");
							break;
						case "Y":
							model.setTxtTurn_flag("Y:正常轉檔");
							break;
						default:
							break;
					}
				}
			}

			// 回傳結果
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
	 * 設定本再保人轉檔狀況
	 *
	 * @param bill_no_external
	 * @param transfer_status
	 * @param slip_no
	 * @param rin_com_id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "設定本再保人轉檔狀況", response = JsonBean.class, tags = {
			"Rin1303api" }, notes = "用更正號與再保人代號當條件，設定本再保人轉檔狀況與外部帳單號碼")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/updatestatusbynoid")
	@ResponseBody
	public ResponseEntity<Object> updateStatusByNoId(@ApiParam(value = "設定轉檔狀況") @RequestBody Rin1303UpdateStatusByNoIdVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1303Controller.updateStatusByNoId(設定本再保人轉檔狀況)");

		JsonBean jsonBean = new JsonBean();

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		int result = 0;
		
		try {		
			FriFacRincomMapper friFacRincomMapper = sqlSession.getMapper(FriFacRincomMapper.class);
			FriAccountingMapper friAccountingMapper = sqlSession.getMapper(FriAccountingMapper.class);
			
			//更新fri_fac_rincom 轉檔狀況
			result = friFacRincomService.updateStatusByNoId(parJson, friFacRincomMapper);
			
			//更新成功且轉檔狀況改為"否(N)"或"暫緩(H)"時，刪除fri_accounting對應資料
			if(result != 0 && StringUtils.equalsAny(parJson.getTransferStatus(), "N","H")) {
				friAccountingService.deleteChangeStatusRin1303(parJson.getSlipNo(), friAccountingMapper);
			}
			
			sqlSession.commit();

			// 回傳結果
			jsonBean.setData("");
			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("已完成"+result+"筆更新");

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
	 * 列印報表
	 *
	 * @param bill_no_external
	 * @param transfer_status
	 * @param slip_no
	 * @param rin_com_id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "列印報表", response = JsonBean.class, tags = {
			"Rin1303api" }, notes = "列印報表")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/printrin1303report")
	@ResponseBody
	public ResponseEntity<Object> printRin1303Report(@ApiParam(value = "列印報表") @RequestBody Rin1303PrintVOReq parJson)
			throws Exception {

		log.debug(">>> Rin1303Controller.printRin1303Report(列印報表)");

		JsonBean jsonBean = new JsonBean();
		
		try {		
			//列印報表
			jsonBean = rin1303Service.rin1303PrintPdf(parJson);

		} catch (Exception e) {

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));

			jsonBean.setMessage("「列印」失敗!!!請聯絡管理人員!!!");
			jsonBean.setStatus(SysEnum.statusError.code);

		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}


}
