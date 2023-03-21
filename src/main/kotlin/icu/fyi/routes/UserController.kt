package icu.fyi.routes

import icu.fyi.data.dao.user.User
import icu.fyi.data.dao.user.UserSrv
import icu.fyi.data.dto.req.ResetReq
import icu.fyi.data.dto.res.ApiRes
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import java.security.Principal

@Controller("/user")
class UserController(private val userSrv: UserSrv) {

        @Secured(SecurityRule.IS_ANONYMOUS)
        @Post("/reset")
        @Produces(MediaType.APPLICATION_JSON)
        fun reset(@Body resetReq: ResetReq): ApiRes {
                println("Reset from $resetReq")
                userSrv.reset(resetReq)
                return ApiRes(status = 400, message = "Error with email request, Contact support.")
        }

        @Secured(SecurityRule.IS_AUTHENTICATED)
        @Produces(MediaType.APPLICATION_JSON)
        @Get
        fun user(principal: Principal): User {
                println("Get User $principal")
                return userSrv.getUser(principal.name)
        }
}