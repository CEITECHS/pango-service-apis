package com.ceitechs.service.apis.rest.controllers;

import com.ceitechs.domain.service.domain.Attachment;
import com.ceitechs.domain.service.domain.User;
import com.ceitechs.domain.service.service.AttachmentProjection;
import com.ceitechs.domain.service.service.PangoDomainService;
import com.ceitechs.service.apis.handler.ExceptionHandlerUtil;
import com.ceitechs.service.apis.rest.resources.AttachmentResource;
import com.ceitechs.service.apis.security.PangoUserDetails;
import com.ceitechs.service.apis.security.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author iddymagohe on 12/18/16.
 */
@RestController
@RequestMapping(value = "/attachments")
public class AttachmentRestController {

    private static Logger logger = LoggerFactory.getLogger(AttachmentRestController.class);

    @Autowired
    private ConversionService conversionService;

    @Autowired
    PangoDomainService domainService;

    @RequestMapping(value = "/{attachmentParentReferenceId}", method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> handleAttachmentUploads(@RequestHeader(value = "user-token") String userToken,
                                                     @RequestPart(name = "attachment") MultipartFile file,
                                                     @Valid AttachmentResource attachmentResource, BindingResult result) {
        if (file == null)
            result.addError(new ObjectError("attachment", "Attachment can not be null or empty"));
        if (file.getSize() / 1024 > 1024)
            result.addError(new ObjectError("attachment", "Attachment size can not be greater than 1024 KB"));

        if (result.hasErrors())
            return ExceptionHandlerUtil.handleException(HttpStatus.BAD_REQUEST, result, null);

        try {
            attachmentResource.setAttachment(file);
            String userName = TokenUtils.getUserNameFromToken(userToken);
            User user = domainService.retrieveVerifiedUserByUsername(userName);
            Attachment attachment = conversionService.convert(attachmentResource, Attachment.class);
            Optional<Attachment> attachmentOptional = domainService.saveAttachment(user, attachment);
            return ResponseEntity.ok((AttachmentProjection) attachmentOptional.get());

        } catch (Exception ex) {
            return ExceptionHandlerUtil.handleException(ex instanceof  IllegalArgumentException ? HttpStatus.BAD_REQUEST: HttpStatus.INTERNAL_SERVER_ERROR, null, new Exception(ex.getMessage(), ex.getCause()));
        }

    }


    @RequestMapping(value = "/{attachmentParentReferenceId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAttachment(@RequestHeader(value = "user-token") String userToken,@PathVariable(name = "attachmentParentReferenceId") String attachmentReferenceId) {

        try {
            String userName = TokenUtils.getUserNameFromToken(userToken);
            User user = domainService.retrieveVerifiedUserByUsername(userName);
            Optional<Attachment> attachmentOptional = domainService.deleteAttachment(user,attachmentReferenceId);
            return ResponseEntity.ok((AttachmentProjection) attachmentOptional.get());

        } catch (Exception ex) {
            return ExceptionHandlerUtil.handleException(ex instanceof  IllegalArgumentException ? HttpStatus.BAD_REQUEST:HttpStatus.INTERNAL_SERVER_ERROR, null, new Exception(ex.getMessage(), ex.getCause()));
        }

    }
}
