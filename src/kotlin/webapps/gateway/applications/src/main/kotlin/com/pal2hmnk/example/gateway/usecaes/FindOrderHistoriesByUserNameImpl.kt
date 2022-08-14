package com.pal2hmnk.example.gateway.usecaes

import com.pal2hmnk.example.gateway.domains.querymodels.OrderHistoryQueryService
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class FindOrderHistoriesByUserNameImpl(
    private val orderHistoryQueryService: OrderHistoryQueryService,
) : FindOrderHistoriesByUserName {
    override fun exec(params: String): OrderHistoryOutputData = runBlocking {
        OrderHistoryOutputData(orderHistoryQueryService.findBy(params))
    }
}
