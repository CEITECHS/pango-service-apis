package com.ceitechs.service.apis.rest.resources;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <code>ErrorResponse</code> - Error response to be sent to the client in case of any errors
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class PangoErrorResponse {

    private String title;

    private int status;

    private String detail;

    private long timeStamp;

    private String developerMessage;

    private List<String> errors = new ArrayList<>();

    public void addErrorMessage(String error){
        errors.add(error);
    }

    public PangoErrorResponse(String statusPhrase, String statusLabel, int statusCode) {
        this.title = statusLabel;
        this.detail = statusPhrase;
        this.developerMessage = statusPhrase;
        this.status = statusCode;
        this.timeStamp = System.currentTimeMillis();
    }
}
