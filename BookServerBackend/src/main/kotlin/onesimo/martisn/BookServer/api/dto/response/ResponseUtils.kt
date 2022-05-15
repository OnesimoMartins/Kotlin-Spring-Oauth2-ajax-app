package onesimo.martisn.BookServer.api.dto.response

import onesimo.martisn.BookServer.domain.model.User
import java.util.ArrayList

fun userToUserResponse(user: User): UserResponse =UserResponse(user.id!!,user.name!!,user.books!!.size)

fun usersToResponseUsers(users:List<User>): ArrayList<UserResponse> {
    val usersResponse= ArrayList<UserResponse>()
     users.forEach { usersResponse.add( userToUserResponse(it)) }
return usersResponse
}