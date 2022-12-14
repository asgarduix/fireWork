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
 * ???????????????
 * 
 */
@Lazy
@RequestMapping("sysgrant")
@RestController
@Api(value = "fixed test", tags = { "sysgrant" })
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
	 * TODO ?????????
	 * 
	 * ??????index(????????????)
	 * 
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "??????index(????????????)??????", response = JsonBean.class, tags = {
			"sysgrant" }, notes = "??????index(????????????)??????")
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

				// TODO ???????

				List<SysIndex> indexList = sysIndexService.queryAll(sqlSession);

				List<Integer> numList = indexList.stream().map(index -> index.getIndexId().intValue())
						.collect(Collectors.toList());

				// ???????????????????????????????????????????????????????????????????????????(??????????????????9000000000????????????????????????????????????999???)
				// ?????????999??????????????????
				// XXX ???????????????????????????????????????????????????????
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
	 * TODO ?????????
	 * 
	 * ??????????????????
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

				// ??????????????????
				SysAccount sysAccCond = new SysAccount();
				sysAccCond.setAkaId(userId);
				List<SysAccount> accList = sysAccountService.queryBySysAccount(sysAccCond, sqlSession);

				if (accList == null || accList.size() != 1) {
					jsonBean.setStatus(SysEnum.statusSuccess.code);
					jsonBean.setMessage("we find user id:" + userId + ", size is not 1:"
							+ (accList == null ? null : accList.size()));
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				List<SysAccount> newAccList = accList.stream().map(acc -> {
					acc.setAkaId(userId);
					acc.setMail(mail);
					acc.setUserName(userName);
					acc.setCipher(password);
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
	 * TODO ?????????
	 * 
	 * ??????/????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "??????/????????????", response = JsonBean.class, tags = { "sysgrant" }, notes = "??????/????????????")
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

				// ????????????
				SysMenu sysMenu = new SysMenu();
				List<SysMenu> sysMenuList = sysMenuService.queryBySysMenu(sysMenu, sqlSession);

				// ???????????????????????????enable_mark???????????????N
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
	 * TODO ?????????
	 * 
	 * ??????????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "??????????????????", response = JsonBean.class, tags = { "sysgrant" }, notes = "??????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchRole")
	@ResponseBody
	public ResponseEntity<?> template(
			@ApiParam(value = "?????????role id???????????????????????????") @RequestParam(name = "roleId", required = true) String roleId) {
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
	 * ?????????????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "?????????????????????", response = JsonBean.class, tags = { "sysgrant" }, notes = "?????????????????????")
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

				// ????????????????????????
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
	 * ????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "????????????(?????????????????????????????????)", response = JsonBean.class, tags = { "sysgrant" }, notes = "")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping("/createAccount")
	@ResponseBody
	public ResponseEntity<?> createAccount(@RequestParam(name = "userId", required = true) String akaId,
			@RequestParam(name = "userName", required = true) String userName,
			@RequestParam(name = "mail", required = false) String mail,
			@ApiParam(value = "(We will encode to MD5)") @RequestParam(name = "password") String cipher) {
		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				SysAccountRoleMapper sysAccountRoleMapper = sqlSession.getMapper(SysAccountRoleMapper.class);
				SysAccountMapper sysAccountMapper = sqlSession.getMapper(SysAccountMapper.class);

				// ?????????????????????ID???????????????
				SysAccount acc = new SysAccount();
				acc.setAkaId(akaId);
				List<SysAccount> accList = sysAccountService.queryBySysAccount(acc, sqlSession);

				if (accList.size() > 0) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("user id is existing!");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// ????????????????????????
				Asiutil util = new Asiutil();
				String cipherEncode = util.encrypt(cipher, util.genalgstr());

				SysAccount newAcc = new SysAccount();
				newAcc.setAkaId(akaId);
				newAcc.setUserName(userName);
				newAcc.setCipher(cipherEncode);
				newAcc.setCrtAkaId(akaId);
				newAcc.setMail(mail);
				newAcc.setCrtDate(new Date());

				int insVal = sysAccountService.insert(newAcc, sysAccountMapper);

				// ?????????????????????(TODO ??????????????????)
				SysAccountRole newAccRole = new SysAccountRole();
				newAccRole.setAkaId(akaId);
				newAccRole.setUserRole(akaId);
				newAccRole.setUserRole(SysEnum.systemDefaultRole.context);
				int insVal2 = sysAccountRoleService.insert(newAccRole, sysAccountRoleMapper);

				if (insVal != 1 & insVal2 != 1) {
					jsonBean.setStatus(SysEnum.statusSuccess.code);
					jsonBean.setMessage("added user id:" + akaId + " have serious error, insert val:" + insVal);
					return new ResponseEntity<>(jsonBean, HttpStatus.OK);
				}

				sqlSession.commit();

				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("added user id:" + akaId);
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
	 * TODO ?????????
	 * 
	 * ??????/????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "???/????????????", response = JsonBean.class, tags = { "sysgrant" }, notes = "???/????????????")
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

				// ????????????
				SysAccount sysAcc = new SysAccount();
				sysAcc.setAkaId(userId);
				List<SysAccount> sysAccList = sysAccountService.queryBySysAccount(sysAcc, sqlSession);

				// ???????????????????????????enable_mark???????????????N
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
	 * TODO ?????????
	 * 
	 * ???????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "????????????", response = JsonBean.class, tags = { "sysgrant" }, notes = "????????????")
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

				// ?????????????????????????????????
				SysAccount accCond = new SysAccount();
				List<SysAccount> sysAccList = sysAccountService.queryBySysAccount(accCond, sqlSession);

				if (sysAccList == null || sysAccList.isEmpty()) {
					jsonBean.setStatus(SysEnum.statusSuccess.code);
					jsonBean.setMessage("user id:" + userId + " is not existing");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				int delVal = sysAccountService.deleteByKey(userId, sysAccountMapper);

				// ????????????????????????????????????
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

	// TODO ????????????

//	/**
//	 * 
//	 * ?????????????????????
//	 * 
//	 * @param paramTestList
//	 */
//	@ApiOperation(value = "??????token?????????????????????", response = JsonBean.class, tags = { "sysgrant","sysadmin" }, notes = "????????????????????????")
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
	 * ?????????????????????
	 * 
	 * XXX ????????????????????????????????????????????????(???SQL??????)?????????????????????????????????????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "??????(???)????????????(???API?????????????????????)", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "?????????????????????")
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

				// TODO ???????????????index????????????????????????????

				// ?????????????????????(manu_name???menu_func_url)?????????????????????
				List<SetupMenuSub> menus4valid = context.getMenuList();

				boolean isAnyBlank = menus4valid.stream()
						.anyMatch(menu -> (StringUtils.isAnyBlank(menu.getMenuName(), menu.getMenuFuncUrl())));

				if (isAnyBlank == true) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("Error! You maybe not input menu url or menu name ");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// ???????????????????????????????????????????????????
				List<SysMenu> menuList = sysMenuService.queryAll(sqlSession);

				List<SysMenu> rootList = menuList.stream().filter(menu -> menu.getMenuUpperId() == null)
						.collect(Collectors.toList());

				// ????????????????????????999????????????
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

				// ???????????????????????????????????????????????????????????????????????????(??????????????????9000000000????????????????????????????????????999???)
				// ?????????999??????????????????
				// XXX ???????????????????????????????????????????????????????
				List<BigDecimal> canUseNos = new ArrayList<>();

				for (int i = 1; i < 1000; i++) {
					int x = i;
					boolean already = numList.stream().anyMatch(num -> num.compareTo(Integer.valueOf(x)) != 0);

					if (already == false) {
						canUseNos.add(new BigDecimal("9" + String.format("%09d", i)));
					}
				}

				// ??????????????????????????????
				List<SetupMenuSub> tmpMenuList = context.getMenuList();
				int size = context.getMenuList().size();

				// ??????sys_menu??????
				List<SysMenu> insertList = new ArrayList<>();

				// ???????????????????????????????????????????????????for??????
				for (int i = 0; i < size; i++) {
					SetupMenuSub tmpMenu = tmpMenuList.get(i);

					SysMenu sysMenu = new SysMenu();
					sysMenu.setMenuId(canUseNos.get(i));// ????????????????????????
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
	 * TODO ??????????????????????????????????????????compareTo?????????????????????????????????
	 * 
	 * ??????????????????????????????????????????????????????????????????
	 * 
	 * @param peak
	 * @param numList
	 */
	public void foo(int peak, List<Integer> numList, int lackVal) {
		int limit = peak;

		// ???????????????????????????????????????????????????????????????????????????(??????????????????9000000000????????????????????????????????????999???)
		// ?????????999??????????????????
		// XXX ???????????????????????????????????????????????????????
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
	 * ??????????????????
	 * 
	 * XXX ??????????????????????????????????????????(???SQL??????)?????????????????????????????????????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "??????????????????(???API?????????????????????)", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "??????????????????(?????????)")
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

				// TODO ????????????(index)?
				// ???????????????index???????????????????????????

				// ???????????????menuid????????????
				// ???????????????????????????????????????menuid
				List<SetupMenuSub> inputMenuList = context.getMenuList();

				// ?????????????????????menuid
				try {
					Map<Object, Object> map = inputMenuList.stream().map(menu -> menu.getMenuId())
							.collect(Collectors.toMap(o -> o, o -> "x"));

					// TODO ?????????????????????
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

				// ??????????????????????????????????????????????????????????????????????????????
				List<SysMenu> originMenuList = sysMenuService.queryAll(sqlSession);

				Map<Object, SysMenu> index = originMenuList.stream()
						.map(menu -> new MapBuffer(menu.getMenuId().toString(), menu))
						.collect(Collectors.toMap(m -> m.getKey(), m -> (SysMenu) m.getVal()));

				// ??????????????????
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
	 * ??????????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "??????????????????", response = JsonBean.class, tags = { "sysgrant", "sysadmin" }, notes = "??????????????????")
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

				// ?????????????????????menuid
				try {
					Map<Object, Object> map = menu2List.stream().map(menu -> menu.getMenuId())
							.collect(Collectors.toMap(o -> o, o -> "x"));
				} catch (Exception e) {
					jsonBean.setStatus(SysEnum.statusError.context);
					jsonBean.setMessage("menu id of context is repeat!");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				List<String> menuIdList = menu2List.stream().map(menu -> menu.getMenuId()).collect(Collectors.toList());

				// ??????????????????menuid??????sys_menu_role????????????
				List<SysMenuRole> sysMenuRoleList = sysMenuRoleService.queryAll(sqlSession);

				// ??????????????????????????????
				Map<String, Boolean> map = menuIdList.stream().map(menuId -> {
					String key = menuId;
					Boolean val = sysMenuRoleList.stream()
							.anyMatch(menuRole -> menuRole.getMenuId().toString().equals(menuId));
					return new MapBuffer(key, val);
				}).collect(Collectors.toMap(o -> o.getKey(), o -> (Boolean) o.getVal()));

				// ??????????????????
				Iterator<Entry<String, Boolean>> ite = map.entrySet().iterator();

				List<String> errMenuIds = new ArrayList<>();

				while (ite.hasNext()) {
					Entry<String, Boolean> o = ite.next();
					boolean b = o.getValue();

					if (b == true) {
						errMenuIds.add(o.getKey());// key???menuId
					}
				}

				if (errMenuIds.size() > 0) {
					String list = errMenuIds.stream().collect(Collectors.joining("???"));
					jsonBean.setStatus(SysEnum.statusError.context);
					jsonBean.setMessage("Some data have be link to role!:" + list);
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// ???????????????????????????????????????????????????
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
	 * TODO ????????????(index)????????????
	 * 
	 * @param roleName
	 * @return
	 */
//	@ApiOperation(value = "????????????(index)????????????", response = JsonBean.class, tags = { "sysgrant","sysadmin" }, notes = "????????????(index)????????????")
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
	 * TODO ??????????????????????????????-???????????????
	 * 
	 * @param roleName
	 * @return
	 */
//	@ApiOperation(value = "??????????????????????????????-???????????????", response = JsonBean.class, tags = { "sysgrant","sysadmin" }, notes = "??????????????????????????????-???????????????")
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
	 * TODO ??????????????????????????????-???????????????
	 * 
	 * @param roleName
	 * @return
	 */
//	@ApiOperation(value = "??????????????????????????????-???????????????", response = JsonBean.class, tags = { "sysgrant" ,"sysadmin"}, notes = "??????????????????????????????-???????????????")
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
	 * ??????????????????????????????(???????????????????????????)
	 */
//	@Autowired
//	private SpringPropertyNewMechanismTest springPropertyNewMechanismTest;
//	log.debug(springPropertyNewMechanismTest.getEnvironments().getProject().getAuthorization().getServer()
//	.getDomain());

	/**
	 * TODO ??????????????????????????????(index)
	 * 
	 * @param roleName
	 * @return
	 */
//	@ApiOperation(value = "??????????????????????????????(index)", response = JsonBean.class, tags = {
//			"sysgrant","sysadmin" }, notes = "??????????????????????????????(index)")
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
//	???	return new ResponseEntity<>(jsonBean, HttpStatus.OK);
//	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "??????/??????????????????????????????", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "??????/??????????????????????????????")
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

				// ????????????????????????
				String roleId = context.getRoleId();

				SysRole role = new SysRole();
				role.setUserRole(roleId);
				List<SysRole> roleList = sysRoleService.queryBySysRole(role, sqlSession);

				if (roleList == null || roleList.size() == 0) {
					jsonBean.setStatus(SysEnum.statusError.context);
					jsonBean.setMessage("parameter-roleId:" + roleId + " no data");
					return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// ?????????????????????menu_id????????????????????????
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

				// ???????????????sys_menu_role????????????????????????
				SysMenuRole delCond = new SysMenuRole();
				delCond.setUserRole(roleId);
				int delVal = sysMenuRoleService.delete(delCond, sysMenuRoleMapper);

				log.debug("delete sys_menu_role val:" + delVal);

				// ????????????sys_menu_role?????????
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
	 * ??????????????????????????????/????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "??????/?????????????????????", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "??????/?????????????????????")
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

				// ???????????????userId???????????????
				// ???????????????sysRole???????????????
				SysAccount acc = new SysAccount();
				acc.setAkaId(userId);
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

				if (linkSwitch == true) {// ????????????????????????
					SysAccountRole accRole = new SysAccountRole();
					accRole.setAkaId(userId);
					accRole.setUserRole(roleId);
					List<SysAccountRole> accRoleList = sysAccountRoleService.queryBySysAccountRole(accRole, sqlSession);

					// ??????sys_account_role???????????????
					if (accRoleList != null & accRoleList.size() > 0) {
						jsonBean.setStatus(SysEnum.statusError.context);
						jsonBean.setMessage(
								"parameter-userId:" + userId + " and roleId:" + roleId + " data is existing!");
						return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
					}

					// ??????sys_account_role?????????
					SysAccountRole insData = new SysAccountRole();
					insData.setAkaId(userId);
					insData.setUserRole(roleId);
					doVal = sysAccountRoleService.insert(insData, sysAccountRoleMapper);
				} else {// ????????????????????????
					SysAccountRole accRole = new SysAccountRole();
					accRole.setAkaId(userId);
					accRole.setUserRole(roleId);
					List<SysAccountRole> accRoleList = sysAccountRoleService.queryBySysAccountRole(accRole, sqlSession);

					// ??????sys_account_role???????????????
					if (accRoleList != null & accRoleList.size() == 0) {
						jsonBean.setStatus(SysEnum.statusError.context);
						jsonBean.setMessage(
								"parameter-userId:" + userId + " and roleId:" + roleId + " no data, do nothing");
						return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
					}

					// TODO ????????????sys_account_role????????????????????????sys_default_role??????????
					// ??????sys_account_role?????????
					SysAccountRole delData = new SysAccountRole();
					delData.setAkaId(userId);
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
	 * ???/????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "???/???????????????????????????????????????????????????????????????????????????????????????", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "???/????????????")
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

				// ????????????
				SysRole sysRole = new SysRole();
				sysRole.setUserRole(roleId);
				List<SysRole> sysRoleList = sysRoleService.queryBySysRole(sysRole, sqlSession);

				// ???????????????????????????enable_mark???????????????N
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
	 * ??????????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "???????????????????????????????????????????????????????????????????????????????????????", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "??????????????????")
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

				// ???????????????????????????????????????
				SysAccountRole sysAccountRole = new SysAccountRole();
				sysAccountRole.setUserRole(roleId);
				List<SysAccountRole> sysAccountRoleList = sysAccountRoleService.queryBySysAccountRole(sysAccountRole,
						sqlSession);

				if (sysAccountRoleList.size() > 0) {
					List<RemoveRoleBean> resList = sysAccountRoleList.stream().map(accRole -> {

						// ??????account?????????????????????????????????accountRole?????????
						SysAccount sysAccount = new SysAccount();
						sysAccount.setAkaId(accRole.getAkaId());
						List<SysAccount> sysAccList = null;

						try {
							sysAccList = sysAccountService.queryBySysAccount(sysAccount, sqlSession);
						} catch (Exception e1) {
							log.debug("query sys_account data have serious error:" + accRole.getAkaId());
							return null;
						}

						if (sysAccList == null) {
							log.debug("query sys_account data have serious error:" + accRole.getAkaId());
							return null;
						}

						// ????????????????????????????????????????????????
						if (sysAccList.size() == 0) {
							SysAccountRoleKey pk = new SysAccountRoleKey();
							pk.setAkaId(accRole.getAkaId());
							pk.setUserRole(roleId);
							int delAccRoleVal = 0;
							int delRoleVal = 0;

							try {
								delAccRoleVal = sysAccountRoleService.deleteByKey(pk, sysAccountRoleMapper);
								delRoleVal = sysRoleService.deleteByKey(roleId, sysRoleMapper);

								RemoveRoleBean res = new RemoveRoleBean();
								res.setAkaId(accRole.getAkaId());
								res.setUserRole(accRole.getUserRole());
								res.setDelSysAccRoleVal(Long.valueOf(delAccRoleVal));
								res.setDelSysRoleVal(Long.valueOf(delRoleVal));
								return List.of(res);
							} catch (Exception e) {
								log.debug("delete sys_account_role data have serious error:" + accRole.getAkaId());
								return null;
							}

						}

						// ?????????????????????????????????????????????????????????????????????????????????
						if (sysAccList.size() > 0) {
							List<RemoveRoleBean> resList2 = sysAccList.stream().map(acc -> {
								RemoveRoleBean res = new RemoveRoleBean();
								res.setAkaId(acc.getAkaId());
								res.setUserRole(roleId);
								return res;
							}).collect(Collectors.toList());

							return resList2;
						}

						return null;

					}).filter(res -> res != null).flatMap(list -> list.stream()).collect(Collectors.toList());

					//
					sqlSession.commit();

					// ????????????
					String resMsg = resList.stream().map(res -> {
						if (res.getDelSysAccRoleVal() == null && res.getDelSysRoleVal() == null) {
							return "????????????" + res.getAkaId() + "??????" + "??????" + res.getUserRole() + "?????????????????????";
						} else {
							return "??????:" + res.getAkaId() + "-??????:" + res.getUserRole() + "?????????????????????????????????";
						}
					}).collect(Collectors.joining("\r\n"));

					jsonBean.setMessage(resMsg);
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// ????????????
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

		public String getAkaId() {
			return userId;
		}

		public String getUserRole() {
			return userRole;
		}

		public void setAkaId(String userId) {
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
	 * ??????????????????
	 * 
	 * @param roleName
	 * @return
	 */
	@ApiOperation(value = "???????????????????????????????????????????????????????????????????????????", response = JsonBean.class, tags = {
			"sysgrant" }, notes = "??????????????????")
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
			// ?????????????????????????????????
			SysRole roleCond = new SysRole();
			roleCond.setUserRole(roleId);
			List<SysRole> roleList = sysRoleService.queryBySysRole(roleCond);

			if (roleList == null) {
				log.debug("query error!");
				return new ResponseEntity<>(jsonBean.setMessage("????????????????????????"), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			if (roleList.size() > 0) {
				log.debug("role data is existed!");
				return new ResponseEntity<>(jsonBean.setMessage("???????????????????????????????????????"), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			SysRole model = new SysRole();
			model.setUserRole(roleId);
			model.setRoleName(roleName);
			model.setEnableMark(enable == true ? "Y" : "N");
			int succVal = sysRoleService.insert(model);

			if (succVal != 1) {
				jsonBean.setStatus(SysEnum.statusSuccess.code);
				jsonBean.setMessage("???????????????????????????????????????????????????:" + succVal);
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
	@ApiOperation(value = "????????????ID?????????????????????????????????????????????????????????ID???????????????????????????????????????", response = JsonBean.class, tags = { "sysgrant",
			"sysadmin" }, notes = "????????????ID??????")
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
			// ?????????????????????????????????
			SysRole roleCond = new SysRole();
			roleCond.setUserRole(originRoleId);
			List<SysRole> roleList = sysRoleService.queryBySysRole(roleCond);

			if (roleList == null || roleList.size() == 0) {
				log.debug("role data too less!");
				return new ResponseEntity<>(jsonBean.setMessage("????????????????????????"), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			if (roleList.size() > 1) {
				log.debug("role data too much!");
				return new ResponseEntity<>(jsonBean.setMessage("????????????????????????????????????"), HttpStatus.INTERNAL_SERVER_ERROR);
			}

			// ?????????????????????????????????
			SysRole roleCond2 = new SysRole();
			roleCond2.setUserRole(newRoleId);
			List<SysRole> roleList2 = sysRoleService.queryBySysRole(roleCond2);

			if (roleList2 != null && roleList2.size() > 0) {
				log.debug("we will renew role name but re find data too much for new role name!");
				return new ResponseEntity<>(jsonBean.setMessage("??????????????????????????????????????????????????????"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

			log.debug("check " + originRoleId + " to " + newRoleId + " available");
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return new ResponseEntity<>(new ArrayList<String>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// ?????????????????????????????????
		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
			SysAccountRoleMapper sysAccountRoleMapper = sqlSession.getMapper(SysAccountRoleMapper.class);
			SysFuncUiButtonRoleMapper sysFuncUiButtonRoleMapper = sqlSession.getMapper(SysFuncUiButtonRoleMapper.class);
			SysFuncUiFieldRoleMapper sysFuncUiFieldRoleMapper = sqlSession.getMapper(SysFuncUiFieldRoleMapper.class);
			SysIndexRoleMapper sysIndexRoleMapper = sqlSession.getMapper(SysIndexRoleMapper.class);
			SysMenuRoleMapper sysMenuRoleMapper = sqlSession.getMapper(SysMenuRoleMapper.class);

			try {
				// ???????????????????????????????????????????????????
				// ???????????????????????????????????????????????????sys_role???sys_account_role???sys_func_ui_button_role???sys_func_ui_field_role???sys_index_role???sys_menu_role????????????
				// sys_role
				// ??????sys_role??????????????????????????????????????????????????????id??????index_id?????????????????????id?????????????????????
				// ??????!??????java lambda???api???try-catch???????????????????????????????????????????????????????????????????????????????????????
				// XXX ?????????????????????????????????Java relection???????????????????????????????????????...
				SysRole sysRoleCond = new SysRole();
				sysRoleCond.setUserRole(originRoleId);
				List<SysRole> sysRoleList = sysRoleService.queryBySysRole(sysRoleCond, sqlSession);

				long succValSysRole = 0;

				// ???????????????bean???????????????set???bean
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
						pk.setAkaId(data.getAkaId());
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
	 * ?????????????????????
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "?????????????????????", response = JsonBean.class, tags = { "sysgrant" }, notes = "?????????????????????")
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
				sysAcc.setAkaId(userId);
				List<SysAccount> sysAcclist = sysAccountService.queryBySysAccount(sysAcc, sqlSession);

				// TODO ??????????????????????????????
				Map<String, String> map = sysAcclist.stream().map(acc -> {
					return List.of(List.of("userId", acc.getAkaId()));
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
	 * ???????????????????????????
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "?????????????????????????????????????????????????????????", response = JsonBean.class, tags = { "sysgrant" }, notes = "???????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchSysFuction")
	@ResponseBody
	public ResponseEntity<?> fetchSysFuction(String userId) {
		log.info("???????????? -??????????????????????????????  >>> fetchSysFuuction");

		JsonBean jsonBean = new JsonBean();

		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				if (StringUtils.isNotBlank(userId) == false) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("????????????????????????");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// ????????????????????????
				SysAccount sysAccount = new SysAccount();
				sysAccount.setAkaId(userId.trim());
				List<SysAccount> account = sysAccountService.queryBySysAccount(sysAccount, sqlSession);

				if (account.size() == 0) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("????????????????????????");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				if (account.size() > 1) {
					log.debug("accont:" + userId + " size is bigger 1, something is warning");
				}

				String resAkaId = account.get(0).getAkaId();

				SysAccountRole accRole = new SysAccountRole();
				accRole.setAkaId(resAkaId);
				List<SysAccountRole> accRoleList = sysAccountRoleService.queryBySysAccountRole(accRole, sqlSession);

				if (accRoleList.size() != 1) {
					log.debug("accont:" + userId + " size is bigger 1, something is warning");
				}

				// ???????????????????????????"??????"?????????
				String userRole = accRoleList.get(0).getUserRole();

				SysRole sysRole = new SysRole();
				sysRole.setUserRole(userRole);
				List<SysRole> roleList = sysRoleService.queryBySysRole(sysRole, sqlSession);

				if (roleList == null || roleList.size() == 0) {
					log.debug("account:" + userId + " of sysrole:" + " " + " no data");
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("?????????????????????????????????");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				roleList = roleList.stream().distinct()
						.filter(role -> role.getUserRole() != null && "Y".equals(role.getEnableMark()))
						.collect(Collectors.toList());

				// ??????button??????
				// ?????????????????????????????????
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

				// ??????????????????????????????
				List<Container> btnMapList = btnRoleList.stream().map(btnRole -> {
					try {
						if (StringUtils.isBlank(btnRole.getButtonId()) && btnRole.getMenuId() == null) {
							return null;
						}

						SysFuncUiButton btn = new SysFuncUiButton();
						btn.setMenuId(btnRole.getMenuId());
						btn.setButtonId(btnRole.getButtonId());
						List<SysFuncUiButton> tmpList = sysFuncUiButtonService.queryBySysFuncUiButton(btn, sqlSession);

						// ????????????enabled mark???????????????????????????
						tmpList.stream().forEach(tmp -> tmp.setEnableMark(btnRole.getEnableMark()));

						return tmpList;
					} catch (Exception e) {
						log.debug(e.toString());
						return null;
					}
				}).filter(res -> res != null).flatMap(tmpList -> tmpList.stream()).distinct().map(tmp -> {
					// ??????????????????
					boolean mark = false;

					if (tmp.getEnableMark() != null
							&& ("Y".equals(tmp.getEnableMark()) || "y".equals(tmp.getEnableMark()))) {
						mark = true;
					}

					// ??????API????????????????????????????????????????????????????????????
					Container btnRes = new Container();
					btnRes.setObjId(tmp.getButtonId());
					btnRes.setObjName(tmp.getButtonName());
					btnRes.setMenuId(tmp.getMenuId().toString());
					btnRes.setEnableMark(mark);
					btnRes.setObjType(GrantEnum.button.typeName);
					return btnRes;
				}).collect(Collectors.toList());

				// ??????field??????
				// ?????????????????????????????????
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

				// ??????????????????????????????
				List<Container> fieldMapList = fieldRoleList.stream().map(fieldRole -> {
					try {
						if (StringUtils.isBlank(fieldRole.getFieldId()) && fieldRole.getMenuId() == null) {
							return null;
						}

						SysFuncUiField field = new SysFuncUiField();
						field.setMenuId(fieldRole.getMenuId());
						field.setFieldId(fieldRole.getFieldId());
						List<SysFuncUiField> tmpList = sysFuncUiFieldService.queryBySysFuncUiField(field, sqlSession);

						// ????????????enabled mark???????????????????????????
						tmpList.stream().forEach(tmp -> tmp.setEnableMark(fieldRole.getEnableMark()));

						return tmpList;
					} catch (Exception e) {
						log.debug(e.toString());
						return null;
					}
				}).filter(res -> res != null).flatMap(tmpList -> tmpList.stream()).distinct().map(tmp -> {
					// ??????????????????
					boolean mark = false;

					if (tmp.getEnableMark() != null
							&& ("Y".equals(tmp.getEnableMark()) || "y".equals(tmp.getEnableMark()))) {
						mark = true;
					}

					// ??????API????????????????????????????????????????????????????????????
					Container btnRes = new Container();
					btnRes.setObjId(tmp.getFieldId());
					btnRes.setObjName(tmp.getFieldName());
					btnRes.setMenuId(tmp.getMenuId().toString());
					btnRes.setEnableMark(mark);
					btnRes.setObjType(GrantEnum.field.typeName);
					return btnRes;
				}).collect(Collectors.toList());

				// ????????????
				Map<String, List<Container>> res = List.of(btnMapList, fieldMapList).stream()
						.flatMap(list -> list.stream())
						.collect(Collectors.groupingBy(Container::getMenuId, Collectors.toList()));

				// ????????????
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
	 * ?????????????????????????????????(??????)
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "?????????????????????????????????(??????)", response = JsonBean.class, tags = {
			"sysgrant" }, notes = "?????????????????????????????????(??????)")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchSysIndex")
	@ResponseBody
	public ResponseEntity<?> fetchSysIndex(String userId) {
		log.info("???????????? -??????????????????????????????_Index  >>> fetchSysIndex");

		JsonBean jsonBean = new JsonBean();

		try {

			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
			List<? extends Object> resList = new ArrayList<>();

			try {

				// 1.????????????????????????
				if (StringUtils.isNotBlank(userId) == false) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("????????????????????????");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// ????????????????????????
				SysAccount sysAccount = new SysAccount();
				sysAccount.setAkaId(userId.trim());
				List<SysAccount> account = sysAccountService.queryBySysAccount(sysAccount, sqlSession);

				if (account.size() == 0) {
					jsonBean.setStatus(SysEnum.statusError.code);
					jsonBean.setMessage("????????????????????????");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				if (account.size() > 1) {
					log.debug("accont:" + userId + " size is bigger 1, something is warning");
				}

				// ???user_id???sys_account_role?????????
				String userIdCond = account.get(0).getAkaId();

				SysAccountRole accRole = new SysAccountRole();
				accRole.setAkaId(userIdCond);
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
					jsonBean.setMessage("?????????????????????????????????");
					return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
				}

				roleList = roleList.stream().distinct().filter(role -> role.getUserRole() != null)
						.collect(Collectors.toList());

				List<String> resFkUserRole = roleList.stream().map(role -> role.getUserRole())
						.collect(Collectors.toList());

				// 3.???????????????
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

				// 4.???????????????
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
	 * ???????????????????????????
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "???????????????????????????", response = JsonBean.class, tags = { "sysgrant" }, notes = "???????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchSysMenu")
	@ResponseBody
	public ResponseEntity<?> fetchSysMenu(String userId) {
		JsonBean jsonBean = new JsonBean();

		// ??????????????????menu????????????????????????
		// ???????????????????????????
		// ???????????????JSON??????,???????????????
		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				List<SysRole> sysRoleList = this.findRoleWithAkaId(userId, sqlSession);

				// ??????menu???index????????????
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

				// ????????????
				List<SysMenu> sysMenuList = sysMenuService.queryAll(sqlSession);

				// ?????????????????????
				sysMenuList = sysMenuList.stream()
						.filter(menu -> "Y".equals(menu.getEnableMark()) & menu.getIndexSort() != null)
						.collect(Collectors.toList());

				// ????????????????????????????????????????????????????????????
				List<? extends Object> newMenuList = this.findSameFiledDataAListByBList(
						new Bean4Compare().setName("sys_menu").setFieldNm("menu_id").setBeanList(sysMenuList),
						new Bean4Compare().setName("sys_menu_role").setFieldNm("menu_id").setBeanList(sysMenuRoleList));

				// ????????????(????????????????????????)
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
	 * ????????????indexid??????????????????????????????????????????
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "???????????????????????????????????????", response = JsonBean.class, tags = { "sysgrant" }, notes = "???????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchSysMenuType2")
	@ResponseBody
	public ResponseEntity<?> fetchSysMenuType2(@RequestParam(value = "indexId") String indexId,
			@RequestParam(name = "userId") String userId) {
		JsonBean jsonBean = new JsonBean();

		// ??????????????????menu????????????????????????
		// ???????????????????????????
		// ???????????????JSON??????,???????????????
		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				// ???????????????????????????????????????????????????????????????indexId????????????null????????????
				// ??????sys??????????????????????????????0???????????????????????????indexId???null??????0?????????????????????
				if (indexId == null || "null".equals(indexId)) {
					indexId = "0";
				} else {
					log.debug("have some error - indexId:" + indexId);
				}

				List<SysRole> sysRoleList = this.findRoleWithAkaId(userId, sqlSession);

				// ??????menu???index????????????
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

				// ????????????
				SysMenu sysMenu = new SysMenu();
				sysMenu.setIndexId(new BigDecimal(indexId));// TODO ????????????????????????????????????Exception?????????
				List<SysMenu> sysMenuList = sysMenuService.queryBySysMenu(sysMenu, sqlSession);

				// ?????????????????????
				sysMenuList = sysMenuList.stream()
						.filter(menu -> "Y".equals(menu.getEnableMark()) & menu.getIndexSort() != null)
						.collect(Collectors.toList());

				// ????????????????????????????????????????????????????????????
				List<? extends Object> newMenuList = this.findSameFiledDataAListByBList(
						new Bean4Compare().setName("sys_menu").setFieldNm("menu_id").setBeanList(sysMenuList),
						new Bean4Compare().setName("sys_menu_role").setFieldNm("menu_id").setBeanList(sysMenuRoleList));

				// ????????????(????????????????????????)
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
	 * ????????????indexid??????????????????????????????
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "???????????????????????????(????????????)", response = JsonBean.class, tags = { "sysgrant" }, notes = "???????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/fetchSysMenuType3")
	// TODO ?????????????????????????????????x-www-form-urlencoded
	// XXX ???method?????????menu??????????????????????????????????????????
//	@PostMapping("/fetchSysMenuType3") // ????????????@RequestParam???????????????header??????content-type:application/x-www-form-urlencoded
	@ResponseBody
	public ResponseEntity<?> fetchSysMenuType3(@RequestParam(value = "indexId") String indexId,
			@RequestParam(name = "userId") String userId) {
		JsonBean jsonBean = new JsonBean();

		// ????????????menu??????
		// ???????????????????????????
		// ???????????????JSON??????,???????????????
		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				List<SysRole> sysRoleList = this.findRoleWithAkaId(userId, sqlSession);

				// ???????????????????????????????????????????????????????????????indexId????????????null????????????
				// ??????sys??????????????????????????????0???????????????????????????indexId???null??????0?????????????????????
				if (indexId == null || "null".equals(indexId)) {
					indexId = "0";
				} else {
					log.debug("have some error - indexId:" + indexId);
				}

				// ??????menu???index????????????
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

				// ????????????
				SysMenu sysMenu = new SysMenu();
				sysMenu.setIndexId(new BigDecimal(indexId));// TODO ????????????????????????????????????Exception?????????
				List<SysMenu> sysMenuList = sysMenuService.queryBySysMenu(sysMenu, sqlSession);

				// ??????????????????????????????????????????????????????
				sysMenuList = sysMenuList.stream()
						.filter(menu -> "Y".equals(menu.getEnableMark()) & menu.getIndexSort() != null)
						.collect(Collectors.toList());

				// ????????????????????????????????????????????????????????????
				List<? extends Object> newMenuList = this.findSameFiledDataAListByBList(
						new Bean4Compare().setName("sys_menu").setFieldNm("menu_id").setBeanList(sysMenuList),
						new Bean4Compare().setName("sys_menu_role").setFieldNm("menu_id").setBeanList(sysMenuRoleList));

				// ????????????(????????????????????????)
				Comparator<SysMenu> comparator = new Comparator<SysMenu>() {
					@Override
					public int compare(SysMenu o1, SysMenu o2) {
						return o1.getIndexSort().intValue() - o2.getIndexSort().intValue();
					}
				};

				newMenuList = newMenuList.stream().map(bean -> (SysMenu) bean).sorted(comparator)
						.map(bean -> (Object) bean).collect(Collectors.toList());

				// ???????????????
				List<SysMenu> rootList = newMenuList.stream().map(bean -> (SysMenu) bean)
						.filter(menu -> menu.getMenuUpperId() == null).collect(Collectors.toList());

				List<SysMenu> subList = newMenuList.stream().map(bean -> (SysMenu) bean)
						.filter(menu -> menu.getMenuUpperId() != null).collect(Collectors.toList());

				// XXX ??????????????????????????????????????????
				// ??????????????????????????????
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
	 * ????????????
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

			// ????????????????????????????????????????????????
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
	 * ????????????indexid??????????????????????????????
	 * 
	 * @param paramTestList
	 */
	@ApiOperation(value = "???????????????????????????", response = JsonBean.class, tags = { "sysgrant" }, notes = "???????????????????????????")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully insert data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
//	@GetMapping("/fetchSysMenuType4")
	// TODO ?????????????????????????????????x-www-form-urlencoded
	@PostMapping("/fetchSysMenuType3") // ????????????@RequestParam???????????????header??????content-type:application/x-www-form-urlencoded
	@ResponseBody
	public ResponseEntity<?> fetchSysMenuType4(@RequestParam(value = "indexId") String indexId,
			@RequestParam(name = "userId") String userId) {
		JsonBean jsonBean = new JsonBean();

		// ????????????menu??????
		// ???????????????????????????
		// ???????????????JSON??????,???????????????
		try {
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			try {
				List<SysRole> sysRoleList = this.findRoleWithAkaId(userId, sqlSession);

				// ???????????????????????????????????????????????????????????????indexId????????????null????????????
				// ??????sys??????????????????????????????0???????????????????????????indexId???null??????0?????????????????????
				if (indexId == null || "null".equals(indexId)) {
					indexId = "0";
				} else {
					log.debug("have some error - indexId:" + indexId);
				}

				// ??????menu???index????????????
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

				// ????????????
				SysMenu sysMenu = new SysMenu();
				sysMenu.setIndexId(new BigDecimal(indexId));// TODO ????????????????????????????????????Exception?????????
				List<SysMenu> sysMenuList = sysMenuService.queryBySysMenu(sysMenu, sqlSession);

				// ????????????????????????????????????????????????????????????
				List<? extends Object> newMenuList = this.findSameFiledDataAListByBList(
						new Bean4Compare().setName("sys_menu").setFieldNm("menu_id").setBeanList(sysMenuList),
						new Bean4Compare().setName("sys_menu_role").setFieldNm("menu_id").setBeanList(sysMenuRoleList));

				// ????????????(????????????????????????)
				Comparator<SysMenu> comparator = new Comparator<SysMenu>() {
					@Override
					public int compare(SysMenu o1, SysMenu o2) {
						return o1.getIndexSort().intValue() - o2.getIndexSort().intValue();
					}
				};

				newMenuList = newMenuList.stream().map(bean -> (SysMenu) bean).sorted(comparator)
						.map(bean -> (Object) bean).collect(Collectors.toList());

				// ???????????????
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
	 * ????????????
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

			// ????????????????????????????????????????????????
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
	public List<SysRole> findRoleWithAkaId(String userId, SqlSession sqlSession) throws Exception {
		SysAccount sysAccount = new SysAccount();
		sysAccount.setAkaId(userId);

		// ?????????????????????
		List<SysAccount> accList = sysAccountService.queryBySysAccount(sysAccount, sqlSession);

		if (accList == null || accList.size() != 1) {
			throw new Exception("data of sys_account have serious error");
		}

		// ???user_id???sys_account_role?????????
		String userIdCond = accList.get(0).getAkaId();

		SysAccountRole accRole = new SysAccountRole();
		accRole.setAkaId(userIdCond);
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
	 * ????????????
	 * 
	 * ??????????????????????????????????????????????????????????????????????????????????????????
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
	 * ?????????????????????????????????????????????????????????(??????!???????????????????????????????????????)
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

		// XXX ??????Filed?????????????????????...??????????????????getMethod?????????
		Class<? extends Object> aIns = obj.getClass();
		Object insIdObj = aIns.getMethod(methodNm).invoke(obj);

		String insIdStr = null;

		if (insIdObj != null) {
			insIdStr = String.valueOf(insIdObj);
		}

		return insIdStr;
	}

	/**
	 * ??????????????????????????????,???????????????????????????????????????,???:abc???get=>getAbc abc???edf=>edfAbc
	 */
	public String appendStr2Head(String head, String str) {
		return head + this.firstToUppercase(str);
	}

	/**
	 * ????????????????????????"_",??????"_"?????????????????????????????????(????????????????????????????????????,??????"_"????????????????????????,???:Ab_CD=>abCd)
	 */
	public String delExpAnd1stStrUppercaseType4(String str, String exp) {
		String res = Arrays.asList(str.split(exp)).stream().map(ss -> this.firstToUppercase(ss.toLowerCase()))
				.collect(Collectors.joining());
		return this.firstToLowcase(res.toString());
	}

	/**
	 * ??????????????????????????????????????????????????????
	 */
	public String firstToUppercase(String s) {
		StringBuffer b = new StringBuffer();
		b.append(s.substring(0, 1).toUpperCase());
		b.append(s.substring(1));
		return b.toString();
	}

	/**
	 * ??????????????????????????????????????????????????????
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
			// TODO ??????gson????????????????????????????????????
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
		private String name;// ??????log??????
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