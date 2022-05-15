package onesimo.martisn.BookServer.domain.service

import onesimo.martisn.BookServer.domain.exception.BookNotFoundException
import onesimo.martisn.BookServer.domain.model.Book
import onesimo.martisn.BookServer.domain.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class BookService(val userService: UserService) {
    @Autowired lateinit var books:BookRepository

    fun getBookByIdOrFail(id:Int)=books.findById(id).orElseThrow{
        BookNotFoundException("no book with id $id was found.")
    }

    @Transactional
    fun removeBookFromShelf(username:String,bookId:Int)=
        userService.findByEmailOrFail(username).books!!.remove(getBookByIdOrFail(bookId))

    @Transactional
    fun addBookOnShelf(username:String, book: Book)=
        userService.findByEmailOrFail(username).books!!.add(book)
}