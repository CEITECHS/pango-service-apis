package com.ceitechs.service.apis.converters.response;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.UnitRentalHistory;
import com.ceitechs.service.apis.rest.resources.UnitRentalHistoryResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class UnitRentalHistoryToUnitRentalHistoryResource
        implements Converter<UnitRentalHistory, UnitRentalHistoryResource> {

    @Override
    public UnitRentalHistoryResource convert(UnitRentalHistory unitRentalHistory) {
        UnitRentalHistoryResource rentalHistoryResource = new UnitRentalHistoryResource();
        rentalHistoryResource.setActive(unitRentalHistory.isActive());
        rentalHistoryResource.setEndDate(unitRentalHistory.getEndDate().toString());
        rentalHistoryResource.setPropertyReferenceId(unitRentalHistory.getPropertyUnit().getPropertyUnitId());
        rentalHistoryResource.setRentalReferenceId(unitRentalHistory.getRentalReferenceId());
        rentalHistoryResource.setStartDate(unitRentalHistory.getStartDate().toString());
        rentalHistoryResource.setUserReferenceId(unitRentalHistory.getUser().getUserReferenceId());
        return rentalHistoryResource;
    }
}
