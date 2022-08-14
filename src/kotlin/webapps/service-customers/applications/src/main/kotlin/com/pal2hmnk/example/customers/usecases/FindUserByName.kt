package com.pal2hmnk.example.customers.usecases

import com.pal2hmnk.example.customers.domains.entities.User

interface FindUserByName {
    fun exec(name: String): User
}
