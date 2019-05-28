package com.crawlnews.study.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping("/test1")
	public void test() {
		String man = "[[1, 소, 2, 닭, 3, 말]";
		ArrayList<String> resultArray = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(man, ", ");
		ArrayList<String> man2 = new ArrayList<String>();
		
		while(st.hasMoreElements()) {
			man2.add(st.nextToken());
		}
		
		man2.stream()
			.forEach(
				n -> {
					StringBuilder sb = new StringBuilder();
					
				    for (int i = 0; i < n.length(); i++){
				    	Character nch = n.charAt(i);
				    	
				    	if(StringUtils.isNumeric(String.valueOf(nch))) {
							System.out.println(String.valueOf(nch));
							System.out.println("숫자");
							resultArray.add(String.valueOf(nch));
						}
						
				        if(StringUtils.isAlpha(String.valueOf(nch))) {
				        	System.out.println(String.valueOf(nch));
				        	System.out.println("문자");
				        	resultArray.add(String.valueOf(nch));
				        }
				    }
				}
			);
		System.out.println("=========================");
		System.out.println(resultArray.toString());
	}
	
	@RequestMapping("/test2")
	public void test2() {
		IntStream.range(1, 10).parallel().forEach(index ->{
			System.out.println("Starting " + Thread.currentThread().getName() + ",    index=" + index + ", " + new Date());
			try {
				Thread.sleep(5000);
			}catch (InterruptedException e) {}
		});
	}
}
