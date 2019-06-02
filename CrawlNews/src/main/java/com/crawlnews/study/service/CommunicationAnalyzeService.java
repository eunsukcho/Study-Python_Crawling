package com.crawlnews.study.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Service;

import com.crawlnews.study.utils.VariableUtils;
import com.crawlnews.study.vo.AnalyzeRequestVo;
import com.crawlnews.study.vo.AnalyzeResponseVo;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONObject;

@Service
public class CommunicationAnalyzeService {
	
    public List<AnalyzeResponseVo> runAnalyze(String contents){
    	List<AnalyzeResponseVo> analyzeResponseVoList = null;
    	AnalyzeRequestVo analyzeVo = new AnalyzeRequestVo();
    	StringBuffer stringBuffer = new StringBuffer();
        int responseCode;
        OutputStream os;
        HttpURLConnection connection;
        URL conUrl;

        try {
            conUrl = new URL("http://localhost:9200/_analyze");
            connection = (HttpURLConnection) conUrl.openConnection();

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", "length");

            connection.setDoInput(true);
            connection.setDoOutput(true);

            analyzeVo.setAnalyzer("nori");
            analyzeVo.setText(contents);
            analyzeVo.setExplain(true);

            os = connection.getOutputStream();
            os.write(VariableUtils.gson.toJson(analyzeVo).getBytes("UTF-8"));
            os.flush();
            os.close();

            responseCode = connection.getResponseCode();

            if(responseCode == 200){
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                JSONObject jsonObject = new JSONObject();
                String line;

                while((line = br.readLine()) != null){
                	stringBuffer.append(line);
                }
                
                analyzeResponseVoList = VariableUtils.gson.fromJson(VariableUtils.gson.toJson(jsonObject.fromObject(jsonObject.fromObject(jsonObject.fromObject(VariableUtils.gson.fromJson(stringBuffer.toString(), Object.class)).get("detail")).get("analyzer")).getJSONArray("tokens")), new TypeToken<List<AnalyzeResponseVo>>() {}.getType());
            } else {
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;

                while((line = br.readLine()) != null){
                    System.out.println(line);
                }
            }

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
		return analyzeResponseVoList;
    }
}
