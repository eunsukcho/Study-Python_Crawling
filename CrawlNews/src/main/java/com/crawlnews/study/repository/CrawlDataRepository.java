package com.crawlnews.study.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crawlnews.study.domain.CrawlDataDomain;

public interface CrawlDataRepository extends JpaRepository<CrawlDataDomain, Integer>{
	List<CrawlDataDomain> findAll();
}
