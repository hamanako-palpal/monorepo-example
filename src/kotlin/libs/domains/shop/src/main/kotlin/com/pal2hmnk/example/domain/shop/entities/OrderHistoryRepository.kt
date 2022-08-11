package com.pal2hmnk.example.domain.shop.entities

interface OrderHistoryRepository {
    fun findBy(id: UserId): List<OrderHistory>
}
