package com.pal2hmnk.example.permissions.domains.entities

import com.pal2hmnk.example.permissions.domains.values.Role
import com.pal2hmnk.example.permissions.domains.values.ShopId
import com.pal2hmnk.example.permissions.domains.values.UserId

class SecurityToken(
    userId: UserId,
    stuffInfos: List<Pair<ShopId, Role>>,
    clientId: String,
) {
    val accessToken = AccessToken(userId, clientId, listOf("customers", "contracts"))
    val idToken = IdToken(userId, stuffInfos, clientId, listOf("customers", "contracts"))
    val refreshToken = RefreshToken(userId, clientId, listOf("customers", "contracts"))
}
