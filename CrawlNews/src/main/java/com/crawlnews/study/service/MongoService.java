package com.crawlnews.study.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawlnews.study.domain.MongoDomain;
import com.crawlnews.study.repository.MongoRepository;

@Service
public class MongoService {

	@Autowired
	MongoRepository mongoRepository;
	
	public MongoDomain save(MongoDomain vo) {
		return mongoRepository.save(vo);
	}
	
	public Iterable<MongoDomain> findAll(){
		Iterable<MongoDomain> list = new ArrayList<MongoDomain>();
		list = mongoRepository.findAll();
		return list;
	}
}
