package com.pal2hmnk.example.gateway.domains.querymodels

import com.pal2hmnk.example.gateway.domains.entities.Order
import com.pal2hmnk.example.gateway.domains.entities.User

class OrderHistory(
    val user: User,
    val orders: List<Order>
)
