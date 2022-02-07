package com.lfd.fsmusic.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger3Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .securitySchemes(
                        Collections.singletonList(HttpAuthenticationScheme.JWT_BEARER_BUILDER
                                .name("JWT")
                                .build()))
                .securityContexts(
                        Collections.singletonList(SecurityContext.builder()
                                .securityReferences(Collections.singletonList(
                                        SecurityReference.builder()
                                                .scopes(new AuthorizationScope[0]).reference("JWT").build()))
                                .operationSelector(o -> o.requestMappingPattern().matches("/.*")).build()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lfd.fsmusic.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("全栈音乐App接口文档")
                .contact(new Contact("lfd", "http://xxx", "xxx@xxx.com"))
                .version("1.0")
                .build();
    }

    // @Bean
    // public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
    // return new BeanPostProcessor() {

    // @Override
    // public Object postProcessAfterInitialization(Object bean, String beanName)
    // throws BeansException {
    // // System.err.println("[swagger3] ① ----" + beanName);
    // if (bean instanceof WebMvcRequestHandlerProvider) {
    // customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
    // }
    // return bean;
    // }

    // private <T extends RequestMappingInfoHandlerMapping> void
    // customizeSpringfoxHandlerMappings(
    // List<T> mappings) {
    // // System.err.println("[swagger3] ② ----" + mappings);
    // List<T> copy = mappings.stream()
    // .filter(mapping -> mapping.getPatternParser() == null)
    // .collect(Collectors.toList());
    // mappings.clear();
    // mappings.addAll(copy);
    // }

    // @SuppressWarnings("unchecked")
    // private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object
    // bean) {
    // // System.err.println("[swagger3] ③ ----" + bean);
    // try {
    // Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
    // field.setAccessible(true);
    // return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
    // } catch (IllegalArgumentException | IllegalAccessException e) {
    // throw new IllegalStateException(e);
    // }
    // }
    // };
    // }
}