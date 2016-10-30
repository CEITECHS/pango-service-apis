package com.ceitechs.service.apis.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by iddymagohe on 10/30/16.
 */
public interface PangoUserDetails extends UserDetails {

    String getFirstName();
    String getLastName();
    String getUserReferenceId();
}
