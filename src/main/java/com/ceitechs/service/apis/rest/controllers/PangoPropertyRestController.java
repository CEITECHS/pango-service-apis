package com.ceitechs.service.apis.rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

import com.ceitechs.domain.service.domain.Address;
import com.ceitechs.domain.service.util.PangoUtility;
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
     * @param propertySearchCriteriaResource
     * @return
     */
    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public ResponseEntity<?> getProperties(@RequestHeader(required = false, value = "user-token") String userToken,
            @RequestHeader(required = false) String userReferenceId,
            @Valid @RequestBody PropertySearchCriteriaResource propertySearchCriteriaResource) {
        logger.info("getProperties : Header : " + userToken + " : " + userReferenceId);
        logger.info("getProperties : Request : " + propertySearchCriteriaResource);
        List<PropertyResource> propertiesList = new ArrayList<>();
        // Create a new Address
        Address address = new Address();
        address.setAddressLine1("Address Line 1");
        address.setAddressLine2("Address Line 2");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Country");
        address.setZip("12345");
        IntStream.range(0, 5).forEach(i -> {
            PropertyResource propertyResource = new PropertyResource();
            propertyResource.setPropertyReferenceId(PangoUtility.generateIdAsString());
            propertyResource.setPropertyDescription("Excellent Property");
            propertyResource.setListingFor("RENT");
            propertyResource.setAddress(address);
            propertyResource.setDistance(i / 2);
            // Add the property resource to the list
            propertiesList.add(propertyResource);
        });
        return ResponseEntity.ok(propertiesList);
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
            @RequestHeader String userReferenceId, @Valid @RequestBody PropertyDetailResource propertyDetailResource) {
        logger.info("createProperty : Header Params : " + userToken + " : " + userReferenceId);
        logger.info("createProperty : Request Params : " + propertyDetailResource);
        return new ResponseEntity<>("Ok, successfully created a new Property", HttpStatus.CREATED);
    }

    /**
     * This Property endpoint will return a list of active Pango rental property unit by status as specified in the
     * query parameter
     * 
     * @param userToken
     * @param userReferenceId
     * @param status - RENT or ON-HOLD
     * @param by - Owner or User
     * @return
     */
    @RequestMapping(value = "/properties/list", method = RequestMethod.GET)
    public ResponseEntity<?> getUserPropertiesByStatus(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @RequestParam String status, @RequestParam String by) {
        logger.info(
                "getUserPropertiesByStatus : Request Params : " + userToken + " : " + userReferenceId + " : " + status);
        List<PropertyResource> propertiesList = new ArrayList<>();
        // Create a new Address
        Address address = new Address();
        address.setAddressLine1("Address Line 1");
        address.setAddressLine2("Address Line 2");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Country");
        address.setZip("12345");
        IntStream.range(0, 5).forEach(i -> {
            PropertyResource propertyResource = new PropertyResource();
            propertyResource.setPropertyReferenceId(PangoUtility.generateIdAsString());
            propertyResource.setPropertyDescription("Excellent Property");
            propertyResource.setListingFor("RENT");
            propertyResource.setAddress(address);
            propertyResource.setDistance(i / 2);
            // Add the property resource to the list
            propertiesList.add(propertyResource);
        });
        return ResponseEntity.ok(propertiesList);
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
        List<PropertyResource> propertiesList = new ArrayList<>();
        // Create a new Address
        Address address = new Address();
        address.setAddressLine1("Address Line 1");
        address.setAddressLine2("Address Line 2");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Country");
        address.setZip("12345");
        IntStream.range(0, 5).forEach(i -> {
            PropertyResource propertyResource = new PropertyResource();
            propertyResource.setPropertyReferenceId(PangoUtility.generateIdAsString());
            propertyResource.setPropertyDescription("Excellent Property");
            propertyResource.setListingFor("RENT");
            propertyResource.setAddress(address);
            propertyResource.setDistance(i / 2);
            // Add the property resource to the list
            propertiesList.add(propertyResource);
        });
        return ResponseEntity.ok(propertiesList);
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
