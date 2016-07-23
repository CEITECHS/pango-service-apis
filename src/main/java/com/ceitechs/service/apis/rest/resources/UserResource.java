package com.ceitechs.service.apis.rest.resources;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.ceitechs.domain.service.domain.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResource {

    @JsonProperty("firstName")
    @NotNull
    private String firstName;

    @JsonProperty("lastName")
    @NotNull
    private String lastName;

    @JsonProperty("phoneNumber")
    @NotNull
    private String phoneNumber;

    @JsonProperty("password")
    @NotNull
    private String password;

    @JsonProperty("emailAddress")
    @NotNull
    @Email
    private String emailAddress;

    @JsonProperty("address")
    @NotNull
    private Address address;
}
