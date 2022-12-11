package com.hyun.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

// 사용자 요청 -> 응답(HTML 파일)
// @Controller

// 사용자 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
	
	// http://localhost:8000/blog/http/lombok
	@GetMapping("/http/lombok")
	public String lombokTest() {
//		Member m = new Member(1, "hyun", "1234", "hyun@naver.com");		// AllArgsConstructor
		Member m = Member.builder().username("hyun").password("1234").email("hyun@naver.com").build();	// 순서 상관 없음
//		Member m2 = new Member();											// NoArgsConstructor
		System.out.println(TAG + "getter : " + m.getUsername());
		m.setUsername("hsb");
		System.out.println(TAG + "setter : " + m.getUsername());
		return "lombok test 완료";
	}

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
