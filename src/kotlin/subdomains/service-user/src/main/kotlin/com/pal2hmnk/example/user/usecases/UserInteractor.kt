package com.pal2hmnk.example.user.usecases

import com.pal2hmnk.example.user.domains.Name
import com.pal2hmnk.example.user.domains.User
import com.pal2hmnk.example.user.domains.UserRepository

class UserInteractor(
    private val repo: UserRepository,
): UserScenario {
    override fun findByName(name: String): User =
        repo.findBy(Name.of(name))
}
