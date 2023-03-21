package icu.fyi.data.dao.token

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.time.LocalDateTime

@MappedEntity
data class RefreshToken(
    @field:Id @GeneratedValue
    val id: Long? = null,
    var email: String,
    var token: String,
    var revoked: Boolean,
    var dateCreated: LocalDateTime = LocalDateTime.now()
)