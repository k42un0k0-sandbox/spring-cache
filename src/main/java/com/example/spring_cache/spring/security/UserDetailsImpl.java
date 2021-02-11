package com.example.spring_cache.spring.security;

import com.example.spring_cache.db.AuthorityDto;
import com.example.spring_cache.db.CustomerDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private CustomerDto customerDto;
    private AuthorityDto authorityDto;

    public UserDetailsImpl(CustomerDto customerDto, AuthorityDto authorityDto){
        this.customerDto=customerDto;
        this.authorityDto=authorityDto;
    }
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> arr = new ArrayList<>(1);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authorityDto.getAuthority());
        arr.add(grantedAuthority);
        return arr;
    }

    @Override
    public String getPassword() {
        return customerDto.getPassword();
    }

    @Override
    public String getUsername() {
        return customerDto.getUsername();
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
