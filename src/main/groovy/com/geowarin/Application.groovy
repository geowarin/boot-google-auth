package com.geowarin

import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 *
 * Date: 05/06/2014
 * Time: 04:56
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@EnableAutoConfiguration
@CompileStatic
@ComponentScan
@Controller('/')
class Application {

    static void main(String[] args) {
        SpringApplication.run(Application, args)
    }

    @RequestMapping
    String home() {
        'messages/home'
    }
}
