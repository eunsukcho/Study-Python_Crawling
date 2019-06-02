package com.crawlnews.study.domain;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="navernews")
public class MongoDomain {
	private String id;
	private String date;
	private String category;
	private Map<String, Integer> keywordRankMap;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Map<String, Integer> getKeywordRankMap() {
		return keywordRankMap;
	}
	public void setKeywordRankMap(Map<String, Integer> keywordRankMap) {
		this.keywordRankMap = keywordRankMap;
	} 
}
