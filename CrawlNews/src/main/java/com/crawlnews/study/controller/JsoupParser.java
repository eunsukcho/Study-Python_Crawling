package com.crawlnews.study.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class JsoupParser {
	
	@RequestMapping("/test")
	public void CrawlNews() throws UnsupportedEncodingException {
		Gson gson = new Gson();
		String url = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=264&sid1=100&mid=shm&date=20190519&page=1";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url)
					.header("content-type", "application/json;charset=UTF-8")
					.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.header("accept-encoding", "gzip, deflate, br")
					.header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
					.ignoreContentType(true).get();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("ul.type06_headline li");
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>(); 
		
		for (int i = 0; i < element.size(); i++) {
			String title = element.get(i).select("a").text();
			String link = element.get(i).select("a").attr("abs:href");
			String newsPaper = element.get(i).select("span.writing").text();
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("title", title);
			map.put("link", URLEncoder.encode(link,"UTF-8"));
			map.put("newsPaper", newsPaper);
			
			JsouParserContent(link);
			
			
			list.add(map);
		}
		for (HashMap<String, String> e : list) {
			System.out.println(gson.toJson(e));
		}
		
	}
	
	public HashMap<String, String> JsouParserContent(String link) {
		Document doc = null;
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			doc = Jsoup.connect(link)
					.header("content-type", "application/json;charset=UTF-8")
					.header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
					.header("accept-encoding", "gzip, deflate, br")
					.header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
					.userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
					.ignoreContentType(true).get();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("div.article_info");
		String uploadDate = element.select("div.sponsor span.t11").text();
		map.put("uploadDate", uploadDate);
		
		String content = element.select("div.articleBodyContents").text();
		System.out.println("기사 내용 : " + content);
		
		return map;
	}
}
