package com.ceitechs.service.apis.converters.request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.ListingFor;
import com.ceitechs.domain.service.domain.PropertyUnit;
import com.ceitechs.domain.service.domain.PropertyUnit.PropertyPurpose;
import com.ceitechs.domain.service.domain.User;
import com.ceitechs.service.apis.rest.resources.PropertyDetailResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class PropertyDetailResourceToPropertyUnit implements Converter<PropertyDetailResource, PropertyUnit> {

    @Override
    public PropertyUnit convert(PropertyDetailResource source) {
        PropertyUnit propertyUnit = new PropertyUnit();
        propertyUnit.setActive(source.isActive());
        propertyUnit.setAddress(source.getAddress());
        propertyUnit.setAttachments(source.getPhotoUrl());
        propertyUnit.setAutoListInd(source.isAutoListInd());
        propertyUnit.setCoverPhoto(source.getCoverPhoto());
        propertyUnit.setCreatedDate(LocalDateTime.now());
        propertyUnit.setFeatures(source.getPropertyFeature());
        propertyUnit.setListingFor(ListingFor.valueOf(source.getListingFor()));
        propertyUnit.setLocation(source.getLocation());
        User user = new User();
        user.setUserReferenceId(source.getOwnerReferenceId());
        propertyUnit.setOwner(user);
        propertyUnit.setPropertyRating(source.getRating());
        propertyUnit.setPropertyTerms(source.getPropertyTerms());
        propertyUnit.setPropertyUnitDesc(source.getPropertyDescription());
        propertyUnit.setPropertyId(source.getPropertyReferenceId());
        propertyUnit.setRent(source.getPropertyRent());
        propertyUnit.setUnitNumber(source.getPropertyUnitId());
        //TODO Move this code to pangoUtility class for date conversion
        propertyUnit.setNextAvailableDate(LocalDateTime.parse(source.getAvailableOn(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        propertyUnit.setPurpose(PropertyPurpose.valueOf(source.getPurpose()));

        // Missing properties
        // propertyUnit.setEnquiries();
        // propertyUnit.setHistory();
        // propertyUnit.setHoldingHistory();
        // propertyUnit.setNextAvailableDate();
        // propertyUnit.setPropertyUnitImages();
        // propertyUnit.setPurpose();
        return propertyUnit;
    }
}
