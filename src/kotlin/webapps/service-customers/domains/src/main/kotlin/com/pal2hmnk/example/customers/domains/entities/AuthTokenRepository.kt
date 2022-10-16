package com.pal2hmnk.example.customers.domains.entities

interface AuthTokenRepository {
    fun createToken(user : User) : AuthToken
}
