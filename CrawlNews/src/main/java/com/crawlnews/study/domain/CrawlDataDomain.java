package com.crawlnews.study.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.crawlnews.study.utils.JsonDataSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name="t_crawl")
public class CrawlDataDomain {
	
	@Id
	@Column(name="idx", columnDefinition = "INT(10)")
	private int idx;
	
	@Column(name="mongo_id", columnDefinition = "VARCHAR(50)")
	private String mongo_id;
	
	@Column(name="elastic_id", columnDefinition = "VARCHAR(50)")
	private String elastic_id;
	
	@Column(name="title", columnDefinition = "VARCHAR(50)")
	private String title;
	
	@Column(name="category", columnDefinition = "VARCHAR(50)")
	private String category;
	
	@Column(name="reg_date")
	@JsonSerialize(using=JsonDataSerializer.class)
	private Date uploadDate;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getMongo_id() {
		return mongo_id;
	}

	public void setMongo_id(String mongo_id) {
		this.mongo_id = mongo_id;
	}

	public String getElastic_id() {
		return elastic_id;
	}

	public void setElastic_id(String elastic_id) {
		this.elastic_id = elastic_id;
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

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	

}
