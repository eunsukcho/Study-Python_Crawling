package com.crawlnews.study.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NewsWebController {

	@RequestMapping("/news")
	public String newsPage() {
		
		return "test";
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
