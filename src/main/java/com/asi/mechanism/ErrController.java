package com.asi.mechanism;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.asi.json.model.root.JsonBean;

@Lazy
@RestController
public class ErrController implements ErrorController {

	@RequestMapping("/error")
	@ResponseBody
	public ResponseEntity<?> error() {
		JsonBean jsonBean = new JsonBean();
		jsonBean.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
		jsonBean.setMessage("system_serious_error");
		return new ResponseEntity<>(jsonBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// TODO Spring boot的ErrprController的interface不能用了
//	public String getErrorPath() {
//		return "error_page";
//	}

}
