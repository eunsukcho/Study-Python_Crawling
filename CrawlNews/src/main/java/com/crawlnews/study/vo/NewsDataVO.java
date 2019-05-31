package com.crawlnews.study.vo;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "articles", type = "article")
public class NewsDataVO {

	public String title;
	public String content;
	public String newsDate;
	public String reg_date;
	
	public NewsDataVO() {}

	public NewsDataVO(String title, String content, String reg_date) {
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNewsDate() {
		return newsDate;
	}
	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
}
