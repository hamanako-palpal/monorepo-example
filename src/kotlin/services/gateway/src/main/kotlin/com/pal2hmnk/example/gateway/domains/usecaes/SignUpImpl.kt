package com.pal2hmnk.example.gateway.domains.usecaes

import com.pal2hmnk.example.gateway.domains.entities.User
import com.pal2hmnk.example.gateway.domains.entities.UserRepository
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class SignUpImpl(
    private val userRepository: UserRepository,
) : SignUp {
    override fun exec(userName: String, password: String, email: String): User = runBlocking {
        userRepository.signUp(userName, password, email)
    }
}
