package com.crawlnews.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crawlnews.study.service.NewsCrawlingService;
import com.crawlnews.study.vo.NewsDataVO;
import com.crawlnews.study.vo.NewsLinkVo;

@RestController
public class NewsCrawlingController {
	
	@Autowired
	NewsCrawlingService service = new NewsCrawlingService();
	
	@RequestMapping(value = "/crawlNews", method = RequestMethod.GET)
	public ResponseEntity<?> delete() {
		ResponseEntity<?> entity;
		List<NewsLinkVo> newsLinkList = null;
		List<NewsDataVO> newsDataList = null;
		
		try {
			newsLinkList = service.newsLinkList();
			newsDataList = service.newsDataList(newsLinkList);
			
			entity = new ResponseEntity<Object>(newsDataList, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
