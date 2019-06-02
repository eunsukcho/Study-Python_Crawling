package com.crawlnews.study.vo;

public class AnalyzeRequestVo {
	private String text;
	private String analyzer;
	private boolean explain;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAnalyzer() {
		return analyzer;
	}
	public void setAnalyzer(String analyzer) {
		this.analyzer = analyzer;
	}
	public boolean isExplain() {
		return explain;
	}
	public void setExplain(boolean explain) {
		this.explain = explain;
	}
}
