package com.crawlnews.study.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.crawlnews.study.domain.ElasticDomain;
import com.crawlnews.study.repository.ElasticRepository;

@Service
public class ElasticService {
	
	@Autowired
	ElasticRepository repository;
	
	public ElasticDomain save(ElasticDomain vo) {
		return repository.save(vo);
	}
	
	@GetMapping
	public Iterable<ElasticDomain> findAll(){
		Iterable<ElasticDomain> list = new ArrayList<ElasticDomain>();
		list = repository.findAll();
		return list;
	}
	
	public ElasticDomain findByTitle(String title) {
		return repository.findByTitle(title);
	}
}
