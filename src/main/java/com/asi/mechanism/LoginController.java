package com.asi.mechanism;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Lazy
@RequestMapping("manager")
@Controller
public class LoginController {

	/**
	 * @return
	 * 
	 */
	@RequestMapping("/login_test")
	public String loginPage_test() {
		return "public/assets/testView/login";
	}

	/**
	 * @return
	 * 
	 */
	@RequestMapping("/login_test2")
	public String login2Page() {
		return "public/assets/testView/login2";
	}

	/**
	 * @return
	 * 
	 */
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}

}
