package com.pal2hmnk.example.contracts.usecases

import com.pal2hmnk.example.contracts.domains.entities.Order
import com.pal2hmnk.example.contracts.domains.values.UserId
import com.pal2hmnk.example.shared.usecases.Scenario

interface FindOrderHistory : Scenario<OrderHistoryOutputData, UserId>

class OrderHistoryOutputData(
    val orderHistory: List<Order>,
)
