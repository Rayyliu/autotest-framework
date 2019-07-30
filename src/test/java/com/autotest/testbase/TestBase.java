package com.autotest.testbase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hc.common.config.util.Md5SHA1Util;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
public class TestBase {
    protected RestTemplate restTemplate = new RestTemplate();

    /**
     * 获取登录sessionId
     */
    public String getSessionId(String mobile,String password){
        String pwd = Md5SHA1Util.md5(password);
        String url = "http://127.0.0.1:8080/sc/borrower/auth/!/login/bypwd";
        HttpHeaders header = new HttpHeaders();
        header.add("Accept", "application/json;charset=UTF-8");
        header.add("Content-Type", "application/json");
        Map<Object, Object> dataMap = new HashMap<Object, Object>();
        dataMap.put("mobile",mobile);
        dataMap.put("password", pwd);
        HttpEntity<Map<Object, Object>> entity = new HttpEntity<>(dataMap, header);
        JSONObject  parse = restTemplate.postForObject(url,entity, JSONObject .class);
        System.out.println(parse);
        String sessionId = (String) parse.get("accessToken");
        System.out.println("sessionId::"+sessionId);
        return sessionId;
    }
}
