package icu.fyi.sec

import icu.fyi.data.dao.user.UserSrv
import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

@Singleton
class JWTAuthenticationProvider(private val userSrv: UserSrv): AuthenticationProvider {
    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>
    ): Publisher<AuthenticationResponse> {
        println("Auth")
        return Flux.create({ emitter: FluxSink<AuthenticationResponse> ->
            if (userSrv.signIn(authenticationRequest.identity.toString(), authenticationRequest.secret.toString())) {
                val user = userSrv.getUser(authenticationRequest.identity.toString())
                //emitter.next(AuthenticationResponse.success(authenticationRequest.identity as String, user.roles.map{r -> r.name}))
                emitter.next(AuthenticationResponse.success(authenticationRequest.identity as String, listOf("ROLE_USER")))
                emitter.complete()
            } else {
                emitter.error(AuthenticationResponse.exception())
            }
        }, FluxSink.OverflowStrategy.ERROR)
    }
}