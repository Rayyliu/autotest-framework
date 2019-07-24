package com.autotest.extension;

import java.lang.annotation.Annotation;

public class Parame {

    /**
     * 参数名
     */
    private String name;


    /**
     * 参数类型
     */
    private Class type;

    /**
     * 参数对应annotation
     */
    private Annotation annotation;

    /**
     * 对象值
     */
    private String value;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Class getType() {
        return type;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public String getValue() {
        return value;
    }
}
