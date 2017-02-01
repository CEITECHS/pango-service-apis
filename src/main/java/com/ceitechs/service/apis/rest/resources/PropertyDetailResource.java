package com.ceitechs.service.apis.rest.resources;

import java.util.List;

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
public class PropertyDetailResource {
    private String propertyReferenceId;
    
    private String propertyUnitId;

    private String propertyDescription;

    private String listingFor;

    private PropertyRent propertyRent;

    private PropertyFeature propertyFeature;

    private String availability;

    private String availableOn;

    private boolean autoListInd;

    private double rating;

    private int reviewCount;

    private Attachment coverPhoto;

    private List<Attachment> photos;

    private double[] location;

    private Address address;

    private String ownerReferenceId;

    private String propertyTerms;

    private boolean active;
    
    private String purpose;
}
