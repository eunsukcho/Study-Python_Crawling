package com.crawlnews.study.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.crawlnews.study.dto.ElasticRepository;
import com.crawlnews.study.vo.ElasticVO;

@Service
public class ElasticService {
	
	@Autowired
	private ElasticRepository repository;
	
	public ElasticVO save(ElasticVO vo) {
		return repository.save(vo);
	}
	
	@GetMapping
	public Iterable<ElasticVO> findAll(){
		Iterable<ElasticVO> list = new ArrayList<ElasticVO>();
		list = repository.findAll();
		System.out.println(list);
		return list;
	}
	
	public ElasticVO findByTitle(String title) {
		return repository.findByTitle(title);
	}
}
