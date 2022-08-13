package com.pal2hmnk.example.gateway.domains

import java.time.LocalDate

class OrderHistory(
    val user: User,
    val orders: List<Order>
)

class Order(
    val shop: Shop,
    val ordered: LocalDate,
)
