package com.crawlnews.study.dto;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crawlnews.study.vo.MongoVO;

@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<MongoVO, String>{
	List<MongoVO> findAll();
	MongoVO findByTitle(String title);
}
