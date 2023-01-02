package com.pal2hmnk.example.permissions.infrastructure

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.pal2hmnk.example.permissions.domains.entities.Token
import com.pal2hmnk.example.shared.utils.DateConverter

class JwtIssuer(
    private val token: Token,
) {
    private val privateKey = "secret"

    fun doIssue(cmd: (JWTCreator.Builder.() -> Unit)): String {
        var issuedToken = ""
        try {
            val algorithm = Algorithm.HMAC256(privateKey)
            issuedToken = JWT.create()
                .withJWTId(token.jti)
                .withIssuer(token.issuer)
                .withSubject(token.userId.value.toString())
                .withKeyId("keyId")
                .withIssuedAt(DateConverter.localDateTimeToDate(token.created))
                .withExpiresAt(DateConverter.localDateTimeToDate(token.expired))
                .apply(cmd)
                .sign(algorithm)
        } catch (e: JWTCreationException) {
            //Invalid Signing
        }
        return issuedToken
    }
}
