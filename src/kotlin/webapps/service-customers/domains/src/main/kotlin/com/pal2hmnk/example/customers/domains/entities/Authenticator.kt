package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.Password

class Authenticator(
    private val user: User,
    private val passwordMatcher: (User, Password) -> Boolean,
    private val tokenGenerator: (User) -> AuthToken,
) {
    private var isMatched = false

    fun authenticate(inputPassword: Password): Authenticator =
        this.also { it.isMatched = passwordMatcher(user, inputPassword) }

    fun generateToken() = this
        .takeIf { isMatched }
        ?.let { tokenGenerator.invoke(user) }
}
