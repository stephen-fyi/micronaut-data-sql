package icu.fyi.data.dto.res

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class ApiRes(
    val status: Int = 200,
    val message: String = ""
)
