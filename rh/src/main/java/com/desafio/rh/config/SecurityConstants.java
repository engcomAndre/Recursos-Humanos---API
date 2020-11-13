package com.desafio.rh.config;

import com.desafio.rh.config.security.SecurityEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class SecurityConstants {
    static final String SECRET = "ChavedeSegurancadoTokenJWT";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final long EXPIRATION_TIME = 86400000L;

    public static final SecurityEntity FOO_SECURITY_ENTITY = SecurityEntity.builder()
            .username("admin")
            .password("admin")
            .grantedAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")))
            .build();
}