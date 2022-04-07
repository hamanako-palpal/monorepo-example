package com.pal2hmnk.example.user.usecases

import com.pal2hmnk.example.user.domains.User

interface UserScenario {
    fun findByName(name: String): User
}
