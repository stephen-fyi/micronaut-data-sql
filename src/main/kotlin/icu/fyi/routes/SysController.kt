package icu.fyi.routes

import icu.fyi.data.dto.res.ApiRes
import io.micronaut.http.MediaType.APPLICATION_JSON
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/sys")
class SysController {

    @Produces(APPLICATION_JSON)
    @Get
    fun index(): ApiRes = ApiRes(message = "System Running")

}