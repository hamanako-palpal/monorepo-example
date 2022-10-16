package com.pal2hmnk.example.customers.infrastructures

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC256
import com.auth0.jwt.exceptions.JWTCreationException
import com.pal2hmnk.example.customers.domains.entities.AuthToken
import com.pal2hmnk.example.customers.domains.entities.AuthTokenRepository
import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.util.DateConverter
import java.time.LocalDateTime

class AuthTokenRepositoryImpl : AuthTokenRepository {
    private val privateKey = "secret"

    override fun createToken(user : User) : AuthToken {
        lateinit var token : String
        try {
            val algorithm: com.auth0.jwt.algorithms.Algorithm = HMAC256(privateKey)
            val expireTime = LocalDateTime.now().plusSeconds(30)
            token = JWT.create()
                .withIssuer("auth0")
                .withClaim("name", user.name.value)
                .withExpiresAt(DateConverter.localDateTimeToDate(expireTime))
                .sign(algorithm)

        } catch (e : JWTCreationException) {
            //Invalid Signing
        }
        return AuthToken(token)
    }
}
