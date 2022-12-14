package com.hyun.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder	// 빌더 패턴
//ORM -> Java (모든 언어) object -> 테이블로 매핑해주는 기술
@Entity	// User 클래스가 MySQL에  테이블이 생성됨
//@DynamicInsert	// insert 시에 null인 필드를 제외시켜줌
public class User {

	@Id	// primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략을 따라감	
	private int id;	// 시퀀스, auto-increment

	@Column(nullable = false, length = 100)
	private String username;	// 아이디
	
	@Column(nullable = false, length = 100)	// 12345 => 해쉬(비밀번호 암호화)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user")
	// DB는 RoleType이라는게 없음
	@Enumerated(EnumType.STRING)
	private RoleType role;	// Enum 사용하는게 좋음		// USER, ADMIN
	
	private String oauth;	// 카카오 로그인시 kakao, 일반 회원가입시 null	- 카카오 로그인시 회원정보 수정 불가능하게 만들기 위해 
	
	@CreationTimestamp	// 시간이 자동 입력
	private Timestamp createDate;


}
