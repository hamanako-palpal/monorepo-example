package com.pal2hmnk.example.customers.domains.entities

interface UserIdentityResolver {
    fun resolve(token: String): UserIdentity
}
