package com.ceitechs.service.apis.rest.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceitechs.service.apis.rest.resources.PropertyDetailResource;
import com.ceitechs.service.apis.rest.resources.PropertyResource;
import com.ceitechs.service.apis.rest.resources.PropertySearchCriteriaResource;
import com.ceitechs.service.apis.rest.resources.UserRentResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@RestController
public class PangoPropertyRestController {

    private static Logger logger = LoggerFactory.getLogger(PangoPropertyRestController.class);

    /**
     * The Properties endpoint returns information about *Pango* rental properties available around a given location.
     * The response includes some details about the rental property and lists the the property in the proper display
     * order by distance.
     * 
     * @param userToken
     * @param userReferenceId
     * @param latitude
     * @param longitude
     * @param radius
     * @param propertyPurpose
     * @param moveInDate
     * @param roomsCount
     * @param bedRoomsCount
     * @param selfContainedRooms
     * @return
     */
    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public ResponseEntity<?> getProperties(@RequestHeader(required = false, value = "user-token") String userToken,
            @RequestHeader(required = false) String userReferenceId,
            @Valid @RequestBody PropertySearchCriteriaResource propertySearchCriteriaResource) {
        logger.info("getProperties : Request Params : " + userToken + " : " + userReferenceId);
        // TODO : Return a list of Property
        return ResponseEntity.ok("List of properties matching the search criteria : ");
    }

    /**
     * This endpoint will create a new property. This property will be in an unverified status till the coordinator
     * verifies it.
     * 
     * @param propertyResource
     * @return
     */
    @RequestMapping(value = "/properties", method = RequestMethod.POST)
    public ResponseEntity<?> createProperty(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @Valid @RequestBody PropertyResource propertyResource) {
        logger.info("createProperty : Request Params : " + userToken + " : " + userReferenceId);
        return new ResponseEntity<>("Ok, successfully created a new Property", HttpStatus.CREATED);
    }

    /**
     * This Property endpoint will return a list of active Pango rental property unit by status as specified in the
     * query parameter
     * 
     * @param userToken
     * @param userReferenceId
     * @param status
     * @return
     */
    @RequestMapping(value = "/properties/list", method = RequestMethod.GET)
    public ResponseEntity<?> getUserPropertiesByStatus(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @RequestParam String status) {
        logger.info(
                "getUserPropertiesByStatus : Request Params : " + userToken + " : " + userReferenceId + " : " + status);
        PropertyResource resource = new PropertyResource();
        // TODO: Return a list of Property
        return ResponseEntity.ok(resource);
    }

    /**
     * This Properties endpoint returns information about *Pango* rental properties that have not been verified. The
     * properties can be grouped by a coordinator or by the owner.
     * 
     * @param userToken
     * @param userReferenceId
     * @param referenceId
     * @param by
     * @return
     */
    @RequestMapping(value = "/properties/pending/list", method = RequestMethod.GET)
    public ResponseEntity<?> getPendingPropertiesList(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @RequestParam String referenceId, @RequestParam String by) {
        logger.info("getPendingPropertiesList : Request Params : " + userToken + " : " + userReferenceId + " : "
                + referenceId + " : " + by);
        PropertyResource resource = new PropertyResource();
        // TODO: Return a list of Property
        return ResponseEntity.ok(resource);
    }

    /**
     * This Property endpoint returns detailed information of a specific Pango rental property
     * 
     * @param userToken
     * @param userReferenceId
     * @param propertyReferenceId
     * @return
     */
    @RequestMapping(value = "/properties/{propertyReferenceId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPropertyUnit(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @PathVariable String propertyReferenceId) {
        logger.info("getPropertyUnit : Request : " + userToken + " : " + userReferenceId + " : " + propertyReferenceId);
        PropertyDetailResource propertyDetailResource = new PropertyDetailResource();
        return ResponseEntity.ok(propertyDetailResource);
    }

    /**
     * This Property endpoint updates a specific Pango rental property
     * 
     * @param userToken
     * @param userReferenceId
     * @param propertyReferenceId
     * @param propertyUnit
     * @return
     */
    @RequestMapping(value = "/properties/{propertyReferenceId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePropertyUnit(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @PathVariable String propertyReferenceId,
            @Valid @RequestBody PropertyDetailResource propertyDetailResource) {
        logger.info(
                "updatePropertyUnit : Request : " + userToken + " : " + userReferenceId + " : " + propertyReferenceId);
        return ResponseEntity.ok("Ok, property updated successfully");
    }

    /**
     * This Property endpoint updates a specific Pango rental property unit with the rental details
     * 
     * @param userToken
     * @param userReferenceId
     * @param propertyReferenceId
     * @param userRentRequest
     * @return
     */
    @RequestMapping(value = "/properties/{propertyReferenceId}/rent", method = RequestMethod.PUT)
    public ResponseEntity<?> rentPropertyUnit(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @PathVariable String propertyReferenceId,
            @Valid @RequestBody UserRentResource userRentResource) {
        logger.info("rentPropertyUnit : Request : " + userToken + " : " + userReferenceId + " : " + propertyReferenceId
                + " : " + userRentResource);
        return ResponseEntity.ok("Ok, the Pango rental unit has been updated successfully with the rental details");
    }

    /**
     * This Property endpoint updates a specific Pango rental property unit with the holding details. As part of this
     * API the following orchestration will take place
     * <ol>
     * <li>Calling the tegopesa payment APIs for making a holding payment
     * <li>Creating a transaction in customer transaction history and
     * <li>Creating a record in unit holding history
     * </ol>
     * 
     * @param userToken
     * @param userReferenceId
     * @param propertyReferenceId
     * @param userRentRequest
     * @return
     */
    @RequestMapping(value = "/properties/{propertyReferenceId}/hold", method = RequestMethod.PUT)
    public ResponseEntity<?> holdPropertyUnit(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @PathVariable String propertyReferenceId) {
        logger.info(
                "holdPropertyUnit : Request : " + userToken + " : " + userReferenceId + " : " + propertyReferenceId);
        return ResponseEntity.ok("Ok, the Pango rental unit has been updated successfully with the holding details");
    }
}
