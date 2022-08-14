package com.pal2hmnk.example.contracts.usecases

import com.pal2hmnk.example.contracts.domains.entities.OrderRepository
import com.pal2hmnk.example.contracts.domains.values.UserId

class FindOrderHistoryImpl(
    private val repo: OrderRepository,
) : FindOrderHistory {
    override fun exec(userId: UserId): OrderHistoryOutputData {
        return OrderHistoryOutputData(repo.findOrderHistoryBy(userId))
    }
}