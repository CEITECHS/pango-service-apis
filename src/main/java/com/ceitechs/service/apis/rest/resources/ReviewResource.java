package com.ceitechs.service.apis.rest.resources;

import java.time.Clock;
import java.time.LocalDateTime;

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
public class ReviewResource {
    private String reviewId;

    @NotNull
    private String reviewText;

    @NotNull
    private String reviewedBy;

    private String reviewedDate = LocalDateTime.now(Clock.systemUTC()).toString();

    private boolean recommend;

    private double rating;

    private String userReferenceId;

    private String propertyReferenceId;
}
