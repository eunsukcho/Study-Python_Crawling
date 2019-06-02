package com.crawlnews.study.dto;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.crawlnews.study.vo.ElasticVO;

@Repository("elasticRepository")
public interface ElasticRepository extends ElasticsearchRepository<ElasticVO, String>{
	ElasticVO findByTitle(String title);
	Iterable<ElasticVO> findAll();
}
