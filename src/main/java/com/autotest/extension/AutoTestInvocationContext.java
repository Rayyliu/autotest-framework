package com.autotest.extension;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import java.util.List;

import static java.util.Collections.singletonList;

public class AutoTestInvocationContext implements TestTemplateInvocationContext{

    private final AutoTestNameFormatter formatter;
    private final Object[] arguments;


    AutoTestInvocationContext(AutoTestNameFormatter formatter,Object[] arguments){
        this.formatter = formatter;
        this.arguments = arguments;
    }

    @Override
    public String getDisplayName(int invocationIndex) {
        return formatter.format(invocationIndex, this.arguments);
    }

    @Override
    public List<Extension> getAdditionalExtensions() {
        return singletonList(new AutoTestParameterResolver(arguments));
    }
}
