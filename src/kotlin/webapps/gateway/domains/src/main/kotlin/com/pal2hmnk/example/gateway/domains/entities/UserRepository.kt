package com.pal2hmnk.example.gateway.domains.entities

interface UserRepository {
    suspend fun findUserInfo(name: String): User
}
