package com.project.config;

import com.project.filter.CrossFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置跨域过滤器
 */
@Configuration
public class CrossFilterConfig {

    @Bean
    public FilterRegistrationBean<CrossFilter> crossFilterBean() {
        FilterRegistrationBean<CrossFilter> registration =
                new FilterRegistrationBean<>();

        // 设置filter-class, 创建过滤器的对象
        registration.setFilter(new CrossFilter());

        // url-pattern
        registration.addUrlPatterns("/*");

        // order
        registration.setOrder(1);
        return registration;
    }
}
