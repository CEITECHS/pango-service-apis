package com.ceitechs.service.apis.rest.resources;

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
public class UnitRentalHistoryResource {

    private String rentalReferenceId;

    private String startDate;

    private String endDate;

    private boolean active;

    private String propertyReferenceId;

    private String userReferenceId;
}
