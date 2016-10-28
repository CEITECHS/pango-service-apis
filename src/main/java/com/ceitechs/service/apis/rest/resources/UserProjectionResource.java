package com.ceitechs.service.apis.rest.resources;

import com.ceitechs.domain.service.domain.Address;
import com.ceitechs.domain.service.domain.Attachment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author iddymagohe on 10/27/16.
 */
@Getter
@Setter
public class UserProjectionResource {
    private String userReferenceId;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailAddress;

    private String fullName;

    @JsonIgnore
    private int customerRating;

    private String joinDate;

    private Attachment profilePicture;

    private Address address;

}
