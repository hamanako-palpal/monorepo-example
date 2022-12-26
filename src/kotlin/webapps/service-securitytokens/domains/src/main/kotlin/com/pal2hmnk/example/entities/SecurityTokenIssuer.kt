package com.pal2hmnk.example.entities

interface SecurityTokenIssuer {
    fun issue(securityToken: SecurityToken): String
}
