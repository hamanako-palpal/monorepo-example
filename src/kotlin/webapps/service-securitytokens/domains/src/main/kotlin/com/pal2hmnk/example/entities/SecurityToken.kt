package com.pal2hmnk.example.entities

import com.pal2hmnk.example.values.Role
import com.pal2hmnk.example.values.ShopId
import com.pal2hmnk.example.values.UserId
import java.time.LocalDateTime

class SecurityToken(
    val userId: UserId,
    val stuffInfos: List<Pair<ShopId, Role>>,
) {
    val expired: LocalDateTime = LocalDateTime.now().plusMinutes(30)
    val connectionId: String = generateCode(50)

    companion object {
        private val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        fun generateCode(count: Int): String =
            (1..count)
                .map { charset.random() }
                .joinToString(separator = "")
    }
}
