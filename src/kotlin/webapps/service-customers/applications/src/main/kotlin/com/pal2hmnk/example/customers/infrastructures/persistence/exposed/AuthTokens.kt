package com.pal2hmnk.example.customers.infrastructures.persistence.exposed

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object AuthTokens : Table("auth_tokens") {
    val userId = AuthTokens.integer("user_id")
    val jwtId = AuthTokens.varchar("jwt_id", 255)
    val ordered = AuthTokens.datetime("ordered")
}
