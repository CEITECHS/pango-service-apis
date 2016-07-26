package com.ceitechs.service.apis;

import com.ceitechs.domain.autoconfigure.EnablePangoDomainService;
import com.ceitechs.service.apis.rest.converters.Response.PropertyGeoResultToPropertyResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnablePangoDomainService
public class PangoServiceApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PangoServiceApisApplication.class, args);
	}

    @Bean(name = "conversionService")
    public ConversionService conversionService() {
        ConversionServiceFactoryBean conversionService = new ConversionServiceFactoryBean();
        conversionService.setConverters(converters());
        conversionService.afterPropertiesSet();
        return conversionService.getObject();
    }

    @SuppressWarnings({ "rawtypes" })
    private Set<Converter> converters() {
        Set<Converter> converters = new HashSet<>();
        converters.add(new PropertyGeoResultToPropertyResource());
        return converters;
    }
}


