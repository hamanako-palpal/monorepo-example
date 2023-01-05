package com.pal2hmnk.example.contracts.domains.entities

import com.pal2hmnk.example.contracts.domains.values.Permission
import com.pal2hmnk.example.contracts.domains.values.ShopId
import com.pal2hmnk.example.contracts.domains.values.UserId

class UserIdentity(
    val userId: UserId,
    val shopId: ShopId,
    val permissions: List<Permission>,
    val isValid: Boolean,
) {
}
