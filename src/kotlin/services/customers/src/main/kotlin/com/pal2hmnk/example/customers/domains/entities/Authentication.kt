package com.pal2hmnk.example.customers.domains.entities

data class Authentication(
    val accessToken: IssuedToken,
    val refreshToken: IssuedToken,
)
