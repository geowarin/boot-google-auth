package com.geowarin.security

import groovy.transform.CompileStatic
import org.brickred.socialauth.SocialAuthConfig
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

/**
 * Created by gewarin on 12/06/2014.
 */
public interface SocialAuthConfigFactory {
    SocialAuthConfig createConfig()
}

@Profile('dev')
@Component
@CompileStatic
class DevSocialAuthFactory implements  SocialAuthConfigFactory {

    @Override
    SocialAuthConfig createConfig() {
        def socialAuthConfig = new SocialAuthConfig()
        socialAuthConfig.load('auth.properties')
        socialAuthConfig
    }
}

@Profile('heroku')
@Component
@CompileStatic
class HerokuSocialAuthFactory implements  SocialAuthConfigFactory {

    @Override
    SocialAuthConfig createConfig() {
        def socialAuthConfig = new SocialAuthConfig()
        socialAuthConfig.load(System.properties)
        socialAuthConfig
    }
}