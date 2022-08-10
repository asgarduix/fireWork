package com.asi.mechanism;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author biruh
 *
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	private Log log = LogFactory.getLog(ExceptionHandler.class);

	// TODO 測試中
//	@Override
//	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		// TODO Auto-generated method stub
//		log.debug("test");
//		return super.handleExceptionInternal(ex, body, headers, status, request);
//	}

	/*
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// Map<String, List<String>> body = new HashMap<>();
		// List<String> errors = ex.getBindingResult().getFieldErrors().stream()
		// .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

		Map<String, List<String>> fields = ex.getBindingResult().getFieldErrors().stream()
				.map(f -> new String[] { f.getField(), f.getDefaultMessage() })
				.collect(Collectors.groupingBy(f -> f[0], Collectors.mapping(f -> f[1], Collectors.toList())));

		Map<String, Map<String, List<String>>> map = new HashMap<>();
		map.put("errror", fields);

		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
}
