package com.crawlnews.study.vo;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "articles", type = "article")
public class ElasticVO {
	
	private String id;
	private String title;
	private String content;
	private String reg_date;
	
	public ElasticVO() {}
	
	public ElasticVO(String title, String content, String reg_date) {
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
	}

	public ElasticVO(String id, String title, String content, String reg_date) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.reg_date = reg_date;
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
	
}
