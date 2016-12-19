package com.ceitechs.service.apis.rest.controllers;

import com.ceitechs.service.apis.rest.resources.AttachmentResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleAttachmentUploads(@RequestHeader(value = "user-token") String userToken,
                                                     @RequestHeader String userReferenceId,
                                                     AttachmentResource attachmentResource,
                                                     @RequestPart(name = "attachment") MultipartFile file) throws Exception {
        try {


            attachmentResource.setAttachment(file);
            logger.info("size: - KB " + attachmentResource.getAttachment().getSize()/1024);
            logger.info("type : -" + attachmentResource.getAttachment().getContentType());
            logger.info("name : -" + attachmentResource.getAttachment().getName());
            logger.info("Original : -" + attachmentResource.getAttachment().getOriginalFilename());

            return ResponseEntity.ok(attachmentResource);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        logger.info("request received ...");
        return ResponseEntity.ok(attachmentResource);
    }
}
