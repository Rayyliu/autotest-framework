package com.autotest.test.borrowerauth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.autotest.annotation.AutoTest;
import com.hc.common.config.util.Md5SHA1Util;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetSessionId {

    protected RestTemplate restTemplate = new RestTemplate();
    @AutoTest(file = "login.csv")
    public void login(int testId){

        String pwd = Md5SHA1Util.md5("aaa123456");
        String url = "http://127.0.0.1:8079/sc/borrower/auth/!/login/bypwd";
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json");

        Map<Object, Object> dataMap = new HashMap<Object, Object>();
        dataMap.put("mobile","13333333303");
        dataMap.put("password", pwd);
        HttpEntity<Map<Object, Object>> entity = new HttpEntity<>(dataMap, header);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,entity, String.class);
        System.out.println(response);
        JSONObject parse = JSON.parseObject(response.getBody());
        String sessionId = (String) parse.get("accessToken");
        System.out.println("sessionId::"+sessionId);

    }


    @AutoTest(file = "login.csv")
    public void login1(int testId){

        String pwd = Md5SHA1Util.md5("aaa123456");
        String url = "http://127.0.0.1:8079/sc/borrower/auth/!/login/bypwd";

        Map<Object, Object> dataMap = new HashMap<Object, Object>();
        dataMap.put("mobile","13333333303");
        dataMap.put("password", pwd);
        ResponseEntity<String> response = restTemplate.postForEntity(url, dataMap, String.class);
        System.out.println(response);
        JSONObject parse = JSON.parseObject(response.getBody());
        assertEquals("登陆成功",parse.get("message"));
        String sessionId = (String) parse.get("accessToken");
        System.out.println("sessionId::"+sessionId);

    }
}
