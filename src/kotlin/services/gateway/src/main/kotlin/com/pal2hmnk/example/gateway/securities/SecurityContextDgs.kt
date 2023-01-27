package com.pal2hmnk.example.gateway.securities

import com.netflix.graphql.dgs.reactive.DgsReactiveCustomContextBuilderWithRequest
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import reactor.core.publisher.Mono

@Component
class SecurityContextDgs : DgsReactiveCustomContextBuilderWithRequest<SecurityContext> {
    override fun build(
        extensions: Map<String, Any>?,
        headers: HttpHeaders?,
        serverRequest: ServerRequest?
    ): Mono<SecurityContext> {
        return ReactiveSecurityContextHolder.getContext()
    }
}
