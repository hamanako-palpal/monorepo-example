package com.pal2hmnk.example.contracts.domains.entities

interface UserIdentityRepository {
    fun getUserIdentity(token: String): UserIdentity
}
