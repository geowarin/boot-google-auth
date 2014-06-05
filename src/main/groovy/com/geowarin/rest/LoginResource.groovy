package com.geowarin.rest

import groovy.transform.CompileStatic
import org.brickred.socialauth.AuthProvider
import org.brickred.socialauth.Profile
import org.brickred.socialauth.spring.bean.SocialAuthTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * Date: 05/06/2014
 * Time: 21:14
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@RestController
@CompileStatic
class LoginResource {

    @Autowired
    private SocialAuthTemplate socialAuthTemplate

    @RequestMapping('/authDenied')
    String authDenied() {
        'authDenied'
    }

    @RequestMapping('/authSuccess')
    Profile authSuccess() {
        AuthProvider provider = socialAuthTemplate.socialAuthManager?.currentAuthProvider
        return provider ? provider.userProfile : null
    }
}
