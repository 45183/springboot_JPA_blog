package com.hyun.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyun.blog.model.User;
import com.hyun.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해 bean에 등록해줌 - IoC 해줌
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
	}
}
