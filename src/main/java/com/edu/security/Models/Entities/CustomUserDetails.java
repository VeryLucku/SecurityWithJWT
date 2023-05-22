package com.edu.security.Models.Entities;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntityData;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + userEntityData.getRole().name()));
    }

    @Override
    public String getPassword() {
        return userEntityData.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntityData.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return userEntityData.isEnabled();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}
