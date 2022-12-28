package com.hyun.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogControllerTest {

	@GetMapping("/test/hello")		// http://loscalhost:8080/test/hello
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}
