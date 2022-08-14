package com.pal2hmnk.example.contracts.usecases

import com.pal2hmnk.example.contracts.domains.entities.Order
import com.pal2hmnk.example.contracts.domains.values.UserId

interface FindOrderHistory {
    fun exec(userId: UserId): OrderHistoryOutputData
}

class OrderHistoryOutputData(
    val orderHistory: List<Order>,
)
