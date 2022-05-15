package onesimo.martisn.BookServer.core.security.server.authorization


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore

const val CLIENT_ID="client"
const val CLIENT_SECRET="123"

@Configuration
@EnableAuthorizationServer
class AuthorizationServer (
  private val authenticationManager: AuthenticationManager,
  private val userDatailService:UserDetailsService,
  private val passwordEncoder: PasswordEncoder,
  private  val redisConnectionFactory: RedisConnectionFactory

): AuthorizationServerConfigurerAdapter(){

    override fun configure(clients: ClientDetailsServiceConfigurer) {
      clients.inMemory().withClient(CLIENT_ID).secret(passwordEncoder.encode(CLIENT_SECRET))
        .scopes("read","write").authorizedGrantTypes("password"
        ,"refresh_token")
        .accessTokenValiditySeconds(60*10)
        .refreshTokenValiditySeconds((((60*60)*0.10).toInt()))//*30)

    }

  override fun configure(security: AuthorizationServerSecurityConfigurer) {
    security.passwordEncoder(passwordEncoder)
      .checkTokenAccess("isAuthenticated()")
  }

  override fun configure( endpoints: AuthorizationServerEndpointsConfigurer) {
    endpoints.userDetailsService(userDatailService)
      .authenticationManager(authenticationManager)
      .tokenStore(tokenStore())
      .reuseRefreshTokens(false)
  }

  @Bean fun tokenStore( ):TokenStore= RedisTokenStore(redisConnectionFactory)

}