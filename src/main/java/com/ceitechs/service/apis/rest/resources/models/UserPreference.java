package com.ceitechs.service.apis.rest.resources.models;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe
 * @since 0.1
 */
@Getter
@Setter
public class UserPreference {

    public enum PreferenceType {
        Notification;
    }
    
    public enum PreferenceCategory {
        SEARCH;
    }

    private String preferenceId;
    private PreferenceType preferenceType;
    private String fromDate;
    private String toDate;
    private boolean active;
    private boolean sendNotification;
    private PreferenceCategory category;
    private UserSearchHistory userSearchHistory;
}
