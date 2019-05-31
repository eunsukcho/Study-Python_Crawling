package com.crawlnews.study.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawlnews.study.dto.MongoRepository;
import com.crawlnews.study.vo.ElasticVO;
import com.crawlnews.study.vo.MongoVO;

@Service
public class MongoService {

	@Autowired
	MongoRepository mongoRepository;
	
	public MongoVO save(MongoVO vo) {
		return mongoRepository.save(vo);
	}
	
	public Iterable<MongoVO> findAll(){
		Iterable<MongoVO> list = new ArrayList<MongoVO>();
		list = mongoRepository.findAll();
		return list;
	}
	
	public MongoVO findByTitle(String title) {
		return mongoRepository.findByTitle(title);
	}
}
