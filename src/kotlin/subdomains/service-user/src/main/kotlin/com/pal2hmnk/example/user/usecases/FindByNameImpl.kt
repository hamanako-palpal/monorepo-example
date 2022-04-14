package com.pal2hmnk.example.user.usecases

import com.pal2hmnk.example.user.domains.Name
import com.pal2hmnk.example.user.domains.User
import com.pal2hmnk.example.user.domains.UserRepository

class FindByNameImpl(
    private val repo: UserRepository,
): FindByName {
    override fun exec(params: String): User =
        repo.findBy(Name(params))
}
