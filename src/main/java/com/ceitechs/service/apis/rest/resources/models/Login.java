package com.ceitechs.service.apis.rest.resources.models;

import javax.validation.constraints.NotNull;

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
public class Login {

    @NotNull
    @Email
    @JsonProperty("userName")
    private String emailAddress;

    @NotNull
    @JsonProperty("password")
    private String password;
}
