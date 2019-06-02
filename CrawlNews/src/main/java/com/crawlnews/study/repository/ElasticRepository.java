package com.crawlnews.study.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.crawlnews.study.domain.ElasticDomain;

public interface ElasticRepository extends ElasticsearchRepository<ElasticDomain, String>{
	ElasticDomain findByTitle(String title);
	Iterable<ElasticDomain> findAll();
}
