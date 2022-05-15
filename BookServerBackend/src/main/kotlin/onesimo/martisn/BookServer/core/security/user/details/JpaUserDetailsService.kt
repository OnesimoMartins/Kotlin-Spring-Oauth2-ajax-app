package onesimo.martisn.BookServer.core.security.user.details

import onesimo.martisn.BookServer.domain.model.Authoritie
import onesimo.martisn.BookServer.domain.service.UserService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class JpaUserDetailsService(val users:UserService):UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        users.findByEmailOrFail(username).let {
          return AuthUser(it.credentials,authoritiesToGrantedAuthorities(it.authorities!!))
        }
    }

    private fun authoritiesToGrantedAuthorities(authorities:Set<Authoritie>):List<GrantedAuthority>{
        val grantedAuthorities=ArrayList<GrantedAuthority>()
        authorities.forEach {
            grantedAuthorities.add(SimpleGrantedAuthority(it.name.uppercase()))
        }
        return grantedAuthorities
    }

}