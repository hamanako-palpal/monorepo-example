package com.pal2hmnk.example.permissions.infrastructures

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.pal2hmnk.example.permissions.domains.entities.AccessToken
import com.pal2hmnk.example.permissions.domains.values.UserId
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.util.Base64

class AccessTokenIssuerImplTest : FunSpec({
    test("issue") {
        val token = AccessToken(
            UserId(1000),
            "gateway",
            listOf("customers","contracts"),
        )
        val issued = AccessTokenIssuerImpl().issue(token)
        val algorithm = Algorithm.HMAC256("secret")
        val verifier = JWT.require(algorithm)
            .withIssuer("permissions")
            .build()
        verifier.verify(issued).also {
            it.keyId shouldBe "keyId"
            it.type shouldBe "JWT"
            it.algorithm shouldBe "HS256"
            it.subject shouldBe "1000"
            it.audience shouldContainAll listOf("customers", "contracts")
        }
    }
})
