package com.pal2hmnk.example.gateway.domains.usecaes

import com.pal2hmnk.example.gateway.domains.querymodels.OrderHistory
import reactor.core.publisher.Mono

interface FindOrderHistoriesByUserName {
    fun exec(input: OrderHistoryInputData): Mono<OrderHistoryOutputData>
}

data class OrderHistoryInputData(
    val name: String,
)

class OrderHistoryOutputData(
    val orderHistory: OrderHistory
)
