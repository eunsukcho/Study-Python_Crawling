package com.crawlnews.study.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crawlnews.study.vo.JSoupVO;
import com.google.gson.Gson;

@RestController
public class JsoupParser {
	
	@RequestMapping("/test")
	public void CrawlNews() {
		Gson gson = new Gson();
		String url = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=264&sid1=100&mid=shm&date=20190519&page=1";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("ul.type06_headline li");
		List<JSoupVO> list = new ArrayList<JSoupVO>(); 
		
		for (int i = 0; i < element.size(); i++) {
			String title = element.get(i).select("a").text();
			String link = element.get(i).select("a").attr("href");
			String newsPaper = element.get(i).select("span.writing").text();
			
			JSoupVO vo = new JSoupVO(title, link, newsPaper);
			list.add(vo);
		}
		
		for (JSoupVO e : list) {
			System.out.println(gson.toJson(e));
		}
		
	}
}
