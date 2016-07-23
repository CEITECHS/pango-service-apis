package com.ceitechs.service.apis.rest.resources.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhisheksingh
 * @since 0.1
 */
@Getter
@Setter
@ToString
public class User {
    private String userReferenceId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailAddress;

    private Address address;

    private UserProfile profile;

    private List<UserPreference> preferences; //TODO do you need this here?
}
