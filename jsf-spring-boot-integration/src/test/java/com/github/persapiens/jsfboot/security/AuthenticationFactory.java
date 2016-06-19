package com.github.persapiens.jsfboot.security;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class AuthenticationFactory {

    private static Collection<? extends GrantedAuthority> grantedAuthorities(String... authorities) {
        Collection<SimpleGrantedAuthority> result = new HashSet<>();
        for (String authority : authorities)
        {
            result.add(new SimpleGrantedAuthority(authority));
        }
        return result;
    }
    
    public static Authentication anonymous(String... authorities) {
        return new AnonymousAuthenticationToken("anonymous", "anonymous", grantedAuthorities(authorities));
    }

    public static Authentication authentication(String... authorities) {
        return new UsernamePasswordAuthenticationToken("user", "user", grantedAuthorities(authorities));
    }

}
