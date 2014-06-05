package com.geowarin

import groovy.transform.CompileStatic
import org.brickred.socialauth.SocialAuthConfig
import org.brickred.socialauth.spring.bean.SocialAuthTemplate
import org.brickred.socialauth.spring.bean.SpringSocialAuthManager
import org.brickred.socialauth.spring.controller.SocialAuthWebController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode

/**
 *
 * Date: 05/06/2014
 * Time: 05:07
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@Configuration
@CompileStatic
class OAuthConfig {

    @Bean
    SocialAuthConfig socialAuthConfig() {
        def socialAuthConfig = new SocialAuthConfig()
        socialAuthConfig.load('auth.properties')
        socialAuthConfig
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    SpringSocialAuthManager socialAuthManager() {
        new SpringSocialAuthManager()
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    SocialAuthTemplate socialAuthTemplate() {
        new SocialAuthTemplate()
    }

    @Bean
    SocialAuthWebController socialAuthWebController() {
        new SocialAuthWebController('http://guarded-hollows-2080.herokuapp.com', 'authSuccess', 'authDenied')
    }
}
