package com.hyun.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hyun.blog.model.User;

// DAO
// 자동으로 bean 등록이 됨
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer>{

}

// JPA Naming 쿼리
// SELECT * FROM user WHERE username=?1 AND password=?2;
//	User findByUsernameAndPassword(String username, String password);


//	@Query(value = "SELECT * FROM user WHERE username=?1 AND password=?2;", nativeQuery = true)
//	User login(String username, String password);