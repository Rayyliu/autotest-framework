package com.autotest.test;

import com.autotest.annotation.AutoTest;
import com.autotest.example.QueryOrder;
import com.autotest.example.QueryUserFacade;
import com.autotest.example.UserResult;

import javax.annotation.Resource;

public class HttpInterFaceTest {

    @Resource
    QueryUserFacade queryUserFacade;
    @AutoTest(file = "httpTest.csv")
    public void httpTestSuccess(
            //基础参数
            String testId,
            //业务参数
            QueryOrder order){
        // 清除数据
        // 准备数据
        // 测试过程
        // 调用接口
        UserResult result = queryUserFacade.queryUserById(order);
        // 结果验证
        // 数据验证
    }
}
