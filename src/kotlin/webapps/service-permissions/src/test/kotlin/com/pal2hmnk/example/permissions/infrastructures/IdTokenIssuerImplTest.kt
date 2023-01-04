package com.pal2hmnk.example.permissions.infrastructures

import com.ninja_squad.dbsetup_kotlin.dbSetup
import com.pal2hmnk.example.permissions.configurations.KotestProjectConfig
import com.pal2hmnk.example.permissions.domains.entities.IdToken
import com.pal2hmnk.example.permissions.domains.values.Role
import com.pal2hmnk.example.permissions.domains.values.ShopId
import com.pal2hmnk.example.permissions.domains.values.UserId
import io.kotest.core.spec.style.FunSpec
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
    test("lll") {
        val issuer = IdTokenIssuerImpl(PermissionMapperImpl())
        val token = IdToken(
            UserId(1000),
            listOf(
                ShopId(1000) to Role("manager"),
                ShopId(1001) to Role("member"),
            ),
            "gateway",
            listOf("customers","contracts"),
        )
        val decode = { it : String ->
            Base64.getDecoder().decode(it.toByteArray()).toString(Charsets.UTF_8)
        }
        val issued = issuer.issue(token)
        val jwt = issued.split(".")
        decode(jwt[0]).also { header ->
            header shouldContain "\"kid\":\"keyId\""
            header shouldContain "\"typ\":\"JWT\""
            header shouldContain "\"alg\":\"HS256\""
        }
        decode(jwt[1]).also { payload ->
            payload shouldContain "\"sub\":\"1000\""
            payload shouldContain "\"aud\":[\"customers\",\"contracts\"]"
            payload shouldContain "\"iss\":\"permissions\""
            payload shouldContain "{\"shopId\":1000,\"permissions\":[\"ReadStaffInfo\",\"AddStaffInfo\"]}"
            payload shouldContain "{\"shopId\":1001,\"permissions\":[\"ReadStaffInfo\"]}"
        }
    }

})
