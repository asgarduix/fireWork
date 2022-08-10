package com.asi.mechanism;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.asi.mechanism.security.jwtref.JwtTokenUtil;
import com.asi.mechanism.service.SysAccessRecordService;
import com.asi.mechanism.service.dao.mybatis.model.SysAccessRecord;

@Aspect
@Component
public class SpringAspectjComponent {
	/**
	 * 
	 */

	private static Log log = LogFactory.getLog(SpringAspectjComponent.class);

	@Autowired
	private SysAccessRecordService sysAccessRecordService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

//	@Autowired
//	private TokenVerifyCommon tokenVerifyCommon;

//	private CpUtil casi = new CpUtil();
	private String PAGE = "PAGE";
	private String APIS = "APIS";

	@Pointcut("execution(* com.asi.huanan.controller.apis.*Auth.*(..))")
	public void controllerApisExePointcutWithAuth() {

	}

	@Pointcut("execution(* com.asi.huanan.controller.*Auth.*(..))")
	public void controllerExePointcutWithAiuth() {

	}

	// =====without Authentication=====
	@Pointcut("execution(* com.asi.huanan.controller.*.*(..))")
	public void controllerExePointcut() {

	}

	@Pointcut("execution(* com.asi.huanan.mechanism.*.*(..))")
	public void mechanismExePointcut() {

	}

	@Pointcut("execution(* com.asi.huanan.controller.api.*.*(..))")
	public void controllerApisExePointcut() {

	}
	// =========

	/**
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("controllerExePointcut()")
	public void controllerExePointcutBefore(JoinPoint joinPoint) throws Throwable {
//		casi.log4Method(Thread.currentThread().getStackTrace()[1]);// log too much, comment
	}

	/**
	 * 
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("mechanismExePointcut()")
	public void mechanismExePointcutBefore(JoinPoint joinPoint) throws Throwable {
		Object[] objArray = joinPoint.getArgs();

		if (objArray.length <= 0) {
			return;
		}

		String accessTokenName = "";
		// TODO
		// String accessTokenName = SpringBootProperty.getAccessTokenName();

		String argsStr = Arrays.asList(objArray).stream().filter(o -> accessTokenName.equals(o.toString()))
				.map(o -> o.toString()).collect(Collectors.joining(","));
		log.debug("args:" + argsStr);
	}

	/**
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("controllerApisExePointcut()")
	public Object controllerApisExePointcutAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		log.debug("#test@controllerApisExePointcut");

		// fetch all request info
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();

		StringBuffer name = new StringBuffer();
		String userId = null;

		try {
			String token = request.getHeader("Authorization");
			String temp = token.replace("bearer ", "");
			String username = jwtTokenUtil.getUsernameFromToken(temp);
			// log.debug("header-autorization:" + token);
			name.append("user:" + username + ",");

			userId = username;
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			name.append("user:" + "{fetch_error},");
			log.debug("we cant fetch username");
		}

		String url = request.getRequestURL().toString();
		String method = request.getMethod();
		String uri = request.getRequestURI();
		String params = request.getQueryString();

//		log.debug("###start");
//		log.debug("url: {" + url + "}, method: {" + method + "}, uri: {" + uri + "}, params: {" + params + "}");

		// fetch module name of concatenate api

		StringBuffer contextSb = new StringBuffer();
		contextSb.append("url:" + url + "," + "method:" + method + "," + "uri:" + uri + "," + "params:" + params);

		String msg = Tmp.apiconstract.get(uri);
//		log.debug(">>>>" + uri + "-" + test);

		// log to database(or kafka)
		try {
			SysAccessRecord record = new SysAccessRecord();
			record.setAccUserid(userId);
			record.setAccLog(contextSb.toString());
			record.setAccTime(new Date());
			record.setAccMsg(msg);
			// 軌跡紀錄
			sysAccessRecordService.insert(record);
		} catch (Exception e) {
			log.debug(e.toString());
			log.debug("record access have fail!");
		}

		Object obj = proceedingJoinPoint.proceed();
		return obj;
	}

	/**
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("controllerExePointcutWithAiuth()")
	public Object controllerExePointcutWithAiuthAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return this.verifyToken(proceedingJoinPoint, this.PAGE);
	}

	/**
	 * 
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("controllerApisExePointcutWithAuth()")
	public Object controllerApisExePointcutWithAuthAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return this.verifyToken(proceedingJoinPoint, this.APIS);
	}

	/**
	 * 
	 * @return
	 * @throws Throwable
	 */
	public Object verifyToken(ProceedingJoinPoint proceedingJoinPoint, String func) throws Throwable {
//		casi.log4Method(Thread.currentThread().getStackTrace()[1]);//log too much, comment
		Object obj = null;
		return obj;
	}

	/**
	 * 
	 * @param func
	 * @param pageRes
	 * @param apisRes
	 * @return
	 */
	public Object switchObj(String func, Object pageRes, Object apisRes) {
		Object obj = null;

		switch (func) {
		case "PAGE":
			obj = pageRes;
			break;
		case "APIS":
			obj = apisRes;
			break;
		}

		return obj;
	}

	/**
	 * 
	 * @param method
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public Object newInsByReturnTypeOfMethod(Method method) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
		// return type will accord to method
		String returnTypeName = method.getReturnType().getName();

		Class<?> clazz = Class.forName(returnTypeName);
		Constructor<?>[] construc = clazz.getConstructors();

		if (construc.length == 0) {
			log.debug("contruc have err, we will return null");
			return null;
		}

		Object ins = construc[0].newInstance();
		return ins;
	}
}
