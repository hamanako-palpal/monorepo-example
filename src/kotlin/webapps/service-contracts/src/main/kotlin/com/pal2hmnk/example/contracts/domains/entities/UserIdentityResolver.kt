package com.pal2hmnk.example.contracts.domains.entities

interface UserIdentityResolver {
    fun resolve(token: String): UserIdentity
}
