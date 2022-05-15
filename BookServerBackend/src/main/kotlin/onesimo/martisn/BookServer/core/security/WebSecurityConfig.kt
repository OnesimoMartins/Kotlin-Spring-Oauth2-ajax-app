package onesimo.martisn.BookServer.core.security


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class WebSecurityConfig: WebSecurityConfigurerAdapter(){

    @Bean
    fun passwordEncoder(): PasswordEncoder =BCryptPasswordEncoder()
    @Bean
    fun authenticatonManager(): AuthenticationManager =super.authenticationManagerBean()

}