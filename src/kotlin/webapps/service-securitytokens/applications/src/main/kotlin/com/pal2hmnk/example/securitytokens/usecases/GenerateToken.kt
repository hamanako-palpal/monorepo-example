package com.pal2hmnk.example.securitytokens.usecases

import com.pal2hmnk.example.entities.SecurityToken

interface GenerateToken {
    fun exec(securityToken: SecurityToken): String
}
