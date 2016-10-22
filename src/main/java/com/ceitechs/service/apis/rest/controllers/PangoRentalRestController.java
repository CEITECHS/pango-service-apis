package com.ceitechs.service.apis.rest.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceitechs.domain.service.domain.PropertyHoldingHistory;
import com.ceitechs.domain.service.domain.PropertyUnit;
import com.ceitechs.domain.service.domain.PropertyRentalHistory;
import com.ceitechs.domain.service.domain.User;
import com.ceitechs.service.apis.rest.resources.UnitHoldingHistoryResource;
import com.ceitechs.service.apis.rest.resources.UnitRentalHistoryResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@RestController
public class PangoRentalRestController {

    private static Logger logger = LoggerFactory.getLogger(PangoRentalRestController.class);

    @Autowired
    private ConversionService conversionService;

    /**
     * This endpoint will returns the rental history of the owner or the customer as specified by the
     * propertyReferenceId
     * 
     * @param userToken
     * @param userReferenceId
     * @param propertyReferenceId
     * @return
     */
    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public ResponseEntity<?> getRentalHistory(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @RequestParam(required = false) String propertyReferenceId) {
        logger.info(
                "getRentalHistory : Request : " + userToken + " : " + userReferenceId + " : " + propertyReferenceId);
        List<PropertyRentalHistory> rentalHistoryList = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
        	PropertyRentalHistory historyResource = new PropertyRentalHistory();
            historyResource.setStartDate(LocalDate.now());
            historyResource.setEndDate(LocalDate.now().plusMonths(12));
            historyResource.setPropertyUnit(new PropertyUnit());
            historyResource.setUser(new User());
            rentalHistoryList.add(historyResource);
        });
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(PropertyRentalHistory.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(UnitRentalHistoryResource.class));
        List<UnitRentalHistoryResource> target = (List<UnitRentalHistoryResource>) conversionService
                .convert(rentalHistoryList, sourceType, targetType);
        return ResponseEntity.ok(target);
    }

    /**
     * This endpoint will returns the holding history of the owner or the customer as specified in the
     * 'landlordIndicator' query parameter
     * 
     * @param userToken
     * @param userReferenceId
     * @param landlordIndicator
     * @return
     */
    @RequestMapping(value = "/holdings", method = RequestMethod.GET)
    public ResponseEntity<?> getHoldingHistory(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @RequestParam(defaultValue = "false") boolean landlordIndicator) {
        logger.info("getHoldingHistory : Request : " + userToken + " : " + userReferenceId + " : " + landlordIndicator);
        List<PropertyHoldingHistory> holdingHistoryList = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
        	PropertyHoldingHistory historyResource = new PropertyHoldingHistory();
            historyResource.setEndDate(LocalDateTime.now().plusHours(48));
            historyResource.setStartDate(LocalDateTime.now());
            historyResource.setPropertyUnit(new PropertyUnit());
            historyResource.setUser(new User());
            holdingHistoryList.add(historyResource);
        });
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(PropertyHoldingHistory.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(UnitHoldingHistoryResource.class));
        List<UnitHoldingHistoryResource> target = (List<UnitHoldingHistoryResource>) conversionService
                .convert(holdingHistoryList, sourceType, targetType);
        return ResponseEntity.ok(target);
    }
}
