package com.pal2hmnk.example.customers.usecases

import com.pal2hmnk.example.customers.domains.entities.AuthToken
import com.pal2hmnk.example.customers.domains.entities.AuthTokenRepository
import com.pal2hmnk.example.customers.domains.entities.Authenticator
import com.pal2hmnk.example.customers.domains.entities.PasswordHashedRepository
import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.domains.entities.UserRepository
import com.pal2hmnk.example.customers.domains.values.Name
import com.pal2hmnk.example.customers.domains.values.Password

class AuthenticateImpl(
    private val authTokenRepository: AuthTokenRepository,
    private val passwordHashedRepository : PasswordHashedRepository,
    private val userRepository: UserRepository,
) : Authenticate {

    override fun exec(authParam: Pair<Name, Password>): AuthToken? {
        val user = userRepository.findBy(authParam.first) ?: return null
        val password = authParam.second

        return initializeAuthenticator(user)
            .authenticate(password)
            .generateToken()
    }

    private fun initializeAuthenticator(user: User) : Authenticator {
        return Authenticator(
            user,
            passwordMatcher = { _user, _password ->
                passwordHashedRepository.match(_user.passwordHashed, _password)
            },
            tokenGenerator = authTokenRepository::createToken
        )
    }
}
