package com.pal2hmnk.example.customers.domains.entities

import java.time.LocalDateTime

class AuthToken(
    val userId: Int,
    val expired: LocalDateTime,
    val jwtId: String,
    val token: String,
)
