package com.ceitechs.service.apis.converters.request;

import org.springframework.core.convert.converter.Converter;

import com.ceitechs.domain.service.domain.UserPreference;
import com.ceitechs.domain.service.domain.UserPreference.PreferenceCategory;
import com.ceitechs.domain.service.domain.UserPreference.PreferenceType;
import com.ceitechs.domain.service.util.PangoUtility;
import com.ceitechs.service.apis.rest.resources.UserPreferenceResource;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class UserPreferenceResourceToUserPreference implements Converter<UserPreferenceResource, UserPreference> {

    @Override
    public UserPreference convert(UserPreferenceResource userPreferenceResource) {
        UserPreference userPreference = new UserPreference();
        userPreference.setPreferenceId(userPreferenceResource.getPreferenceId());
        userPreference.setUserSearchHistory(userPreferenceResource.getUserSearchHistory());
        userPreference.setFromDate(PangoUtility.getLocalDateDateFrom(userPreferenceResource.getFromDate()).get());
        userPreference.setToDate(PangoUtility.getLocalDateDateFrom(userPreferenceResource.getToDate()).get());
        userPreference.setPreferenceType(PreferenceType.valueOf(userPreferenceResource.getPreferenceType()));
        userPreference.setCategory(PreferenceCategory.valueOf(userPreferenceResource.getCategory()));
        userPreference.setActive(userPreferenceResource.isActive());
        userPreference.setSendNotification(userPreferenceResource.isSendNotification());
        return userPreference;
    }
}
