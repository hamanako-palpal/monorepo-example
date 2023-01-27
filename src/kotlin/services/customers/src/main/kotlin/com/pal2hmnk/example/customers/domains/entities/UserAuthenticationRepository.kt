package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.Email

interface UserAuthenticationRepository {
    fun findBy(email: Email): UserAuthentication?
}
