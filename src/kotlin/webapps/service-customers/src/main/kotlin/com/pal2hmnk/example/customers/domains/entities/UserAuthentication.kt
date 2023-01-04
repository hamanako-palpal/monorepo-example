package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.Email
import com.pal2hmnk.example.customers.domains.values.UserId

class UserAuthentication(
    val userId: UserId,
    val email: Email,
    val passwordHashed: PasswordHashed? = null,
)
