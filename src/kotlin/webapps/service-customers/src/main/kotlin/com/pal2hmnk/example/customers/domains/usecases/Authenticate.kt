package com.pal2hmnk.example.customers.domains.usecases

import com.pal2hmnk.example.customers.domains.entities.Authentication
import com.pal2hmnk.example.customers.domains.values.Email
import com.pal2hmnk.example.customers.domains.entities.PasswordRow

interface Authenticate {
    fun exec(authParam: Pair<Email, PasswordRow>): Authentication?
}
