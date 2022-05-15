package onesimo.martisn.BookServer.domain.repository

import onesimo.martisn.BookServer.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository:JpaRepository<User,Int>{
   @Query("SELECT * FROM user u  WHERE u.email= :email", nativeQuery = true)
   fun findUserByEmail(@Param("email") email:String): Optional<User>

   @Query("SELECT u.id,u.email,u.name,u.password from user  u \n" +
           " join user_authorities ua on ua.id_user =u.id \n" +
           " GROUP BY ua.id_user\n" +
           " HAVING COUNT(ua.id_user)=1", nativeQuery = true)
   fun findNoAdministratorUsers():List<User>
}