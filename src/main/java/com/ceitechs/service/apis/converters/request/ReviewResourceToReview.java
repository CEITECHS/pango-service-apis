package com.ceitechs.service.apis.converters.request;

import java.time.LocalDateTime;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.Review;
import com.ceitechs.service.apis.rest.resources.ReviewResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class ReviewResourceToReview implements Converter<ReviewResource, Review> {

    @Override
    public Review convert(ReviewResource reviewResource) {
        Review review = new Review();
        review.setCreatedDate(LocalDateTime.now());
        review.setPropertyUnitReferenceId(reviewResource.getPropertyReferenceId());
        review.setRating(reviewResource.getRating());
        review.setRecommend(reviewResource.isRecommend());
        review.setReviewedBy(reviewResource.getReviewedBy());
        review.setReviewReferenceId(reviewResource.getReviewId());
        review.setReviewText(reviewResource.getReviewText());
        review.setTenantReferenceId(reviewResource.getUserReferenceId());
        return review;
    }
}
