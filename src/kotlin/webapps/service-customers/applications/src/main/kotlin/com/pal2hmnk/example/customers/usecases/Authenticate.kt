package com.pal2hmnk.example.customers.usecases

import com.pal2hmnk.example.customers.domains.entities.AuthToken
import com.pal2hmnk.example.customers.domains.values.Name
import com.pal2hmnk.example.customers.domains.values.Password

interface Authenticate {
    fun exec(authParam: Pair<Name, Password>): AuthToken?
}
