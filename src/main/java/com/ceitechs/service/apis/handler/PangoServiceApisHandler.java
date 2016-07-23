package com.ceitechs.service.apis.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ceitechs.service.apis.exception.FileUploadException;
import com.ceitechs.service.apis.exception.UserAlreadyExistsException;
import com.ceitechs.service.apis.rest.resources.ErrorResponse;
import com.ceitechs.service.apis.rest.resources.ValidationError;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@ControllerAdvice
public class PangoServiceApisHandler {

    /**
     * @param uaee
     * @return
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException uaee) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTitle("User Already Exists.");
        errorResponse.setTimeStamp((new Date()).getTime());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setDetail(uaee.getMessage());
        errorResponse.setDeveloperMessage("User already exists");
        return new ResponseEntity<>(errorResponse, null, HttpStatus.CONFLICT);
    }

    /**
     * 
     * @param fue
     * @return
     */
    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<?> handleFileUploadException(FileUploadException fue) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTitle("Error while uploading the picture");
        errorResponse.setTimeStamp((new Date()).getTime());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDetail(fue.getMessage());
        errorResponse.setDeveloperMessage("Error while uploading the picture");
        return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
    }

    /**
     * 
     * @param manve
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleInputValidationError(MethodArgumentNotValidException manve) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTitle("Input request validation failed.");
        errorResponse.setTimeStamp((new Date()).getTime());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDetail(manve.getMessage());
        errorResponse.setDeveloperMessage("Input request validation failed.");
        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            List<ValidationError> validationErrorList = errorResponse.getErrors().get(fieldError.getField());
            if (validationErrorList == null) {
                validationErrorList = new ArrayList<ValidationError>();
                errorResponse.getErrors().put(fieldError.getField(), validationErrorList);
            }
            ValidationError validationError = new ValidationError();
            validationError.setErrorCode(fieldError.getCode());
            validationError.setErrorMessage(fieldError.getDefaultMessage());
            validationErrorList.add(validationError);
        }
        return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
    }
}
