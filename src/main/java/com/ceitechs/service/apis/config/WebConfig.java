package com.ceitechs.service.apis.config;

import com.ceitechs.service.apis.rest.controllers.RequestResponseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author iddymagohe on 10/29/16.
 * @since 1.0
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private RequestResponseLogger requestResponseLogger;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestResponseLogger).addPathPatterns("/**");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new FormHttpMessageConverter());
    }
}
