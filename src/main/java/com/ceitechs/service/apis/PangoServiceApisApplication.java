package com.ceitechs.service.apis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.autoconfigure.EnablePangoDomainService;
import com.ceitechs.service.apis.converters.request.CorrespondenceResourceToEnquiryCorrespondence;
import com.ceitechs.service.apis.converters.request.EnquiryResourceToPropertyUnitEnquiry;
import com.ceitechs.service.apis.converters.request.PropertyDetailResourceToPropertyUnit;
import com.ceitechs.service.apis.converters.request.PropertySearchCriteriaResourceToPropertySearchCriteria;
import com.ceitechs.service.apis.converters.request.ReviewResourceToReview;
import com.ceitechs.service.apis.converters.request.UserPreferenceResourceToUserPreference;
import com.ceitechs.service.apis.converters.request.UserProfileResourceToUserProfile;
import com.ceitechs.service.apis.converters.request.UserResourceToUser;
import com.ceitechs.service.apis.converters.response.PropertyGeoResultToPropertyResource;
import com.ceitechs.service.apis.converters.response.PropertyUnitEnquiryToEnquiryResource;
import com.ceitechs.service.apis.converters.response.PropertyUnitToPropertyDetailResource;
import com.ceitechs.service.apis.converters.response.ReviewToReviewResource;
import com.ceitechs.service.apis.converters.response.UnitHoldingHistoryToUnitHoldingHistoryResource;
import com.ceitechs.service.apis.converters.response.UnitRentalHistoryToUnitRentalHistoryResource;
import com.ceitechs.service.apis.converters.response.UserPreferenceToUserPreferenceResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
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
        return converters;
    }
}
