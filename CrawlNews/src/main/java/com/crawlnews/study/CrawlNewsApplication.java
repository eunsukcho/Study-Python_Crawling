package com.crawlnews.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CrawlNewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrawlNewsApplication.class, args);
	}
	
	@RequestMapping("/")
	public String index() {
		
		return "Hello, world!";
	}

}
