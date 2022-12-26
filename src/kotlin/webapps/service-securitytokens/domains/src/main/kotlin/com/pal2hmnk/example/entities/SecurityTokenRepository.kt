package com.pal2hmnk.example.entities

interface SecurityTokenRepository {
    fun store(securityToken: SecurityToken, issuedToken: String)
}
