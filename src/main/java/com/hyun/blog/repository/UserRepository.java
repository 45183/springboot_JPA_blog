package com.hyun.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyun.blog.model.User;

// DAO
// 자동으로 bean 등록이 됨
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer>{

}
