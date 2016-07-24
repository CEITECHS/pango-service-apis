package com.ceitechs.service.apis.rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * This endpoint will returns the rental history of the owner or the customer as specified in the 'by' query
     * parameter and the property Id
     * 
     * @param userToken
     * @param userReferenceId
     * @param landlordIndicator
     * @param propertyReferenceId
     * @return
     */
    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    public ResponseEntity<?> getRentalHistory(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @RequestParam boolean landlordIndicator,
            @RequestParam(required = false) String propertyReferenceId) {
        logger.info("getRentalHistory : Request : " + userToken + " : " + userReferenceId + " : " + propertyReferenceId
                + " : " + landlordIndicator);
        List<UnitRentalHistoryResource> rentalHistoryList = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
            UnitRentalHistoryResource historyResource = new UnitRentalHistoryResource();
            rentalHistoryList.add(historyResource);
        });
        return ResponseEntity.ok(rentalHistoryList);
    }

    /**
     * This endpoint will returns the holding history of the owner or the customer as specified in the 'by' query
     * parameter and the property Id
     * 
     * @param userToken
     * @param userReferenceId
     * @param landlordIndicator
     * @param propertyReferenceId
     * @return
     */
    @RequestMapping(value = "/holdings", method = RequestMethod.GET)
    public ResponseEntity<?> getHoldingHistory(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @RequestParam boolean landlordIndicator,
            @RequestParam(required = false) String propertyReferenceId) {
        logger.info("getHoldingHistory : Request : " + userToken + " : " + userReferenceId + " : " + propertyReferenceId
                + " : " + landlordIndicator);
        List<UnitHoldingHistoryResource> holdingHistoryList = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
            UnitHoldingHistoryResource historyResource = new UnitHoldingHistoryResource();
            holdingHistoryList.add(historyResource);
        });
        return ResponseEntity.ok(holdingHistoryList);
    }
}
