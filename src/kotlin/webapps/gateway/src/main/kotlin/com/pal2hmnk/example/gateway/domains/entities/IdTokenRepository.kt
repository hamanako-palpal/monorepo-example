package com.pal2hmnk.example.gateway.domains.entities

interface IdTokenRepository {
    fun get(authToken: String): IdToken
}
