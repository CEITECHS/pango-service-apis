package com.ceitechs.service.apis.converters.response;

import com.ceitechs.domain.service.service.UserProjection;
import com.ceitechs.service.apis.rest.resources.UserProjectionResource;
import org.springframework.core.convert.converter.Converter;

/**
 * @author iddymagohe on 10/27/16.
 */
public class UserProjectionToProjectionResource implements Converter<UserProjection, UserProjectionResource> {

    @Override
    public UserProjectionResource convert(UserProjection userProjection) {
        UserProjectionResource resource = new UserProjectionResource();
        resource.setUserReferenceId(userProjection.getUserReferenceId());
        resource.setFirstName(userProjection.getFirstName());
        resource.setLastName(userProjection.getLastName());
        resource.setFullName(userProjection.getFullName());
        resource.setPhoneNumber(userProjection.getPhoneNumber());
       // resource.setCustomerRating(userProjection.geC);
        resource.setAddress(userProjection.getAddress());
        resource.setProfilePicture(userProjection.getProfilePicture());
        resource.setEmailAddress(userProjection.getEmailAddress());
        return resource;
    }
}
