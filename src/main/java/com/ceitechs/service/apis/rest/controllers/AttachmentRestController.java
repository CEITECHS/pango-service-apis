package com.ceitechs.service.apis.rest.controllers;

import com.ceitechs.service.apis.rest.resources.AttachmentResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author iddymagohe on 12/18/16.
 */
@RestController
@RequestMapping(value = "/attachments")
public class AttachmentRestController {

    private static Logger logger = LoggerFactory.getLogger(AttachmentRestController.class);

    @Autowired
    private ConversionService conversionService;

    @RequestMapping(value = "/{attachmentReferenceId}", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleAttachmentUploads(@RequestHeader(value = "user-token") String userToken,
                                                     @RequestHeader String userReferenceId,
                                                     @PathVariable String attachmentReferenceId,
                                                     @RequestBody MultiValueMap params) {
        try {

            AttachmentResource attachmentResource = conversionService.convert(params, AttachmentResource.class);

            return ResponseEntity.ok(attachmentResource);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        logger.info("request received ...");
        return ResponseEntity.ok(params);
    }
}
