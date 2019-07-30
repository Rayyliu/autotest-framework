package com.autotest.test.creditLine;

import com.alibaba.fastjson.JSONObject;
import com.autotest.annotation.AutoTest;
import com.autotest.testbase.TestBase;
import org.springframework.http.*;
import java.util.HashMap;
import java.util.Map;

public class SelectAuditStatus extends TestBase {

    @AutoTest(file = "selectAuditStatusSuccess.csv")
    public void selectAuditStatusSuccess(int testId,String merchantId,String borrowerId,String sessionId){
        //删除数据
        //准备数据
        //调用接口
        if(testId ==1001){
            sessionId = getSessionId("13333333303","aaa123456");
        }else{
            sessionId = getSessionId("13314652346","aaa123456");
        }
        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        header.add("Accept", "application/json;charset=UTF-8");
        header.add("Content-Type", "application/json");
        header.add("X-Auth-Token", sessionId);
        String url = "http://127.0.0.1:8080/sc/creditline/selectAuditStatus";
        Map<Object, Object> dataMap = new HashMap<>();
        dataMap.put("merchantId",merchantId);
        dataMap.put("borrowerId", borrowerId);
        HttpEntity<Map<Object, Object>> entity = new HttpEntity<>(dataMap, header);
        ResponseEntity<JSONObject> parse = restTemplate.exchange(url,HttpMethod.GET, entity,JSONObject.class);
        //数据验证
        System.out.println(parse);
        System.out.println("parse:::"+parse);
        System.out.println("用戶授信结果：："+parse.getBody().get("creditAuditInfoDTO"));
        //结果验证
    }
}
