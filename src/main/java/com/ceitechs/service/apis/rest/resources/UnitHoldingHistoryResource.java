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
public class UnitHoldingHistoryResource {

    private String holdingReferenceId;

    private String startDate;

    private String endDate;

    private String timeRemaining;

    private String propertyReferenceId;

    private String userReferenceId;
}
