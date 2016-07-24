package com.ceitechs.service.apis.rest.resources;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

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
public class EnquiryResource {

    private String enquiryReferenceId;

    @NotNull
    private String subject;

    @NotNull
    private String introduction;

    @NotNull
    private String message;
    
    private String enquiredOn = LocalDateTime.now().toString();

    @NotNull
    private String enquiredBy;

    @NotNull
    private String enquiryType;

    private String propertyReferenceId;

    private int correspondenceCount;

    private List<CorrespondenceResource> correspondence = Collections.emptyList();
}
