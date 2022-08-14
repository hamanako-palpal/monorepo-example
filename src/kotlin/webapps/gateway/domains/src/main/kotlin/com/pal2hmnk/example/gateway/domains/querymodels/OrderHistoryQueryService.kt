package com.pal2hmnk.example.gateway.domains.querymodels

interface OrderHistoryQueryService {
    suspend fun findBy(name: String): OrderHistory
}
