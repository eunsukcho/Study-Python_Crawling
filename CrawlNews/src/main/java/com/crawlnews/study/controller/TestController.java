package com.crawlnews.study.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crawlnews.study.domain.CrawlDataDomain;
import com.crawlnews.study.repository.CrawlDataRepository;
import com.crawlnews.study.utils.VariableUtils;
import com.crawlnews.study.vo.AnalyzeResponseVo;
import com.crawlnews.study.vo.NewsDataVO;

import net.sf.json.JSONObject;
import com.google.gson.reflect.TypeToken;

@RestController
public class TestController {

	@Autowired
	CrawlDataRepository crawlRepository;
	
	@RequestMapping("/test1")
	public void test() {
		String man = "[[1, 소, 2, 닭, 3, 말]";
		ArrayList<String> resultArray = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(man, ", ");
		ArrayList<String> list = new ArrayList<String>();
		
		while(st.hasMoreElements()) {
			list.add(st.nextToken());
		}
		
		list.stream()
			.forEach(
				n -> {
					StringBuilder sb = new StringBuilder();
					
				    for (int i = 0; i < n.length(); i++){
				    	Character nch = n.charAt(i);
				    	
				    	if(StringUtils.isNumeric(String.valueOf(nch))) {
							System.out.println(String.valueOf(nch));
							System.out.println("숫자");
							resultArray.add(String.valueOf(nch));
						}
						
				        if(StringUtils.isAlpha(String.valueOf(nch))) {
				        	System.out.println(String.valueOf(nch));
				        	System.out.println("문자");
				        	resultArray.add(String.valueOf(nch));
				        }
				    }
				}
			);
		
		list.stream().forEach(s->{
			
		});
		System.out.println("=========================");
		System.out.println(resultArray.toString());
	}
	
	@RequestMapping("/test2")
	public void test2() {
		IntStream.range(1, 10).parallel().forEach(index ->{
			System.out.println("Starting " + Thread.currentThread().getName() + ",    index=" + index + ", " + new Date());
			try {
				Thread.sleep(5000);
			}catch (InterruptedException e) {}
		});
	}
	
	@RequestMapping("/test3")
	public void test3() {
		List<NewsDataVO> list = new ArrayList<NewsDataVO>();
		
		for (int i = 0; i < 100000; i++) {
			NewsDataVO vo = new NewsDataVO();
			vo.setTitle(i+"번째 제목");
			vo.setContent(i+"번째 내용");
			vo.setReg_date(i+"번째날짜");
			list.add(vo);
		}
		
		long start = System.currentTimeMillis();
		list.parallelStream().forEach(s->{
			System.out.println(s.getTitle());
		});
		long end = System.currentTimeMillis();
		
		System.out.println("실행 시간 : " + (end - start)/1000.0 + "초");
		
		/*
		 * list.parallelStream().forEach(s->{ System.out.println(s.getTitle()); });
		 */
	}
	
	@RequestMapping("/test4")
	public void test4() {
		List<CrawlDataDomain> crawlDataDomainsList = crawlRepository.findAll();
		System.out.println(crawlDataDomainsList);
	}
	
	
	@RequestMapping("/test5")
	public void test5() {
		CrawlDataDomain crawlDataDomain  = new CrawlDataDomain();
		crawlDataDomain.setCategory("A");
		crawlDataDomain.setElastic_id("A01");
		crawlDataDomain.setMongo_id("B02");
		crawlDataDomain.setTitle("C");
		crawlDataDomain.setUploadDate(new Date());
		crawlRepository.save(crawlDataDomain);
	}
	
	
	@RequestMapping("/test6")
	public void test6() {
		String testJson = "{\"detail\": {\"custom_analyzer\": false,\"analyzer\": {\"name\": \"org.apache.lucene.analysis.ko.KoreanAnalyzer\",\"tokens\": [{\"token\": \"아버지\",\"start_offset\": 0,\"end_offset\": 3,\"type\": \"word\",\"position\": 0,\"bytes\": \"[ec 95 84 eb b2 84 ec a7 80]\",\"leftPOS\": \"NNG(General Noun)\",\"morphemes\": null,\"posType\": \"MORPHEME\",\"positionLength\": 1,\"reading\": null,\"rightPOS\": \"NNG(General Noun)\",\"termFrequency\": 1},{\"token\": \"방\",\"start_offset\": 5,\"end_offset\": 6,\"type\": \"word\",\"position\": 2,\"bytes\": \"[eb b0 a9]\",\"leftPOS\": \"NNG(General Noun)\",\"morphemes\": null,\"posType\": \"MORPHEME\",\"positionLength\": 1,\"reading\": null,\"rightPOS\": \"NNG(General Noun)\",\"termFrequency\": 1},{\"token\": \"들어가\",\"start_offset\": 8,\"end_offset\": 11,\"type\": \"word\",\"position\": 4,\"bytes\": \"[eb 93 a4 ec 96 b4 ea b0 80]\",\"leftPOS\": \"VV(Verb)\",\"morphemes\": null,\"posType\": \"MORPHEME\",\"positionLength\": 1,\"reading\": null,\"rightPOS\": \"VV(Verb)\",\"termFrequency\": 1}]}}}";
		JSONObject jsonObject = new JSONObject();
		List<AnalyzeResponseVo> analyzeResponseVoList = VariableUtils.gson.fromJson(VariableUtils.gson.toJson(jsonObject.fromObject(jsonObject.fromObject(jsonObject.fromObject(VariableUtils.gson.fromJson(testJson, Object.class)).get("detail")).get("analyzer")).getJSONArray("tokens")), new TypeToken<List<AnalyzeResponseVo>>() {}.getType());
		
		for(AnalyzeResponseVo tmpAnalyzeResponseVo : analyzeResponseVoList) {
			System.out.println(tmpAnalyzeResponseVo.getToken() + ", " + tmpAnalyzeResponseVo.getLeftPOS());
		}
		
		System.out.println(VariableUtils.gson.toJson(analyzeResponseVoList));
	}
}
