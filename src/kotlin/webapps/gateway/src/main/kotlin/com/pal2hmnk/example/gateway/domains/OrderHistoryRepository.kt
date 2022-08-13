package com.pal2hmnk.example.gateway.domains

interface OrderHistoryRepository {
    suspend fun findBy(name: String): OrderHistory
}
