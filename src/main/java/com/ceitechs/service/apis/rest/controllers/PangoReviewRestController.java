package com.ceitechs.service.apis.rest.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceitechs.domain.service.domain.Review;
import com.ceitechs.service.apis.rest.resources.ReviewResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@RestController
public class PangoReviewRestController {

    private static Logger logger = LoggerFactory.getLogger(PangoReviewRestController.class);

    @Autowired
    private ConversionService conversionService;

    /**
     * This endpoint will create a new review for the given Pango rental by an owner or a customer as specified in the
     * 'by' query parameter
     * 
     * @param userToken
     * @param userReferenceId
     * @param by
     * @param rentalReferenceId
     * @param reviewResource
     * @return
     */
    @RequestMapping(value = "/rentals/{rentalReferenceId}/reviews", method = RequestMethod.POST)
    public ResponseEntity<?> createReview(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @RequestParam String by, @PathVariable String rentalReferenceId,
            @Valid @RequestBody ReviewResource reviewResource) {
        logger.info("createReview : Request : " + userToken + " : " + userReferenceId + " : " + rentalReferenceId);
        Review review = conversionService.convert(reviewResource, Review.class);
        logger.info("Converted Review : " + review);
        return new ResponseEntity<>("Ok, successfully created a new review", HttpStatus.CREATED);
    }

    /**
     * This endpoint will return a list of reviews for a given property or for a customer as specified in the 'by' query
     * parameter and the referenceId in the path parameter
     * 
     * @param userToken
     * @param userReferenceId
     * @param by
     * @param referenceId
     * @return
     */
    @RequestMapping(value = "/reviews/by/{referenceId}", method = RequestMethod.GET)
    public ResponseEntity<?> getReviews(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @RequestParam String by, @PathVariable String referenceId) {
        logger.info("getReviews : Request : " + userToken + " : " + userReferenceId + " : " + referenceId);
        List<Review> reviewList = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
            Review review = new Review();
            reviewList.add(review);
        });
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Review.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ReviewResource.class));
        List<ReviewResource> target = (List<ReviewResource>) conversionService.convert(reviewList, sourceType,
                targetType);
        return ResponseEntity.ok(target);
    }

    /**
     * This endpoint will update a specific review as specified in the path parameter
     * 
     * @param userToken
     * @param userReferenceId
     * @param reviewId
     * @param reviewResource
     * @return
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateReview(@RequestHeader(value = "user-token") String userToken,
            @RequestHeader String userReferenceId, @PathVariable String reviewId,
            @Valid @RequestBody ReviewResource reviewResource) {
        logger.info("updateReview : Request : " + userToken + " : " + userReferenceId + " : " + reviewId);
        Review review = conversionService.convert(reviewResource, Review.class);
        return ResponseEntity.ok("Ok, the review was updated successfully");
    }
}
