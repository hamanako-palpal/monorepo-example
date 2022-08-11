package com.pal2hmnk.example.shop.usecases

import com.pal2hmnk.example.domain.shop.entities.OrderHistory
import com.pal2hmnk.example.shared.usecases.Scenario

interface FindOrderHistory: Scenario<OrderHistoryOutputData, Int>

class OrderHistoryOutputData(val value: List<OrderHistory>)
