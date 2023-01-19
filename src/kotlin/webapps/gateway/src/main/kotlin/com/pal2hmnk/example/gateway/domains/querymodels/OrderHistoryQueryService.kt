package com.pal2hmnk.example.gateway.domains.querymodels

import reactor.core.publisher.Mono

interface OrderHistoryQueryService {
    fun findBy(name: String): Mono<OrderHistory>
}
