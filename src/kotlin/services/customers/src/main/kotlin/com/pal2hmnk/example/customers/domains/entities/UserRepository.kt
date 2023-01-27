package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.Name

interface UserRepository {
    fun findBy(name: Name): User?

    fun save(user: User): User?
}
