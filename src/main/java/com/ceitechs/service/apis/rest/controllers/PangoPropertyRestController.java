package com.ceitechs.service.apis.rest.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceitechs.service.apis.rest.resources.PangoServiceResponse;
import com.ceitechs.service.apis.rest.resources.PropertyResource;
import com.ceitechs.service.apis.rest.resources.PropertyResponse;
import com.ceitechs.service.apis.rest.resources.models.PropertyUnit;

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
            @RequestHeader(required = false) String userReferenceId, @RequestParam double latitude,
            @RequestParam double longitude, @RequestParam int radius, @RequestParam String propertyPurpose,
            @RequestParam String moveInDate, @RequestParam int roomsCount, @RequestParam int bedRoomsCount,
            @RequestParam(defaultValue = "false") boolean selfContainedRooms) {
        logger.info("getProperties : Request Params : " + userToken + " : " + userReferenceId);
        PropertyResponse response = new PropertyResponse();
        response.setDeveloperText("List of properties matching the search criteria : ");
        return ResponseEntity.ok(response);
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
        logger.info("getProperties : Request Params : " + userToken + " : " + userReferenceId);
        PangoServiceResponse response = new PangoServiceResponse();
        response.setDeveloperText("Ok, successfully created a new Property");
        return ResponseEntity.ok(response);
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
        PropertyResponse response = new PropertyResponse();
        response.setDeveloperText("List of Pango rental units assigned to the user filtered as per the status");
        return ResponseEntity.ok(response);
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
        PropertyResponse response = new PropertyResponse();
        response.setDeveloperText(
                "List of un-verified Pango rental units by an owner or coodinator filtered as per the query parameters");
        return ResponseEntity.ok(response);
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
        PropertyResponse response = new PropertyResponse();
        response.setDeveloperText("Detailed information of the property");
        return ResponseEntity.ok(response);
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
            @RequestBody PropertyUnit propertyUnit) {
        logger.info(
                "updatePropertyUnit : Request : " + userToken + " : " + userReferenceId + " : " + propertyReferenceId);
        PangoServiceResponse response = new PangoServiceResponse();
        response.setDeveloperText("Ok, property updated successfully");
        return ResponseEntity.ok(response);
    }
}
