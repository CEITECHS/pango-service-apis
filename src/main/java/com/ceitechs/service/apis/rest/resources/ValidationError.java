package com.ceitechs.service.apis.rest.resources;

import lombok.Getter;
import lombok.Setter;

/**
 * <code>ValidationError</code>
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@Getter
@Setter
public class ValidationError {

    private String errorCode;

    private String errorMessage;
}
