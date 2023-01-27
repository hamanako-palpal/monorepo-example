package com.pal2hmnk.example.permissions.infrastructures.persistance.exposed

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object RefreshTokens : Table("refresh_tokens") {
    val jti = RefreshTokens.varchar("jti", 255)
    val userId = RefreshTokens.integer("user_id")
    val clientId = RefreshTokens.varchar("client_id", 255)
    val expired = RefreshTokens.datetime("expired")
}
