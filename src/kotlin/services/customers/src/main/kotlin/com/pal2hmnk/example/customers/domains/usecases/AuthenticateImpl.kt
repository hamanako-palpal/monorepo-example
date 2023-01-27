package com.pal2hmnk.example.customers.domains.usecases

import com.pal2hmnk.example.customers.domains.entities.Authentication
import com.pal2hmnk.example.customers.domains.entities.AuthenticationRepository
import com.pal2hmnk.example.customers.domains.entities.PasswordHashedRepository
import com.pal2hmnk.example.customers.domains.entities.UserAuthenticationRepository
import com.pal2hmnk.example.customers.domains.values.Email
import com.pal2hmnk.example.customers.domains.entities.PasswordRow
import com.pal2hmnk.example.customers.domains.entities.Staff
import org.springframework.stereotype.Service

@Service
class AuthenticateImpl(
    private val authenticationRepository: AuthenticationRepository,
    private val passwordHashedRepository: PasswordHashedRepository,
    private val userAuthenticationRepository: UserAuthenticationRepository,
) : Authenticate {
    override fun exec(authParam: Pair<Email, PasswordRow>): Authentication? {
        val (email, password) = authParam
        val user = userAuthenticationRepository.findBy(email)
        if (user == null ||
            !passwordHashedRepository.match(user.passwordHashed, password)
        ) {
            return null
        }
        return authenticationRepository.save(Staff(user.userId))
    }
}
