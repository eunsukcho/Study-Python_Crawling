package com.crawlnews.study.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.crawlnews.study.domain.MongoDomain;

@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<MongoDomain, String>{
	List<MongoDomain> findAll();
	//Map<String, Integer> findByDateAndCategory(String date, String category);
}
