package com.ceitechs.service.apis.rest.resources;

import com.ceitechs.domain.service.domain.Address;
import com.ceitechs.domain.service.domain.PropertyFeature;
import com.ceitechs.domain.service.domain.PropertyRent;
import com.ceitechs.domain.service.service.AttachmentProjection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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

    private AttachmentProjection coverPhoto;

    private List<AttachmentProjection> photos;

    private double[] location;

    private Address address;

    private String ownerReferenceId;

    private String propertyTerms;

    private boolean active;
    
    private String purpose;
}
