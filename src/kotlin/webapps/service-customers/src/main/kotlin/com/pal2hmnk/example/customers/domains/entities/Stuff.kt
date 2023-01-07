package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.ShopId

data class Stuff(
    val user: User,
    val shopId: ShopId,
    val role: String,
)
