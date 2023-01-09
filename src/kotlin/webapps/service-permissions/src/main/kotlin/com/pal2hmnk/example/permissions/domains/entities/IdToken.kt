package com.pal2hmnk.example.permissions.domains.entities

import com.pal2hmnk.example.permissions.domains.values.Role
import com.pal2hmnk.example.permissions.domains.values.ShopId
import com.pal2hmnk.example.permissions.domains.values.UserId
import java.time.LocalDateTime

class IdToken(
    override val userId: UserId,
    val stuffInfo: Pair<ShopId?, Role?>,
    override val clientId: String,
    override val audience: List<String>,
    override val created: LocalDateTime = LocalDateTime.now(),
    override val expired: LocalDateTime = LocalDateTime.now().plusDays(30),
) : Token() {
    fun getKey() = "it_${userId.value}"
}
