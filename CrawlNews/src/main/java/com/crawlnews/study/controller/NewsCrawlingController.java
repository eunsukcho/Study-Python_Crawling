package com.crawlnews.study.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crawlnews.study.domain.ElasticDomain;
import com.crawlnews.study.domain.MongoDomain;
import com.crawlnews.study.service.ElasticService;
import com.crawlnews.study.service.NewsCrawlingService;
import com.crawlnews.study.vo.NewsLinkVo;
import com.google.gson.Gson;


@RestController
public class NewsCrawlingController {
	
	@Autowired
	NewsCrawlingService service = new NewsCrawlingService();
	
	@Autowired
	ElasticService elasticService;
	
	@RequestMapping(value = "/crawlNews", method = RequestMethod.GET)
	public ResponseEntity<?> crawlNews() {
		Gson gson = new Gson();
		List<String> newsCategory = Arrays.asList(new String[] {"264", "265", "266", "267", "268", "269"}); //네이버 뉴스 정치 파트 카테고리(ex. 청화대, 북한...등)의 값
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
		Date currentDate = new Date();
		String currentDay = dateFormat.format(currentDate);
		
		ResponseEntity<?> entity;
		List<NewsLinkVo> newsLinkList = null;
		Iterable<ElasticDomain> elasticList = null;
		
		List<ElasticDomain> tmpList = new ArrayList<ElasticDomain>();
		
		try {
			for(String e : newsCategory) {
				newsLinkList = service.newsLinkList(e, currentDay); //카테고리 value, 오늘 날짜별로 뉴스 링크 수집
				elasticList = service.newsDataList(newsLinkList); //수집된 링크의 기사 내용 수집
				tmpList.addAll((Collection<? extends ElasticDomain>) elasticList); //한 개의 카테고리에서 수집된 데이터들을 list에 이어서 add 
			}
			
			for(ElasticDomain elstic : tmpList) { 
				elasticService.save(elstic);
			}
			
			elasticList = elasticService.findAll();
			entity = new ResponseEntity<Object>(tmpList, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
