package com.ceitechs.service.apis.rest.resources;

import com.ceitechs.domain.service.domain.Address;
import com.ceitechs.domain.service.domain.Attachment;
import com.ceitechs.domain.service.domain.PropertyFeature;
import com.ceitechs.domain.service.domain.PropertyRent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class PropertyResource {
    private String propertyReferenceId;

    private String propertyUnitId;

    private String propertyDescription;

    private String listingFor;

    private PropertyRent propertyRent;

    private PropertyFeature propertyFeature;

    private String availability;

    private double rating;

    private int reviewCount;

    private Attachment coverPhoto;

    private double distance;

    private Address address;

    private String ownerReferenceId;
}
