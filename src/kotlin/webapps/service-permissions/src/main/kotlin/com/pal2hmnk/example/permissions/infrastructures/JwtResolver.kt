package com.pal2hmnk.example.permissions.infrastructures

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.pal2hmnk.example.permissions.domains.values.UserId

class JwtResolver {
    private val verifier = JWT.require(
        Algorithm.HMAC256("secret")
    )
        .withIssuer("permissions")
        .build()

    fun resolveAccessToken(token: String): Pair<String, UserId> =
        verifier.verify(token).let {
            it.id to UserId(it.subject.toInt())
        }
}
