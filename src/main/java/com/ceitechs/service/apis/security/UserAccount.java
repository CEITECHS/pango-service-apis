package com.ceitechs.service.apis.security;

import com.ceitechs.domain.service.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author iddymagohe on 10/29/16.
 * @since 1.0
 */
public class UserAccount implements UserDetails {

    private final User account;

    public UserAccount(User account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return account.getProfile().getRoles().stream().map(String::valueOf)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return account.getProfile().getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.getProfile().isVerified();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return account.getProfile().isVerified();
    }

    @Override
    public boolean isEnabled() {
        return account.getProfile().isVerified();
    }
}
