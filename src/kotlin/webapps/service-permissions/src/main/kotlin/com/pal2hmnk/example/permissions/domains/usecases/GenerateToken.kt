package com.pal2hmnk.example.permissions.domains.usecases

import com.pal2hmnk.example.permissions.domains.entities.SecurityToken

interface GenerateToken {
    fun exec(securityToken: SecurityToken): String
}
