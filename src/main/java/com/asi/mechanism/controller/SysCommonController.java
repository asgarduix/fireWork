package com.asi.mechanism.controller;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.huanan.controller.api.common.ExcelController;
import com.asi.huanan.service.BatchlogService;
import com.asi.huanan.service.BatchqueueService;
import com.asi.huanan.service.customerize.CustomerizeService;
import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.SpringProperty;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.security.jwtref.JwtTokenUtil;
import com.asi.mechanism.service.connector.DBConnectionSqlite;
import com.asi.swissknife.Asiutil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Lazy

@RequestMapping("syscommon")
@RestController
@Api(value = "api_value")
public class SysCommonController {

	private static Logger log = LogManager.getLogger(SysCommonController.class);

	@Autowired
	private CustomerizeService customerizeService;

	@Autowired
	private ExcelController excelController;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private DBConnectionSqlite sqlite;

	@Autowired
	private BatchlogService batchlogService;

	@Autowired
	private BatchqueueService batchqueueService;

	@Value("spring.config.activate.on-profile")
	private String profile;

	/**
	 * 由電腦名稱取2碼查詢承作地區
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	@ApiOperation(value = "由電腦名稱取2碼查詢承作地區", response = JsonBean.class, tags = { "common" }, notes = "由電腦名稱取2碼查詢承作地區")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/getDoarea")
	@ResponseBody
	public ResponseEntity<?> getDoarea(HttpServletRequest httpServletRequest,
			@RequestParam(name = "ip", required = false) String ip,
			@RequestParam(name = "emplno", required = false) String emplno,
			@RequestHeader(value = "Authorization", required = true) String token) {
		log.debug("LEO.................................start");
		JsonBean jsonBean = new JsonBean();
		Gson gson = new Gson();
		String computerName = "";
		// 小單位
		String doarea = "";
		// 小單位名稱
		String deptna = "";
		// 大單位
		String deptwn = "";
		Asiutil util = new Asiutil();
		Map<String, String> returnMap = new HashMap<String, String>();
		try {
			if ("dev".equals(this.profile)) {
				doarea = "00";
				deptna = "本機測試通訊處";
				deptwn = "00";
				returnMap.put("doarea", doarea);
				returnMap.put("deptna", deptna);
				returnMap.put("deptwn", deptwn);
				getComputername(httpServletRequest, ip);
				// 模擬回傳資料寫死測試
//				String res2 = "{\"status\":\"200\",\"message\":\"回傳成功\",\"data\":[{\"deptno\":\"00\",\"deptwn\":\"00\",\"deptni\":\"test\",\"deptna\":null,\"dcrtdy\":\"2007-09-30T16\",\"drnwdy\":null,\"doprno\":\"HUA\",\"dmark1\":\"test\",\"dmark2\":\"test\",\"depten\":\"0\",\"staunt\":\"00\",\"fil\":\"00\", \"emdede\":\"00\", \"lstdat\":null, \"deptac\":\"test\", \"telZon1\":\"test\", \"telNo1\":\"test\", \"faxNo1\":\"test\", \"telZon2\":\"test\", \"telNo2\":\"test\", \"faxNo2\":\"test\", \"zipcod\":\"test\", \"addres\":\"tets\", \"coumZip\":\"test\", \"coumTel\":\"test\", \"faxNo\":\"test\", \"countryCd\":\"test\", \"empNum\":358, \"coumCnty\":\"test\", \"coumAddr\":\"test\", \"setdat\":null}]}";
//				String res2 = "{\"status\":\"200\",\"message\":\"回傳成功\",\"data\":[{\"deptno\":\"00\",\"deptwn\":\"00\"}]}";
//				log.debug("res2: " + res2);
//				
//				JsonBean jsonBean2 = gson.fromJson(res2, JsonBean.class);
//				jsonBean2.getData();
//				
//				Type listType = new TypeToken<ArrayList<Deptpf1>>() {}.getType();
//				ArrayList<Deptpf1> deptpf1List = new Gson().fromJson(jsonBean2.getData().toString(), listType);
//				if(deptpf1List.size()!=0) {
//					doarea = deptpf1List.get(0).getDeptwn();
//				}
			} else if ("sit".equals(this.profile)) {
				ResponseEntity<?> res = getComputername(httpServletRequest, ip);
				String json = ((JsonBean) res.getBody()).getData().toString();
				// TODO 列印測試用系統訊息
				log.debug("+++===+++===+++=== getComputername(httpServletRequest) Method 回傳: " + json);
				System.out.println("+++===+++===+++=== getComputername(httpServletRequest) Method 回傳: " + json);
				// 避免在本機測試時因IP有冒號而導制gson無法轉換格式
				json = json.replaceAll(":", ".");
				Map<String, Object> map = new HashMap<String, Object>();
				map = gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				for (Entry<String, Object> entry : map.entrySet()) {
					if ("hostname".equals(entry.getKey())) {
						computerName = String.valueOf(entry.getValue());
						break;
					}
				}
				// TODO 列印測試用系統訊息
				log.debug("+++===+++===+++=== 電腦名稱:  " + computerName);
				System.out.println("+++===+++===+++=== 電腦名稱:  " + computerName);
//			String ip = httpServletRequest.getRemoteHost();
//			String cutIp = ip.substring(1, 3);
				String cutComputerName = "";
				if (computerName.length() >= 3) {
					cutComputerName = computerName.substring(1, 3);
				}
				// TODO 列印測試用系統訊息
				log.debug("+++===+++===+++=== 切割後電腦名稱:  " + cutComputerName);
				System.out.println("+++===+++===+++=== 切割後電腦名稱:  " + cutComputerName);
				doarea = cutComputerName;
				if (StringUtils.isNotBlank(cutComputerName)) {
					// 改成查5500
//					deptpf1List = deptpf1Service.queryByDeptno(cutComputerName);
//					if(deptpf1List.size() != 0) {
//						doarea = deptpf1List.get(0).getDeptwn();
//					}
					// call informixapi 的API
					Map<String, String> httpHeaderMap = new HashMap<String, String>();
					httpHeaderMap.put("Authorization", token);
					Map<String, String> conditionMap = new HashMap<String, String>();
					conditionMap.put("deptno", cutComputerName);
					// TODO 列印測試用系統訊息
					log.debug("+++===+++===+++=== 查詢 deptpf1 參數為 deptno:  " + cutComputerName);
					System.out.println("+++===+++===+++=== 查詢 deptpf1 參數為 deptno:  " + cutComputerName);
					String res2 = util.fetchPostMethdRestful(
							SpringProperty.getEnvironmentsProjectProtocol() + SpringProperty.getApiDomaininformixapi()
									+ "/informixapi/queryByDeptno",
							httpHeaderMap, conditionMap, SysEnum.encodeUtf8.context);
					log.debug("res2: " + res2);
					// TODO 列印測試用系統訊息
					log.debug("+++===+++===+++=== 查詢 deptpf1 回傳資料:  " + res2);
					System.out.println("+++===+++===+++=== 查詢 deptpf1 回傳資料:  " + res2);
					JsonBean jsonBean2 = gson.fromJson(res2, JsonBean.class);
					jsonBean2.getData();

//					Type listType = new TypeToken<ArrayList<Deptpf1>>() {
//					}.getType();
//					ArrayList<Deptpf1> deptpf1List = new Gson().fromJson(jsonBean2.getData().toString(), listType);
//					if (deptpf1List.size() != 0) {
//						deptwn = deptpf1List.get(0).getDeptwn();
//						deptna = deptpf1List.get(0).getDeptna();
//					}
					returnMap.put("doarea", doarea);
					returnMap.put("deptna", deptna);
					returnMap.put("deptwn", deptwn);
					// TODO 列印測試用系統訊息
					log.debug("+++===+++===+++=== 承作地區doarea:  " + doarea);
					System.out.println("+++===+++===+++=== 承作地區doarea:  " + doarea);
					log.debug("+++===+++===+++=== 中文名稱 deptna:  " + deptna);
					System.out.println("+++===+++===+++=== 中文名稱 deptna:  " + deptna);
					// TODO 先寫死
//					doarea = "00";

				} else {
					// TODO 列印測試用系統訊息
					log.debug("+++===+++===+++=== 電腦名稱為空");
					System.out.println("+++===+++===+++=== 電腦名稱為空");
//				if(StringUtils.isBlank(computerName)) {
//				}
					// 此段先保留
//				if trim(strComputerName) ="" then
//						sql = "SELECT * FROM  EMPLPF1  where emplno = '"& right(uid,6) &"' "
//						set rs = conn.execute(sql)
//						if not rs.eof then
//							strComputerName = rs("EMDEPA")
//						end if	
//					end if
				}
			} else {
				// prod (ocp環境)
				// 以 emplno 查詢地區
				// call informixapi 的API

//				Map<String, String> httpHeaderMap = new HashMap<String, String>();
//				httpHeaderMap.put("Authorization", token);
//				Map<String, String> conditionMap = new HashMap<String, String>();
//				conditionMap.put("emplno", emplno);
//				String res = util.fetchPostMethdRestful(
//						SpringProperty.getEnvironmentsProjectProtocol() + SpringProperty.getApiDomaininformixapi() + "/informixapi/getEmailname",
//						httpHeaderMap, conditionMap, SysEnum.encodeUtf8.context);
//				log.debug("res: " + res);
//				JsonBean jsonBean2 = gson.fromJson(res, JsonBean.class);
//				Type listType = new TypeToken<List<Emplpf1>>() {}.getType();
//				List<Emplpf1> result = new Gson().fromJson(jsonBean2.getData().toString(), listType);
//				if(result.size() != 0) {
//					doarea = result.get(0).getEmdepa();
//				}

//				ResponseEntity<?> res = getComputername(httpServletRequest, ip);
				// getComputernameTest 大Steven測試成功，所以用這個
				ResponseEntity<?> res = getComputernameTest(httpServletRequest, ip);
				String json = ((JsonBean) res.getBody()).getData().toString();
				// TODO 列印測試用系統訊息
				log.debug("+++===+++===+++=== getComputername(httpServletRequest) Method 回傳: " + json);
				System.out.println("+++===+++===+++=== getComputername(httpServletRequest) Method 回傳: " + json);
				// 避免在本機測試時因IP有冒號而導制gson無法轉換格式
				json = json.replaceAll(":", ".");
				Map<String, Object> map = new HashMap<String, Object>();
				map = gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {
				}.getType());
				for (Entry<String, Object> entry : map.entrySet()) {
					if ("hostname".equals(entry.getKey())) {
						computerName = String.valueOf(entry.getValue());
						break;
					}
				}
				// TODO 列印測試用系統訊息
				log.debug("+++===+++===+++=== 電腦名稱:  " + computerName);
				System.out.println("+++===+++===+++=== 電腦名稱:  " + computerName);
//			String ip = httpServletRequest.getRemoteHost();
//			String cutIp = ip.substring(1, 3);
				String cutComputerName = "";
				if (computerName.length() >= 3) {
					cutComputerName = computerName.substring(1, 3);
				}
				// TODO 列印測試用系統訊息
				log.debug("+++===+++===+++=== 切割後電腦名稱:  " + cutComputerName);
				System.out.println("+++===+++===+++=== 切割後電腦名稱:  " + cutComputerName);
				doarea = cutComputerName;

				if (StringUtils.isNotBlank(doarea)) {
					// call informixapi 的API
					Map<String, String> httpHeaderMap2 = new HashMap<String, String>();
					httpHeaderMap2.put("Authorization", token);
					Map<String, String> conditionMap2 = new HashMap<String, String>();
					conditionMap2.put("deptno", doarea);
					// TODO 列印測試用系統訊息
					log.debug("+++===+++===+++=== 查詢 deptpf1 參數為 deptno:  " + doarea);
					System.out.println("+++===+++===+++=== 查詢 deptpf1 參數為 deptno:  " + doarea);
					String res2 = util.fetchPostMethdRestful(
							SpringProperty.getEnvironmentsProjectProtocol() + SpringProperty.getApiDomaininformixapi()
									+ "/informixapi/queryByDeptno",
							httpHeaderMap2, conditionMap2, SysEnum.encodeUtf8.context);
					log.debug("res2: " + res2);
					// TODO 列印測試用系統訊息
					log.debug("+++===+++===+++=== 查詢 deptpf1 回傳資料:  " + res2);
					System.out.println("+++===+++===+++=== 查詢 deptpf1 回傳資料:  " + res2);
					JsonBean jsonBean3 = gson.fromJson(res2, JsonBean.class);
					jsonBean3.getData();

//					Type listType2 = new TypeToken<ArrayList<Deptpf1>>() {
//					}.getType();
//					ArrayList<Deptpf1> deptpf1List = new Gson().fromJson(jsonBean3.getData().toString(), listType2);
//					if (deptpf1List.size() != 0) {
//						deptwn = deptpf1List.get(0).getDeptwn();
//						deptna = deptpf1List.get(0).getDeptna();
//					}
					returnMap.put("doarea", doarea);
					returnMap.put("deptna", deptna);
					returnMap.put("deptwn", deptwn);
					// TODO 列印測試用系統訊息
					log.debug("+++===+++===+++=== 承作地區doarea:  " + doarea);
					System.out.println("+++===+++===+++=== 承作地區doarea:  " + doarea);
					log.debug("+++===+++===+++=== 中文名稱 deptna:  " + deptna);
					System.out.println("+++===+++===+++=== 中文名稱 deptna:  " + deptna);
					// TODO 先寫死
//					doarea = "00";

				}
//				doarea = "00";
//				deptna = "本機測試通訊處";
//				deptwn = "00";
//				returnMap.put("doarea", doarea);
//				returnMap.put("deptna", deptna);
//				returnMap.put("deptwn", deptwn);
			}
			jsonBean.setData(returnMap);
			jsonBean.setStatus("200");
			jsonBean.setMessage(SysEnum.statusSuccess.context);
			log.debug("LEO.................................end");
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setData("");
			jsonBean.setStatus("400");
			jsonBean.setMessage(SysEnum.statusError.context);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 取得電腦名稱(測試方法)
	 * 
	 * @return
	 */
	@ApiOperation(value = "取得電腦名稱(測試方法)", response = JsonBean.class, tags = { "common" }, notes = "取得電腦名稱(測試方法)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/getComputernameTest")
	@ResponseBody
	public ResponseEntity<?> getComputernameTest(HttpServletRequest httpServletRequest,
			@RequestParam(name = "ip", required = true) String ip2) {
		JsonBean jsonBean = new JsonBean();
		try {
//			String ip = httpServletRequest.getRemoteHost();
			System.out.println("ip2: " + ip2 + ".............................++");

			String ip = httpServletRequest.getHeader("X-Forwarded-For");
			System.out.println("ip_X-Forwarded-For: " + ip + "........................++");
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = httpServletRequest.getHeader("X-Forwarded-Proto");
//				System.out.println("ip_X-Forwarded-Proto: " + ip + "........................++");
//			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = httpServletRequest.getHeader("X-Real-IP"); // nginx代理
				System.out.println("ip_X-Real-IP: " + ip + "........................++");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = httpServletRequest.getHeader("Proxy-Client-IP");
				System.out.println("ip_Proxy-Client-IP: " + ip + "........................++");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
				System.out.println("ip_WL-Proxy-Client-IP: " + ip + "........................++");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = httpServletRequest.getHeader("HTTP_CLIENT_IP");
				System.out.println("ip_HTTP_CLIENT_IP: " + ip + "........................++");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
				System.out.println("ip_HTTP_X_FORWARDED_FOR: " + ip + "........................++");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = httpServletRequest.getRemoteAddr();
				System.out.println("ip_getRemoteAddr: " + ip + "\n host:" + httpServletRequest.getRemoteHost()
						+ "\n user:" + httpServletRequest.getRemoteUser() + "........................++");
			}

			System.out.println("print all............................++");
			System.out.println("ip_X-Forwarded-For: " + httpServletRequest.getHeader("X-Forwarded-For")
					+ "........................++");
			System.out.println(
					"ip_X-Real-IP: " + httpServletRequest.getHeader("X-Real-IP") + "........................++");
			System.out.println("ip_Proxy-Client-IP: " + httpServletRequest.getHeader("Proxy-Client-IP")
					+ "........................++");
			System.out.println("ip_WL-Proxy-Client-IP: " + httpServletRequest.getHeader("WL-Proxy-Client-IP")
					+ "........................++");
			System.out.println("ip_HTTP_CLIENT_IP: " + httpServletRequest.getHeader("HTTP_CLIENT_IP")
					+ "........................++");
			System.out.println("ip_HTTP_X_FORWARDED_FOR: " + httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR")
					+ "........................++");
			System.out
					.println("ip_getRemoteAddr: " + httpServletRequest.getRemoteAddr() + "........................++");

//			List<HttpRequestHeader> headers = Collections.emptyList();
			Enumeration<String> names = httpServletRequest.getHeaderNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				String value = httpServletRequest.getHeader(name);
				System.out.println("name:" + name + ", value:" + value);
			}

