package icu.fyi.data.dao.token

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import jakarta.inject.Singleton


@Singleton
@JdbcRepository(dialect = Dialect.MYSQL)
interface RefreshTokenRepo : CrudRepository<RefreshToken, Long> {
    fun findByToken(token: String): RefreshToken
}
