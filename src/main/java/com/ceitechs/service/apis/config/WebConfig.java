package com.ceitechs.service.apis.config;

import com.ceitechs.service.apis.rest.controllers.RequestResponseLogger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author iddymagohe on 10/29/16.
 * @since 1.0
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestResponseLogger());
        super.addInterceptors(registry);
    }
}
