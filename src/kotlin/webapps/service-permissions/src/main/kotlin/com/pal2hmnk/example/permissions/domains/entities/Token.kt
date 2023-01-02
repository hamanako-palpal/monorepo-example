package com.pal2hmnk.example.permissions.domains.entities

import com.pal2hmnk.example.permissions.domains.values.UserId
import java.time.LocalDateTime
import java.util.UUID

abstract class Token {
    val issuer: String = "permissions"
    val jti: String = UUID.randomUUID().toString()
    abstract val userId: UserId
    abstract val clientId: String
    abstract val audience: List<String>
    abstract val created: LocalDateTime
    abstract val expired: LocalDateTime
}
