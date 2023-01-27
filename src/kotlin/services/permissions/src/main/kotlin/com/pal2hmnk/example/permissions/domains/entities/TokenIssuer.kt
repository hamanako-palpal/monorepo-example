package com.pal2hmnk.example.permissions.domains.entities

interface TokenIssuer<in T : Token> {
    fun issue(token: T): String
}
