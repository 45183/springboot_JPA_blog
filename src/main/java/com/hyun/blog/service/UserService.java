package com.hyun.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//@Transactional(readOnly = true)	// select할 때 transaction 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
//public User 로그인(User user) {
//	return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//}