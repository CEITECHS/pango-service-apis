package com.ceitechs.service.apis.handler;

import com.ceitechs.service.apis.rest.resources.PangoErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

/**
 * @author iddymagohe on 10/23/16.
 * @since 1.
 */
public class ExceptionHandlerUtil {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerUtil.class);
    //TODO handle more status codes

    public enum ERRORS_TAGS {
        VALIDATION_ERROR,
        INTERNAL_SERVER_ERROR
    }

    /**
     *
     * @param status
     * @param result optional, required for validation like errors
     * @param ex optional, required for server like errors
     * @return
     */
    public static ResponseEntity<?> handleException(HttpStatus status, BindingResult result, Exception ex) {
        PangoErrorResponse errorResponse = null;
        //handles 400
        if (status.equals(HttpStatus.BAD_REQUEST)) {
            errorResponse = new PangoErrorResponse(status.getReasonPhrase(), ERRORS_TAGS.VALIDATION_ERROR.name(), status.value());
            if (result != null && result.hasErrors()) {
                errorResponse.addErrorMessages(result.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.toList())
                );
            }
            logger.debug(errorResponse.toString());
        }

        // handles 500
        if (status.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
            errorResponse = new PangoErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ERRORS_TAGS.INTERNAL_SERVER_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR.value());
            errorResponse.setDeveloperMessage(ex.getMessage());
            logger.error(ex.getMessage(), ex.getCause());
        }
        return ResponseEntity.status(status).body(errorResponse);
    }
}
