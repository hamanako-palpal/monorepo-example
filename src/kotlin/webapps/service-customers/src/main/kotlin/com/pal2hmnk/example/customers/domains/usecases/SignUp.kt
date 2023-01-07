package com.pal2hmnk.example.customers.domains.usecases

import com.pal2hmnk.example.customers.domains.entities.PasswordRow
import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.domains.values.Email

interface SignUp {
    fun exec(name: String, passwordRow: PasswordRow, email: Email) : User?
}
