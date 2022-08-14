package com.pal2hmnk.example.gateway.domains.entities

import java.time.LocalDate

class Order(
    val shop: Shop,
    val ordered: LocalDate,
)
