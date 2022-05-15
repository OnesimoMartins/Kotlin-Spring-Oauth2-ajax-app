package onesimo.martisn.BookServer.api.controller

import onesimo.martisn.BookServer.domain.service.BookService
import onesimo.martisn.BookServer.domain.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user/books")
class UserBooksController(val userService: UserService,val bookService: BookService){

    @GetMapping
    private fun getAllBooks(authentication: Authentication)=
         userService.findByEmailOrFail(authentication.name).books

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private fun removeBook(@PathVariable bookId:Int,authentication: Authentication)
    =bookService.removeBookFromShelf(authentication.name,bookId)
}