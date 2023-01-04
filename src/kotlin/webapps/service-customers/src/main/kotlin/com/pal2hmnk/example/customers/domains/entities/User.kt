package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.Email

class User(
    val userId: Int,
    val email: Email,
    val passwordHashed: PasswordHashed? = null,
)
