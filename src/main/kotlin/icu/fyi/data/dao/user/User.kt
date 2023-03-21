package icu.fyi.data.dao.user

import icu.fyi.data.dao.role.Role
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import io.micronaut.data.jdbc.annotation.JoinColumn
import io.micronaut.data.jdbc.annotation.JoinTable

@MappedEntity
data class User(
    @field:Id @GeneratedValue
    val id: Long? = null,
    val username: String,
    val name: String,
    val email: String,
    val password: String,
    val salt: String,
    @Relation(Relation.Kind.MANY_TO_MANY)
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableSet<Role> = mutableSetOf()
)
