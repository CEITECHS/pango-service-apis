package com.ceitechs.service.apis.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@RestController
public class PangoEnquiryRestController {

    private static Logger logger = LoggerFactory.getLogger(PangoEnquiryRestController.class);

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
            @RequestHeader String userReferenceId, @PathVariable String propertyReferenceId) {
        logger.info("createEnquiry : Request : " + userToken + " : " + userReferenceId + " : " + propertyReferenceId);
        return new ResponseEntity<>("The request has been fulfilled and resulted in a new resource being created",
                HttpStatus.CREATED);
    }
}
