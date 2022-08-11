package com.pal2hmnk.example.domain.user.entities

interface UserRepository {
    fun findBy(name: Name): User?
}
