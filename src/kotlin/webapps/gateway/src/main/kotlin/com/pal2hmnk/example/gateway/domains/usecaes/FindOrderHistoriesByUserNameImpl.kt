package com.pal2hmnk.example.gateway.domains.usecaes

import com.pal2hmnk.example.gateway.domains.querymodels.OrderHistoryQueryService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class FindOrderHistoriesByUserNameImpl(
    private val orderHistoryQueryService: OrderHistoryQueryService,
) : FindOrderHistoriesByUserName {
    override fun exec(input: OrderHistoryInputData): Mono<OrderHistoryOutputData> =
        orderHistoryQueryService.findBy(input.name)
            .map { OrderHistoryOutputData(it) }
}
