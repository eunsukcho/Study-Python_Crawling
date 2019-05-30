package com.crawlnews.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crawlnews.study.service.ElasticService;
import com.crawlnews.study.service.NewsCrawlingService;
import com.crawlnews.study.vo.ElasticVO;
import com.crawlnews.study.vo.NewsDataVO;
import com.crawlnews.study.vo.NewsLinkVo;
import com.google.gson.Gson;

@RestController
public class NewsCrawlingController {
	
	@Autowired
	NewsCrawlingService service = new NewsCrawlingService();
	
	@Autowired
	ElasticService elasticService;
	
	@RequestMapping(value = "/crawlNews", method = RequestMethod.GET)
	public ResponseEntity<?> delete() {
		ResponseEntity<?> entity;
		List<NewsLinkVo> newsLinkList = null;
		List<NewsDataVO> newsDataList = null;
		Iterable<ElasticVO> elasticList = null;
		
		try {
			newsLinkList = service.newsLinkList();
			newsDataList = service.newsDataList(newsLinkList);
			
			ElasticVO vo = new ElasticVO("article3", "content3", "20190530");
			elasticService.save(vo);
			
			elasticList = elasticService.findAll();
			
			entity = new ResponseEntity<Object>(elasticList, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
