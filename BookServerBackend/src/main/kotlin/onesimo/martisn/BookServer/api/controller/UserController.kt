package onesimo.martisn.BookServer.api.controller

import onesimo.martisn.BookServer.api.dto.request.UserInput
import onesimo.martisn.BookServer.api.dto.response.usersToResponseUsers
import onesimo.martisn.BookServer.domain.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(val userService: UserService){

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getAll()= usersToResponseUsers( userService.findNonAdminUsers())


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody newUser: UserInput){

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id:Int){

    }


}