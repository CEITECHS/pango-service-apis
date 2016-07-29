package com.ceitechs.service.apis.rest.controllers;

import java.time.LocalDate;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceitechs.domain.service.domain.User;
import com.ceitechs.domain.service.domain.UserPreference;
import com.ceitechs.domain.service.domain.UserPreference.PreferenceCategory;
import com.ceitechs.domain.service.domain.UserPreference.PreferenceType;
import com.ceitechs.domain.service.domain.UserProfile;
import com.ceitechs.domain.service.domain.UserSearchHistory;
import com.ceitechs.domain.service.util.PangoUtility;
import com.ceitechs.service.apis.rest.resources.LoginResource;
import com.ceitechs.service.apis.rest.resources.UserPreferenceResource;
import com.ceitechs.service.apis.rest.resources.UserProfileResource;
import com.ceitechs.service.apis.rest.resources.UserResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@RestController
public class PangoUserRestController {

    private static Logger logger = LoggerFactory.getLogger(PangoUserRestController.class);

    @Autowired
    private ConversionService conversionService;

    /**
     * This endpoint will create a new Pango user, an email with verification link will be sent to the registered email
     * address
     * 
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserResource userResource) {
        logger.info("createUser : Request : " + userResource);
        User user = conversionService.convert(userResource, User.class);
        logger.info("Converted User : " + user);
        return new ResponseEntity<>("Ok, User registered, verification email sent", HttpStatus.CREATED);
    }

    /**
     * This endpoint will update an existing Pango User
     * 
     * @param userToken
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@RequestHeader(value = "user-token") String userToken,
            @PathVariable String userReferenceId, @Valid @RequestBody UserResource userResource) {
        logger.info("updateUser : Request : " + userResource);
        User user = conversionService.convert(userResource, User.class);
        logger.info("Converted User : " + user);
        return ResponseEntity.ok("Ok, User updated");
    }

    /**
     * This endpoint will update the password of an existing Pango User
     * 
     * @param userToken
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/changepassword", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserPassword(@RequestHeader(value = "user-token") String userToken,
            @PathVariable String userReferenceId, @Valid @RequestBody UserProfileResource userProfileResource) {
        logger.info("updateUserPassword : User Profile Request : " + userProfileResource);
        UserProfile userProfile = conversionService.convert(userProfileResource, UserProfile.class);
        logger.info("Converted User Profile : " + userProfile);
        return ResponseEntity.ok("Ok, User password updated");
    }

    /**
     * This endpoint will update the profile picture of an existing Pango User
     * 
     * @param userToken
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/changeprofilepic", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserProfilePic(@RequestHeader(value = "user-token") String userToken,
            @PathVariable String userReferenceId, @Valid @RequestBody UserProfileResource userProfileResource) {
        logger.info("updateUserProfilePic : User Profile Request : " + userProfileResource);
        UserProfile userProfile = conversionService.convert(userProfileResource, UserProfile.class);
        logger.info("Converted User Profile : " + userProfile);
        return ResponseEntity.ok("Ok, User profile picture updated");
    }

    /**
     * This endpoint will create a new preference for an existing Pango user
     * 
     * @param userToken
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/preferences", method = RequestMethod.POST)
    public ResponseEntity<?> createUserPreference(@RequestHeader(value = "user-token") String userToken,
            @PathVariable String userReferenceId, @Valid @RequestBody UserPreferenceResource userPreferenceResource) {
        logger.info("createUserPreference : User Preference Request : " + userPreferenceResource);
        UserPreference userPreference = conversionService.convert(userPreferenceResource, UserPreference.class);
        logger.info("Converted User Preference : " + userPreference);
        return new ResponseEntity<>("Ok, successfully created a new user preference", HttpStatus.CREATED);
    }

    /**
     * This endpoint will retrieve all the preferences for an existing Pango user
     * 
     * @param userToken
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/preferences", method = RequestMethod.GET)
    public ResponseEntity<?> getUserPreferences(@RequestHeader(value = "user-token") String userToken,
            @PathVariable String userReferenceId) {
        logger.info("getUserPreferences : User Reference Id : " + userReferenceId);
        List<UserPreference> userPreferenceList = new ArrayList<>();
        IntStream.range(0, 5).forEach(i -> {
            UserPreference userPreference = new UserPreference();
            userPreference.setPreferenceId(PangoUtility.generateIdAsString());
            userPreference.setPreferenceType(PreferenceType.Notification);
            userPreference.setCategory(PreferenceCategory.SEARCH);
            if (i % 2 == 0) {
                userPreference.setSendNotification(true);
                userPreference.setActive(true);
            } else {
                userPreference.setSendNotification(false);
                userPreference.setActive(false);
            }
            userPreference.setFromDate(LocalDate.now());
            userPreference.setToDate(LocalDate.now().plusMonths(6));
            // Create a new User Search History
            UserSearchHistory userSearchHistory = new UserSearchHistory();
            userPreference.setUserSearchHistory(userSearchHistory);
            userPreferenceList.add(userPreference);
        });
        TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(UserPreference.class));
        TypeDescriptor targetType = TypeDescriptor.collection(List.class,
                TypeDescriptor.valueOf(UserPreferenceResource.class));
        List<UserPreferenceResource> target = (List<UserPreferenceResource>) conversionService
                .convert(userPreferenceList, sourceType, targetType);
        return new ResponseEntity<>(target, HttpStatus.OK);
    }

    /**
     * This endpoint will update an existing preference for an existing Pango user
     * 
     * @param userToken
     * @param userReferenceId
     * @param preferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/preferences/{preferenceId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserPreference(@RequestHeader(value = "user-token") String userToken,
            @PathVariable String userReferenceId, @PathVariable String preferenceId,
            @Valid @RequestBody UserPreferenceResource userPreferenceResource) {
        logger.info(
                "updateUserPreference : User Reference Id : " + userReferenceId + " Preference Id : " + preferenceId);
        logger.info("updateUserPreference : User Preference Request : " + userPreferenceResource);
        UserPreference userPreference = conversionService.convert(userPreferenceResource, UserPreference.class);
        logger.info("Converted User Preference : " + userPreference);
        return ResponseEntity.ok("Ok, successfully updated the preference");
    }

    /**
     * This endpoint will authenticate a pango user with supplied emailAddress and password, an expiring access token is
     * returned along side the firstname, lastname as well the userReferenceId
     * 
     * @param login
     * @return
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginResource loginResource) {
        logger.info("authenticate : User Authentication Request : " + loginResource);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("user-token", PangoUtility.generateIdAsString());
        headers.set("userReferenceId", PangoUtility.generateIdAsString());
        headers.set("lastName", "lName");
        headers.set("firstName", "fName");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    /**
     * This endpoint will verify a pango user with supplied access token
     * 
     * @param confirmationToken
     * @return
     */
    @RequestMapping(value = "/verify/confirmAccount", method = RequestMethod.GET)
    public ResponseEntity<?> verifyUser(@RequestParam("confirm-token") String confirmationToken) {
        logger.info("verifyUser : Request Params : " + confirmationToken);
        return ResponseEntity.ok("Ok, User verified.");
    }
}
