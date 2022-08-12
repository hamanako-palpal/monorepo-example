package com.pal2hmnk.example.gateway.domains

interface UserRepository {
    suspend fun findUserInfo(name: String): User
}
