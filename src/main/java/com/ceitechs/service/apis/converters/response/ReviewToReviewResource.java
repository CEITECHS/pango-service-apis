package com.ceitechs.service.apis.converters.response;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.Review;
import com.ceitechs.service.apis.rest.resources.ReviewResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class ReviewToReviewResource implements Converter<Review, ReviewResource> {

    @Override
    public ReviewResource convert(Review review) {
        ReviewResource reviewResource = new ReviewResource();
        reviewResource.setPropertyReferenceId(review.getPropertyUnitReferenceId());
        reviewResource.setRating(review.getRating());
        reviewResource.setRecommend(review.isRecommend());
        reviewResource.setReviewedBy(review.getReviewedBy());
        reviewResource.setReviewedDate(review.getCreatedDate().toString());
        reviewResource.setReviewId(review.getReviewReferenceId());
        reviewResource.setReviewText(review.getReviewText());
        reviewResource.setUserReferenceId(review.getTenantReferenceId());
        return reviewResource;
    }
}
