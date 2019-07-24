package com.autotest.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestMethodParameters {

    public static List<Parame> getTestMethodParameters(ExtensionContext context){
        Parameter[] parametersType = context.getTestMethod().orElseThrow(() -> new RuntimeException("测试方法不存在")).getParameters();
        String[] parametersName = new LocalVariableTableParameterNameDiscoverer().getParameterNames(context.getTestMethod().get());
        List<Parame> pars = new ArrayList<>();
        for (int i = 0; i < parametersType.length; i++) {
            Parame par = new Parame();
            par.setName(parametersName[i]);
            par.setType(parametersType[i].getType());
            pars.add(par);
        }
        return pars;
    }
}
