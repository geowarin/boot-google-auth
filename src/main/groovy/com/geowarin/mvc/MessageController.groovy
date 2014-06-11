package com.geowarin.mvc

import groovy.transform.CompileStatic
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Created by gewarin on 11/06/2014.
 */
@Controller
@CompileStatic
class MessageController {

    @RequestMapping(value = '/sendMessage', method=RequestMethod.POST)
    String sendMessage(String text) {
        'messages/home'
    }
}
