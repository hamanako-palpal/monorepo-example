package com.pal2hmnk.example.user.domains

interface UserRepository {
    fun findBy(name: Name): User
}
