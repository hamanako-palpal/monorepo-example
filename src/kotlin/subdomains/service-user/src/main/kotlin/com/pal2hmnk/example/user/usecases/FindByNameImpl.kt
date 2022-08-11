package com.pal2hmnk.example.user.usecases

import com.pal2hmnk.example.domain.user.entities.Name
import com.pal2hmnk.example.domain.user.entities.User
import com.pal2hmnk.example.domain.user.entities.UserRepository
import com.pal2hmnk.example.shared.exceptions.DomainException

class FindByNameImpl(
    private val repo: UserRepository,
): FindByName {
    override fun exec(params: String): User =
        repo.findBy(Name(params)) ?: throw DomainException()
}
