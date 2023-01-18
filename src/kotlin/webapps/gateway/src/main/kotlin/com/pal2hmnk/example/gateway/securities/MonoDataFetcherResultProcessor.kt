package com.pal2hmnk.example.gateway.securities

import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.internal.DataFetcherResultProcessor
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class MonoDataFetcherResultProcessor : DataFetcherResultProcessor {
    override fun supportsType(originalResult: Any): Boolean {
        return originalResult is Mono<*>
    }

    override fun process(originalResult: Any, dfe: DgsDataFetchingEnvironment): Any {
        if (originalResult is Mono<*>) {
            val context = ReactiveSecurityContextHolder
                .withAuthentication(
                    (dfe.getDgsContext().customContext as SecurityContext).authentication
                )
            return originalResult.contextWrite(context).toFuture()
        } else {
            throw IllegalArgumentException("Instance passed to ${this::class.qualifiedName} was not a Mono<*>. It was a ${originalResult::class.qualifiedName} instead")
        }
    }
}
