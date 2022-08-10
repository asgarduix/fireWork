package com.asi.huanan.controller;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.controller.JwtAuthorizationController;
import com.asi.mechanism.security.jwtref.JwtTokenUtil;
import com.asi.swissknife.Asiutil;

@Lazy
//@RequestMapping("//")
@RequestMapping("frontendfirereins")
@Controller
public class FrontendController {

	private static Logger log = LogManager.getLogger(FrontendController.class);

	@Autowired
	private JwtAuthorizationController jwtAuthController;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		log.info("---initail login page---");
		return "public/login";
	}

	/**
	 * XXX 可每次都檢核token?(annotation可以設定@RequestMapping("/{directUrl}/{token}")?)
	 * 
	 * @param directUrl
	 * @return
	 */
	@RequestMapping("/{directUrl}")
	public String directUrl(@PathVariable(value = "directUrl", required = true) String directUrl) {
		return "public/" + directUrl;
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/loading", method = RequestMethod.POST, consumes = { "application/x-www-form-urlencoded" })
	public String loading(@RequestParam(name = "token", required = true) String token) {
		try {
			// 使用token檢核，自己呼叫自己的api
			ResponseEntity<?> x = jwtAuthController.checkToken(token);
			if (HttpStatus.OK.equals(x.getStatusCode())) {
				String username = jwtTokenUtil.getUsernameFromToken(token);
				log.debug("check token ok, user name:" + username);
			} else {
				return "public/login";
			}
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			return "public/login";
		}
		return "public/loading";
	}

	// =====test=====
	// /**
	// * @RequestMapping context in value，be same with method name and return value
	// of
	// * var
	// * @param var1
	// * @param model
	// * @return
	// */
	// @RequestMapping("/index")
	// public String index2() {
	// try {
	// log.debug("go_to_dayu!");
	// } catch (Exception e) {
	// log.debug(e.toString());
	// Arrays.asList(e.getStackTrace()).stream().forEach(sube ->
	// log.debug(sube.toString()));
	// return "public/login";
	// }
	// return "public/index";
	// }
	// /**
	// * @RequestMapping context in value，be same with method name and return value
	// of
	// * var
	// * @param var1
	// * @param model
	// * @return
	// */
	// @RequestMapping(value = "/dpsfrontend", method = RequestMethod.POST, consumes
	// = {
	// "application/x-www-form-urlencoded" })
	//// @RequestMapping("/dpsfrontend")
	// public String dpsfrontend(@RequestParam(name = "token", required = true)
	// String token) {
	// log.debug("go_to_dayu!");
	// log.debug("token!:" + token);
	// return "public/dpsfrontend";
	// }

}
