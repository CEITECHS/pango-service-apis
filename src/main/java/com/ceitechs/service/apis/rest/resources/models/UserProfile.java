package com.ceitechs.service.apis.rest.resources.models;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe
 * @since 0.1
 */
@Getter
@Setter
public class UserProfile {
    private String password;
    private String profilePicture;
    private boolean verified;
    private String verificationCode;
    private LocalDate verificationDate;
    private double customerRating;
    private LocalDate createdDate;
    private List<UserPreference> preferences; //TODO Do you need this here?
}
