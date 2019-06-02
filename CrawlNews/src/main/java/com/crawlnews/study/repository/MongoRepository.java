package com.crawlnews.study.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crawlnews.study.domain.MongoDomain;

@Repository
public interface MongoRepository extends org.springframework.data.mongodb.repository.MongoRepository<MongoDomain, String>{
	List<MongoDomain> findAll();
}
