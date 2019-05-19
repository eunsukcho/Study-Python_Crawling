package com.crawlnews.study.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsoupParser {
	
	@RequestMapping("/test")
	public void CrawlNews() {
		String url = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=264&sid1=100&mid=shm&date=20190519&page=1";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("ul.type06_headline li");
		  
		String title = element.get(1).select("a").text();
		String newspaper = element.get(2).select("span.writing").text();
		
		System.out.println("length : " + element.size());
		System.out.println(title);
		System.out.println(newspaper);
	}
}
