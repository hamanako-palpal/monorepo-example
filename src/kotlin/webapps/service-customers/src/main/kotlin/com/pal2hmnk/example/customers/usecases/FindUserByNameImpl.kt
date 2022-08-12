package com.pal2hmnk.example.customers.usecases

import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.domains.entities.UserRepository
import com.pal2hmnk.example.customers.domains.values.Name
import com.pal2hmnk.example.shared.exceptions.DomainException

class FindUserByNameImpl(
    private val repo: UserRepository,
): FindUserByName {
    override fun exec(params: String): User =
        repo.findBy(Name(params)) ?: throw DomainException()
}
