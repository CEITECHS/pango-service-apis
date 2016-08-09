package com.ceitechs.service.apis.rest.resources;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.ceitechs.domain.service.domain.PropertyUnit;

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

    private LocalDateTime enquiredOn = LocalDateTime.now();

    @NotNull
    private String enquiredBy;

    @NotNull
    private String enquiryType;

    private PropertyUnit propertyUnit;

    private int correspondenceCount;

    private List<CorrespondenceResource> correspondence = Collections.emptyList();
}
