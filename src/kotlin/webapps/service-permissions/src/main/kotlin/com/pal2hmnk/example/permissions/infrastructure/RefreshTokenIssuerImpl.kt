package com.pal2hmnk.example.permissions.infrastructure

import com.auth0.jwt.JWTCreator
import com.pal2hmnk.example.permissions.domains.entities.RefreshToken
import com.pal2hmnk.example.permissions.domains.entities.RefreshTokenIssuer
import org.springframework.stereotype.Component

@Component
class RefreshTokenIssuerImpl : RefreshTokenIssuer {
    override fun issue(token: RefreshToken): String =
        JwtIssuer(token).doIssue(withAudiences(token))

    private fun withAudiences(accessToken: RefreshToken): JWTCreator.Builder.() -> Unit = {
        withAudience(*accessToken.audience.toTypedArray())
    }
}
