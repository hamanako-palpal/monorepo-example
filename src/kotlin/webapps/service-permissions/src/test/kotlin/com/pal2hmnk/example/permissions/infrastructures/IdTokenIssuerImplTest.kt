package com.pal2hmnk.example.permissions.infrastructures

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.ninja_squad.dbsetup_kotlin.dbSetup
import com.pal2hmnk.example.permissions.configurations.KotestProjectConfig
import com.pal2hmnk.example.permissions.domains.entities.IdToken
import com.pal2hmnk.example.permissions.domains.values.Role
import com.pal2hmnk.example.permissions.domains.values.ShopId
import com.pal2hmnk.example.permissions.domains.values.UserId
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import java.util.Base64

class IdTokenIssuerImplTest : FunSpec({
    beforeTest {
        dbSetup(to = KotestProjectConfig.dataSource()) {
            insertInto("permissions") {
                columns("id", "name")
                values(1000, "ReadStaffInfo")
                values(1001, "AddStaffInfo")
            }
            insertInto("role_permissions") {
                columns("role_key", "permission_id")
                values("manager", 1000)
                values("manager", 1001)
                values("member", 1000)
            }
        }.launch()
    }
    test("test") {
        val issuer = IdTokenIssuerImpl(PermissionMapperImpl())
        val token = IdToken(
            UserId(1000),
            ShopId(1000) to Role("manager"),
            "gateway",
            listOf("customers","contracts"),
        )
        val issued = issuer.issue(token)
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
            it.claims["shopId"]!!.asInt() shouldBe 1000
            it.claims["permissions"]!!
                .asArray(String::class.java) shouldContainAll listOf("ReadStaffInfo", "AddStaffInfo")
        }
    }
})
