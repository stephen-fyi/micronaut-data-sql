package icu.fyi.data.dto.req

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ResetReq(
    val email: String
)