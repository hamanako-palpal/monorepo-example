package com.pal2hmnk.example.shop.usecases

import com.pal2hmnk.example.shop.domains.OrderHistory
import com.pal2hmnk.example.shop.domains.OrderHistoryRepository
import com.pal2hmnk.example.shop.domains.UserId

class OrderInteractor(
    private val repository: OrderHistoryRepository,
): OrderScenario {
    override fun findOrderHistory(id: UserId): List<OrderHistory> =
        repository.findBy(id)
}
