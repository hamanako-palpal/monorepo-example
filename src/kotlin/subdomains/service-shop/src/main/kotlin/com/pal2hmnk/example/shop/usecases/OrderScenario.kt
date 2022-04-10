package com.pal2hmnk.example.shop.usecases

import com.pal2hmnk.example.shop.domains.OrderHistory
import com.pal2hmnk.example.shop.domains.UserId

interface OrderScenario {
    fun findOrderHistory(id: UserId): List<OrderHistory>
}
