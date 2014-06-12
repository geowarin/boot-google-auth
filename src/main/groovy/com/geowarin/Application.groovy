package com.geowarin

import com.geowarin.mvc.MvcConfig
import com.geowarin.rest.RestConfig
import com.geowarin.security.SecurityConfig
import groovy.transform.CompileStatic
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.Import

/**
 *
 * Date: 05/06/2014
 * Time: 04:56
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@EnableAutoConfiguration
@CompileStatic
@Import([SecurityConfig, RestConfig, MvcConfig])
class Application {

    static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(Application)
                .profiles(onHeroku() ? 'heroku' : 'dev')
                .run(args)
    }

    static boolean onHeroku() {
        System.getProperty('DYNO')
    }
}
