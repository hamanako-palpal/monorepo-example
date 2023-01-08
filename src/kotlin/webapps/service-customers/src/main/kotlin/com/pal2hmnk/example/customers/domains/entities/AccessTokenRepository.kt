package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.UserId

interface AccessTokenRepository {
    fun save(userId: UserId): AccessToken
    fun save(stuff: Stuff): AccessToken
}
