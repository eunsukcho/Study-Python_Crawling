package com.crawlnews.study.domain;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "naver", type = "navernews")
public class ElasticDomain {
	
	private String id;
	private String title;
	private String content;
	private String reg_date;
	private String category;
	
	public ElasticDomain() {}

	public ElasticDomain(String title, String content, String reg_date, String category) {
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
		this.category = category;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
