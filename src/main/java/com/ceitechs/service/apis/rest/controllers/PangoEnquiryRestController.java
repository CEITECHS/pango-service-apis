package com.ceitechs.service.apis.rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceitechs.domain.service.domain.CorrespondenceType;
import com.ceitechs.domain.service.domain.EnquiryCorrespondence;
import com.ceitechs.domain.service.domain.PropertyUnit;
import com.ceitechs.domain.service.domain.PropertyUnitEnquiry;
import com.ceitechs.domain.service.domain.User;
import com.ceitechs.service.apis.rest.resources.CorrespondenceResource;
import com.ceitechs.service.apis.rest.resources.EnquiryResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@RestController
public class PangoEnquiryRestController {

    private static Logger logger = LoggerFactory.getLogger(PangoEnquiryRestController.class);

    @Autowired
    private ConversionService conversionService;

    /**
     * This Enquiries endpoint will create a new Enquiry about a Pango based property, that will subsequently notify the
     * landlords about this new intent about their properties
     * 
     * @param userToken
     * @param userReferenceId
     * @param propertyReferenceId
     * @return
     */
    @RequestMapping(value = "/properties/{propertyReferenceId}/enquiries", method = RequestMethod.POST)
    public ResponseEntity<?> createEnquiry(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @PathVariable String propertyReferenceId,
            @Valid @RequestBody EnquiryResource enquiryResource) {
        logger.info("createEnquiry : Request : " + userToken + " : " + userReferenceId + " : " + propertyReferenceId);
        logger.info("createEnquiry : Request : " + enquiryResource);
        PropertyUnitEnquiry propertyUnitEnquiry = conversionService.convert(enquiryResource, PropertyUnitEnquiry.class);
        logger.info("converted property unit enquiry : " + propertyUnitEnquiry);
        return new ResponseEntity<>("The request has been fulfilled and resulted in a new resource being created",
                HttpStatus.CREATED);
    }

    /**
     * This endpoint will return information about enquiries to this property by the current user(potential-tenant)
     * default or All the enquiries made by many potential tenants to this property primarily for the landlord to
     * access.
     * 
     * @param userToken
     * @param userReferenceId
     * @param propertyReferenceId
     * @param landlordIndicator
     * @return
     */
    @RequestMapping(value = "/properties/{propertyReferenceId}/enquiries", method = RequestMethod.GET)
    public ResponseEntity<?> getPropertyEnquiries(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @PathVariable String propertyReferenceId,
            @RequestParam(defaultValue = "false") boolean landlordIndicator) {
        logger.info("getPropertyEnquiries : Request : " + userToken + " : " + userReferenceId + " : "
                + propertyReferenceId + " : " + landlordIndicator);
        List<PropertyUnitEnquiry> results = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
            PropertyUnitEnquiry propertyUnitEnquiry = new PropertyUnitEnquiry();
            PropertyUnit propertyUnit = new PropertyUnit();
            propertyUnit.setPropertyUnitId(propertyReferenceId);
            propertyUnitEnquiry.setPropertyUnit(propertyUnit);
            propertyUnitEnquiry.setProspectiveTenant(new User());
            propertyUnitEnquiry.setEnquiryType(CorrespondenceType.INTERESTED);
            results.add(propertyUnitEnquiry);
        });
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(PropertyUnitEnquiry.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(EnquiryResource.class));
        List<EnquiryResource> target = (List<EnquiryResource>) conversionService.convert(results, sourceType,
                targetType);
        return ResponseEntity.ok(target);
    }

    /**
     * This endpoint will return information about the Enquiries made by the user to different *Pango* properties when
     * accessed by potential tenants or Enquiries to *Pango* properties owned by the user when accessed by landlords.
     * The response include some details about the rental property(grouped by) and enquiries to it, in the proper
     * display order of relevance.
     * 
     * @param userToken
     * @param userReferenceId
     * @param landlordIndicator
     * @return
     */
    @RequestMapping(value = "/properties/enquiries", method = RequestMethod.GET)
    public ResponseEntity<?> getAllEnquiries(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @RequestParam(defaultValue = "false") boolean landlordIndicator) {
        logger.info("getAllEnquiries : Request : " + userToken + " : " + userReferenceId + " : " + landlordIndicator);
        List<PropertyUnitEnquiry> results = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
            PropertyUnitEnquiry propertyUnitEnquiry = new PropertyUnitEnquiry();
            PropertyUnit propertyUnit = new PropertyUnit();
            propertyUnit.setPropertyUnitId("12345");
            propertyUnitEnquiry.setPropertyUnit(propertyUnit);
            propertyUnitEnquiry.setProspectiveTenant(new User());
            propertyUnitEnquiry.setEnquiryType(CorrespondenceType.INTERESTED);
            results.add(propertyUnitEnquiry);
        });
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(PropertyUnitEnquiry.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(EnquiryResource.class));
        List<EnquiryResource> target = (List<EnquiryResource>) conversionService.convert(results, sourceType,
                targetType);
        return ResponseEntity.ok(target);
    }

    /**
     * This endpoint will update an existing Enquiry with correspondence from either the potential tenant or the
     * landlord
     * 
     * @param userToken
     * @param userReferenceId
     * @param enquiryReferenceId
     * @param correspondenceResource
     * @return
     */
    @RequestMapping(value = "/properties/enquiries/{enquiryReferenceId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEnquiry(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @PathVariable String enquiryReferenceId,
            @Valid @RequestBody CorrespondenceResource correspondenceResource) {
        logger.info("updateEnquiry : Request : " + userToken + " : " + userReferenceId + " : " + enquiryReferenceId);
        logger.info("updateEnquiry : Request : " + correspondenceResource);
        EnquiryCorrespondence enquiryCorrespondence = conversionService.convert(correspondenceResource,
                EnquiryCorrespondence.class);
        logger.info("updateEnquiry : converted enquiry correspondence : " + enquiryCorrespondence);
        return ResponseEntity.ok("Ok, Enquiry updated");
    }

    /**
     * This endpoint will return detailed information about the Enquiry and it's associated correspondence(s)
     * 
     * @param userToken
     * @param userReferenceId
     * @param enquiryReferenceId
     * @return
     */
    @RequestMapping(value = "/properties/enquiries/{enquiryReferenceId}", method = RequestMethod.GET)
    public ResponseEntity<?> getEnquiryCorrespondence(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @PathVariable String enquiryReferenceId) {
        logger.info("getEnquiryCorrespondence : Request : " + userToken + " : " + userReferenceId + " : "
                + enquiryReferenceId);
        List<PropertyUnitEnquiry> results = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
            PropertyUnitEnquiry propertyUnitEnquiry = new PropertyUnitEnquiry();
            PropertyUnit propertyUnit = new PropertyUnit();
            propertyUnit.setPropertyUnitId("12345");
            propertyUnitEnquiry.setPropertyUnit(propertyUnit);
            propertyUnitEnquiry.setProspectiveTenant(new User());
            propertyUnitEnquiry.setEnquiryType(CorrespondenceType.INTERESTED);
            results.add(propertyUnitEnquiry);
        });
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(PropertyUnitEnquiry.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(EnquiryResource.class));
        List<EnquiryResource> target = (List<EnquiryResource>) conversionService.convert(results, sourceType,
                targetType);
        return ResponseEntity.ok(target);
    }
}
