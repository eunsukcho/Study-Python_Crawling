package com.crawlnews.study.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawlnews.study.repository.MongoRepository;

@Controller
public class NewsWebController {

	@Autowired
	MongoRepository mongoRepository;
	
	@RequestMapping("/news")
	public String newsPage() {
		
		return "newsKeywordSearch";
	}
	
	@RequestMapping("/news/search")
	@ResponseBody
	public String newsPageSearch(HttpServletRequest request) {
		String tempString = request.getParameter("searchDate");
		String searchCategory = request.getParameter("searchCategory");
		String searchDate = tempString.replace("-", ".");
		System.out.println(searchDate);
		System.out.println(searchCategory);
		
		
		
		return "test";
	}
}
