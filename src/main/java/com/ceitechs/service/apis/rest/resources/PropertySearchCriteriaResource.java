package com.ceitechs.service.apis.rest.resources;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhisheksingh
 * @since 0.1
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertySearchCriteriaResource {

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    @NotNull
    private int radius;

    @NotNull
    private String propertyPupose;

    @NotNull
    private String moveInDate;

    @NotNull
    private int roomsCount;

    @NotNull
    private double bedRoomsCount;

    @NotNull
    private int bathCount;

    private double minPrice;

    private double maxPrice;

    private String amenities;
}
