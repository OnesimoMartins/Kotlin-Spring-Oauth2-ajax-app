package onesimo.martisn.BookServer.core.security

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

@Configuration
class CorsConfig{

    @Bean
    fun corsFilterConfig(): FilterRegistrationBean<CorsFilter> {

        val corsConfig=CorsConfiguration()
        corsConfig.allowedOriginPatterns= listOf("*")
        corsConfig.allowedMethods= listOf("*")
        corsConfig.allowedHeaders= listOf("*")
        corsConfig.allowCredentials=true

        val sourceConfig=UrlBasedCorsConfigurationSource()
        sourceConfig.registerCorsConfiguration("/**",corsConfig)

        val bean=FilterRegistrationBean<CorsFilter>()
        bean.filter= CorsFilter(sourceConfig)
        bean.order= Ordered.HIGHEST_PRECEDENCE
        return bean
    }
}