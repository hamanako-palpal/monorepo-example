package com.pal2hmnk.example.contracts.infrastructures

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.pal2hmnk.example.contracts.domains.entities.UserIdentity
import com.pal2hmnk.example.contracts.domains.entities.UserIdentityResolver
import com.pal2hmnk.example.contracts.domains.values.Permission
import com.pal2hmnk.example.contracts.domains.values.ShopId
import com.pal2hmnk.example.contracts.domains.values.UserId
import org.springframework.stereotype.Component

@Component
class JwtUserIdentityResolver : UserIdentityResolver {
    private val verifier = JWT.require(
        Algorithm.HMAC256("secret")
    )
        .withIssuer("permissions")
        .build()

    override fun resolve(token: String): UserIdentity {
        val verified = verifier.verify(token)
        return UserIdentity(
            UserId(verified.subject.toInt()),
            verified.claims["shopId"]?.let { ShopId(it.asInt()!!) },
            verified.claims["permissions"]!!.asArray(String::class.java).map(::Permission),
            true,
        )
    }

}
