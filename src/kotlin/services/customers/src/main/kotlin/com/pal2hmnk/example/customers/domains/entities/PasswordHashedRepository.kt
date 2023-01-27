package com.pal2hmnk.example.customers.domains.entities

interface PasswordHashedRepository {
    fun generate(passwordRow: PasswordRow): PasswordHashed
    fun match(passwordHashed: PasswordHashed?, rowPassword: PasswordRow): Boolean
}
