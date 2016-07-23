package com.ceitechs.service.apis.rest.resources;

import java.util.List;

import com.ceitechs.service.apis.rest.resources.models.UserPreferenceRequest;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@Getter
@Setter
public class UserPreferenceResponse {

    private String developerText;

    private List<UserPreferenceRequest> userPreferences;
}
