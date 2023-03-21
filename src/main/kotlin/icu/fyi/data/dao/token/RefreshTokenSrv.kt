package icu.fyi.data.dao.token

import io.micronaut.security.authentication.Authentication
import io.micronaut.security.errors.OauthErrorResponseException
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent
import io.micronaut.security.token.refresh.RefreshTokenPersistence
import io.micronaut.security.errors.IssuingAnAccessTokenErrorCode.INVALID_GRANT
import jakarta.inject.Singleton
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.FluxSink

@Singleton
class RefreshTokenSrv(private val refreshTokenRepository: RefreshTokenRepo)
    : RefreshTokenPersistence {

    override fun persistToken(event: RefreshTokenGeneratedEvent?) {
        if (event?.refreshToken != null && event.authentication?.name != null) {
            val payload = event.refreshToken
            refreshTokenRepository.save(
                RefreshToken(
                    email = event.authentication.name,
                    token = payload,
                    revoked = false
                )
            )
        }
    }

    override fun getAuthentication(token: String): Publisher<Authentication> {
        return Flux.create({ emitter: FluxSink<Authentication> ->
            val refreshToken = refreshTokenRepository.findByToken(token)
            if (refreshToken.revoked) {
                emitter.error(OauthErrorResponseException(INVALID_GRANT, "refresh token revoked", null))
            } else {
                emitter.next(Authentication.build(refreshToken.email))
                emitter.complete()
            }
        }, FluxSink.OverflowStrategy.ERROR)
    }
}
