package com.geowarin.rest

import groovy.transform.CompileStatic
import org.brickred.socialauth.Profile
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * Date: 05/06/2014
 * Time: 21:15
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@RequestMapping('/protected')
@RestController
@CompileStatic
class ProtectedResource {

    @RequestMapping('/stuff')
    Profile protectedStuff() {
        SecurityContextHolder.context.authentication.details as Profile
    }
}
