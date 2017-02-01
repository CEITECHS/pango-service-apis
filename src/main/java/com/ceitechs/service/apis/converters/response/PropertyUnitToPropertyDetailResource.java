package com.ceitechs.service.apis.converters.response;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.PropertyUnit;
import com.ceitechs.service.apis.rest.resources.PropertyDetailResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class PropertyUnitToPropertyDetailResource implements Converter<PropertyUnit, PropertyDetailResource> {

    @Override
    public PropertyDetailResource convert(PropertyUnit source) {
        PropertyDetailResource detailResource = new PropertyDetailResource();
        detailResource.setActive(source.isActive());
        detailResource.setAddress(source.getAddress());
        detailResource.setPhotos(source.getAttachments());
        detailResource.setAutoListInd(source.isAutoListInd());
        detailResource.setCoverPhoto(source.getCoverPhoto());
        detailResource.setPropertyFeature(source.getFeatures());
        detailResource.setListingFor(source.getListingFor().name());
        detailResource.setLocation(source.getLocation());
        detailResource.setOwnerReferenceId(source.getOwner().getUserReferenceId());
        detailResource.setRating(source.getPropertyRating());
        detailResource.setPropertyTerms(source.getPropertyTerms());
        detailResource.setPropertyDescription(source.getPropertyUnitDesc());
        detailResource.setPropertyReferenceId(source.getPropertyId());
        detailResource.setPropertyRent(source.getRent());
        detailResource.setPropertyUnitId(source.getUnitNumber());
        detailResource.setAvailableOn(source.getNextAvailableDate().toString());
        // Missing properties
        detailResource.setReviewCount(0);
        detailResource.setAvailability("");
        return detailResource;
    }
}
