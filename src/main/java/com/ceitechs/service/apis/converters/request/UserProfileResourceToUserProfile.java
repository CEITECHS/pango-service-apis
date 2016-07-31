package com.ceitechs.service.apis.converters.request;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.Attachment;
import com.ceitechs.domain.service.domain.UserProfile;
import com.ceitechs.service.apis.rest.resources.UserProfileResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class UserProfileResourceToUserProfile implements Converter<UserProfileResource, UserProfile> {

    @Override
    public UserProfile convert(UserProfileResource userProfileResource) {
        UserProfile userProfile = new UserProfile();
        userProfile.setPassword(userProfileResource.getNewPassword());
        // Create a new Attachment
        Attachment attachment = new Attachment();
        attachment.setProfilePicture(true);
        attachment.setContentBase64(userProfileResource.getProfilePic());
        userProfile.setProfilePicture(attachment);
        return userProfile;
    }
}
