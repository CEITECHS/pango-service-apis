package com.ceitechs.service.apis.rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.geo.GeoResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceitechs.domain.service.domain.PropertySearchCriteria;
import com.ceitechs.domain.service.domain.PropertyUnit;
import com.ceitechs.domain.service.domain.User;
import com.ceitechs.domain.service.service.PangoDomainService;
import com.ceitechs.service.apis.handler.ExceptionHandlerUtil;
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

    @Autowired
    private ConversionService conversionService;

    @Autowired
    PangoDomainService pangoDomainService;

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
            @Valid PropertySearchCriteriaResource propertySearchCriteriaResource, BindingResult result) {

        logger.debug("getProperties : Header : " + userToken + " : " + userReferenceId);
        logger.debug("getProperties : Request : " + propertySearchCriteriaResource);
        // dealing with 400s
        // TODO custom validation to insure that conversion will pass
        if (result.hasErrors()) {
            return ExceptionHandlerUtil.handleException(HttpStatus.BAD_REQUEST, result, null);
        }
        try {
            PropertySearchCriteria searchCriteria = conversionService.convert(propertySearchCriteriaResource,
                    PropertySearchCriteria.class);
            List<GeoResult<PropertyUnit>> results = pangoDomainService.searchForProperties(searchCriteria, null);
            TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(GeoResult.class));
            TypeDescriptor targetType = TypeDescriptor.collection(List.class,
                    TypeDescriptor.valueOf(PropertyResource.class));
            List<PropertyResource> target = (List<PropertyResource>) conversionService.convert(results, sourceType,
                    targetType);
            return ResponseEntity.ok(target);
        } catch (Exception ex) {
            return ExceptionHandlerUtil.handleException(HttpStatus.INTERNAL_SERVER_ERROR, null, ex);
        }
    }

    /**
     * This endpoint will create a new property. This property will be in an unverified status till the coordinator
     * verifies it.
     * 
     * @param propertyDetailResource
     * @return
     */
    @RequestMapping(value = "/properties", method = RequestMethod.POST)
    public ResponseEntity<?> createProperty(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @Valid @RequestBody PropertyDetailResource propertyDetailResource) {
        logger.info("createProperty : Header Params : " + userToken + " : " + userReferenceId);
        logger.info("createProperty : Request Params : " + propertyDetailResource);
        PropertyUnit propertyUnit = conversionService.convert(propertyDetailResource, PropertyUnit.class);
        logger.info("Converted Property Unit : " + propertyUnit);
        pangoDomainService.createProperty(propertyUnit, null);
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
        List<GeoResult<PropertyUnit>> results = new ArrayList<>();
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(GeoResult.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(PropertyResource.class));
        List<PropertyResource> target = (List<PropertyResource>) conversionService.convert(results, sourceType,
                targetType);

        return ResponseEntity.ok(target);
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
        List<GeoResult<PropertyUnit>> results = new ArrayList<>();
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(GeoResult.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(PropertyResource.class));
        List<PropertyResource> target = (List<PropertyResource>) conversionService.convert(results, sourceType,
                targetType);
        return ResponseEntity.ok(target);
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
        try {
            User user = new User();
            PropertyDetailResource propertyDetailResource = null;
            user.setUserReferenceId(userReferenceId);
            Optional<PropertyUnit> propertyUnit = pangoDomainService.retrievePropertyBy(propertyReferenceId, user);
            if (propertyUnit.isPresent()) {
                propertyDetailResource = conversionService.convert(propertyUnit.get(), PropertyDetailResource.class);
                return ResponseEntity.ok(propertyDetailResource);
            } else {
                logger.debug(String.format("Property with propertyReferenceId : %s not found. ", propertyReferenceId));
                return new ResponseEntity<>(
                        String.format("Property with propertyReferenceId : %s not found. ", propertyReferenceId),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ExceptionHandlerUtil.handleException(HttpStatus.INTERNAL_SERVER_ERROR, null, ex);
        }
    }

    /**
     * This Property endpoint updates a specific Pango rental property
     * 
     * @param userToken
     * @param userReferenceId
     * @param propertyReferenceId
     * @param propertyDetailResource
     * @return
     */
    @RequestMapping(value = "/properties/{propertyReferenceId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePropertyUnit(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @PathVariable String propertyReferenceId,
            @Valid @RequestBody PropertyDetailResource propertyDetailResource) {
        logger.info(
                "updatePropertyUnit : Request : " + userToken + " : " + userReferenceId + " : " + propertyReferenceId);
        PropertyUnit propertyUnit = conversionService.convert(propertyDetailResource, PropertyUnit.class);
        logger.info("Converted Property Unit : " + propertyUnit);
        return ResponseEntity.ok("Ok, property updated successfully");
    }

    /**
     * This Property endpoint updates a specific Pango rental property unit with the rental details
     * 
     * @param userToken
     * @param userReferenceId
     * @param propertyReferenceId
     * @param userRentResource
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
