package com.crawlnews.study.vo;

public class JSoupVO {

	String title;
	String link;
	String newspaper;
	
	public JSoupVO() {}

	public JSoupVO(String title, String link, String newspaper) {
		this.title = title;
		this.link = link;
		this.newspaper = newspaper;
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

	public String getNewspaper() {
		return newspaper;
	}

	public void setNewspaper(String newspaper) {
		this.newspaper = newspaper;
	}

}
