package com.ceitechs.service.apis.security;

import com.ceitechs.domain.service.domain.User;
import com.ceitechs.domain.service.service.PangoDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author iddymagohe on 10/29/16.
 * @since 1.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PangoDomainService domainService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User account = domainService.retrieveVerifiedUserByUsername(userId);
        if (account == null) {
            throw new UsernameNotFoundException("Unknown user with name  " + userId);
        }
        return new UserAccount(account);
    }
}
