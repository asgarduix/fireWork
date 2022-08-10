/*
 * Copyright (c) 2000-2016 Asgard System, Inc. 
 * Taipei, Taiwan. All rights reserved. 
 * 
 * This software is the confidential and proprietary information of  
 * Asgard System, Inc. ("Confidential Information").  You shall not 
 * disclose such Confidential Information and shall use it only in 
 * accordance with the terms of the license agreement you entered into 
 * with Asgard. 
 * 
 */
package com.asi.mechanism.controller;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.json.model.root.JsonBean;
import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.controller.sysgrant.bean.MapBuffer;
import com.asi.mechanism.controller.sysgrant.bean.SetupIndex;
import com.asi.mechanism.controller.sysgrant.bean.SetupIndexSub;
import com.asi.mechanism.controller.sysgrant.bean.SetupMenu;
import com.asi.mechanism.controller.sysgrant.bean.SetupMenuRole;
import com.asi.mechanism.controller.sysgrant.bean.SetupMenuSort;
import com.asi.mechanism.controller.sysgrant.bean.SetupMenuSub;
import com.asi.mechanism.service.SysAccountRoleService;
import com.asi.mechanism.service.SysAccountService;
import com.asi.mechanism.service.SysFuncUiButtonRoleService;
import com.asi.mechanism.service.SysFuncUiButtonService;
import com.asi.mechanism.service.SysFuncUiFieldRoleService;
import com.asi.mechanism.service.SysFuncUiFieldService;
import com.asi.mechanism.service.SysIndexRoleService;
import com.asi.mechanism.service.SysIndexService;
import com.asi.mechanism.service.SysMenuRoleService;
import com.asi.mechanism.service.SysMenuService;
import com.asi.mechanism.service.SysRoleService;
import com.asi.mechanism.service.connector.DBConnection;
import com.asi.mechanism.service.connector.DBConnectionSqlite;
import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.mechanism.service.dao.mybatis.mapper.SysAccountMapper;
import com.asi.mechanism.service.dao.mybatis.mapper.SysAccountRoleMapper;
import com.asi.mechanism.service.dao.mybatis.mapper.SysFuncUiButtonRoleMapper;
import com.asi.mechanism.service.dao.mybatis.mapper.SysFuncUiFieldRoleMapper;
import com.asi.mechanism.service.dao.mybatis.mapper.SysIndexMapper;
import com.asi.mechanism.service.dao.mybatis.mapper.SysIndexRoleMapper;
import com.asi.mechanism.service.dao.mybatis.mapper.SysMenuMapper;
import com.asi.mechanism.service.dao.mybatis.mapper.SysMenuRoleMapper;
import com.asi.mechanism.service.dao.mybatis.mapper.SysRoleMapper;
import com.asi.mechanism.service.dao.mybatis.model.SysAccount;
import com.asi.mechanism.service.dao.mybatis.model.SysAccountRole;
import com.asi.mechanism.service.dao.mybatis.model.SysAccountRoleKey;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiButton;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiButtonRole;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiField;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiFieldRole;
import com.asi.mechanism.service.dao.mybatis.model.SysIndex;
import com.asi.mechanism.service.dao.mybatis.model.SysIndexRole;
import com.asi.mechanism.service.dao.mybatis.model.SysMenu;
import com.asi.mechanism.service.dao.mybatis.model.SysMenuRole;
import com.asi.mechanism.service.dao.mybatis.model.SysRole;
import com.asi.mechanism.service.repository.SysAccountRepository;
import com.asi.swissknife.Asiutil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 選單產生器
 * 
 */
@Lazy
@RequestMapping("sysgrant")
@RestController
@Api(value = "fixed test")
public class SysGrantController {

	private static Logger log = LogManager.getLogger(SysGrantController.class);

	@Autowired
	private SysAccountService sysAccountService;
	@Autowired
	private SysAccountRoleService sysAccountRoleService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysIndexRoleService sysIndexRoleService;
	@Autowired
	private SysMenuRoleService sysMenuRoleService;
	@Autowired
	private SysIndexService sysIndexService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysFuncUiButtonService sysFuncUiButtonService;
	@Autowired
	private SysFuncUiFieldService sysFuncUiFieldService;
	@Autowired
	private SysFuncUiButtonRoleService sysFuncUiButtonRoleService;
	@Autowired
	private SysFuncUiFieldRoleService sysFuncUiFieldRoleService;
	@Autowired
	private MyBatisConnector mybatis;

//	/**
//	 *controller method(apis)coed template
//	 * @param roleName
//	 * @return
//	 */
//	@ApiOperation(value = "grant template code", response = JsonBean.class, tags = { "sysgrant" }, notes = "")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
//			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
//	@PostMapping("/refRoleAcc")
//	@ResponseBody
//	public ResponseEntity<?> template(@RequestParam(name = "roleId", required = true) String roleId) {
//		JsonBean jsonBean = new JsonBean();
//
//		try {
//			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
//
//			try {
//				SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
//
//			} catch (Exception e) {
//				sqlSession.rollback();
//				throw e;
//			} finally {
//				sqlSession.close();
//			}
//		} catch (Exception e) {
//			jsonBean.setStatus(SysEnum.statusError.context);
//			jsonBean.setData(0);
//
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
//			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
//	}

	/**
	 * TODO 待測試
	 * 
	 * 新增index(模組選單)
	 * 
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "新增index(模組選單)資料", response = JsonBean.class, tags = {
			"sysgrant" }, notes = "新增index(模組選單)資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/createIndex")
	@ResponseBody
	public ResponseEntity<?> createIndex(@RequestParam(name = "roleId", required = true) SetupIndex setupIndex) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
				SysIndexMapper sysIndexMapper = sqlSession.getMapper(SysIndexMapper.class);
				List<SetupIndexSub> tmpList = setupIndex.getIndexList();

				// TODO 檢核?

				List<SysIndex> indexList = sysIndexService.queryAll(sqlSession);

				List<Integer> numList = indexList.stream().map(index -> index.getIndexId().intValue())
						.collect(Collectors.toList());

				// 取得空號。取得根資料的編號「後三碼」插入或後補編號(系統編號格式9000000000，最後三碼為根目錄，上限999個)
				// 找出至999前可用的編號
				// XXX 檢查輸入的選單資料是否已經存在系統中?
				List<BigDecimal> canUseNos = new ArrayList<>();

				for (int i = 1; i < 1000; i++) {
					int x = i;
					boolean already = numList.stream().anyMatch(num -> num.compareTo(Integer.valueOf(x)) != 0);

					if (already == false) {
						canUseNos.add(new BigDecimal("9" + String.format("%0" + "3" + "d", i)));
					}
				}

				// ...
				List<SysIndex> insList = new ArrayList<>();

				for (int i = 0; i < tmpList.size(); i++) {
					SysIndex sysIndex = new SysIndex();
					sysIndex.setCrtDate(new Date());
					sysIndex.setEnableMark("Y");
					sysIndex.setIndexId(canUseNos.get(i));
					sysIndex.setIndexName(tmpList.get(i).getIndexName());
					sysIndex.setIndexFuncUrl(tmpList.get(i).getIndexFuncUrl());
					sysIndex.setIndexIconPath(tmpList.get(i).getIndexIconPath());
					insList.add(sysIndex);
				}

				int insVal = insList.stream().mapToInt(index -> {
					try {
						return sysIndexService.insert(index, sysIndexMapper);
					} catch (Exception e) {
						log.debug(e.toString());
						return 0;
					}
				}).sum();

				if (insVal == 0) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("added value:" + insVal);
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("added value:" + insVal);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * TODO 待測試
	 * 
	 * 修改帳號資料
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "grant template code", response = JsonBean.class, tags = { "sysgrant" }, notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/modifyAccount")
	@ResponseBody
	public ResponseEntity<?> modifyAccount(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "userName", required = true) String userName,
			@RequestParam(name = "mail", required = false) String mail,
			@ApiParam(value = "(We will encode to MD5)") @RequestParam(name = "password") String password) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysAccountMapper sysAccountMapper = sqlSession.getMapper(SysAccountMapper.class);

				// 查詢原有資料
				SysAccount sysAccCond = new SysAccount();
				sysAccCond.setUserId(userId);
				List<SysAccount> accList = sysAccountService.queryBySysAccount(sysAccCond, sqlSession);

				if (accList == null || accList.size() != 1) {
					jsonBean.setStatus(SysEnum.statusSuccess.code);
					jsonBean.setMessage("we find user id:" + userId + ", size is not 1:"
							+ (accList == null ? null : accList.size()));
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				List<SysAccount> newAccList = accList.stream().map(acc -> {
					acc.setUserId(userId);
					acc.setMail(mail);
					acc.setUserName(userName);
					acc.setPassword(password);
					return acc;
				}).collect(Collectors.toList());

				int succVal = newAccList.stream().mapToInt(acc -> {
					try {
						return sysAccountService.update(acc, sysAccountMapper);
					} catch (Exception e) {
						log.debug(e.toString());
						return 0;
					}
				}).sum();

				if (succVal != newAccList.size()) {
					jsonBean.setStatus(SysEnum.statusSuccess.code);
					jsonBean.setMessage("modify user data have serious error");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("modify succ");
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * TODO 待測試
	 * 
	 * 關閉/開啟選單
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "關閉/開啟選單", response = JsonBean.class, tags = { "sysgrant" }, notes = "關閉/開啟選單")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/switchEnableMenu")
	@ResponseBody
	public ResponseEntity<?> switchEnableMenu(@RequestParam(name = "menuId", required = true) String roleId,
			@RequestParam(name = "enabled", required = true) boolean enabled) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysMenuMapper sysMenuMapper = sqlSession.getMapper(SysMenuMapper.class);

				// 查詢帳號
				SysMenu sysMenu = new SysMenu();
				List<SysMenu> sysMenuList = sysMenuService.queryBySysMenu(sysMenu, sqlSession);

				// 將查詢後的資料修改enable_mark欄位資料為N
				List<SysMenu> newSysMenuList = sysMenuList.stream().map(menu -> {
					menu.setEnableMark(enabled == true ? "Y" : "N");
					return menu;
				}).collect(Collectors.toList());

				long succVal = newSysMenuList.stream().mapToInt(menu -> {
					try {
						return sysMenuService.update(menu, sysMenuMapper);
					} catch (Exception e) {
						log.debug(e.toString());
						Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
						return 0;
					}
				}).sum();

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("updated:" + succVal);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * TODO 待測試
	 * 
	 * 查詢角色資料
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "查詢角色資料", response = JsonBean.class, tags = { "sysgrant" }, notes = "查詢角色資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchRole")
	@ResponseBody
	public ResponseEntity<?> template(
			@ApiParam(value = "無輸入role id即查詢全部角色資料") @RequestParam(name = "roleId", required = true) String roleId) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				List<SysRole> roleList = null;

				if (StringUtils.isBlank(roleId) == true) {
					SysRole sysRole = new SysRole();
					sysRole.setUserRole(roleId);
					roleList = sysRoleService.queryBySysRole(sysRole);
				} else {
					roleList = sysRoleService.queryAll(sqlSession);
				}

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setData(roleList);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * TODO
	 * 
	 * 設定選單的排序
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "設定選單的排序", response = JsonBean.class, tags = { "sysgrant" }, notes = "設定選單的排序")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully insert data", response = SetupMenuSort.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/setupMenuSort")
	@ResponseBody
	public ResponseEntity<?> setupMenuSort(@RequestBody(required = true) SetupMenuSort context) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);

				// 取得所有選單資料
				List<SysMenu> sysMenuList = sysMenuService.queryAll(sqlSession);

			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 新增帳號
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "新增帳號(同時可關聯帳號使用角色)", response = JsonBean.class, tags = { "sysgrant" }, notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/createAccount")
	@ResponseBody
	public ResponseEntity<?> createAccount(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "userName", required = true) String userName,
			@RequestParam(name = "mail", required = false) String mail,
			@ApiParam(value = "(We will encode to MD5)") @RequestParam(name = "password") String password) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysAccountRoleMapper sysAccountRoleMapper = sqlSession.getMapper(SysAccountRoleMapper.class);
				SysAccountMapper sysAccountMapper = sqlSession.getMapper(SysAccountMapper.class);

				// 檢查該輸入帳號ID是否已存在
				SysAccount acc = new SysAccount();
				acc.setUserId(userId);
				List<SysAccount> accList = sysAccountService.queryBySysAccount(acc, sqlSession);

				if (accList.size() > 0) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("user id is existing!");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// 新增輸入帳號資料
				Asiutil util = new Asiutil();
				String passwdEncode = util.encrypt(password, "MD5");

				SysAccount newAcc = new SysAccount();
				newAcc.setUserId(userId);
				newAcc.setUserName(userName);
				newAcc.setPassword(passwdEncode);
				newAcc.setCrtUserid(userId);
				newAcc.setMail(mail);
				newAcc.setCrtDate(new Date());

				int insVal = sysAccountService.insert(newAcc, sysAccountMapper);

				// 先賦予預設角色(TODO 須再討論看看)
				SysAccountRole newAccRole = new SysAccountRole();
				newAccRole.setUserId(userId);
				newAccRole.setUserRole(userId);
				newAccRole.setUserRole(SysEnum.systemDefaultRole.context);
				int insVal2 = sysAccountRoleService.insert(newAccRole, sysAccountRoleMapper);

				if (insVal != 1 & insVal2 != 1) {
					jsonBean.setStatus(SysEnum.statusSuccess.code);
					jsonBean.setMessage("added user id:" + userId + " have serious error, insert val:" + insVal);
					return new ResponseEntity<>(jsonBean, HttpStatus.OK);
				}

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("added user id:" + userId);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * TODO 待測試
	 * 
	 * 關閉/開啟帳號
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "停/啟用帳號", response = JsonBean.class, tags = { "sysgrant" }, notes = "停/啟用帳號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/switchEnableAccount")
	@ResponseBody
	public ResponseEntity<?> switchEnableAccount(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "enabled", required = true) boolean enabled) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysAccountMapper sysAccountMapper = sqlSession.getMapper(SysAccountMapper.class);

				// 查詢帳號
				SysAccount sysAcc = new SysAccount();
				sysAcc.setUserId(userId);
				List<SysAccount> sysAccList = sysAccountService.queryBySysAccount(sysAcc, sqlSession);

				// 將查詢後的資料修改enable_mark欄位資料為N
				List<SysAccount> newSysAccList = sysAccList.stream().map(acc -> {
					acc.setEnableMark(enabled == true ? "Y" : "N");
					return acc;
				}).collect(Collectors.toList());

				long succVal = newSysAccList.stream().mapToInt(acc -> {
					try {
						return sysAccountService.update(acc, sysAccountMapper);
					} catch (Exception e) {
						log.debug(e.toString());
						Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
						return 0;
					}
				}).sum();

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("updated:" + succVal);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);

			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * TODO 待測試
	 * 
	 * 刪除使用者
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "刪除帳號", response = JsonBean.class, tags = { "sysgrant" }, notes = "刪除帳號")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@DeleteMapping("/removeAccount")
	@ResponseBody
	public ResponseEntity<?> removeAccount(@RequestParam(name = "userId", required = true) String userId) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
				SysAccountMapper sysAccountMapper = sqlSession.getMapper(SysAccountMapper.class);

				// 確認欲刪除帳號是否存在
				SysAccount accCond = new SysAccount();
				List<SysAccount> sysAccList = sysAccountService.queryBySysAccount(accCond, sqlSession);

				if (sysAccList == null || sysAccList.size() < 0) {
					jsonBean.setStatus(SysEnum.statusSuccess.code);
					jsonBean.setMessage("user id:" + userId + " is not existing");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				int delVal = sysAccountService.deleteByKey(userId, sysAccountMapper);

				// 可刪除，但刪除數量有問題
				if (delVal != 1) {
					jsonBean.setStatus(SysEnum.statusSuccess.code);
					jsonBean.setMessage(
							"deleted user id:" + userId + " success! but something wraning!delete size:" + delVal);
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("deleted user id:" + userId + " success!");
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// TODO 關閉選單

//	/**
//	 * 
//	 * 查詢使用者資料
//	 * 
//	 * @param paramTestList
//	 */
//	@ApiOperation(value = "使用token取得使用者名稱", response = JsonBean.class, tags = { "sysgrant","sysadmin" }, notes = "查詢使用者資料單")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
//			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
//	@GetMapping("/fetchUserNameByToken")
//	@ResponseBody
//	public ResponseEntity<?> fetchUserNameByToken(@RequestParam(name = "accessToken") String accessToken) {
//		JsonBean jsonBean = new JsonBean();
//
//		try {
//			Gson gson = new Gson();
//			Decoder decoder = Base64.getDecoder();
//
//			Token bar = gson.fromJson(resStr, Token.class);
//			String token = bar.getAccess_token();
//
//			String[] chunks = token.split("\\.");
//			String chunks2 = new String(decoder.decode(chunks[1]), "UTF-8");
//
//			Chunks2 foo = gson.fromJson(chunks2, Chunks2.class);
//			nmFromToken = foo.getPreferred_username();
//		} catch (Exception e) {
//			jsonBean.setStatus(SysEnum.statusError.context);
//			jsonBean.setData(0);
//
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
//			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
//	}

