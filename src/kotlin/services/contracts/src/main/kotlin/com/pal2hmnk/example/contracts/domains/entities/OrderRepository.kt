package com.pal2hmnk.example.contracts.domains.entities

import com.pal2hmnk.example.contracts.domains.values.UserId

interface OrderRepository {
    fun findOrderHistoryBy(id: UserId): List<Order>
}
