package com.ceitechs.service.apis.converters.response;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.PropertyHoldingHistory;
import com.ceitechs.service.apis.rest.resources.UnitHoldingHistoryResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class UnitHoldingHistoryToUnitHoldingHistoryResource
        implements Converter<PropertyHoldingHistory, UnitHoldingHistoryResource> {

    @Override
    public UnitHoldingHistoryResource convert(PropertyHoldingHistory unitHoldingHistory) {
        UnitHoldingHistoryResource holdingHistoryResource = new UnitHoldingHistoryResource();
        holdingHistoryResource.setEndDate(unitHoldingHistory.getEndDate().toString());
        holdingHistoryResource.setHoldingReferenceId(unitHoldingHistory.getHoldingReferenceId());
        holdingHistoryResource.setPropertyReferenceId(unitHoldingHistory.getPropertyUnit().getPropertyId());
        holdingHistoryResource.setStartDate(unitHoldingHistory.getStartDate().toString());
        holdingHistoryResource.setTimeRemaining(unitHoldingHistory.getRemainingTme());
        holdingHistoryResource.setUserReferenceId(unitHoldingHistory.getUser().getUserReferenceId());
        return holdingHistoryResource;
    }
}
