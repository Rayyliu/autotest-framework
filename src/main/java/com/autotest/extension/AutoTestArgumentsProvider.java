package com.autotest.extension;

import com.autotest.annotation.AutoTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public  class AutoTestArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<AutoTest> {

    private String file;
    private List<Object>  objects;

    @Override
    public void accept(AutoTest autoTest){
        this.file = autoTest.file();
        this.objects = new ArrayList<>();
    }

    @Override
    public Stream<? extends Arguments>  provideArguments(ExtensionContext context){
        return null;
    }
}
