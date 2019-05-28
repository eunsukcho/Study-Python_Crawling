package com.crawlnews.study.vo;

public class NewsLinkVo {
	
	public String title;
	public String link;
	
	public NewsLinkVo() {}
	
	public NewsLinkVo(String title, String link) {
		this.title = title;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
