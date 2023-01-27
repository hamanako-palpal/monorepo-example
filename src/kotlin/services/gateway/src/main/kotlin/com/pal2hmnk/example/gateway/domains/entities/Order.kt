package com.pal2hmnk.example.gateway.domains.entities

import java.time.LocalDateTime

class Order(
    val shop: Shop,
    val ordered: LocalDateTime,
)
