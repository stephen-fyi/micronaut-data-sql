package icu.fyi.data.dto.res

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class SignInReq(
    val email: String,
    val password: String
)