	/**
	 * 
	 * 新增根選單資料
	 * 
	 * XXX 初始化系統時建議應該由初始化腳本(如SQL腳本)處理，不供使用者處理初始化問題
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "新增(根)選單資料(此API不處理排序問題)", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "新增根選單資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data", response = SetupMenu.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/createMenu")
	@ResponseBody
	public ResponseEntity<?> createMenu(@RequestBody(required = true) SetupMenu context) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysMenuMapper sysMenuMapper = sqlSession.getMapper(SysMenuMapper.class);

				// TODO 確認輸入的index已在系統中已經存在?

				// 檢查需要的欄位(manu_name、menu_func_url)是否均已經輸入
				List<SetupMenuSub> menus4valid = context.getMenuList();

				boolean isAnyBlank = menus4valid.stream()
						.anyMatch(menu -> (StringUtils.isAnyBlank(menu.getMenuName(), menu.getMenuFuncUrl())));

				if (isAnyBlank == true) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("Error! You maybe not input menu url or menu name ");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// 取得全部的選單資料，待後續可以使用
				List<SysMenu> menuList = sysMenuService.queryAll(sqlSession);

				List<SysMenu> rootList = menuList.stream().filter(menu -> menu.getMenuUpperId() == null)
						.collect(Collectors.toList());

				// 如果既有資料已到999則不處理
				List<Integer> numList = rootList.stream().map(menu -> menu.getMenuId().toString()).map(num -> {
					int val = num.length();
					String tmp = num.substring(val - 3, val);
					return Integer.valueOf(tmp);
				}).collect(Collectors.toList());

				boolean isTooBigger = numList.stream().anyMatch(num -> num.intValue() >= 999);

				if (isTooBigger == true) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("System can not add any menu data!");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// 取得空號。取得根資料的編號「後三碼」插入或後補編號(系統編號格式9000000000，最後三碼為根目錄，上限999個)
				// 找出至999前可用的編號
				// XXX 檢查輸入的選單資料是否已經存在系統中?
				List<BigDecimal> canUseNos = new ArrayList<>();

				for (int i = 1; i < 1000; i++) {
					int x = i;
					boolean already = numList.stream().anyMatch(num -> num.compareTo(Integer.valueOf(x)) != 0);

					if (already == false) {
						canUseNos.add(new BigDecimal("9" + String.format("%09d", i)));
					}
				}

				// 後續配發給新增的資料
				List<SetupMenuSub> tmpMenuList = context.getMenuList();
				int size = context.getMenuList().size();

				// 準備sys_menu資料
				List<SysMenu> insertList = new ArrayList<>();

				// 因需要使用迭代數取編號，故這邊使用for迴圈
				for (int i = 0; i < size; i++) {
					SetupMenuSub tmpMenu = tmpMenuList.get(i);

					SysMenu sysMenu = new SysMenu();
					sysMenu.setMenuId(canUseNos.get(i));// 取自動編號的資料
					sysMenu.setMenuName(tmpMenu.getMenuName());
					sysMenu.setMenuFuncUrl(tmpMenu.getMenuFuncUrl());
					sysMenu.setMenuItemClass(tmpMenu.getMenuItemClass());
					sysMenu.setIndexId(new BigDecimal(tmpMenu.getIndexId()));
					sysMenu.setIndexSort(0);

					insertList.add(sysMenu);
				}

				int succVal = insertList.stream().mapToInt(data -> {
					try {
						return sysMenuService.insert(data, sysMenuMapper);
					} catch (Exception e) {
						log.debug(e.toString());
						return 0;
					}
				}).sum();

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("add menu data success:" + succVal);

				return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * TODO 考慮要不要變成共用，因為需要compareTo，容易出錯，可以再考慮
	 * 
	 * 根據輸入上限和輸入數字清單，找到未使用的號碼
	 * 
	 * @param peak
	 * @param numList
	 */
	public void foo(int peak, List<Integer> numList, int lackVal) {
		int limit = peak;

		// 取得空號。取得根資料的編號「後三碼」插入或後補編號(系統編號格式9000000000，最後三碼為根目錄，上限999個)
		// 找出至999前可用的編號
		// XXX 檢查輸入的選單資料是否已經存在系統中?
		List<BigDecimal> canUseNos = new ArrayList<>();

		for (int i = 1; i < limit; i++) {
			int x = i;
			boolean already = numList.stream().anyMatch(num -> num.compareTo(Integer.valueOf(x)) != 0);

			if (already == false) {
				canUseNos.add(new BigDecimal("9" + String.format("%0" + String.valueOf(lackVal) + "d", i)));
			}
		}
	}

