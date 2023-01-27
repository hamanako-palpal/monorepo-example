package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.ShopId
import com.pal2hmnk.example.customers.domains.values.UserId

data class Staff(
    val userId: UserId,
    val shopId: ShopId? = null,
    private val role: String? = null,
) {
    fun getRole() = role ?: "guest"
}
