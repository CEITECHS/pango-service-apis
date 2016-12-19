package com.ceitechs.service.apis;

import com.ceitechs.domain.autoconfigure.EnablePangoDomainService;
import com.ceitechs.service.apis.converters.request.*;
import com.ceitechs.service.apis.converters.response.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@Controller
@SpringBootApplication(exclude = {
        VelocityAutoConfiguration.class,
        MultipartAutoConfiguration.class
})
@EnablePangoDomainService
@EnableAsync
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

    @SuppressWarnings({"rawtypes"})
    private Set<Converter> converters() {
        Set<Converter> converters = new HashSet<>();
        converters.add(new PropertyGeoResultToPropertyResource());
        converters.add(new UserResourceToUser());
        converters.add(new UserProfileResourceToUserProfile());
        converters.add(new UserPreferenceResourceToUserPreference());
        converters.add(new UserPreferenceToUserPreferenceResource());
        converters.add(new ReviewResourceToReview());
        converters.add(new ReviewToReviewResource());
        converters.add(new UnitHoldingHistoryToUnitHoldingHistoryResource());
        converters.add(new UnitRentalHistoryToUnitRentalHistoryResource());
        converters.add(new PropertySearchCriteriaResourceToPropertySearchCriteria());
        converters.add(new PropertyDetailResourceToPropertyUnit());
        converters.add(new PropertyUnitToPropertyDetailResource());
        converters.add(new EnquiryResourceToPropertyUnitEnquiry());
        converters.add(new PropertyUnitEnquiryToEnquiryResource());
        converters.add(new CorrespondenceResourceToEnquiryCorrespondence());
        converters.add(new UserProjectionToProjectionResource());
        return converters;
    }

    @RequestMapping(value = {"/**"}, method = RequestMethod.OPTIONS)
    ResponseEntity<Void> getProposalsOptions() {
        return allows(HttpMethod.GET, HttpMethod.POST,HttpMethod.PUT,HttpMethod.DELETE,HttpMethod.OPTIONS);

    }

    public static ResponseEntity<Void> allows(HttpMethod... methods) {
        HttpHeaders headers = new HttpHeaders();
        Set<HttpMethod> allow = new HashSet<>();
        for (HttpMethod method : methods) {
            allow.add(method);
        }
        headers.setAllow(allow);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setDispatchOptionsRequest(true);
        return servlet;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        return  new MultipartConfigElement("");
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    @Bean public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
