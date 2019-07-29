package com.autotest.base;

import com.autotest.AutotestFrameworkApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(
        classes = AutotestFrameworkApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@ExtendWith(SpringExtension.class)
public class SpringBootTestBase {
}
