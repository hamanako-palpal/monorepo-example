package com.pal2hmnk.example.gateway.domains.usecaes

import com.pal2hmnk.example.gateway.domains.querymodels.OrderHistoryQueryService
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class FindOrderHistoriesByUserNameImpl(
    private val orderHistoryQueryService: OrderHistoryQueryService,
) : FindOrderHistoriesByUserName {
    override fun exec(name: String): OrderHistoryOutputData = runBlocking {
        OrderHistoryOutputData(orderHistoryQueryService.findBy(name))
    }
}
