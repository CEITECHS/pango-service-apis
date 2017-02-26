package com.ceitechs.service.apis.converters.response;

import java.util.Collections;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.PropertyUnitEnquiry;
import com.ceitechs.service.apis.rest.resources.EnquiryResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class PropertyUnitEnquiryToEnquiryResource implements Converter<PropertyUnitEnquiry, EnquiryResource> {

    @Override
    public EnquiryResource convert(PropertyUnitEnquiry source) {
        EnquiryResource enquiryResource = new EnquiryResource();
        enquiryResource.setEnquiredBy(source.getProspectiveTenant().getUserReferenceId());
        enquiryResource.setEnquiredOn(source.getEnquiryDate());
        enquiryResource.setEnquiryReferenceId(source.getEnquiryReferenceId());
        enquiryResource.setEnquiryType(source.getEnquiryType().name());
        enquiryResource.setIntroduction(source.getIntroduction());
        enquiryResource.setMessage(source.getMessage());
        enquiryResource.setPropertyUnit(source.getPropertyUnit());
        enquiryResource.setSubject(source.getSubject());
        // Missing Properties
        enquiryResource.setCorrespondence(Collections.emptyList());
        enquiryResource.setCorrespondenceCount(0);
        return enquiryResource;
    }

    //TODO Missing UnitEnquiry to EnquiryDetails
}
