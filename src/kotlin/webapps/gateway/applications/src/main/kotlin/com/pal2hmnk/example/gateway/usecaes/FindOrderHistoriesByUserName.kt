package com.pal2hmnk.example.gateway.usecaes

import com.pal2hmnk.example.gateway.domains.querymodels.OrderHistory

interface FindOrderHistoriesByUserName {
    fun exec(name: String): OrderHistoryOutputData
}

class OrderHistoryOutputData(
    val orderHistory: OrderHistory
)
