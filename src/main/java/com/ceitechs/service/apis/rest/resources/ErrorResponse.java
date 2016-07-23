package com.ceitechs.service.apis.rest.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * <code>ErrorResponse</code> - Error response to be sent to the client in case of any errors
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@Getter
@Setter
public class ErrorResponse {

    private String title;

    private int status;

    private String detail;

    private long timeStamp;

    private String developerMessage;

    private Map<String, List<ValidationError>> errors = new HashMap<>();
}
