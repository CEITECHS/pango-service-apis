package com.ceitechs.service.apis.rest.resources;

import com.ceitechs.domain.service.domain.Address;
import com.ceitechs.domain.service.domain.PropertyFeature;
import com.ceitechs.domain.service.domain.PropertyRent;
import com.ceitechs.domain.service.service.AttachmentProjection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class PropertyResource {
    @JsonIgnore
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private String propertyReferenceId;

    @JsonIgnore
    private String propertyUnitId;

    private String propertyDescription;

    private String listingFor;

    private PropertyRent propertyRent;

    private PropertyFeature propertyFeature;

    private String availability;

    private double rating;

    private int reviewCount;

    private AttachmentProjection coverPhoto;

    private double distance;

    private Address address;

    private String ownerReferenceId;

    private boolean verified;

    public void setAvailability(LocalDateTime date) {
        StringBuilder availableDateStr = new StringBuilder();
        if (date.isBefore(LocalDateTime.now()))
            availableDateStr.append(" since ");
        else
            availableDateStr.append(" on  ");
        this.availability = availableDateStr.append(date.format(formatter)).toString();
    }
}
