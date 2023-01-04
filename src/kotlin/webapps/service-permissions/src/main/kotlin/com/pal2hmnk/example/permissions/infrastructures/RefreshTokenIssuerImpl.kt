package com.pal2hmnk.example.permissions.infrastructures

import com.auth0.jwt.JWTCreator
import com.pal2hmnk.example.permissions.domains.entities.RefreshToken
import com.pal2hmnk.example.permissions.domains.entities.RefreshTokenIssuer
import org.springframework.stereotype.Component

@Component
class RefreshTokenIssuerImpl : RefreshTokenIssuer {
    override fun issue(token: RefreshToken): String =
        JwtIssuer(token).doIssue(withCustomClaim(token))

    private fun withCustomClaim(accessToken: RefreshToken): JWTCreator.Builder.() -> Unit = {
        withAudience(*accessToken.audience.toTypedArray())
    }
}
