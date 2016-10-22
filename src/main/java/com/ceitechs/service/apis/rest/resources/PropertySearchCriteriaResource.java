package com.ceitechs.service.apis.rest.resources;

import java.time.LocalDate;

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
    private int radius = 10;

    @NotNull
    private String propertyPurpose = "HOME";

    @NotNull
    private String moveInDate = LocalDate.now().plusWeeks(2).toString();

    @NotNull
    private int roomsCount;

    @NotNull
    private double bedRoomsCount;

    @NotNull
    private int bathCount;
    
    private int page;
    
    private int pageSize;

    private double minPrice = 1.0;

    private double maxPrice;

    private String amenities;
}
