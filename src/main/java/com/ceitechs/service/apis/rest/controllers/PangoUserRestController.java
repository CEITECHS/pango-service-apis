package com.ceitechs.service.apis.rest.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ceitechs.service.apis.exception.FileUploadException;
import com.ceitechs.service.apis.rest.resources.PangoServiceResponse;
import com.ceitechs.service.apis.rest.resources.models.UserRequest;

@RestController
public class PangoUserRestController {

    /**
     * This endpoint will create a new Pango user, an email with verification link will be sent to the registered email
     * address
     * 
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
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
    public ResponseEntity<?> updateUser(@Valid @PathVariable String userReferenceId) {
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
        PangoServiceResponse response = new PangoServiceResponse();
        String fileName = file.getOriginalFilename();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
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
    public ResponseEntity<?> updateUserProfile(@PathVariable String userReferenceId) {
        return null;
    }

    /**
     * This endpoint will create a new preference for an existing Pango user
     * 
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/preferences", method = RequestMethod.POST)
    public ResponseEntity<?> createUserPreference(@PathVariable String userReferenceId) {
        return null;
    }

    /**
     * This endpoint will retrieve all the preferences for an existing Pango user
     * 
     * @param userReferenceId
     * @return
     */
    @RequestMapping(value = "/users/{userReferenceId}/preferences", method = RequestMethod.GET)
    public ResponseEntity<?> getUserPreferences(@PathVariable String userReferenceId) {
        return null;
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
            @PathVariable String preferenceId) {
        return null;
    }
}
