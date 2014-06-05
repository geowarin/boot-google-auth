package com.geowarin

import groovy.transform.CompileStatic
import org.brickred.socialauth.AuthProvider
import org.brickred.socialauth.Profile
import org.brickred.socialauth.spring.bean.SocialAuthTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * Date: 05/06/2014
 * Time: 04:56
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@EnableAutoConfiguration
@RestController
@Import(OAuthConfig)
@CompileStatic
@ComponentScan()
class Application {

    static void main(String[] args) {
        SpringApplication.run(Application, args)
    }

    @RequestMapping
    String home() {
        'Welcome'
    }

    @RequestMapping('/authDenied')
    String authDenied() {
        'authDenied'
    }

    @Autowired
    private SocialAuthTemplate socialAuthTemplate

    @RequestMapping('/authSuccess')
    Profile authSuccess() {
        AuthProvider provider = socialAuthTemplate.socialAuthManager?.currentAuthProvider
        return provider ? provider.userProfile : null
    }

    @RequestMapping('/protected/lol')
    Profile protectedLol() {
        SecurityContextHolder.context.authentication.details as Profile
    }

}
