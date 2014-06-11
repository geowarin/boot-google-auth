package com.geowarin.security

import groovy.transform.CompileStatic
import org.brickred.socialauth.AuthProvider
import org.brickred.socialauth.Profile
import org.brickred.socialauth.spring.bean.SocialAuthTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.web.filter.GenericFilterBean

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 *
 * Date: 05/06/2014
 * Time: 19:52
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@CompileStatic
class AuthenticationFilter extends GenericFilterBean {

    @Autowired
    private SocialAuthTemplate socialAuthTemplate

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            SecurityContext contextBeforeChainExecution = createSecurityContext();
            SecurityContextHolder.context = contextBeforeChainExecution;
            filterChain.doFilter(request, response);

        } finally {
            SecurityContextHolder.clearContext(); // Clear the context and free the thread local
        }
    }

    private SecurityContext createSecurityContext() {

        AuthProvider authProvider = socialAuthTemplate.socialAuthManager?.currentAuthProvider
        if (!authProvider)
            return SecurityContextHolder.createEmptyContext();

        Authentication authentication = createAuthentication(authProvider.userProfile, authProvider)
        return new SecurityContextImpl(authentication: authentication)
    }

    private static Authentication createAuthentication(Profile profile, AuthProvider authProvider) {
        def authentication =
                new PreAuthenticatedAuthenticationToken(
                        profile.fullName,
                        authProvider.accessGrant.providerId,
                        [new SimpleGrantedAuthority('USER')])
        authentication.details = profile
        return authentication
    }
}
