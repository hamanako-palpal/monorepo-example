package com.pal2hmnk.example.gateway.usecaes

import com.pal2hmnk.example.gateway.domains.OrderHistoryRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class FindOrderHistoriesByUserNameImpl(
    private val orderHistoryRepository: OrderHistoryRepository,
) : FindOrderHistoriesByUserName {
    override fun exec(params: String): OrderHistoryOutputData = runBlocking {
        OrderHistoryOutputData(orderHistoryRepository.findBy(params))
    }
}
