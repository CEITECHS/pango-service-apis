package com.ceitechs.service.apis.rest.resources;

import com.ceitechs.domain.service.domain.Address;
import com.ceitechs.domain.service.service.AttachmentProjection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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

    private AttachmentProjection profilePicture;

    private Address address;

}
