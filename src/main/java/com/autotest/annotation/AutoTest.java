package com.autotest.annotation;

import com.autotest.extension.AutoTestExtension;
import com.autotest.extension.AutoTestArgumentsProvider;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ExtendWith(AutoTestExtension.class)
@ArgumentsSource(AutoTestArgumentsProvider.class)
@TestTemplate
public @interface AutoTest {

    String file();


    String name() default "[{index}] {arguments}";

    String encoding() default "UTF-8";



}
