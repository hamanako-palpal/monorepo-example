package com.pal2hmnk.example.customers.infrastructures

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm.HMAC256
import com.auth0.jwt.exceptions.JWTCreationException
import com.pal2hmnk.example.customers.domains.entities.AuthToken
import com.pal2hmnk.example.customers.domains.entities.AuthTokenRepository
import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.AuthTokens
import com.pal2hmnk.example.shared.utils.DateConverter
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class AuthTokenRepositoryImpl : AuthTokenRepository {
    private val privateKey = "secret"

    override fun createToken(user: User): AuthToken {
        lateinit var token: String
        val expireTime = LocalDateTime.now().plusMinutes(30)
        val code = random(25)
        try {
            val algorithm = HMAC256(privateKey)
            token = JWT.create()
                .withIssuer("customer")
                .withClaim("user", user.userId)
                .withKeyId("keyId")
                .withAudience("contracts", "customers")
                .withExpiresAt(DateConverter.localDateTimeToDate(expireTime))
                .withJWTId(code)
                .sign(algorithm)

        } catch (e: JWTCreationException) {
            //Invalid Signing
        }
        transaction {
            AuthTokens.insert {
                it[userId] = user.userId
                it[jwtId] = code
                it[ordered] = expireTime
            }
        }
        return AuthToken(user.userId, jwtId = code, expired = expireTime, token = token)
    }

    companion object {
        private val charset = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        fun random(count: Int): String =
            (1..count)
                .map { charset.random() }
                .joinToString(separator = "")
    }
}
