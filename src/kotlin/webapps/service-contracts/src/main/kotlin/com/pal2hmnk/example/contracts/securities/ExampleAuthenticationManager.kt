package com.pal2hmnk.example.contracts.securities

import com.pal2hmnk.example.contracts.domains.entities.UserIdentityRepository
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ExampleAuthenticationManager(
    private val userIdentityRepository: UserIdentityRepository,
) : ReactiveAuthenticationManager {
    override fun authenticate(
        authentication: Authentication
    ): Mono<Authentication> {
        val authToken = authentication.credentials.toString()
        return Mono.just(userIdentityRepository.getUserIdentity(authToken))
            .filter { it.isValid }
            .switchIfEmpty(Mono.empty())
            .map {
                ExampleIdentifiedToken(it)
            }
    }
}
