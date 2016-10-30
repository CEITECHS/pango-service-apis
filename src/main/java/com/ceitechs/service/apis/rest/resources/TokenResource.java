package com.ceitechs.service.apis.rest.resources;

import com.ceitechs.service.apis.security.PangoUserDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author iddymagohe on 10/30/16.
 */

@Getter
@Setter
public class TokenResource {
    private String firstName;
    private String lastName;
    private String userReferenceId;
    @JsonProperty("user-token")
    private String userToken;

    public TokenResource(PangoUserDetails userDetails, String userToken) {
        this.userToken = userToken;
        firstName = userDetails.getFirstName();
        lastName = userDetails.getLastName();
        userReferenceId = userDetails.getUserReferenceId();
    }
}
