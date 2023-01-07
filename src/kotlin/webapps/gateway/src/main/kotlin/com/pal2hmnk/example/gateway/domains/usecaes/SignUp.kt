package com.pal2hmnk.example.gateway.domains.usecaes

import com.pal2hmnk.example.gateway.domains.entities.User

interface SignUp {
    fun exec(
        userName: String,
        password: String,
        email: String,
    ) : User
}
