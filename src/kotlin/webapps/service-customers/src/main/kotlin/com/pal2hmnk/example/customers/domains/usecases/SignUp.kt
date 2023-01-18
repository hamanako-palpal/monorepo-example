package com.pal2hmnk.example.customers.domains.usecases

import com.pal2hmnk.example.customers.domains.entities.PasswordRow
import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.domains.values.Email

interface SignUp {
    fun exec(model: SignUpModel) : User?
}

data class SignUpModel(val name: String, val passwordRow: PasswordRow, val email: Email)
