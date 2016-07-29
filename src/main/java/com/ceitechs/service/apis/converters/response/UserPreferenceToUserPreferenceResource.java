package com.ceitechs.service.apis.converters.response;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.UserPreference;
import com.ceitechs.service.apis.rest.resources.UserPreferenceResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class UserPreferenceToUserPreferenceResource implements Converter<UserPreference, UserPreferenceResource> {

    @Override
    public UserPreferenceResource convert(UserPreference userPreference) {
        UserPreferenceResource userPreferenceResource = new UserPreferenceResource();
        userPreferenceResource.setActive(userPreference.isActive());
        userPreferenceResource.setSendNotification(userPreference.isSendNotification());
        userPreferenceResource.setCategory(userPreference.getCategory().name());
        userPreferenceResource.setFromDate(userPreference.getFromDate().toString());
        userPreferenceResource.setToDate(userPreference.getToDate().toString());
        userPreferenceResource.setPreferenceId(userPreference.getPreferenceId());
        userPreferenceResource.setPreferenceType(userPreference.getPreferenceType().name());
        userPreferenceResource.setUserSearchHistory(userPreference.getUserSearchHistory());
        return userPreferenceResource;
    }
}
