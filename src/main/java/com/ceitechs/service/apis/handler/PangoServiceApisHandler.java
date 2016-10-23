package com.ceitechs.service.apis.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ceitechs.service.apis.exception.FileUploadException;
import com.ceitechs.service.apis.exception.UserAlreadyExistsException;
import com.ceitechs.service.apis.rest.resources.PangoErrorResponse;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
//TODO : Issue not firing. see ExceptionHandlerUtil - workaround
//@ControllerAdvice
//@RestController
public class PangoServiceApisHandler{
    //xtends ResponseEntityExceptionHandler

    /**
     * @param uaee
     * @return
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException uaee) {
        PangoErrorResponse errorResponse = new PangoErrorResponse(HttpStatus.CONFLICT.getReasonPhrase(),"USER_EXISTS_ERROR",HttpStatus.CONFLICT.value());
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
        PangoErrorResponse errorResponse =  new PangoErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), "VALIDATION_ERROR", HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp((new Date()).getTime());
        errorResponse.setDetail(fue.getMessage());
        errorResponse.setDeveloperMessage("Error while uploading the picture");
        errorResponse.addErrorMessage(fue.getMessage());
        //TODO log this exception
        return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
    }

    /**
     * 
     * @param manve
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleInputValidationError(MethodArgumentNotValidException manve) {
        PangoErrorResponse errorResponse = new PangoErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), "VALIDATION_ERROR", HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimeStamp((new Date()).getTime());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setDetail(manve.getMessage());
        errorResponse.setDeveloperMessage("Input request validation failed.");
        manve.getBindingResult().getAllErrors().forEach(e -> errorResponse.addErrorMessage(e.getDefaultMessage()));
//        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
////        for (FieldError fieldError : fieldErrors) {
////            List<PangoValidationError> validationErrorList = errorResponse.getErrors().get(fieldError.getField());
////            if (validationErrorList == null) {
////                validationErrorList = new ArrayLis t<PangoValidationError>();
////                errorResponse.getErrors().put(fieldError.getField(), validationErrorList);
////            }
////            PangoValidationError validationError = new PangoValidationError();
////            validationError.setErrorCode(fieldError.getCode());
////            validationError.setErrorMessage(fieldError.getDefaultMessage());
////            validationErrorList.add(validationError);
////        }
        return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
    }
}
