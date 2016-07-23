package com.ceitechs.service.apis.rest.resources.models;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.ceitechs.domain.service.util.PangoUtility;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe
 * @since 0.1
 */
@Getter
@Setter
public class PropertySearchCriteria {
    private LocalDateTime searchDate;
    private double longitude;
    private double latitude;
    private int radius;
    private String propertyPupose;
    private String moveInDateAsString; // Date-format YYYY-MM-DD
    private int roomsCount;
    private double bedRoomsCount;
    private int bathCount;

    private double minPrice;
    private double maxPrice;
    private String amenities;

    public Optional<LocalDate> getMoveInDate(){
       return PangoUtility.getLocalDateDateFrom(moveInDateAsString);
    }
}