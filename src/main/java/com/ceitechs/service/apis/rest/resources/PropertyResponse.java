package com.ceitechs.service.apis.rest.resources;

import java.util.List;

import com.ceitechs.service.apis.rest.resources.models.PropertyUnit;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@Getter
@Setter
public class PropertyResponse {
    
    private String developerText;
    private List<PropertyUnit> propertiesList;
}