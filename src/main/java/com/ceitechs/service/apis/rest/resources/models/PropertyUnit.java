package com.ceitechs.service.apis.rest.resources.models;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PropertyUnit {
    public enum PropertyPurpose {
        HOME,
        BUSINESS
    }

    private String propertyUnitId;

    private String unitNumber;

    private String propertyUnitDesc;

    private PropertyPurpose purpose;

    private ListingFor listingFor;

    private String propertyTerms;

    private LocalDateTime nextAvailableDate;

    private boolean autoListInd;

    private double[] location;

    private Address address;

    private double propertyRating;

    private User owner;

    private PropertyFeature features;

    private PropertyRent rent;

    private List<FileMetadata> propertyUnitImages = new ArrayList<>();

    // private List<Attachment> attachments = new ArrayList<>();

    private boolean active = true;

    private LocalDateTime createdDate = LocalDateTime.now(Clock.systemUTC());
}