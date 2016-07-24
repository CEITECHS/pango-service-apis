package com.ceitechs.service.apis.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileResource {

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "newPassword")
    private String newPassword;

    @JsonProperty(value = "confirmPassword")
    private String confirmPassword;

    @JsonProperty(value = "profilePic")
    private String profilePic;
}
