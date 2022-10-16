package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.Password

interface PasswordHashedRepository {
    fun generate(password: Password): PasswordHashed
    fun match(passwordHashed: PasswordHashed?, rowPassword: Password): Boolean
}