			InetAddress address = InetAddress.getByName(ip);
			String hostname = address.getHostName();
			String canonicalHostName = address.getCanonicalHostName();
			String hostAddress = address.getHostAddress();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ip", ip);
			map.put("hostname", hostname);
			map.put("canonicalHostName", canonicalHostName);
			map.put("hostAddress", hostAddress);

			// test code
			InetAddress address2 = InetAddress.getByName(ip);
			String hostname22 = address2.getHostName();
			String canonicalHostName2 = address2.getCanonicalHostName();
			String hostAddress2 = address2.getHostAddress();
			System.out.println("TEST.........................>>>>>>>>>>>>>>>>>>> LOG");
			System.out.println("address2: " + address2);
			System.out.println("hostname22: " + hostname22);
			System.out.println("canonicalHostName2: " + canonicalHostName2);
			System.out.println("hostAddress2: " + hostAddress2);

			jsonBean.setData(map);
			jsonBean.setStatus("200");
			jsonBean.setMessage(SysEnum.statusFinish.context);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setData("");
			jsonBean.setStatus("400");
			jsonBean.setMessage(SysEnum.statusError.context);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 取得電腦名稱
	 * 
	 * @return
	 */
	@ApiOperation(value = "取得電腦名稱", response = JsonBean.class, tags = { "common" }, notes = "取得電腦名稱")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/getComputername")
	@ResponseBody
	public synchronized ResponseEntity<?> getComputername(HttpServletRequest httpServletRequest, String ip2) {
		JsonBean jsonBean = new JsonBean();
		try {
			// TODO 客戶可能沒有設定chrome
//			int c = ip2.split("\\.").length;
//			if(c != 4) {
//				ip2 = httpServletRequest.getRemoteHost();
//			}
			if (!"prod".equals(this.profile)) {
				ip2 = httpServletRequest.getRemoteHost();
			}

//			String ip = httpServletRequest.getRemoteHost();
			System.out.println("ip2: " + ip2);

//			List<String> hostnameList = new ArrayList<String>();
//			List<String> canonicalHostNameList = new ArrayList<String>();
//			List<String> hostAddressList = new ArrayList<String>();
//			String hostname = "";
//			String canonicalHostName = "";
//			String hostAddress = "";
			InetAddress address = InetAddress.getByName(ip2);
			System.out.println("address: " + address);
			String hostname = address.getHostName();
			System.out.println("hostname: " + hostname);
			String canonicalHostName = address.getCanonicalHostName();
			System.out.println("canonicalHostName: " + canonicalHostName);
			String hostAddress = address.getHostAddress();
			System.out.println("hostAddress: " + hostAddress);
//			InetAddress[] addresses = InetAddress.getAllByName(ip); // ip or DNS name
//	        for (int i = 0; i < addresses.length; i++) {
//	        	String hostname = addresses[i].getHostName();
//	        	System.out.println("hostname: "+hostname);
//	        	hostnameList.add(hostname);
//	            String canonicalHostName = addresses[i].getCanonicalHostName();
//	            System.out.println("canonicalHostName: "+canonicalHostName);
//	            canonicalHostNameList.add(canonicalHostName);
//	            String hostAddress = addresses[i].getHostAddress();
//	            System.out.println("hostAddress: "+hostAddress);
//	            hostAddressList.add(hostAddress);
//	        }
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ip", ip2);
			map.put("hostname", hostname);
			map.put("canonicalHostName", canonicalHostName);
			map.put("hostAddress", hostAddress);
			jsonBean.setData(map);
			jsonBean.setStatus("200");
			jsonBean.setMessage(SysEnum.statusFinish.context);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			jsonBean.setData("");
			jsonBean.setStatus("400");
			jsonBean.setMessage(SysEnum.statusError.context);
			return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

}
