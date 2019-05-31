package com.crawlnews.study.vo;

public class NewsLinkVo {
	
	public String title;
	public String category;
	public String link;
	
	public NewsLinkVo() {}
	
	public NewsLinkVo(String title, String link) {
		this.title = title;
		this.link = link;
	}
	
	public NewsLinkVo(String title, String category, String link) {
		this.title = title;
		this.category = category;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
