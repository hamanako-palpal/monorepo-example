package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.ShopId
import com.pal2hmnk.example.customers.domains.values.UserId

data class Stuff(
    val userId: UserId,
    val shopId: ShopId? = null,
    val role: String? = null,
)