	/**
	 * 修改選單資料
	 * 
	 * XXX 初始化系統時應該由初始化腳本(如SQL腳本)處理，不供使用者處理初始化問題
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "修改選單資料(此API不處理排序問題)", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "修改選單資料(開發中)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data", response = SetupMenu.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/modifyMenu")
	@ResponseBody
	public ResponseEntity<?> modifyMenu(@RequestBody(required = true) SetupMenu context) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysMenuMapper sysMenuMapper = sqlSession.getMapper(SysMenuMapper.class);

				// TODO 修改模組(index)?
				// 確認輸入的index已在系統中已經存在

				// 依照輸入的menuid修改資料
				// 檢查輸入的資料是否有重複的menuid
				List<SetupMenuSub> inputMenuList = context.getMenuList();

				// 確認有無重複的menuid
				try {
					Map<Object, Object> map = inputMenuList.stream().map(menu -> menu.getMenuId())
							.collect(Collectors.toMap(o -> o, o -> "x"));

					// TODO 不用了，待移植
					// Map<String, Integer> map = menu2List.stream()
					// .map(menu -> new String[] { menu.getMenuId(), String.valueOf(0) })
					// .collect(Collectors.toMap(o -> o[0], o -> Integer.valueOf(o[1])));
					// Iterator<Entry<String, Integer>> ite = map.entrySet().iterator();
					//
					// while (ite.hasNext()) {
					// Entry<String, Integer> tmp = ite.next();
					// String menuIdTmp = tmp.getKey();
					//
					// int val = menu2List.stream().filter(menuId -> menuId.equals(menuIdTmp))
					// .collect(Collectors.toList()).size();
					//
					// tmp.setValue(val);
					// }
					//
					// boolean ismultiple = false;
					//
					// while (ite.hasNext()) {
					// Entry<String, Integer> tmp = ite.next();
					// String menuIdTmp = tmp.getKey();
					//
					// if (tmp.getValue() > 0) {
					// ismultiple = true;
					// break;
					// }
					// }
					//
					// if (ismultiple == true) {
					//
					// }
				} catch (Exception e) {
					jsonBean.setStatus(SysEnum.statusError.context);
					jsonBean.setMessage("menu id of context is repeat!");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// 取得原資料，因可不用全部將新資料填入，故須保留原資料
				List<SysMenu> originMenuList = sysMenuService.queryAll(sqlSession);

				Map<Object, SysMenu> index = originMenuList.stream()
						.map(menu -> new MapBuffer(menu.getMenuId().toString(), menu))
						.collect(Collectors.toMap(m -> m.getKey(), m -> (SysMenu) m.getVal()));

				// 逐一修改資料
				int succVal = inputMenuList.stream().mapToInt(menu -> {
					SysMenu sysMenu = index.get(menu.getMenuId().toString());

					if (sysMenu == null) {
						log.debug("Menu id(" + menu.getMenuId().toString() + ") is not existing in system");
						return 0;
					}

					sysMenu.setMenuName(menu.getMenuName());
					sysMenu.setMenuFuncUrl(menu.getMenuFuncUrl());
					sysMenu.setMenuItemClass(menu.getMenuItemClass());
					sysMenu.setIndexId(new BigDecimal(menu.getIndexId()));

					try {
						return sysMenuService.update(sysMenu, sysMenuMapper);
					} catch (Exception e) {
						log.debug(e.toString());
						return 0;
					}
				}).sum();

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.context);
				jsonBean.setMessage("modify menu success!:" + succVal);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 刪除選單資料
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "刪除選單資料", response = JsonBean.class, tags = { "sysgrant", "sysadmin" }, notes = "刪除選單資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data", response = SetupMenu.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@DeleteMapping("/removeMenu")
	@ResponseBody
	public ResponseEntity<?> removeMenu(@RequestBody(required = true) SetupMenu context) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysMenuMapper sysMenuMapper = sqlSession.getMapper(SysMenuMapper.class);

				List<SetupMenuSub> menu2List = context.getMenuList();

				// 確認有無重複的menuid
				try {
					Map<Object, Object> map = menu2List.stream().map(menu -> menu.getMenuId())
							.collect(Collectors.toMap(o -> o, o -> "x"));
				} catch (Exception e) {
					jsonBean.setStatus(SysEnum.statusError.context);
					jsonBean.setMessage("menu id of context is repeat!");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				List<String> menuIdList = menu2List.stream().map(menu -> menu.getMenuId()).collect(Collectors.toList());

				// 確認欲刪除之menuid已和sys_menu_role解除關係
				List<SysMenuRole> sysMenuRoleList = sysMenuRoleService.queryAll(sqlSession);

				// 找出可能有關聯的資料
				Map<String, Boolean> map = menuIdList.stream().map(menuId -> {
					String key = menuId;
					Boolean val = sysMenuRoleList.stream()
							.anyMatch(menuRole -> menuRole.getMenuId().toString().equals(menuId));
					return new MapBuffer(key, val);
				}).collect(Collectors.toMap(o -> o.getKey(), o -> (Boolean) o.getVal()));

				// 整理問題結果
				Iterator<Entry<String, Boolean>> ite = map.entrySet().iterator();

				List<String> errMenuIds = new ArrayList<>();

				while (ite.hasNext()) {
					Entry<String, Boolean> o = ite.next();
					boolean b = o.getValue();

					if (b == true) {
						errMenuIds.add(o.getKey());// key為menuId
					}
				}

				if (errMenuIds.size() > 0) {
					String list = errMenuIds.stream().collect(Collectors.joining("、"));
					jsonBean.setStatus(SysEnum.statusError.context);
					jsonBean.setMessage("Some data have be link to role!:" + list);
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// 上面步驟如果均無問題，逐一刪除資料
				int succVal = menu2List.stream().mapToInt(menu -> {
					SysMenu sysMenu = new SysMenu();
					sysMenu.setMenuId(new BigDecimal(menu.getMenuId()));

					try {
						return sysMenuService.deleteByKey(new BigDecimal(menu.getMenuId()), sysMenuMapper);
					} catch (Exception e) {
						log.debug(e.toString());
						return 0;
					}
				}).sum();

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("delete data success!:" + succVal);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * TODO 設定模組(index)基本資料
	 * 
	 * @param roleName
	 * @return
	 */
//	@ApiOperation(value = "設定模組(index)基本資料", response = JsonBean.class, tags = { "sysgrant","sysadmin" }, notes = "設定模組(index)基本資料")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
//			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
//	@GetMapping("/disableRole")
//	@ResponseBody
//	public ResponseEntity<?> disableRole(@RequestParam(name = "roleId", required = true) String roleId) {
//		JsonBean jsonBean = new JsonBean();
//
//		try {
//		} catch (Exception e) {
//			jsonBean.setStatus(SysEnum.statusError.context);
//			jsonBean.setData(0);
//
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
//			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
//	}

	/**
	 * TODO 設定角色所使用的選單-功能內按鈕
	 * 
	 * @param roleName
	 * @return
	 */
//	@ApiOperation(value = "設定角色所使用的選單-功能內按鈕", response = JsonBean.class, tags = { "sysgrant","sysadmin" }, notes = "設定角色所使用的選單-功能內按鈕")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
//			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
//	@GetMapping("/disableRole")
//	@ResponseBody
//	public ResponseEntity<?> disableRole(@RequestParam(name = "roleId", required = true) String roleId) {
//		JsonBean jsonBean = new JsonBean();
//
//		try {
//		} catch (Exception e) {
//			jsonBean.setStatus(SysEnum.statusError.context);
//			jsonBean.setData(0);
//
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
//			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
//	}

	/**
	 * TODO 設定角色所使用的選單-功能內欄位
	 * 
	 * @param roleName
	 * @return
	 */
//	@ApiOperation(value = "設定角色所使用的選單-功能內欄位", response = JsonBean.class, tags = { "sysgrant" ,"sysadmin"}, notes = "設定角色所使用的選單-功能內欄位")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
//			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
//	@GetMapping("/disableRole")
//	@ResponseBody
//	public ResponseEntity<?> disableRole(@RequestParam(name = "roleId", required = true) String roleId) {
//		JsonBean jsonBean = new JsonBean();
//
//		try {
//		} catch (Exception e) {
//			jsonBean.setStatus(SysEnum.statusError.context);
//			jsonBean.setData(0);
//
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
//			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
//	}

	/**
	 * 測試讀取設定檔新機制(測試失敗，原因不明)
	 */
//	@Autowired
//	private SpringPropertyNewMechanismTest springPropertyNewMechanismTest;
//	log.debug(springPropertyNewMechanismTest.getEnvironments().getProject().getAuthorization().getServer()
//	.getDomain());

	/**
	 * TODO 設定角色所使用的模組(index)
	 * 
	 * @param roleName
	 * @return
	 */
//	@ApiOperation(value = "設定角色所使用的模組(index)", response = JsonBean.class, tags = {
//			"sysgrant","sysadmin" }, notes = "設定角色所使用的模組(index)")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
//			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
//			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
//	@GetMapping("/setupIndexRole")
//	@ResponseBody
//	public ResponseEntity<?> setupIndexRole(@RequestParam(name = "roleId", required = true) String roleId) {
//		JsonBean jsonBean = new JsonBean();
//
//		try {
//
//		} catch (Exception e) {
//			jsonBean.setStatus(SysEnum.statusError.context);
//			jsonBean.setData(0);
//
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
//			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//	ㄋ	return new ResponseEntity<>(jsonBean, HttpStatus.OK);
//	}

