package com.ceitechs.service.apis.converters.response;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.UnitHoldingHistory;
import com.ceitechs.service.apis.rest.resources.UnitHoldingHistoryResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class UnitHoldingHistoryToUnitHoldingHistoryResource
        implements Converter<UnitHoldingHistory, UnitHoldingHistoryResource> {

    @Override
    public UnitHoldingHistoryResource convert(UnitHoldingHistory unitHoldingHistory) {
        UnitHoldingHistoryResource holdingHistoryResource = new UnitHoldingHistoryResource();
        holdingHistoryResource.setEndDate(unitHoldingHistory.getEndDate().toString());
        holdingHistoryResource.setHoldingReferenceId(unitHoldingHistory.getHoldingReferenceId());
        holdingHistoryResource.setPropertyReferenceId(unitHoldingHistory.getPropertyUnit().getPropertyUnitId());
        holdingHistoryResource.setStartDate(unitHoldingHistory.getStartDate().toString());
        holdingHistoryResource.setTimeRemaining(unitHoldingHistory.getRemaingTme());
        holdingHistoryResource.setUserReferenceId(unitHoldingHistory.getUser().getUserReferenceId());
        return holdingHistoryResource;
    }
}
