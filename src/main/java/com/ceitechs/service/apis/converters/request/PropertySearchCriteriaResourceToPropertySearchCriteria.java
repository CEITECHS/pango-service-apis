package com.ceitechs.service.apis.converters.request;

import java.time.LocalDateTime;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.PropertySearchCriteria;
import com.ceitechs.service.apis.rest.resources.PropertySearchCriteriaResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class PropertySearchCriteriaResourceToPropertySearchCriteria
        implements Converter<PropertySearchCriteriaResource, PropertySearchCriteria> {

    @Override
    public PropertySearchCriteria convert(PropertySearchCriteriaResource source) {
        PropertySearchCriteria propertySearchCriteria = new PropertySearchCriteria();
        propertySearchCriteria.setAmenities(source.getAmenities());
        propertySearchCriteria.setBathCount(source.getBathCount());
        propertySearchCriteria.setBedRoomsCount(source.getBedRoomsCount());
        propertySearchCriteria.setLatitude(source.getLatitude());
        propertySearchCriteria.setLongitude(source.getLongitude());
        propertySearchCriteria.setMaxPrice(source.getMaxPrice());
        propertySearchCriteria.setMinPrice(source.getMinPrice() > 0? source.getMinPrice() : 1.0);
        propertySearchCriteria.setMoveInDateAsString(source.getMoveInDate());
        propertySearchCriteria.setPropertyPupose(source.getPropertyPurpose().toUpperCase());
        propertySearchCriteria.setRadius(source.getRadius());
        propertySearchCriteria.setRoomsCount(source.getRoomsCount());
        if (source.getPage() > 0)propertySearchCriteria.setPage(source.getPage());
        if (source.getPageSize() > 0)propertySearchCriteria.setPageSize(source.getPageSize());
        //TODO: Missing properties
        propertySearchCriteria.setPropertyReferenceId("");
        propertySearchCriteria.setSearchDate(LocalDateTime.now());
        return propertySearchCriteria;
    }
}
