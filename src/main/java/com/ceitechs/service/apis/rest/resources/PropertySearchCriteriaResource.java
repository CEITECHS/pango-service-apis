package com.ceitechs.service.apis.rest.resources;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.ceitechs.domain.service.domain.PropertyUnit;
import com.ceitechs.service.apis.rest.resources.validators.StringEnumValidator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

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
    @Min(value = 1,message = "Radius must be greater than zero")
    private int radius = 10;

    @StringEnumValidator(enumClass = PropertyUnit.PropertyPurpose.class, message = " propertyPurpose value is not valid, allowed values are [HOME, BUSINESS]"  )
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
