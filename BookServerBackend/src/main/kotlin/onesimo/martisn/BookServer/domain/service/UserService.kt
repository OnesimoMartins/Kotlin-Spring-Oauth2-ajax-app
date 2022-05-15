package onesimo.martisn.BookServer.domain.service

import onesimo.martisn.BookServer.domain.exception.UserNotFoundException
import onesimo.martisn.BookServer.domain.model.User
import onesimo.martisn.BookServer.domain.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService( val users: UserRepository){

    fun findByEmailOrFail(email:String): User =users.findUserByEmail(email).orElseThrow {
        UsernameNotFoundException("there is not user with '$email' email")
    }

    fun findByIdOrFail(userId:Int)=users.findById(userId).orElseThrow{
        UserNotFoundException("user with id $userId does no exist")//TODO rever essa exception, contructor
    }

   fun findNonAdminUsers(): List<User> {
        users.findNoAdministratorUsers().forEach {
            println(it)
        }
        return users.findNoAdministratorUsers()
    }
    fun deleteUserById(id:Int) =users.deleteById(id);
}