package com.ceitechs.service.apis.rest.resources;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.ceitechs.domain.service.domain.Address;
import com.ceitechs.domain.service.domain.PangoUserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

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

    private String userReferenceId;

    @JsonProperty("firstName")
    @NotEmpty(message = "firstName is can not be null or empty")
    private String firstName;

    @JsonProperty("lastName")
    @NotEmpty(message = "lastName is can not be null or empty")
    private String lastName;

    @JsonProperty("phoneNumber")
    @NotNull
    private String phoneNumber;

    @JsonProperty("password")
    @NotEmpty(message = "password can not be null or empty")
    private String password;

    @JsonProperty("confirmationPassword")
    @NotEmpty(message = "confirmation password can not be null or empty")
    private String confirmationPassword;

    @JsonProperty("emailAddress")
    @NotNull
    @Email(message = "emailAddress must be a valid email")
    private String emailAddress;


    @JsonProperty("address")
    @NotNull
    private Address address;

    private PangoUserRole role = PangoUserRole.USER;
}
