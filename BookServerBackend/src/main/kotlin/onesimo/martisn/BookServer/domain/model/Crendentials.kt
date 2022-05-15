package onesimo.martisn.BookServer.domain.model

import javax.persistence.Embeddable

@Embeddable
data class Credentials (var email:String?="",var password:String?="" )