	/**
	 * 設定角色所使用的選單
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "關聯/解除角色所使用的選單", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "關聯/解除角色所使用的選單")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully insert data", response = SetupMenuRole.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/refMenuRole")
	@ResponseBody
	public ResponseEntity<?> refMenuRole(@RequestBody(required = true) SetupMenuRole context) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
				SysMenuRoleMapper sysMenuRoleMapper = sqlSession.getMapper(SysMenuRoleMapper.class);

				// 確認角色是否存在
				String roleId = context.getRoleId();

				SysRole role = new SysRole();
				role.setUserRole(roleId);
				List<SysRole> roleList = sysRoleService.queryBySysRole(role, sqlSession);

				if (roleList == null || roleList.size() == 0) {
					jsonBean.setStatus(SysEnum.statusError.context);
					jsonBean.setMessage("parameter-roleId:" + roleId + " no data");
					return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// 逐一檢查輸入的menu_id是否在系統中存在
				List<String> menuList = context.getMenuIdList();

				boolean allExistTest = menuList.stream().allMatch(menuIdStr -> {
					try {
						SysMenu menuCond = new SysMenu();
						menuCond.setMenuId(new BigDecimal(menuIdStr));
						List<SysMenu> menuListTmp = sysMenuService.queryBySysMenu(menuCond, sqlSession);

						if (menuListTmp == null || menuListTmp.size() > 0) {
							return true;
						}
					} catch (Exception e) {
						log.debug(e.toString());
						return false;
					}

					return false;
				});

				if (allExistTest == false) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("some menu id is not existing in system!");
					return new ResponseEntity<>(jsonBean, HttpStatus.OK);
				}

				// 一律刪除在sys_menu_role中相關角色的資料
				SysMenuRole delCond = new SysMenuRole();
				delCond.setUserRole(roleId);
				int delVal = sysMenuRoleService.delete(delCond, sysMenuRoleMapper);

				log.debug("delete sys_menu_role val:" + delVal);

				// 逐一新增sys_menu_role的資料
				int insVal = menuList.stream().mapToInt(menuIdStr -> {
					try {
						SysMenuRole sysMenuRole = new SysMenuRole();
						sysMenuRole.setUserRole(roleId);
						sysMenuRole.setMenuId(new BigDecimal(menuIdStr));
						return sysMenuRoleService.insert(sysMenuRole, sysMenuRoleMapper);
					} catch (Exception e) {
						log.debug(e.toString());
						return 0;
					}
				}).sum();

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("added data success!:" + insVal);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 將帳號和角色設定連結/解除連結
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "關聯/解除帳號和角色", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "關聯/解除帳號和角色")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/refAccRole")
	@ResponseBody
	public ResponseEntity<?> refAccRole(@RequestParam(name = "userId", required = true) String userId,
			@RequestParam(name = "roleId") String roleId,
			@RequestParam(name = "linkSwitch", required = true) boolean linkSwitch) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysAccountMapper sysAccountMapper = sqlSession.getMapper(SysAccountMapper.class);
				SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
				SysAccountRoleMapper sysAccountRoleMapper = sqlSession.getMapper(SysAccountRoleMapper.class);

				// 檢查輸入的userId是否有資料
				// 檢查輸入的sysRole是否有資料
				SysAccount acc = new SysAccount();
				acc.setUserId(userId);
				List<SysAccount> accList = sysAccountService.queryBySysAccount(acc, sqlSession);

				SysRole role = new SysRole();
				role.setUserRole(roleId);
				List<SysRole> roleList = sysRoleService.queryBySysRole(role, sqlSession);

				if (accList == null || accList.size() == 0 || roleList == null || roleList.size() == 0) {
					jsonBean.setStatus(SysEnum.statusError.context);
					jsonBean.setMessage("parameter-userId:" + userId + " or roleId:" + roleId + " no data");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				int doVal = 0;

				if (linkSwitch == true) {// 設定帳號角色連結
					SysAccountRole accRole = new SysAccountRole();
					accRole.setUserId(userId);
					accRole.setUserRole(roleId);
					List<SysAccountRole> accRoleList = sysAccountRoleService.queryBySysAccountRole(accRole, sqlSession);

					// 檢查sys_account_role資料不存在
					if (accRoleList != null & accRoleList.size() > 0) {
						jsonBean.setStatus(SysEnum.statusError.context);
						jsonBean.setMessage(
								"parameter-userId:" + userId + " and roleId:" + roleId + " data is existing!");
						return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
					}

					// 新增sys_account_role中資料
					SysAccountRole insData = new SysAccountRole();
					insData.setUserId(userId);
					insData.setUserRole(roleId);
					doVal = sysAccountRoleService.insert(insData, sysAccountRoleMapper);
				} else {// 解除帳號角色連結
					SysAccountRole accRole = new SysAccountRole();
					accRole.setUserId(userId);
					accRole.setUserRole(roleId);
					List<SysAccountRole> accRoleList = sysAccountRoleService.queryBySysAccountRole(accRole, sqlSession);

					// 檢查sys_account_role資料已存在
					if (accRoleList != null & accRoleList.size() == 0) {
						jsonBean.setStatus(SysEnum.statusError.context);
						jsonBean.setMessage(
								"parameter-userId:" + userId + " and roleId:" + roleId + " no data, do nothing");
						return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
					}

					// TODO 改為修改sys_account_role使用的角色改為「sys_default_role」資料?
					// 刪除sys_account_role中資料
					SysAccountRole delData = new SysAccountRole();
					delData.setUserId(userId);
					delData.setUserRole(roleId);
					doVal = sysAccountRoleService.deleteByKey(delData, sysAccountRoleMapper);
				}

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusError.context);
				jsonBean.setMessage(
						"parameter-userId:" + userId + " and roleId:" + roleId + " modify data success!:" + doVal);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 停/啟用角色
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "停/啟用角色。注意！關閉或開啟此角色將會同時會設定到關聯之帳號", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "停/啟用角色")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/switchEnableRole")
	@ResponseBody
	public ResponseEntity<?> switchEnableRole(@RequestParam(name = "roleId", required = true) String roleId,
			@RequestParam(name = "enabled", required = true) boolean enabled) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);

				// 查詢角色
				SysRole sysRole = new SysRole();
				sysRole.setUserRole(roleId);
				List<SysRole> sysRoleList = sysRoleService.queryBySysRole(sysRole, sqlSession);

				// 將查詢後的資料修改enable_mark欄位資料為N
				List<SysRole> newSysRoleList = sysRoleList.stream().map(role -> {
					role.setEnableMark(enabled == true ? "Y" : "N");
					return role;
				}).collect(Collectors.toList());

				long succVal = newSysRoleList.stream().mapToInt(role -> {
					try {
						return sysRoleService.update(role, sysRoleMapper);
					} catch (Exception e) {
						log.debug(e.toString());
						Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
						return 0;
					}
				}).sum();

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("updated:" + succVal);
				return new ResponseEntity<>(jsonBean, HttpStatus.OK);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 刪除角色資料
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "刪除角色資料。注意！刪除角色資料須先解除帳號和該角色的關聯", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "刪除角色資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@DeleteMapping("/removeRole")
	@ResponseBody
	public ResponseEntity<?> removeRole(@RequestParam(name = "roleId", required = true) String roleId) {
		JsonBean jsonBean = new JsonBean();

		try {

			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
				SysAccountRoleMapper sysAccountRoleMapper = sqlSession.getMapper(SysAccountRoleMapper.class);

				// 確認是否有帳號尚引用此角色
				SysAccountRole sysAccountRole = new SysAccountRole();
				sysAccountRole.setUserRole(roleId);
				List<SysAccountRole> sysAccountRoleList = sysAccountRoleService.queryBySysAccountRole(sysAccountRole,
						sqlSession);

				if (sysAccountRoleList.size() > 0) {
					List<RemoveRoleBean> resList = sysAccountRoleList.stream().map(accRole -> {

						// 如果account中找不到帳號可直接刪除accountRole中資料
						SysAccount sysAccount = new SysAccount();
						sysAccount.setUserId(accRole.getUserId());
						List<SysAccount> sysAccList = null;

						try {
							sysAccList = sysAccountService.queryBySysAccount(sysAccount, sqlSession);
						} catch (Exception e1) {
							log.debug("query sys_account data have serious error:" + accRole.getUserId());
							return null;
						}

						if (sysAccList == null) {
							log.debug("query sys_account data have serious error:" + accRole.getUserId());
							return null;
						}

						// 如果關聯帳號已不存在可刪除此角色
						if (sysAccList.size() == 0) {
							SysAccountRoleKey pk = new SysAccountRoleKey();
							pk.setUserId(accRole.getUserId());
							pk.setUserRole(roleId);
							int delAccRoleVal = 0;
							int delRoleVal = 0;

							try {
								delAccRoleVal = sysAccountRoleService.deleteByKey(pk, sysAccountRoleMapper);
								delRoleVal = sysRoleService.deleteByKey(roleId, sysRoleMapper);

								RemoveRoleBean res = new RemoveRoleBean();
								res.setUserId(accRole.getUserId());
								res.setUserRole(accRole.getUserRole());
								res.setDelSysAccRoleVal(Long.valueOf(delAccRoleVal));
								res.setDelSysRoleVal(Long.valueOf(delRoleVal));
								return List.of(res);
							} catch (Exception e) {
								log.debug("delete sys_account_role data have serious error:" + accRole.getUserId());
								return null;
							}

						}

						// 帳號尚關聯帳號，須找出來並回傳給呼叫端，且不做任何處理
						if (sysAccList.size() > 0) {
							List<RemoveRoleBean> resList2 = sysAccList.stream().map(acc -> {
								RemoveRoleBean res = new RemoveRoleBean();
								res.setUserId(acc.getUserId());
								res.setUserRole(roleId);
								return res;
							}).collect(Collectors.toList());

							return resList2;
						}

						return null;

					}).filter(res -> res != null).flatMap(list -> list.stream()).collect(Collectors.toList());

					//
					sqlSession.commit();

					// 整理資料
					String resMsg = resList.stream().map(res -> {
						if (res.getDelSysAccRoleVal() == null && res.getDelSysRoleVal() == null) {
							return "尚有帳號" + res.getUserId() + "引用" + "角色" + res.getUserRole() + "，未移除該角色";
						} else {
							return "帳號:" + res.getUserId() + "-角色:" + res.getUserRole() + "均無使用，已移除該角色";
						}
					}).collect(Collectors.joining("\r\n"));

					jsonBean.setMessage(resMsg);
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// 執行刪除
				int delVal = sysRoleService.deleteByKey(roleId, sysRoleMapper);
				String msg = "deleted(" + roleId + ") value:" + delVal;

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage(msg);
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 
	 * @author biruh
	 *
	 */
	public class RemoveRoleBean {
		private String userId;
		private String userRole;
		private Long delSysAccRoleVal;
		private Long delSysRoleVal;

		public String getUserId() {
			return userId;
		}

		public String getUserRole() {
			return userRole;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}

		public Long getDelSysAccRoleVal() {
			return delSysAccRoleVal;
		}

		public Long getDelSysRoleVal() {
			return delSysRoleVal;
		}

		public void setDelSysAccRoleVal(Long delSysAccRoleVal) {
			this.delSysAccRoleVal = delSysAccRoleVal;
		}

		public void setDelSysRoleVal(Long delSysRoleVal) {
			this.delSysRoleVal = delSysRoleVal;
		}

	}

