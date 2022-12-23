package com.hyun.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyun.blog.model.RoleType;
import com.hyun.blog.model.User;
import com.hyun.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해 bean에 등록해줌 - IoC 해줌
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();	// passsword 원문
		String encPassword = encoder.encode(rawPassword);	// 해쉬화
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional
	public void 회원수정(User user) {
		// 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
		// Select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌
		User persistence = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistence.setPassword(encPassword);
		persistence.setEmail(user.getEmail());
		
		// 회원수정 함수 종료 시 =  서비스 종료 시 = 트랜잭션 종료 = 자동으로 commit
		// 영속화된 persistence 객체의 변화가 감지되면 더티체킹되어 자동으로 update문을 날려줌
	}
}

//@Transactional(readOnly = true)	// select할 때 transaction 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
//public User 로그인(User user) {
//	return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
//}