package onesimo.martisn.BookServer.api.dto.request

import onesimo.martisn.BookServer.domain.model.Credentials
import onesimo.martisn.BookServer.domain.model.User

 data class UserInput(val name:String, val email:String, val password:String){
  fun  toUser(): User {
    val credentials=Credentials(email,password)
    return User( credentials = credentials, name = name)

  }
}