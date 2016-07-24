package com.ceitechs.service.apis.rest.resources;

import java.time.Clock;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.ceitechs.domain.service.domain.Attachment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class CorrespondenceResource {

    @NotNull
    private String message;

    private Attachment attachment;

    private String correspondenceDate = LocalDateTime.now(Clock.systemUTC()).toString();

    @NotNull
    private String correspondenceType;

    private boolean owner;
}
