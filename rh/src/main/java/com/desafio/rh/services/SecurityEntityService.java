package com.desafio.rh.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.desafio.rh.config.SecurityConstants.FOO_SECURITY_ENTITY;

@Service
public class SecurityEntityService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return FOO_SECURITY_ENTITY;

    }
}
