package com.ceitechs.service.apis.rest.resources;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

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
public class LoginResource {

    @NotNull
    @Email (message = "Valid email address is required for a userName")
    @Size(max = 50)
    @JsonProperty("userName")
    private String emailAddress;

    @NotNull
    @Size(max = 50)
    @JsonProperty("password")
    private String password;
}
