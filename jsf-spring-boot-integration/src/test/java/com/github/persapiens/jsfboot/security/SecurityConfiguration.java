package com.github.persapiens.jsfboot.security;


import java.util.Arrays;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Spring security configuration for tests
 */
@Configuration
@ConditionalOnWebApplication
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter 
{

   @Override
   protected UserDetailsService userDetailsService() {
      UserDetails user1 = new User("persapiens", "123", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
      UserDetails user2 = new User("nyilmaz", "qwe", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
      return new InMemoryUserDetailsManager(Arrays.asList(user1, user2));
   }
}
