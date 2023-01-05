package com.pal2hmnk.example.contracts.securities

import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class ExampleServerSecurityContextRepository(
    private val authenticationManager: ExampleAuthenticationManager,
) : ServerSecurityContextRepository {

    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> =
        Mono.justOrEmpty(
            exchange.request
                .headers
                .getFirst(HttpHeaders.AUTHORIZATION)
        )
            .filter { it.startsWith("Bearer ") }
            .flatMap {
                val token = it.substring(7)
                val auth = ExampleAuthenticationToken(token)
                authenticationManager.authenticate(auth).map(::SecurityContextImpl)
            }
}
