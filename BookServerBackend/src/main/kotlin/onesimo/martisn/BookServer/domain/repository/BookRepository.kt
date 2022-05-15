package onesimo.martisn.BookServer.domain.repository


import onesimo.martisn.BookServer.domain.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface BookRepository : JpaRepository<Book,Int>