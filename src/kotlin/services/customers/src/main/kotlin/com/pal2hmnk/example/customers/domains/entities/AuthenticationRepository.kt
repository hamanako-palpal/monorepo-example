package com.pal2hmnk.example.customers.domains.entities

interface AuthenticationRepository {
    fun save(staff: Staff): Authentication
}
