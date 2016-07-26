package com.ceitechs.service.apis.rest.converters.Response;

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
         //TODO finish up the rest of properties.
        return propertyResource;
    }
}
