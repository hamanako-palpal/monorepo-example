package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.Permission
import com.pal2hmnk.example.customers.domains.values.ShopId
import com.pal2hmnk.example.customers.domains.values.UserId

class UserIdentity(
    val userId: UserId,
    val shopId: ShopId?,
    val permissions: List<Permission>,
    val isValid: Boolean,
)
