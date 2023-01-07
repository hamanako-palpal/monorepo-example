package com.pal2hmnk.example.customers.domains.usecases

import com.pal2hmnk.example.customers.domains.entities.PasswordHashedRepository
import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.domains.entities.UserRepository
import com.pal2hmnk.example.customers.domains.values.Email
import com.pal2hmnk.example.customers.domains.entities.PasswordRow
import org.springframework.stereotype.Service

@Service
class SignUpImpl(
    private val passwordHashedRepository: PasswordHashedRepository,
    private val userRepository: UserRepository,
) : SignUp {
    override fun exec(name: String, passwordRow: PasswordRow, email: Email) : User? {
        return userRepository.save(
            User(
                name = name,
                email = email,
                passwordHashed = passwordHashedRepository.generate(passwordRow)
            )
        )
    }
}
