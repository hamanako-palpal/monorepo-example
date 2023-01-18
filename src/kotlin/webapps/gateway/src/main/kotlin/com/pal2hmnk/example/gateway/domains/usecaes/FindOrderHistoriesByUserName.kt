package com.pal2hmnk.example.gateway.domains.usecaes

import com.pal2hmnk.example.gateway.domains.querymodels.OrderHistory

interface FindOrderHistoriesByUserName {
    fun exec(input: OrderHistoryInputData): OrderHistoryOutputData
}

class OrderHistoryInputData(
    name: String,
    token: String,
)

class OrderHistoryOutputData(
    val orderHistory: OrderHistory
)
