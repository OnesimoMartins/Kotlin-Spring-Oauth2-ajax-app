package onesimo.martisn.BookServer.core.security.resoureServer

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

@EnableResourceServer
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ResourceServer :ResourceServerConfigurerAdapter(){

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST,"/users").permitAll()
            .anyRequest().authenticated()
    }
}