package com.pal2hmnk.example.gateway.securities

import com.pal2hmnk.example.gateway.domains.entities.IdTokenRepository
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GatewayAuthenticationManager(
    private val idTokenRepository: IdTokenRepository
) : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val authToken = authentication.credentials.toString()
        return Mono.just(idTokenRepository.get(authToken))
            .filter { it.isValid }
            .switchIfEmpty(Mono.empty())
            .map { GatewayIdentifiedToken(it) }
    }
}
