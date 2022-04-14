package com.pal2hmnk.example.shop.usecases

import com.pal2hmnk.example.shared.usecases.Scenario
import com.pal2hmnk.example.shop.domains.OrderHistory

interface FindOrderHistory: Scenario<OutputData, Int>

class OutputData(val value: List<OrderHistory>)