	/**
	 * 修改角色資料
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "新增角色資料。注意！名稱須在系統中為獨一無二的名稱", response = JsonBean.class, tags = {
			"sysgrant" }, notes = "新增角色資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/createRole")
	@ResponseBody
	public ResponseEntity<?> createRole(@RequestParam(name = "roleId", required = true) String roleId,
			@RequestParam(name = "roleName") String roleName,
			@RequestParam(name = "enable", required = true) boolean enable) {
		JsonBean jsonBean = new JsonBean();

		try {
			// 確認欲修改角色是否存在
			SysRole roleCond = new SysRole();
			roleCond.setUserRole(roleId);
			List<SysRole> roleList = sysRoleService.queryBySysRole(roleCond);

			if (roleList == null) {
				log.debug("query error!");
				return new ResponseEntity<>(jsonBean.setMessage("系統發生嚴重錯誤"), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			if (roleList.size() > 0) {
				log.debug("role data is existed!");
				return new ResponseEntity<>(jsonBean.setMessage("所輸入的角色在系統中已存在"), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			SysRole model = new SysRole();
			model.setUserRole(roleId);
			model.setRoleName(roleName);
			model.setEnableMark(enable == true ? "Y" : "N");
			int succVal = sysRoleService.insert(model);

			if (succVal != 1) {
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("執行成功，但新增資料數量和預期不符:" + succVal);
				return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			jsonBean.setStatus(SysEnum.statusSuccess.code);
			jsonBean.setMessage("create user role success!");
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 
	 * @param originRoleId
	 * @param newRoleId
	 * @return
	 */
	@ApiOperation(value = "修改角色ID資料。注意！名稱須在系統中為獨一無二的ID，且會同步修改所關聯的帳號", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "修改角色ID資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/modifyRoleNm")
	@ResponseBody
	public ResponseEntity<?> modifyRoleNm(@RequestParam(name = "originRoleName") String originRoleId,
			@RequestParam(name = "newRoleName") String newRoleId) {
		JsonBean jsonBean = new JsonBean();

		try {
			// 確認欲修改角色是否存在
			SysRole roleCond = new SysRole();
			roleCond.setUserRole(originRoleId);
			List<SysRole> roleList = sysRoleService.queryBySysRole(roleCond);

			if (roleList == null || roleList.size() == 0) {
				log.debug("role data too less!");
				return new ResponseEntity<>(jsonBean.setMessage("查無所輸入的角色"), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			if (roleList.size() > 1) {
				log.debug("role data too much!");
				return new ResponseEntity<>(jsonBean.setMessage("所輸入的角色在系統中有誤"), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			// 確認目標角色是否已使用
			SysRole roleCond2 = new SysRole();
			roleCond2.setUserRole(newRoleId);
			List<SysRole> roleList2 = sysRoleService.queryBySysRole(roleCond2);

			if (roleList2 != null && roleList2.size() > 0) {
				log.debug("we will renew role name but re find data too much for new role name!");
				return new ResponseEntity<>(jsonBean.setMessage("所輸入的新角色名稱，已在系統中被使用"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

			log.debug("check " + originRoleId + " to " + newRoleId + " available");
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// 上述均無誤才可進行修改
		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
			SysAccountRoleMapper sysAccountRoleMapper = sqlSession.getMapper(SysAccountRoleMapper.class);
			SysFuncUiButtonRoleMapper sysFuncUiButtonRoleMapper = sqlSession.getMapper(SysFuncUiButtonRoleMapper.class);
			SysFuncUiFieldRoleMapper sysFuncUiFieldRoleMapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
			SysIndexRoleMapper sysIndexRoleMapper = sqlSession.getMapper(SysIndexRoleMapper.class);
			SysMenuRoleMapper sysMenuRoleMapper = sqlSession.getMapper(SysMenuRoleMapper.class);

			try {
				// 取得所有目標表格的資料，並逐一修改
				// 如果確認可以修改，一次交易中修改「sys_role、sys_account_role、sys_func_ui_button_role、sys_func_ui_field_role、sys_index_role、sys_menu_role、」資料
				// sys_role
				// 除「sys_role」外，其他均須先查回資料，取得該物件id，如index_id，後續使用這些id資料來執行修改
				// 注意!因為java lambda的api的try-catch無法拋出例外，此特性會造成無法一次交易，故這邊使用舊型語法
				// XXX 每組執行後型態均不同，Java relection也不好處理，再看看怎麼優化...
				SysRole sysRoleCond = new SysRole();
				sysRoleCond.setUserRole(originRoleId);
				List<SysRole> sysRoleList = sysRoleService.queryBySysRole(sysRoleCond, sqlSession);

				long succValSysRole = 0;

				// 使用原來的bean將新的名稱set至bean
				if (sysRoleList != null && sysRoleList.size() > 0) {
					for (SysRole data : sysRoleList) {
						SysRole pk = new SysRole();
						pk.setUserRole(data.getUserRole());
						data.setUserRole(newRoleId);
						succValSysRole += sysRoleService.updateModifyPk(pk, data, sysRoleMapper);
					}
				}

				// sys_account_role
				SysAccountRole sysAccountCond = new SysAccountRole();
				sysAccountCond.setUserRole(originRoleId);
				List<SysAccountRole> sysAccountRoleList = sysAccountRoleService.queryBySysAccountRole(sysAccountCond,
						sqlSession);

				long succValSysAccountRole = 0;
				if (sysAccountRoleList != null && sysAccountRoleList.size() > 0) {
					for (SysAccountRole data : sysAccountRoleList) {
						SysAccountRole pk = new SysAccountRole();
						pk.setUserRole(data.getUserRole());
						pk.setUserId(data.getUserId());
						data.setUserRole(newRoleId);
						succValSysAccountRole += sysAccountRoleService.updateModifyPk(pk, data, sysAccountRoleMapper);
					}
				}

				// sys_func_ui_button_role
				SysFuncUiButtonRole sysFuncUiButtonRoleCond = new SysFuncUiButtonRole();
				sysFuncUiButtonRoleCond.setUserRole(originRoleId);
				List<SysFuncUiButtonRole> sysFuncUiButtonRoleList = sysFuncUiButtonRoleService
						.queryBySysFuncUiButtonRole(sysFuncUiButtonRoleCond, sqlSession);

				long succValSysFuncUiButtonRole = 0;
				if (sysFuncUiButtonRoleList != null && sysFuncUiButtonRoleList.size() > 0) {
					for (SysFuncUiButtonRole data : sysFuncUiButtonRoleList) {
						SysFuncUiButtonRole pk = new SysFuncUiButtonRole();
						pk.setUserRole(data.getUserRole());
						pk.setMenuId(data.getMenuId());
						pk.setButtonId(data.getButtonId());
						data.setUserRole(newRoleId);
						succValSysFuncUiButtonRole += sysFuncUiButtonRoleService.updateModifyPk(pk, data,
								sysFuncUiButtonRoleMapper);
					}
				}

				// sys_func_ui_field_role
				SysFuncUiFieldRole sysFuncUiFieldRoleCond = new SysFuncUiFieldRole();
				sysFuncUiFieldRoleCond.setUserRole(originRoleId);
				List<SysFuncUiFieldRole> sysFuncUiFieldRoleList = sysFuncUiFieldRoleService
						.queryBySysFuncUiFieldRole(sysFuncUiFieldRoleCond, sqlSession);

				long succValSysFuncUiFieldRole = 0;
				if (sysFuncUiFieldRoleList != null && sysFuncUiFieldRoleList.size() > 0) {
					for (SysFuncUiFieldRole data : sysFuncUiFieldRoleList) {
						SysFuncUiFieldRole pk = new SysFuncUiFieldRole();
						pk.setUserRole(data.getUserRole());
						pk.setMenuId(data.getMenuId());
						pk.setFieldId(data.getFieldId());
						data.setUserRole(newRoleId);
						succValSysFuncUiFieldRole += sysFuncUiFieldRoleService.updateModifyPk(pk, data,
								sysFuncUiFieldRoleMapper);
					}
				}

				// sys_index_role
				SysIndexRole sysIndexRoleCond = new SysIndexRole();
				sysIndexRoleCond.setUserRole(originRoleId);
				List<SysIndexRole> sysIndexRoleList = sysIndexRoleService.queryBySysIndexRole(sysIndexRoleCond,
						sqlSession);

				long succValSysIndexRole = 0;
				if (sysIndexRoleList != null && sysIndexRoleList.size() > 0) {
					for (SysIndexRole data : sysIndexRoleList) {
						SysIndexRole pk = new SysIndexRole();
						pk.setUserRole(data.getUserRole());
						pk.setIndexId(data.getIndexId());
						data.setUserRole(newRoleId);
						succValSysIndexRole += sysIndexRoleService.updateModifyPk(pk, data, sysIndexRoleMapper);
					}
				}

				// sys_menu_role
				SysMenuRole sysMenuRoleCond = new SysMenuRole();
				sysMenuRoleCond.setUserRole(originRoleId);
				List<SysMenuRole> sysMenuRoleList = sysMenuRoleService.queryBySysMenuRole(sysMenuRoleCond, sqlSession);

				long succValSysMenuRole = 0;
				if (sysMenuRoleList != null && sysMenuRoleList.size() > 0) {
					for (SysMenuRole data : sysMenuRoleList) {
						SysMenuRole pk = new SysMenuRole();
						pk.setUserRole(data.getUserRole());
						pk.setMenuId(data.getMenuId());
						data.setUserRole(newRoleId);
						succValSysMenuRole += sysMenuRoleService.updateModifyPk(pk, data, sysMenuRoleMapper);
					}
				}

				log.debug("succ-succValSysRole:" + succValSysRole + "," + "succ-sysAccountRole:" + succValSysAccountRole
						+ "," + "succ-sysFuncUiButtonRole:" + succValSysFuncUiButtonRole + ","
						+ "succ-sysFuncUiFieldRole:" + succValSysFuncUiFieldRole + "," + "succ-sysIndexRole:"
						+ succValSysIndexRole + "," + "succ-sysMenuRole:" + succValSysMenuRole);

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("success to modify!");
			} catch (Exception e) {
				sqlSession.rollback();
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 
	 * 查詢使用者資料
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "查詢使用者資料", response = JsonBean.class, tags = { "sysgrant" }, notes = "查詢使用者資料")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchAccountInfo")
	@ResponseBody
	public ResponseEntity<?> fetchAccountInfo(@RequestParam(name = "userId") String userId) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysAccount sysAcc = new SysAccount();
				sysAcc.setUserId(userId);
				List<SysAccount> sysAcclist = sysAccountService.queryBySysAccount(sysAcc, sqlSession);

				// TODO 後續再開發其他的資料
				Map<String, String> map = sysAcclist.stream().map(acc -> {
					return List.of(List.of("userId", acc.getUserId()));
				}).flatMap(list -> list.stream())
						.collect(Collectors.toMap(r -> String.valueOf(r.get(0)), r -> String.valueOf(r.get(1))));

				jsonBean.setStatus(SysEnum.statusSuccess.context);
				jsonBean.setData(map);
			} catch (Exception e) {
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 
	 * 查詢使用者可用選單
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "查詢在功能中，使用者可用的欄位和按鈕紐", response = JsonBean.class, tags = { "sysgrant" }, notes = "查詢使用者可用選單")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchSysFuction")
	@ResponseBody
	public ResponseEntity<?> fetchSysFuction(String userId) {
		log.info("顯示資料 -支援帳號可設定多角色  >>> fetchSysFuuction");

		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				if (StringUtils.isNotBlank(userId) == false) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("請輸入使用者代號");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// 找使用者基本資料
				SysAccount sysAccount = new SysAccount();
				sysAccount.setUserId(userId.trim());
				List<SysAccount> account = sysAccountService.queryBySysAccount(sysAccount, sqlSession);

				if (account.size() == 0) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("請輸入使用者代號");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				if (account.size() > 1) {
					log.debug("accont:" + userId + " size is bigger 1, something is warning");
				}

				String resUserId = account.get(0).getUserId();

				SysAccountRole accRole = new SysAccountRole();
				accRole.setUserId(resUserId);
				List<SysAccountRole> accRoleList = sysAccountRoleService.queryBySysAccountRole(accRole, sqlSession);

				if (accRoleList.size() != 1) {
					log.debug("accont:" + userId + " size is bigger 1, something is warning");
				}

				// 找出使用者所設定並"啟用"的角色
				String userRole = accRoleList.get(0).getUserRole();

				SysRole sysRole = new SysRole();
				sysRole.setUserRole(userRole);
				List<SysRole> roleList = sysRoleService.queryBySysRole(sysRole, sqlSession);

				if (roleList == null || roleList.size() == 0) {
					log.debug("account:" + userId + " of sysrole:" + " " + " no data");
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("使用者角色設定發生問題");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				roleList = roleList.stream().distinct()
						.filter(role -> role.getUserRole() != null && "Y".equals(role.getEnableMark()))
						.collect(Collectors.toList());

				// 處理button資料
				// 取得按鈕角色、資本資料
				List<SysFuncUiButtonRole> btnRoleList = roleList.stream().map(role -> {
					try {
						if (StringUtils.isBlank(role.getUserRole())) {
							return null;
						}

						SysFuncUiButtonRole btnRole = new SysFuncUiButtonRole();
						btnRole.setUserRole(role.getUserRole());
						return sysFuncUiButtonRoleService.queryBySysFuncUiButtonRole(btnRole, sqlSession);
					} catch (Exception e) {
						log.debug(e.toString());
						return null;
					}
				}).filter(res -> res != null).flatMap(tmpList -> tmpList.stream()).collect(Collectors.toList());

				// 取得按鈕相關基本資料
				List<Container> btnMapList = btnRoleList.stream().map(btnRole -> {
					try {
						if (StringUtils.isBlank(btnRole.getButtonId()) && btnRole.getMenuId() == null) {
							return null;
						}

						SysFuncUiButton btn = new SysFuncUiButton();
						btn.setMenuId(btnRole.getMenuId());
						btn.setButtonId(btnRole.getButtonId());
						List<SysFuncUiButton> tmpList = sysFuncUiButtonService.queryBySysFuncUiButton(btn, sqlSession);

						// 將權限的enabled mark設定至回傳的資料中
						tmpList.stream().forEach(tmp -> tmp.setEnableMark(btnRole.getEnableMark()));

						return tmpList;
					} catch (Exception e) {
						log.debug(e.toString());
						return null;
					}
				}).filter(res -> res != null).flatMap(tmpList -> tmpList.stream()).distinct().map(tmp -> {
					// 設定是否啟用
					boolean mark = false;

					if (tmp.getEnableMark() != null
							&& ("Y".equals(tmp.getEnableMark()) || "y".equals(tmp.getEnableMark()))) {
						mark = true;
					}

					// 僅供API需要的資料，故這邊需要重新裝載為新的物件
					Container btnRes = new Container();
					btnRes.setObjId(tmp.getButtonId());
					btnRes.setObjName(tmp.getButtonName());
					btnRes.setMenuId(tmp.getMenuId().toString());
					btnRes.setEnableMark(mark);
					btnRes.setObjType(GrantEnum.button.typeName);
					return btnRes;
				}).collect(Collectors.toList());

				// 處理field資料
				// 取得欄位角色、資本資料
				List<SysFuncUiFieldRole> fieldRoleList = roleList.stream().map(role -> {
					try {
						if (StringUtils.isBlank(role.getUserRole())) {
							return null;
						}

						SysFuncUiFieldRole fieldRole = new SysFuncUiFieldRole();
						fieldRole.setUserRole(role.getUserRole());
						return sysFuncUiFieldRoleService.queryBySysFuncUiFieldRole(fieldRole, sqlSession);
					} catch (Exception e) {
						log.debug(e.toString());
						return null;
					}
				}).filter(res -> res != null).flatMap(tmpList -> tmpList.stream()).collect(Collectors.toList());

				// 取得欄位相關基本資料
				List<Container> fieldMapList = fieldRoleList.stream().map(fieldRole -> {
					try {
						if (StringUtils.isBlank(fieldRole.getFieldId()) && fieldRole.getMenuId() == null) {
							return null;
						}

						SysFuncUiField field = new SysFuncUiField();
						field.setMenuId(fieldRole.getMenuId());
						field.setFieldId(fieldRole.getFieldId());
						List<SysFuncUiField> tmpList = sysFuncUiFieldService.queryBySysFuncUiField(field, sqlSession);

						// 將權限的enabled mark設定至回傳的資料中
						tmpList.stream().forEach(tmp -> tmp.setEnableMark(fieldRole.getEnableMark()));

						return tmpList;
					} catch (Exception e) {
						log.debug(e.toString());
						return null;
					}
				}).filter(res -> res != null).flatMap(tmpList -> tmpList.stream()).distinct().map(tmp -> {
					// 設定是否啟用
					boolean mark = false;

					if (tmp.getEnableMark() != null
							&& ("Y".equals(tmp.getEnableMark()) || "y".equals(tmp.getEnableMark()))) {
						mark = true;
					}

					// 僅供API需要的資料，故這邊需要重新裝載為新的物件
					Container btnRes = new Container();
					btnRes.setObjId(tmp.getFieldId());
					btnRes.setObjName(tmp.getFieldName());
					btnRes.setMenuId(tmp.getMenuId().toString());
					btnRes.setEnableMark(mark);
					btnRes.setObjType(GrantEnum.field.typeName);
					return btnRes;
				}).collect(Collectors.toList());

				// 設定結果
				Map<String, List<Container>> res = List.of(btnMapList, fieldMapList).stream()
						.flatMap(list -> list.stream())
						.collect(Collectors.groupingBy(Container::getMenuId, Collectors.toList()));

				// 封裝結果
				jsonBean.setStatus(SysEnum.statusSuccess.context);
				jsonBean.setData(res);
			} catch (Exception e) {
				throw e;
			} finally {
				sqlSession.close();
			}

		} catch (Exception e) {

			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(null);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 
	 * @param mapListContainer
	 * @return
	 */
	public List<String> fetchKey(Map<String, List<Container>> mapListContainer) {
		Iterator<Entry<String, List<Container>>> ite = mapListContainer.entrySet().iterator();

		List<String> keyList = new ArrayList<>();

		while (ite.hasNext()) {
			Entry<String, List<Container>> next = ite.next();
			keyList.add(next.getKey());
		}

		return keyList;
	}

	/**
	 * 
	 * 查詢使用者可用首頁選單(模組)
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "查詢使用者可用首頁選單(模組)", response = JsonBean.class, tags = {
			"sysgrant" }, notes = "查詢使用者可用首頁選單(模組)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchSysIndex")
	@ResponseBody
	public ResponseEntity<?> fetchSysIndex(String userId) {
		log.info("顯示資料 -支援帳號可設定多角色_Index  >>> fetchSysIndex");

		JsonBean jsonBean = new JsonBean();

		try {

			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
			List<? extends Object> resList = new ArrayList<>();

			try {

				// 1.只會找到唯一一筆
				if (StringUtils.isNotBlank(userId) == false) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("請輸入使用者代號");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// 找使用者基本資料
				SysAccount sysAccount = new SysAccount();
				sysAccount.setUserId(userId.trim());
				List<SysAccount> account = sysAccountService.queryBySysAccount(sysAccount, sqlSession);

				if (account.size() == 0) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("請輸入使用者代號");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				if (account.size() > 1) {
					log.debug("accont:" + userId + " size is bigger 1, something is warning");
				}

				// 由user_id找sys_account_role的資料
				String userIdCond = account.get(0).getUserId();

				SysAccountRole accRole = new SysAccountRole();
				accRole.setUserId(userIdCond);
				List<SysAccountRole> accRoleList = sysAccountRoleService.queryBySysAccountRole(accRole, sqlSession);

				List<SysRole> roleList = accRoleList.stream().map(ar -> {
					String userRole = ar.getUserRole();

					if (userRole == null) {
						return null;
					}

					SysRole sysRole = new SysRole();
					sysRole.setUserRole(userRole);

					try {
						List<SysRole> list = sysRoleService.queryBySysRole(sysRole, sqlSession);
						return list;
					} catch (Exception e) {
						log.debug(e.toString());
						return null;
					}
				}).filter(res -> res != null).flatMap(list -> list.stream()).collect(Collectors.toList());

				if (roleList == null || roleList.size() == 0) {
					log.debug("account:" + userId + " of sysrole" + " " + "no data");
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("使用者腳色設定發生問題");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				roleList = roleList.stream().distinct().filter(role -> role.getUserRole() != null)
						.collect(Collectors.toList());

				List<String> resFkUserRole = roleList.stream().map(role -> role.getUserRole())
						.collect(Collectors.toList());

				// 3.多筆找多筆
				List<String> resIndexId = new ArrayList<>();

				if (CollectionUtils.isNotEmpty(roleList)) {

					List<SysIndexRole> insdxRole = new ArrayList<>();

					for (String fkUserRole : resFkUserRole) {

						SysIndexRole model = new SysIndexRole();
						model.setUserRole(fkUserRole.trim());
						List<SysIndexRole> res = sysIndexRoleService.queryBySysIndexRole(model);
						insdxRole.addAll(res);
					}

					resIndexId = insdxRole.stream().map(bean -> String.valueOf(bean.getIndexId())).distinct()
							.collect(Collectors.toList());
				}

				// 4.多筆找多筆
				if (CollectionUtils.isNotEmpty(resIndexId)) {

					List<SysIndex> resultList = new ArrayList<>();

					for (String indexId : resIndexId) {

						SysIndex model = new SysIndex();
						model.setIndexId(new BigDecimal(indexId.trim()));
						List<SysIndex> res = sysIndexService.queryBySysIndex(model);
						resultList.addAll(res);
					}

					resList = resultList;
				}

			} catch (Exception e) {
				throw e;
			} finally {
				sqlSession.close();
			}

			jsonBean.setStatus(SysEnum.statusSuccess.context);
			jsonBean.setData(resList);

		} catch (Exception e) {

			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 
	 * 查詢使用者可用選單
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "查詢使用者可用選單", response = JsonBean.class, tags = { "sysgrant" }, notes = "查詢使用者可用選單")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchSysMenu")
	@ResponseBody
	public ResponseEntity<?> fetchSysMenu(String userId) {
		JsonBean jsonBean = new JsonBean();

		// 總之把需要的menu資料全部查詢出來
		// 根據登入者過濾資料
		// 轉為需要的JSON資料,並送到頁面
		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				List<SysRole> sysRoleList = this.findRoleWithUserId(userId, sqlSession);

				// 查詢menu和index基本資料
				List<SysMenuRole> sysMenuRoleList = sysRoleList.stream().map(role -> {
					String userRole = role.getUserRole();

					if (userRole == null) {
						return null;
					}

					try {
						SysMenuRole sysMenuRole = new SysMenuRole();
						sysMenuRole.setUserRole(userRole);
						List<SysMenuRole> list = sysMenuRoleService.queryBySysMenuRole(sysMenuRole, sqlSession);
						return list;
					} catch (Exception e) {
						log.debug(e.toString());
						return null;
					}
				}).filter(res -> res != null).flatMap(list -> list.stream()).collect(Collectors.toList());

				// 查詢資料
				List<SysMenu> sysMenuList = sysMenuService.queryAll(sqlSession);

				// 過濾已關閉功能
				sysMenuList = sysMenuList.stream()
						.filter(menu -> "Y".equals(menu.getEnableMark()) & menu.getIndexSort() != null)
						.collect(Collectors.toList());

				// 依照上面找到的角色關聯資料，過濾基礎資料
				List<? extends Object> newMenuList = this.findSameFiledDataAListByBList(
						new Bean4Compare().setName("sys_menu").setFieldNm("menu_id").setBeanList(sysMenuList),
						new Bean4Compare().setName("sys_menu_role").setFieldNm("menu_id").setBeanList(sysMenuRoleList));

				// 排序結果(這邊需要強制轉型)
				Comparator<SysMenu> comparator = new Comparator<SysMenu>() {
					@Override
					public int compare(SysMenu o1, SysMenu o2) {
						return o1.getIndexSort().intValue() - o2.getIndexSort().intValue();
					}

				};

				newMenuList = newMenuList.stream().map(bean -> (SysMenu) bean).sorted(comparator)
						.map(bean -> (Object) bean).collect(Collectors.toList());

				jsonBean.setStatus(SysEnum.statusSuccess.context);
				jsonBean.setData(newMenuList);
			} catch (Exception e) {
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 
	 * 根據傳入indexid，查詢使用者可用根選單、選單
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "查詢使用者可用根選單、選單", response = JsonBean.class, tags = { "sysgrant" }, notes = "查詢使用者可用選單")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchSysMenuType2")
	@ResponseBody
	public ResponseEntity<?> fetchSysMenuType2(@RequestParam(value = "indexId") String indexId,
			@RequestParam(name = "userId") String userId) {
		JsonBean jsonBean = new JsonBean();

		// 總之把需要的menu資料全部查詢出來
		// 根據登入者過濾資料
		// 轉為需要的JSON資料,並送到頁面
		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				// 因應其他無模組的設計的系統，很容易不會輸入indexId，故造成null的現象，
				// 利用sys表格設計上預設使用「0」模組的特性，故將indexId的null轉為0即可解決此問題
				if (indexId == null || "null".equals(indexId)) {
					indexId = "0";
				} else {
					log.debug("have some error - indexId:" + indexId);
				}

				List<SysRole> sysRoleList = this.findRoleWithUserId(userId, sqlSession);

				// 查詢menu和index基本資料
				List<SysMenuRole> sysMenuRoleList = sysRoleList.stream().map(role -> {
					String userRole = role.getUserRole();

					if (userRole == null) {
						return null;
					}

					try {
						SysMenuRole sysMenuRole = new SysMenuRole();
						sysMenuRole.setUserRole(userRole);
						List<SysMenuRole> list = sysMenuRoleService.queryBySysMenuRole(sysMenuRole, sqlSession);
						return list;
					} catch (Exception e) {
						log.debug(e.toString());
						return null;
					}
				}).filter(res -> res != null).flatMap(list -> list.stream()).collect(Collectors.toList());

				// 查詢資料
				SysMenu sysMenu = new SysMenu();
				sysMenu.setIndexId(new BigDecimal(indexId));// TODO 好像有資料不為數字，導致Exception，待找
				List<SysMenu> sysMenuList = sysMenuService.queryBySysMenu(sysMenu, sqlSession);

				// 過濾已關閉功能
				sysMenuList = sysMenuList.stream()
						.filter(menu -> "Y".equals(menu.getEnableMark()) & menu.getIndexSort() != null)
						.collect(Collectors.toList());

				// 依照上面找到的角色關聯資料，過濾基礎資料
				List<? extends Object> newMenuList = this.findSameFiledDataAListByBList(
						new Bean4Compare().setName("sys_menu").setFieldNm("menu_id").setBeanList(sysMenuList),
						new Bean4Compare().setName("sys_menu_role").setFieldNm("menu_id").setBeanList(sysMenuRoleList));

				// 排序結果(這邊需要強制轉型)
				Comparator<SysMenu> comparator = new Comparator<SysMenu>() {
					@Override
					public int compare(SysMenu o1, SysMenu o2) {
						return o1.getIndexSort().intValue() - o2.getIndexSort().intValue();
					}

				};

				newMenuList = newMenuList.stream().map(bean -> (SysMenu) bean).sorted(comparator)
						.map(bean -> (Object) bean).collect(Collectors.toList());

				jsonBean.setStatus(SysEnum.statusSuccess.context);
				jsonBean.setData(newMenuList);
			} catch (Exception e) {
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 
	 * 根據傳入indexid，查詢使用者可用選單
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "查詢使用者可用選單(準備棄用)", response = JsonBean.class, tags = { "sysgrant" }, notes = "查詢使用者可用選單")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchSysMenuType3")
	// TODO 尚待測試前端該怎麼使用x-www-form-urlencoded
	// XXX 此method所使用menu物件為早期使用，思考較不周慮
//	@PostMapping("/fetchSysMenuType3") // 可以使用@RequestParam，但需要在header設定content-type:application/x-www-form-urlencoded
	@ResponseBody
	public ResponseEntity<?> fetchSysMenuType3(@RequestParam(value = "indexId") String indexId,
			@RequestParam(name = "userId") String userId) {
		JsonBean jsonBean = new JsonBean();

		// 查詢全部menu資料
		// 根據登入者過濾資料
		// 轉為需要的JSON資料,並送到頁面
		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				List<SysRole> sysRoleList = this.findRoleWithUserId(userId, sqlSession);

				// 因應其他無模組的設計的系統，很容易不會輸入indexId，故造成null的現象，
				// 利用sys表格設計上預設使用「0」模組的特性，故將indexId的null轉為0即可解決此問題
				if (indexId == null || "null".equals(indexId)) {
					indexId = "0";
				} else {
					log.debug("have some error - indexId:" + indexId);
				}

				// 查詢menu和index基本資料
				List<SysMenuRole> sysMenuRoleList = sysRoleList.stream().map(role -> {
					String userRole = role.getUserRole();

					if (userRole == null) {
						return null;
					}

					try {
						SysMenuRole sysMenuRole = new SysMenuRole();
						sysMenuRole.setUserRole(userRole);
						List<SysMenuRole> list = sysMenuRoleService.queryBySysMenuRole(sysMenuRole, sqlSession);
						return list;
					} catch (Exception e) {
						log.debug(e.toString());
						return null;
					}
				}).filter(res -> res != null).flatMap(list -> list.stream()).collect(Collectors.toList());

				// 查詢資料
				SysMenu sysMenu = new SysMenu();
				sysMenu.setIndexId(new BigDecimal(indexId));// TODO 好像有資料不為數字，導致Exception，待找
				List<SysMenu> sysMenuList = sysMenuService.queryBySysMenu(sysMenu, sqlSession);

				// 過濾已關閉功能，排除未設定排序的資料
				sysMenuList = sysMenuList.stream()
						.filter(menu -> "Y".equals(menu.getEnableMark()) & menu.getIndexSort() != null)
						.collect(Collectors.toList());

				// 依照上面找到的角色關聯資料，過濾基礎資料
				List<? extends Object> newMenuList = this.findSameFiledDataAListByBList(
						new Bean4Compare().setName("sys_menu").setFieldNm("menu_id").setBeanList(sysMenuList),
						new Bean4Compare().setName("sys_menu_role").setFieldNm("menu_id").setBeanList(sysMenuRoleList));

				// 排序結果(這邊需要強制轉型)
				Comparator<SysMenu> comparator = new Comparator<SysMenu>() {
					@Override
					public int compare(SysMenu o1, SysMenu o2) {
						return o1.getIndexSort().intValue() - o2.getIndexSort().intValue();
					}
				};

				newMenuList = newMenuList.stream().map(bean -> (SysMenu) bean).sorted(comparator)
						.map(bean -> (Object) bean).collect(Collectors.toList());

				// 將資料分層
				List<SysMenu> rootList = newMenuList.stream().map(bean -> (SysMenu) bean)
						.filter(menu -> menu.getMenuUpperId() == null).collect(Collectors.toList());

				List<SysMenu> subList = newMenuList.stream().map(bean -> (SysMenu) bean)
						.filter(menu -> menu.getMenuUpperId() != null).collect(Collectors.toList());

				// XXX 可能為無用程式碼，確認後可刪
				// 分類第一、二層的資料
				// List<SysMenu> layer1List = new ArrayList<>();
				// List<SysMenu> layer2List = new ArrayList<>();
				//
				// subList.stream().forEach(sub -> {
				// boolean isLayer1 = rootList.stream()
				// .anyMatch(root ->
				// sub.getMenuUpperId().toString().equals(root.getMenuId().toString()));
				//
				// if (isLayer1 == true) {
				// layer1List.add(sub);
				// } else {
				// layer2List.add(sub);
				// }
				// });

				List<Menu> resList = this.lasagna(rootList, subList);

				jsonBean.setStatus(SysEnum.statusSuccess.context);
				jsonBean.setData(resList);
			} catch (Exception e) {
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 分層程式
	 * 
	 * @param rootList
	 * @param subMenuMap
	 * @return
	 */
	public List<Menu> lasagna(List<SysMenu> rootList, List<SysMenu> subMenuList) {
		return rootList.stream().map(r -> {
			Menu menu = new Menu();
			menu.setTitle(r.getMenuName());
			menu.setIcon(r.getMenuItemUrl());
			menu.setHref(r.getMenuFuncUrl());
			menu.setIconclass(r.getMenuItemClass());

			List<SysMenu> subMenuOfRoot = subMenuList.stream()
					.filter(sub -> sub.getMenuUpperId().toString().equals(r.getMenuId().toString()))
					.collect(Collectors.toList());

//			log.debug("sub menu of root'" + r.getMenuId().toString() + "' size:" + subMenuOfRoot.size());

			if (subMenuOfRoot != null && subMenuOfRoot.size() > 0) {
				List<Menu> child = subMenuOfRoot.stream().map(r2 -> {
					Menu nextMenu = new Menu();
					nextMenu.setTitle(r2.getMenuName());
					nextMenu.setIcon(r2.getMenuItemUrl());
					nextMenu.setHref(r2.getMenuFuncUrl());
					menu.setIconclass(r.getMenuItemClass());
					return nextMenu;
				}).collect(Collectors.toList());

				menu.setChild(child);
			}

			// 如果有子項目，則取根資料再往下找
			if (menu.getChild() != null && menu.getChild().size() > 0) {
				List<Menu> renewNewMenu = this.lasagna(subMenuOfRoot, subMenuList);
				menu.setChild(renewNewMenu);
			}

			return menu;
		}).collect(Collectors.toList());
	}

	/**
	 * 
	 * @author biruh
	 *
	 */
	public class Menu {
		private String href = "";
		private String title = "";
		private String icon = "";
		private String iconclass = "";
		private List<Menu> child = new ArrayList<>();

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public List<Menu> getChild() {
			return child;
		}

		public void setChild(List<Menu> child) {
			this.child = child;
		}

		public String getIconclass() {
			return iconclass;
		}

		public void setIconclass(String iconclass) {
			this.iconclass = iconclass;
		}

	}

	/**
	 * 
	 * 根據傳入indexid，查詢使用者可用選單
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "查詢使用者可用選單", response = JsonBean.class, tags = { "sysgrant" }, notes = "查詢使用者可用選單")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
//	@GetMapping("/fetchSysMenuType4")
	// TODO 尚待測試前端該怎麼使用x-www-form-urlencoded
	@PostMapping("/fetchSysMenuType3") // 可以使用@RequestParam，但需要在header設定content-type:application/x-www-form-urlencoded
	@ResponseBody
	public ResponseEntity<?> fetchSysMenuType4(@RequestParam(value = "indexId") String indexId,
			@RequestParam(name = "userId") String userId) {
		JsonBean jsonBean = new JsonBean();

		// 查詢全部menu資料
		// 根據登入者過濾資料
		// 轉為需要的JSON資料,並送到頁面
		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				List<SysRole> sysRoleList = this.findRoleWithUserId(userId, sqlSession);

				// 因應其他無模組的設計的系統，很容易不會輸入indexId，故造成null的現象，
				// 利用sys表格設計上預設使用「0」模組的特性，故將indexId的null轉為0即可解決此問題
				if (indexId == null || "null".equals(indexId)) {
					indexId = "0";
				} else {
					log.debug("have some error - indexId:" + indexId);
				}

				// 查詢menu和index基本資料
				List<SysMenuRole> sysMenuRoleList = sysRoleList.stream().map(role -> {
					String userRole = role.getUserRole();

					if (userRole == null) {
						return null;
					}

					try {
						SysMenuRole sysMenuRole = new SysMenuRole();
						sysMenuRole.setUserRole(userRole);
						List<SysMenuRole> list = sysMenuRoleService.queryBySysMenuRole(sysMenuRole, sqlSession);
						return list;
					} catch (Exception e) {
						log.debug(e.toString());
						return null;
					}
				}).filter(res -> res != null).flatMap(list -> list.stream()).collect(Collectors.toList());

				// 查詢資料
				SysMenu sysMenu = new SysMenu();
				sysMenu.setIndexId(new BigDecimal(indexId));// TODO 好像有資料不為數字，導致Exception，待找
				List<SysMenu> sysMenuList = sysMenuService.queryBySysMenu(sysMenu, sqlSession);

				// 依照上面找到的角色關聯資料，過濾基礎資料
				List<? extends Object> newMenuList = this.findSameFiledDataAListByBList(
						new Bean4Compare().setName("sys_menu").setFieldNm("menu_id").setBeanList(sysMenuList),
						new Bean4Compare().setName("sys_menu_role").setFieldNm("menu_id").setBeanList(sysMenuRoleList));

				// 排序結果(這邊需要強制轉型)
				Comparator<SysMenu> comparator = new Comparator<SysMenu>() {
					@Override
					public int compare(SysMenu o1, SysMenu o2) {
						return o1.getIndexSort().intValue() - o2.getIndexSort().intValue();
					}
				};

				newMenuList = newMenuList.stream().map(bean -> (SysMenu) bean).sorted(comparator)
						.map(bean -> (Object) bean).collect(Collectors.toList());

				// 將資料分層
				List<SysMenu> rootList = newMenuList.stream().map(bean -> (SysMenu) bean)
						.filter(menu -> menu.getMenuUpperId() == null).collect(Collectors.toList());

				List<SysMenu> subList = newMenuList.stream().map(bean -> (SysMenu) bean)
						.filter(menu -> menu.getMenuUpperId() != null).collect(Collectors.toList());

				List<SetupMenuSub> resList = this.lasagna2(rootList, subList);

				jsonBean.setStatus(SysEnum.statusSuccess.context);
				jsonBean.setData(resList);
			} catch (Exception e) {
				throw e;
			} finally {
				sqlSession.close();
			}
		} catch (Exception e) {
			jsonBean.setStatus(SysEnum.statusError.context);
			jsonBean.setData(0);

			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(jsonBean, HttpStatus.OK);
	}

	/**
	 * 分層程式
	 * 
	 * @param rootList
	 * @param subMenuMap
	 * @return
	 */
	public List<SetupMenuSub> lasagna2(List<SysMenu> rootList, List<SysMenu> subMenuList) {
		return rootList.stream().map(r -> {
			SetupMenuSub menu = new SetupMenuSub();
			menu.setMenuName(r.getMenuName());
			menu.setMenuFuncUrl(r.getMenuFuncUrl());
			menu.setMenuItemClass(r.getMenuItemClass());

			List<SysMenu> subMenuOfRoot = subMenuList.stream()
					.filter(sub -> sub.getMenuUpperId().toString().equals(r.getMenuId().toString()))
					.collect(Collectors.toList());

//			log.debug("sub menu of root'" + r.getMenuId().toString() + "' size:" + subMenuOfRoot.size());

			if (subMenuOfRoot != null && subMenuOfRoot.size() > 0) {
				List<SetupMenuSub> child = subMenuOfRoot.stream().map(r2 -> {
					SetupMenuSub nextMenu = new SetupMenuSub();
					nextMenu.setMenuName(r.getMenuName());
					nextMenu.setMenuFuncUrl(r.getMenuFuncUrl());
					nextMenu.setMenuItemClass(r.getMenuItemClass());
					return nextMenu;
				}).collect(Collectors.toList());

				menu.setSubMenuList(child);
			}

			// 如果有子項目，則取根資料再往下找
			if (menu.getSubMenuList() != null && menu.getSubMenuList().size() > 0) {
				List<SetupMenuSub> renewNewMenu = this.lasagna2(subMenuOfRoot, subMenuList);
				menu.setSubMenuList(renewNewMenu);
			}

			return menu;
		}).collect(Collectors.toList());
	}

	/**
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 * 
	 */
	public List<SysRole> findRoleWithUserId(String userId, SqlSession sqlSession) throws Exception {
		SysAccount sysAccount = new SysAccount();
		sysAccount.setUserId(userId);

		// 找到帳號的角色
		List<SysAccount> accList = sysAccountService.queryBySysAccount(sysAccount, sqlSession);

		if (accList == null || accList.size() != 1) {
			throw new Exception("data of sys_account have serious error");
		}

		// 由user_id找sys_account_role的資料
		String userIdCond = accList.get(0).getUserId();

		SysAccountRole accRole = new SysAccountRole();
		accRole.setUserId(userIdCond);
		List<SysAccountRole> accRoleList = sysAccountRoleService.queryBySysAccountRole(accRole, sqlSession);

		List<SysRole> roleList = accRoleList.stream().map(ar -> {
			String userRole = ar.getUserRole();

			if (userRole == null) {
				return null;
			}

			SysRole sysRole = new SysRole();
			sysRole.setUserRole(userRole);

			try {
				List<SysRole> list = sysRoleService.queryBySysRole(sysRole, sqlSession);
				return list;
			} catch (Exception e) {
				log.debug(e.toString());
				return null;
			}
		}).filter(res -> res != null).flatMap(list -> list.stream()).collect(Collectors.toList());

		if (roleList == null || roleList.size() == 0) {
			throw new Exception("user role[" + userId + "] data is not exist sys_role");
		}

		return roleList;
	}

	/**
	 * 過濾資料
	 * 
	 * 此功能僅能針對「少量的資料」作處理，大量的資料請使用其他方法
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	public List<? extends Object> findSameFiledDataAListByBList(Bean4Compare adata, Bean4Compare filterData)
			throws Exception {
		if (adata.getFieldNm() == null || filterData.getFieldNm() == null) {
			throw new Exception("filed name of adata of bdata is null");
		}

		if (adata.getBeanList() == null || adata.getBeanList().size() == 0) {
			log.debug("data list of adata is null or no data:" + adata.getName());
			return new ArrayList<>();
		}

		if (filterData.getBeanList() == null || filterData.getBeanList().size() == 0) {
			log.debug("data list of filter data is null or no data:" + filterData.getName());
			return new ArrayList<>();
		}

		log.debug("a size:" + adata.getBeanList().size());
		log.debug("filter size:" + filterData.getBeanList().size());

		List<String> filterDataFiledVarList = filterData.getBeanList().stream().map(bean -> {
			try {
				String filedNm = this.delExpAnd1stStrUppercaseType4(filterData.getFieldNm(), "_");
				return this.getFiledValFromObj(bean, filedNm);
			} catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException
					| InvocationTargetException e) {
				Arrays.asList(e.getStackTrace()).stream().forEach(sub -> log.debug(sub.toString()));
				return null;
			}
		}).filter(res -> res != null).map(res -> String.valueOf(res)).collect(Collectors.toList());

		if (filterDataFiledVarList.size() == 0) {
			log.debug("no data" + "-" + adata.getName());
		}

		return adata.getBeanList().stream().filter(bean -> {
			String bFildeVar = null;
			try {
				String filedNm = this.delExpAnd1stStrUppercaseType4(filterData.getFieldNm(), "_");
				bFildeVar = this.getFiledValFromObj(bean, filedNm);
			} catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException
					| InvocationTargetException e) {
				Arrays.asList(e.getStackTrace()).stream().forEach(sub -> log.debug(sub.toString()));
			}

			if (bFildeVar == null) {
				return false;
			}

			String bFiledVarTmp = bFildeVar;
			return filterDataFiledVarList.stream().anyMatch(aFiledVar -> aFiledVar.equals(bFiledVarTmp));
		}).collect(Collectors.toList());
	}

	/**
	 * 根據變數名稱，取得物件中該變數名稱的值(注意!此方法會將值一律回傳為字串)
	 * 
	 * @param obj
	 * @param methodNm
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public String getFiledValFromObj(Object obj, String filedNm) throws IllegalAccessException,
			IllegalArgumentException, NoSuchMethodException, SecurityException, InvocationTargetException {
		String methodNm = this.appendStr2Head("get", filedNm);

		// XXX 因為Filed不知道如何使用...故這邊均使用getMethod的方式
		Class<? extends Object> aIns = obj.getClass();
		Object insIdObj = aIns.getMethod(methodNm).invoke(obj);

		String insIdStr = null;

		if (insIdObj != null) {
			insIdStr = String.valueOf(insIdObj);
		}

		return insIdStr;
	}

	/**
	 * 將變數名稱前面加東西,並且變數的第一個字改為大寫,例:abc加get=>getAbc abc加edf=>edfAbc
	 */
	public String appendStr2Head(String head, String str) {
		return head + this.firstToUppercase(str);
	}

	/**
	 * 刪除字串中所有的"_",並將"_"後的第一個字母改為大寫(此方法會將名稱改為全小寫,再將"_"第一個字改為大小,例:Ab_CD=>abCd)
	 */
	public String delExpAnd1stStrUppercaseType4(String str, String exp) {
		String res = Arrays.asList(str.split(exp)).stream().map(ss -> this.firstToUppercase(ss.toLowerCase()))
				.collect(Collectors.joining());
		return this.firstToLowcase(res.toString());
	}

	/**
	 * 將一全為小寫之字串的第一個字轉為大寫
	 */
	public String firstToUppercase(String s) {
		StringBuffer b = new StringBuffer();
		b.append(s.substring(0, 1).toUpperCase());
		b.append(s.substring(1));
		return b.toString();
	}

	/**
	 * 將一全為小寫之字串的第一個字轉為小寫
	 */
	public String firstToLowcase(String s) {
		StringBuffer b = new StringBuffer();
		b.append(s.substring(0, 1).toLowerCase());
		b.append(s.substring(1));
		return b.toString();
	}

	/**
	 * 
	 * @param bList
	 * @return
	 */
	public String genSubMenuDataArray(List<SysMenu> bList) {
		StringBuffer sb = new StringBuffer();

		for (SysMenu b2bIndexMenu : bList) {
			sb.append(this.genSubMenuData(b2bIndexMenu) + ",");
		}

		return null;
	}

	/**
	 * @return
	 * 
	 */
	public String genSubMenuData(SysMenu menu) {
		StringBuffer sb = new StringBuffer();

		Map<String, String> caseMap = new HashMap<>();

		caseMap.put("SUB_MENU_NM", menu.getMenuName());
		caseMap.put("SUB_MENU_HREF", menu.getMenuFuncUrl());
		caseMap.put("SUB_MENU_IMG", menu.getMenuItemUrl());

		if (caseMap.get("SUB_MENU_NM") != null && !"".equals(caseMap.get("SUB_MENU_NM"))) {
			// TODO 使用gson轉字串，可能需要特定物件
			// sb.append(JSONTransUtil.combineMapToJSONStr(caseMap));
		}

		return sb.toString();
	}

	/**
	 * 
	 * @author leo_lee
	 *
	 */
	public class FunctionRes {
		Map<String, List<Container>> button;
		Map<String, List<Container>> field;

		public Map<String, List<Container>> getButton() {
			return button;
		}

		public void setButton(Map<String, List<Container>> button) {
			this.button = button;
		}

		public Map<String, List<Container>> getField() {
			return field;
		}

		public void setField(Map<String, List<Container>> field) {
			this.field = field;
		}

	}

	/**
	 * 
	 * @author biruh
	 *
	 */
	public class Container {
		private String menuId;
		private String objId;
		private String objName;
		private String objType;
		private boolean enableMark;

		public String getMenuId() {
			return menuId;
		}

		public void setMenuId(String menuId) {
			this.menuId = menuId;
		}

		public String getObjId() {
			return objId;
		}

		public void setObjId(String objId) {
			this.objId = objId;
		}

		public String getObjName() {
			return objName;
		}

		public void setObjName(String objName) {
			this.objName = objName;
		}

		public String getObjType() {
			return objType;
		}

		public void setObjType(String objType) {
			this.objType = objType;
		}

		public boolean isEnableMark() {
			return enableMark;
		}

		public void setEnableMark(boolean enableMark) {
			this.enableMark = enableMark;
		}

	}

	/**
	 * 
	 * @author leo_lee
	 *
	 */
	public class Bean4Compare {
		private String name;// 僅供log使用
		private String fieldNm;
		private List<? extends Object> beanList;

		public String getName() {
			return name;
		}

		public Bean4Compare setName(String name) {
			this.name = name;
			return this;
		}

		public String getFieldNm() {
			return fieldNm;
		}

		public Bean4Compare setFieldNm(String fieldNm) {
			this.fieldNm = fieldNm;
			return this;
		}

		public List<? extends Object> getBeanList() {
			return beanList;
		}

		public Bean4Compare setBeanList(List<? extends Object> beanList) {
			this.beanList = beanList;
			return this;
		}

	}

	/**
	 * 
	 * @author biruh
	 *
	 */
	public enum GrantEnum {
		field("field"), button("button");

		public String typeName;

		private GrantEnum(String typeName) {
			this.typeName = typeName;
		}

	}

	// jdbctemplate test
	@Autowired
	private DBConnection dbConnection;

	@Autowired
	private SysAccountRepository accrepo;

//	@Autowired
//	private DBConnectionSqlite dbConnSqlite;

}