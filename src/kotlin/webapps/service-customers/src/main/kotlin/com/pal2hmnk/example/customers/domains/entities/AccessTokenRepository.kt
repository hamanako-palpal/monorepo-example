package com.pal2hmnk.example.customers.domains.entities

interface AccessTokenRepository {
    fun save(stuff: Stuff): AccessToken
}
