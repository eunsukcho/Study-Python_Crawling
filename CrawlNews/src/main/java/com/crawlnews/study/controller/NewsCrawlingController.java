package com.crawlnews.study.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crawlnews.study.dto.MongoRepository;
import com.crawlnews.study.service.ElasticService;
import com.crawlnews.study.service.NewsCrawlingService;
import com.crawlnews.study.vo.ElasticVO;
import com.crawlnews.study.vo.MongoVO;
import com.crawlnews.study.vo.NewsDataVO;
import com.crawlnews.study.vo.NewsLinkVo;

@RestController
public class NewsCrawlingController {
	
	@Autowired
	NewsCrawlingService service = new NewsCrawlingService();
	
	@Autowired
	ElasticService elasticService;
	
	List<String> newsCategory = Arrays.asList(new String[] {"264", "265", "266", "267", "268", "269"});
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
	Date currentDate = new Date();
	String currentDay = dateFormat.format(currentDate);
	
	@RequestMapping(value = "/crawlNews", method = RequestMethod.GET)
	public ResponseEntity<?> crawlNews() {
		ResponseEntity<?> entity;
		List<NewsLinkVo> newsLinkList = null;
		Iterable<ElasticVO> elasticList = null;
		List<MongoVO> mongoList = null;
		
		try {
			for(String e : newsCategory) {
				newsLinkList = service.newsLinkList(e, currentDay);
				elasticList = service.newsDataList(newsLinkList);
			}
			
			for(ElasticVO elstic : elasticList) { 
				//elasticService.save(elstic);
			}
			
			elasticList = elasticService.findAll();
			entity = new ResponseEntity<Object>(elasticService.findAll(), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/mongo", method = RequestMethod.GET)
	public ResponseEntity<?> mongoData(){
		ResponseEntity<?> entity;
		List<MongoVO> mongoList = new ArrayList<MongoVO>();
		
		try {
			
			entity = new ResponseEntity<Object>(mongoList, HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
