package icu.fyi.data.dao.role

import icu.fyi.data.dao.user.User
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@MappedEntity
data class Role(
    @field:Id @GeneratedValue
    val id: Long? = null,
    val name: String,
    @Relation(Relation.Kind.MANY_TO_MANY)
    val users: Set<User> = emptySet()
)