package com.pal2hmnk.example.permissions.domains.entities

import com.pal2hmnk.example.permissions.domains.values.UserId
import java.time.LocalDateTime

class AccessToken(
    override val userId: UserId,
    override val clientId: String,
    override val audience: List<String>,
    override val created: LocalDateTime = LocalDateTime.now(),
    override val expired: LocalDateTime = LocalDateTime.now().plusDays(30),
) : Token() {
    fun getKey() = "at_$jti"
}
