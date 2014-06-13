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
@ActiveProfiles('dev')
@WebAppConfiguration
class DevSocialAuthFactoryTest extends Specification {

    @Autowired
    SocialAuthConfigFactory socialAuthConfigFactory

    def "should inject config factory"() {
        expect:
        socialAuthConfigFactory instanceof DevSocialAuthFactory
    }

    def "should load properties"() {
        when:
        def config = socialAuthConfigFactory.createConfig()

        then:
        config.getProviderConfig('googleplus')._consumerKey == 'cons_key'
    }
}
