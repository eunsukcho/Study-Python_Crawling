package com.crawlnews.study.utils;

/**
 * 기타 라이브러리에서 공통으로 자주 사용하는 Util성 변수 // 메소드 기록
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class VariableUtils {
	
    /**
     * Vo -> Json 혹은 Json -> Vo시 사용할 일반 Gson라이브러리 변수
     */
    public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Vo -> Json 혹은 Json -> Vo시 사용할 HTML태그에 손상을 입히지 않는 Gson라이브러리 변수
     */
    public static final Gson taggingGson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
}
