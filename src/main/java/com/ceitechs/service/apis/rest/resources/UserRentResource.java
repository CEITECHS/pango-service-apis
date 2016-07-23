package com.ceitechs.service.apis.rest.resources;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.ceitechs.domain.service.domain.Address;

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
public class UserRentResource {

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Email
    private String emailAddress;

    @NotNull
    private Address address;
}
