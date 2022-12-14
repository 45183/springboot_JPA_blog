package com.hyun.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.hyun.blog.dto.ResponseDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseDto<String> handleArgumentException(IllegalArgumentException e) {			
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
	
//	모든 exception에 대해서 할 경우
//	@ExceptionHandler(value = Exception.class)
//	public String handleArgumentException(Exception e) {			
//		
//		return "<h1>" + e.getMessage() + "</h1>";
//	}
}
