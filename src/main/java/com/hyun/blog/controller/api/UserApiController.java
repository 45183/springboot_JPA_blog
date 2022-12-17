package com.hyun.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hyun.blog.dto.ResponseDto;
import com.hyun.blog.model.RoleType;
import com.hyun.blog.model.User;
import com.hyun.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {	// username, password, email
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 insert하고 아래에서 return
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	/*
	// 스프링 시큐리티 사용해서 로그인으로 바꿀경우 필요 없음
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user){
		System.out.println("UserApiController : login 호출됨");
		User principal = userService.로그인(user); // principal 접근주체
		
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

	}
	*/
}