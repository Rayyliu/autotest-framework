package com.autotest.extension;

import com.autotest.annotation.AutoTest;
import com.autotest.utils.ObjectTypeChange;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.autotest.extension.AutoTestArgumentsProvider.getClassAllField;
import static com.autotest.extension.ParameterAssemble.getParameters;
import static com.autotest.extension.TestMethodParameters.getTestMethodParameters;

public  class AutoTestArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<AutoTest> {

    private String file;
    private List<Object>  objects;

    @Override
    public void accept(AutoTest autoTest){
        this.file = autoTest.file();
        this.objects = new ArrayList<>();
    }

    @Override
    public Stream<? extends Arguments>  provideArguments(ExtensionContext context) throws Exception {
        List<Object> parameterValue = getParameters(context,this.file);
        List<Parame> pars = getTestMethodParameters(context);
        for(Object ob:parameterValue){
            Map<String,String >  map = (Map<String, String>) ob;
            Object[] obs = null;
            for(int i = 0;i<pars.size();i++){
                Class  type= pars.get(i).getType();
                String p = pars.get(i).getName();
                if(isJavaClass(type)|| type.isEnum()){
                    obs[i] = ObjectTypeChange.processing(type, map.get(p));
                }else{
                    obs[i] =
                }
            }
        }
        return null;
    }

    public boolean isJavaClass(Class clazz){
        return clazz!=null && clazz.getClassLoader()==null;
    }
}

    public static Object mapToObject(Class clz,Map<String,String> map) throws IllegalAccessException, InstantiationException {
        Object obj = clz.newInstance();
        List<Field> fieldList = new ArrayList<>();
        fieldList = getClassAllField(clz,fieldList);

    }

    public static List<Field> getClassAllField(Class<?> clazz,List<Field> allFields){
        if(clazz ==null){
            return null;
        }
    Object parent = clazz.getSuperclass();
        if(parent!=null && !((Class) parent).getName().equals("java.lang.Object")){
            getClassAllField((Class<?>) parent,allFields);
        }
        Field [] fields = clazz.getDeclaredFields();
        if(fields!= null) {
            for (int i = 0; i < fields.length; i++) {
                allFields.add(fields[i]);
            }
        }
        return allFields;
    }
}