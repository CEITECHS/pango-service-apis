package com.ceitechs.service.apis.rest.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ceitechs.domain.service.util.PangoUtility;
import com.ceitechs.service.apis.exception.FileUploadException;
import com.ceitechs.service.apis.rest.resources.PangoServiceResponse;
import com.ceitechs.service.apis.rest.resources.UserPreferenceRequest;
import com.ceitechs.service.apis.rest.resources.UserPreferenceResponse;
import com.ceitechs.service.apis.rest.resources.UserProfileRequest;
import com.ceitechs.service.apis.rest.resources.UserRequest;
import com.ceitechs.service.apis.rest.resources.models.UserPreference;
import com.ceitechs.service.apis.rest.resources.models.UserPreference.PreferenceCategory;
import com.ceitechs.service.apis.rest.resources.models.UserPreference.PreferenceType;
import com.ceitechs.service.apis.rest.resources.models.UserSearchHistory;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@RestController
public class PangoUserRestController {

    private static Logger logger = LoggerFactory.getLogger(PangoUserRestController.class);

    /**
     * This endpoint will create a new Pango user, an email with verification link will be sent to the registered email
     * address
     * 
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
        logger.info("createUser : Request : " + userRequest);
        PangoServiceResponse response = new PangoServiceResponse();
        response.setDeveloperText("Ok, User registered, sent verification email");
        return ResponseEntity.ok(response);
    }

    /**
     * This endpoint will update an existing Pango User
     * 
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable String userReferenceId,
            @Valid @RequestBody UserRequest userRequest) {
        logger.info("updateUser : Request : " + userRequest);
        PangoServiceResponse response = new PangoServiceResponse();
        response.setDeveloperText("Ok, User updated");
        return ResponseEntity.ok(response);
    }

    /**
     * This endpoint will upload the profile pic an existing Pango User
     * 
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadProfilePic(@PathVariable String userReferenceId,
            @RequestParam("file") MultipartFile file) {
        logger.info("uploadProfilePic : Input File Name : " + file.getOriginalFilename());
        PangoServiceResponse response = new PangoServiceResponse();
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                logger.info("uploadProfilePic : bytes - " + bytes);
                response.setDeveloperText("Profile pic uploaded successfully " + fileName);
            } catch (Exception e) {
                throw new FileUploadException("Error while uploading the profile pic : " + fileName, e.getCause());
            }
        } else {
            throw new FileUploadException(
                    "Error while uploading the profile pic : " + fileName + " because the file is empty.");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This endpoint will update the profile of an existing Pango User
     * 
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/profile", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserProfile(@PathVariable String userReferenceId,
            @Valid @RequestBody UserProfileRequest userProfile) {
        logger.info("uploadProfilePic : User Profile Request : " + userProfile);
        PangoServiceResponse response = new PangoServiceResponse();
        response.setDeveloperText("Ok, User updated");
        return ResponseEntity.ok(response);
    }

    /**
     * This endpoint will create a new preference for an existing Pango user
     * 
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/preferences", method = RequestMethod.POST)
    public ResponseEntity<?> createUserPreference(@PathVariable String userReferenceId,
            @Valid @RequestBody UserPreferenceRequest userPreference) {
        logger.info("createUserPreference : User Preference Request : " + userPreference);
        PangoServiceResponse response = new PangoServiceResponse();
        response.setDeveloperText("Ok, successfully created a new user preference");
        return ResponseEntity.ok(response);
    }

    /**
     * This endpoint will retrieve all the preferences for an existing Pango user
     * 
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/preferences", method = RequestMethod.GET)
    public ResponseEntity<?> getUserPreferences(@PathVariable String userReferenceId) {
        logger.info("getUserPreferences : User Reference Id : " + userReferenceId);
        UserPreferenceResponse response = new UserPreferenceResponse();
        response.setDeveloperText("Ok, successfully retrieved all the preferences");
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
            userPreference.setFromDate(LocalDate.now().toString());
            userPreference.setToDate(LocalDate.now().plusMonths(6).toString());

            // Create a new User Search History
            UserSearchHistory userSearchHistory = new UserSearchHistory();
            userPreference.setUserSearchHistory(userSearchHistory);

            if (response.getUserPreferences() == null) {
                List<UserPreference> userPreferenceList = new ArrayList<>();
                userPreferenceList.add(userPreference);
                response.setUserPreferences(userPreferenceList);
            } else {
                response.getUserPreferences().add(userPreference);
            }
        });
        return ResponseEntity.ok(response);
    }

    /**
     * This endpoint will update an existing preference for an existing Pango user
     * 
     * @param userReferenceId
     * @param preferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/preferences/{preferenceId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserPreference(@PathVariable String userReferenceId,
            @PathVariable String preferenceId, @Valid @RequestBody UserPreferenceRequest userPreference) {
        logger.info(
                "updateUserPreference : User Reference Id : " + userReferenceId + " Preference Id : " + preferenceId);
        logger.info("updateUserPreference : User Preference Request : " + userPreference);
        PangoServiceResponse response = new PangoServiceResponse();
        response.setDeveloperText("Ok, successfully updated the preference");
        return ResponseEntity.ok(response);
    }
}
