package com.ceitechs.service.apis.converters.request;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.User;
import com.ceitechs.domain.service.domain.UserProfile;
import com.ceitechs.service.apis.rest.resources.UserResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class UserResourceToUser implements Converter<UserResource, User> {

    @Override
    public User convert(UserResource userResource) {
        User user = new User();
        user.setUserReferenceId(userResource.getUserReferenceId());
        user.setEmailAddress(userResource.getEmailAddress());
        user.setFirstName(userResource.getFirstName());
        user.setLastName(userResource.getLastName());
        user.setPhoneNumber(userResource.getPhoneNumber());
        // Create a new User Profile
        UserProfile userProfile = new UserProfile();
        userProfile.setCreatedDate(LocalDate.now());
        userProfile.setPassword(userResource.getPassword());
        userProfile.setVerified(false);
        userProfile.setRoles(Arrays.asList(userResource.getRole()));
        user.setProfile(userProfile);
        user.setAddress(userResource.getAddress());
        return user;
    }
}
