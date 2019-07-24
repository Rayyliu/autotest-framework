package com.autotest.extension;

public class AutoTestInvocationContext {

    private final AutoTestNameFormatter formatter;
    private final Object[] arguments;


    AutoTestInvocationContext(AutoTestNameFormatter formatter,Object[] arguments){
        this.formatter = formatter;
        this.arguments = arguments;
    }
}
