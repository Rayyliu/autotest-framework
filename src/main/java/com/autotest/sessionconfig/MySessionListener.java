package com.autotest.sessionconfig;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

public class MySessionListener {
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("session正在創建");
        MySessionContext.AddSession(httpSessionEvent.getSession());
    }
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        System.out.println("session注銷中");
        MySessionContext.DelSession(session);
    }
}
