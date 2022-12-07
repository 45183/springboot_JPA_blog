package com.hyun.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자 요청 -> 응답(HTML 파일)
// @Controller

// 사용자 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

	@GetMapping("/http/get")		// http://localhost:8080/http/get (select)			// 인터넷 브라우저 요청은 get요청만 가능
	public String getTest(Member m) {
		return "get 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	@PostMapping("/http/post")		// http://localhost:8080/http/post (insert)			// text/plain, apllication/json
	public String postTest(@RequestBody Member m) {								// MessageConverter(스프링부트)
		return "post 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	@PutMapping("/http/put")		// http://localhost:8080/http/put (update)
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
	}
	
	@DeleteMapping("/http/delete")	// // http://localhost:8080/http/delete (delete)
	public String deleteTest() {
		return "delete 요청";
	}
}
