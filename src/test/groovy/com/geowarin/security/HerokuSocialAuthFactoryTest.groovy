package com.geowarin.security

import com.geowarin.Application
import org.junit.Rule
import org.junit.contrib.java.lang.system.ProvideSystemProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

/**
 * Created by gewarin on 12/06/2014.
 */
@ContextConfiguration(classes = [Application])
@ActiveProfiles('heroku')
@WebAppConfiguration
class HerokuSocialAuthFactoryTest extends Specification {

    @Autowired
    SocialAuthConfigFactory socialAuthConfigFactory

    @Rule
    public final ProvideSystemProperty myPropertyHasMyValue =
            new ProvideSystemProperty('googleapis.com.consumer_key', 'cons_key')
                    .and('googleapis.com.consumer_secret', 'secret')

    def "should inject config factory"() {
        expect:
        socialAuthConfigFactory instanceof DevSocialAuthFactory
    }

    def "should load porperties"() {
        when:
        def config = socialAuthConfigFactory.createConfig()

        then:
        config.getProviderConfig('googleplus')._consumerKey == 'cons_key'
    }

    def "should inject properties"() {
        when:
        def prop = System.getProperty('googleapis.com.consumer_key')

        then:
        prop == 'cons_key'
    }
}
