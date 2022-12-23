package com.hyun.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hyun.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}