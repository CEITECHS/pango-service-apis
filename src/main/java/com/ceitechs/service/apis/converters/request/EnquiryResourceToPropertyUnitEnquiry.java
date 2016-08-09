package com.ceitechs.service.apis.converters.request;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.CorrespondenceType;
import com.ceitechs.domain.service.domain.PropertyUnitEnquiry;
import com.ceitechs.domain.service.domain.User;
import com.ceitechs.service.apis.rest.resources.EnquiryResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class EnquiryResourceToPropertyUnitEnquiry implements Converter<EnquiryResource, PropertyUnitEnquiry> {

    @Override
    public PropertyUnitEnquiry convert(EnquiryResource source) {
        PropertyUnitEnquiry propertyUnitEnquiry = new PropertyUnitEnquiry();
        propertyUnitEnquiry.setSubject(source.getSubject());
        User user = new User();
        user.setUserReferenceId(source.getEnquiredBy());
        propertyUnitEnquiry.setProspectiveTenant(user);
        propertyUnitEnquiry.setPropertyUnit(source.getPropertyUnit());
        propertyUnitEnquiry.setMessage(source.getMessage());
        propertyUnitEnquiry.setIntroduction(source.getIntroduction());
        propertyUnitEnquiry.setEnquiryType(CorrespondenceType.valueOf(source.getEnquiryType()));
        propertyUnitEnquiry.setEnquiryReferenceId(source.getEnquiryReferenceId());
        propertyUnitEnquiry.setEnquiryDate(source.getEnquiredOn());

        // Missing properties
        // propertyUnitEnquiry.setCorrespondences(source.getCorrespondence());

        return propertyUnitEnquiry;
    }
}
