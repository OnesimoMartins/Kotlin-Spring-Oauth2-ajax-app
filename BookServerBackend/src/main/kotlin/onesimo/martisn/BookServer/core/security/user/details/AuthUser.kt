package onesimo.martisn.BookServer.core.security.user.details

import lombok.Getter
import onesimo.martisn.BookServer.domain.model.Credentials
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User;
@Getter
class AuthUser:User{
    constructor(credentials: Credentials,authorities:List<GrantedAuthority> ):
    super( credentials.email,credentials.password, authorities)
}