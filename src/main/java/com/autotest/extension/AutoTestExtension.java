package com.autotest.extension;

import com.autotest.annotation.AutoTest;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.support.AnnotationConsumerInitializer;
import org.junit.platform.commons.util.AnnotationUtils;
import org.junit.platform.commons.util.ExceptionUtils;
import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;
import static org.junit.platform.commons.util.AnnotationUtils.findRepeatableAnnotations;

public  class AutoTestExtension implements TestTemplateInvocationContextProvider {
    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return AnnotationUtils.isAnnotated(context.getTestMethod(), AutoTest.class);
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        Method templateMethod = Preconditions.notNull(context.getTestMethod().orElse(null),
                "test method must not be null");
        AutoTestNameFormatter formatter = createNameFormatter(templateMethod);
        AtomicLong invocationCount = new AtomicLong(0L);
        return (Stream) findRepeatableAnnotations(templateMethod, ArgumentsSource.class)
                .stream()
                .map(ArgumentsSource::value)
                .map(ReflectionUtils::newInstance)
                .map(provider -> AnnotationConsumerInitializer.initialize(templateMethod, provider))
                .flatMap(provider -> arguments(provider, context))
                .map(Arguments::get)
                .map((arguments) -> {
                    return new AutoTestInvocationContext(formatter, arguments);
                })
                .peek((invocationContext) -> {
                    invocationCount.incrementAndGet();
                }).onClose(() -> {
                    Preconditions.condition(invocationCount.get() > 0L, () -> {
                        return "当使用注解 @" + AutoTest.class.getSimpleName() + " 的时候,测试方法需要至少一个参数";
                    });
                });

    }

    private AutoTestNameFormatter createNameFormatter(Method templateMethod) {
        AutoTest autoTest = findAnnotation(templateMethod, AutoTest.class).get();
        String name = Preconditions.notBlank(autoTest.name().trim(),
                () -> String.format(
                        "Configuration error: @ParameterizedTest on method [%s] must be declared with a non-empty name.",
                        templateMethod));
        return new AutoTestNameFormatter(name);
    }

    protected static Stream<? extends Arguments> arguments(ArgumentsProvider provider, ExtensionContext context) {
        try {
            return provider.provideArguments(context);
        }
        catch (Exception e) {
            throw ExceptionUtils.throwAsUncheckedException(e);
        }
    }

}





