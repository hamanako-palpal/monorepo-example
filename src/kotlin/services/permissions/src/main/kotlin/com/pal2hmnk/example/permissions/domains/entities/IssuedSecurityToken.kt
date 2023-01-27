package com.pal2hmnk.example.permissions.domains.entities

data class IssuedSecurityToken(
    val accessToken: String,
    val idToken: String,
    val refreshToken: String,
)
