package com.crawlnews.study.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.crawlnews.study.vo.NewsDataVO;
import com.crawlnews.study.vo.NewsLinkVo;

@Service
public class NewsCrawlingService {
	
	public List<NewsLinkVo> newsLinkList() throws Exception{
		
		List<NewsLinkVo> newsLinkList = new ArrayList<NewsLinkVo>();
		String startUrl = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=264&sid1=100&mid=shm&date=20190519&page=";
		Document document = null;
		int i = 0;
		String newLink = "";
		String oldLink = "";
		
		while(true) {
			i++;
			document = Jsoup.connect(startUrl+i).get();
			List<String> newLinkList = new ArrayList<String>();
			List<Element> newsElementList = new ArrayList<Element>();
			List<Element> element = document.select("ul.type06_headline li");
			List<Element> element2 = document.select("ul.type06 li");
		
			newsElementList.addAll(element);
			newsElementList.addAll(element2);
			
			for (Element e : newsElementList) {
				String title = e.select("a").text();
				String link = e.select("a").attr("abs:href");
				newLinkList.add(link);
				NewsLinkVo vo = new NewsLinkVo(title, link);
				newsLinkList.add(vo);
			}
			
			
			newLink = newLinkList.get(0);
			if (oldLink.equals(newLink)) break;
			oldLink = newLink;
			
			System.out.println("newLinkList " + i + " : " +  newLink);
			System.out.println("oldLinkList " + i + " : " +  oldLink);
			newLink = null;
		}
		
		return newsLinkList;
	}
	
	public List<NewsDataVO> newsDataList(List<NewsLinkVo> newsLinkList) throws Exception{
		List<NewsDataVO> newsDataList = new ArrayList<NewsDataVO>();
		
		newsLinkList.parallelStream().forEach(news -> {
			Document document = null;
			try {
				 document = Jsoup.connect(news.getLink()).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String newsDate = document.select("div.article_info").select("div.sponsor span.t11").text();
			String content = document.select("div#articleBodyContents").text();
			
			NewsDataVO vo = new NewsDataVO();
			vo.setTitle(news.getTitle());
			vo.setContent(content);
			vo.setNewsDate(newsDate);
			
			newsDataList.add(vo);
		});
		return newsDataList;
	}
	
}
