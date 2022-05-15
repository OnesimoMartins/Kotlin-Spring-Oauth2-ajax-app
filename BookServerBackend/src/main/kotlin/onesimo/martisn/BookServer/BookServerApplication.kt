package onesimo.martisn.BookServer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class BookServerApplication
fun main() {
		runApplication<BookServerApplication>()

	val encoder=BCryptPasswordEncoder()

	println("${encoder.encode("cabenda")}\n${encoder.encode("123")} \n ${encoder.encode("lorecas")}")

	}


