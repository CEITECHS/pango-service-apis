package com.ceitechs.service.apis.rest.resources;

import com.ceitechs.domain.service.domain.UserSearchHistory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe
 * @since 0.1
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPreferenceResource {

    private String preferenceId;

    @JsonProperty("preferenceType")
    private String preferenceType;

    @JsonProperty("fromDate")
    private String fromDate;

    @JsonProperty("toDate")
    private String toDate;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("sendNotification")
    private boolean sendNotification;

    @JsonProperty("category")
    private String category;
    
    @JsonProperty("userSearchHistory")
    private UserSearchHistory userSearchHistory;
}
