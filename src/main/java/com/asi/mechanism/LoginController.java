package com.asi.mechanism;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Lazy
@RequestMapping("manager")
@Controller
public class LoginController {

	/**
	 * @return
	 * 
	 */
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

}
