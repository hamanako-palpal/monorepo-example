package com.pal2hmnk.example.permissions.domains.entities

import com.pal2hmnk.example.permissions.domains.entities.SecurityToken

interface SecurityTokenIssuer {
    fun issue(securityToken: SecurityToken): String
}
