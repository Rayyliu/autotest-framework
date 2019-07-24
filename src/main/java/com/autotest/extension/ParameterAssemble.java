package com.autotest.extension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.autotest.extension.TestMethodParameters.getTestMethodParameters;
import static java.util.stream.Collectors.toList;

public class ParameterAssemble {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(
            "ParameterAssemble");

    public static List<Object> getParameters(ExtensionContext context,String file) throws Exception {
        List<Parame> pars = getTestMethodParameters(context);
        List<Object> obs = new ArrayList<>();
        String filePath = Thread.currentThread().getContextClassLoader().getResource("autotest/"+file).getFile();
        File fl = new File(filePath);
        String line= null;
        BufferedReader reader = new BufferedReader(new FileReader(fl));
        int lineNo =0;
        String[] header = null;
        while ((line = reader.readLine())!=null) {
            lineNo++;
            if(lineNo==1) {
                header = line.split(",");
            }else {
                if(line.contains("~")){
                    line.replace("~","");
                }
                HashMap<String, Object> map = assemblyParameters(header, line, lineNo);
                obs.add(map);
            }
        }
        int times = lineNo-1;
        String methodName = context.getTestMethod().get().getName();
        context.getStore(NAMESPACE).put("methodName",methodName);
        context.getStore(NAMESPACE).put("times",times);
        return obs;
    }

    public static HashMap<String, Object> assemblyParameters(String[] header,String line,int lineNo){
        HashMap<String, Object> parMap = new HashMap<>();

        if(header.length!=line.split(",").length){
            throw new RuntimeException("数据文件第"+lineNo+"行格式长度不一致");
        }else {
            for(int i =0;i<header.length;i++){
                parMap.put(header[i],StringUtils.isEmpty(line.split(",")[i])?null:line.split(",")[i]);
            }

        }
        return parMap;
    }
}
