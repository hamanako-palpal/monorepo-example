package com.pal2hmnk.example.shop.domains

interface OrderHistoryRepository {
    fun findBy(id: UserId): List<OrderHistory>
}
