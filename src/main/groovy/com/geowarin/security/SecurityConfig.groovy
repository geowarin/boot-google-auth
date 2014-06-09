package com.geowarin.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.logout.LogoutFilter

import javax.servlet.Filter

/**
 * Created by cedric on 11/03/2014.
 */
@Configuration
@Import(OAuthConfig)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                addFilterBefore(authenticationFilter(), LogoutFilter.class).
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().
                authorizeRequests().
                antMatchers(HttpMethod.GET, "/protected/**").hasAuthority("USER").
//                antMatchers(HttpMethod.GET, "/rest/v1/session/logout").authenticated().
//                antMatchers(HttpMethod.DELETE, "/rest/v1/**").hasAuthority("USER").
                anyRequest().permitAll();
    }

    @Bean
    protected Filter authenticationFilter() {
        new AuthenticationFilter()
    }
}