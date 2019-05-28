package com.crawlnews.study.vo;

public class NewsDataVO {

	public String title;
	public String content;
	public String newsDate;
	
	public NewsDataVO() {}
	
	public NewsDataVO(String title, String content, String newsDate) {
		this.title = title;
		this.content = content;
		this.newsDate = newsDate;
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
}
