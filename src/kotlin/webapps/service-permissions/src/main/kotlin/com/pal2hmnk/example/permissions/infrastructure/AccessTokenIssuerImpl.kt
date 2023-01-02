package com.pal2hmnk.example.permissions.infrastructure

import com.auth0.jwt.JWTCreator
import com.pal2hmnk.example.permissions.domains.entities.AccessToken
import com.pal2hmnk.example.permissions.domains.entities.AccessTokenIssuer
import org.springframework.stereotype.Component

@Component
class AccessTokenIssuerImpl : AccessTokenIssuer {
    override fun issue(token: AccessToken): String =
        JwtIssuer(token).doIssue(withAudiences(token))

    private fun withAudiences(accessToken: AccessToken): JWTCreator.Builder.() ->Unit = {
        withAudience(*accessToken.audience.toTypedArray())
    }
}
