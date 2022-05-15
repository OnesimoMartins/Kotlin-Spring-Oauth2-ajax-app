package onesimo.martisn.BookServer.domain.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Book (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)var id:Int,
    var name:String,
    var rating:Byte
    )