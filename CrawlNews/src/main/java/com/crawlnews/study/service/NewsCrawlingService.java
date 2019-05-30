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
		String startUrl = "https://news.naver.com/main/list.nhn?mode=LS2D&mid=shm&sid1=100&sid2=264&date=20190519&page=";
		Document document = null;
		int i = 1;
		String newLink = "";
		String oldLink = "";
		
		while(true) {
			document = Jsoup.connect(startUrl+i).get();
			List<Element> newsElementList = new ArrayList<Element>();
			// 상위 10개 기사 수집
			List<Element> element = document.select("ul.type06_headline li");
			// 하위 10개 기사 수집
			List<Element> element2 = document.select("ul.type06 li");
		
			// 리스트 연결
			newsElementList.addAll(element);
			newsElementList.addAll(element2);
			
			// 현재 페이지 첫 번째 기사의 url 파싱
			newLink = newsElementList.get(0).select("a").attr("abs:href");
			
			// 현재 페이지 첫 번째 기사 링크와 이전 페이지 첫 번째 기사 링크가 같다면 같은 페이지
			if (oldLink.equals(newLink)) break;
			
			// 현재 페이지 첫 번째 기사 링크는 이전 페이지 첫 번째 기사 링크가됨
			oldLink = newLink;
			newLink = null;
			
			// 기사의 제목, 링크 수집
			for (Element e : newsElementList) {
				String title = e.select("a").text();
				String link = e.select("a").attr("abs:href");
				NewsLinkVo vo = new NewsLinkVo(title, link);
				newsLinkList.add(vo);
			}
			i++;
		}
		
		return newsLinkList;
	}
	
	public List<NewsDataVO> newsDataList(List<NewsLinkVo> newsLinkList) throws Exception{
		List<NewsDataVO> newsDataList = new ArrayList<NewsDataVO>();
		
		newsLinkList.parallelStream().forEach(news -> {
			Document document = null;
			try {
				// 리스트의 링크를 하나씩 접근
				document = Jsoup.connect(news.getLink()).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 기사 등록일과 내용 수집
			String newsDate = document.select("div.article_info").select("div.sponsor span.t11").text();
			String content = document.select("div#articleBodyContents").text();
			
			// vo에 해당 링크의 기사 제목, 내용, 등록일 setting
			NewsDataVO vo = new NewsDataVO();
			vo.setTitle(news.getTitle());
			vo.setContent(content);
			vo.setNewsDate(newsDate);
			
			newsDataList.add(vo);
		});
		return newsDataList;
	}
	
}
