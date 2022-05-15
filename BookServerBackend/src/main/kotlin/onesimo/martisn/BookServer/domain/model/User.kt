package onesimo.martisn.BookServer.domain.model

import lombok.ToString
import javax.persistence.*

@ToString
@Entity
data class User(@Id @GeneratedValue(strategy=GenerationType.IDENTITY) var id:Int?=0,
                var name:String?="",
                @Embedded var credentials:Credentials,

                @OneToMany(fetch = FetchType.EAGER)
                @JoinTable( name = "user_authorities", joinColumns = [JoinColumn(name = "id_user")]
                , inverseJoinColumns = [JoinColumn(name = "id_authoritie")]
                )
                val authorities: Set<Authoritie>?=null
                ,
                @ManyToMany(fetch = FetchType.LAZY)
                @JoinTable( name = "user_books", joinColumns = [JoinColumn(name = "id_user")],
                    inverseJoinColumns = [JoinColumn(name = "id_book")]
                )
                val books: MutableCollection<Book>?=null
              ){

    companion object{
    }

}

fun User.Companion.userInput(email:String,password:String,name: String):User{
    return  User( name = name, credentials = Credentials(email,password ), authorities =
    hashSetOf(Authoritie(name = "User")), books = mutableListOf(Book(1,"w",2))
    )
}