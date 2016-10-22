package com.ceitechs.service.apis.converters.response;

import com.ceitechs.domain.service.domain.PropertyUnit;
import com.ceitechs.service.apis.rest.resources.PropertyResource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.geo.GeoResult;

/**
 * @author iddymagohe - 7/25/16.
 * @since 1.0
 */
public class PropertyGeoResultToPropertyResource implements Converter<GeoResult<PropertyUnit>, PropertyResource> {

    @Override
    public PropertyResource convert(GeoResult<PropertyUnit> propertyUnitGeoResult) {
        PropertyResource propertyResource = new PropertyResource();
        PropertyUnit propertyUnit = propertyUnitGeoResult.getContent();
        propertyResource.setDistance(propertyUnitGeoResult.getDistance().getValue());
        propertyResource.setCoverPhoto(propertyUnit.getCoverPhoto());
        propertyResource.setPropertyDescription(propertyUnit.getPropertyUnitDesc());
        propertyResource.setAddress(propertyUnit.getAddress());
        propertyResource.setListingFor(propertyUnit.getListingFor().name());
        propertyResource.setOwnerReferenceId(propertyUnit.getOwner().getUserReferenceId());
        propertyResource.setPropertyReferenceId(propertyUnit.getPropertyId());
        propertyResource.setPropertyDescription(propertyUnit.getPropertyUnitDesc());
        propertyResource.setPropertyFeature(propertyUnit.getFeatures());
        propertyResource.setPropertyRent(propertyUnit.getRent());
        propertyResource.setPropertyUnitId(propertyUnit.getUnitNumber());
        propertyResource.setRating(propertyUnit.getPropertyRating());

        // Missing properties
        propertyResource.setAvailability(propertyUnit.getNextAvailableDate());
        propertyResource.setReviewCount(0);
        return propertyResource;
    }
}
