package icu.fyi.data.dao.user

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import jakarta.inject.Singleton

@Singleton
@JdbcRepository(dialect = Dialect.MYSQL)
interface UserRepo : CrudRepository<User, Long> {
    fun findByEmail(email: String): User
}