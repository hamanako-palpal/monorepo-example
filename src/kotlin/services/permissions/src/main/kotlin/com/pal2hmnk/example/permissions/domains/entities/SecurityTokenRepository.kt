package com.pal2hmnk.example.permissions.domains.entities

interface SecurityTokenRepository {
    fun store(securityToken: SecurityToken, issuedSecurityToken: IssuedSecurityToken)
}
