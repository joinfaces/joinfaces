package com.github.persapiens.jsfboot.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring security configuration for tests
 */
@Configuration
@ConditionalOnWebApplication
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter 
{
}
