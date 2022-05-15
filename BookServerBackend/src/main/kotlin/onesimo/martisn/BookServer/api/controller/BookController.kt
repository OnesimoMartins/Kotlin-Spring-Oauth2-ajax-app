package onesimo.martisn.BookServer.api.controller

import onesimo.martisn.BookServer.domain.model.Book
import onesimo.martisn.BookServer.domain.service.BookService
import org.springframework.web.bind.annotation.*

@RequestMapping("livros")
@RestController
class BookController(val bookService:BookService) {

   // @GetMapping
   // fun  getAllBooks()=bookService.all()

  //  @PostMapping
    //fun adicionarLivro(@RequestBody book:Book)=bookService.add(book)

}