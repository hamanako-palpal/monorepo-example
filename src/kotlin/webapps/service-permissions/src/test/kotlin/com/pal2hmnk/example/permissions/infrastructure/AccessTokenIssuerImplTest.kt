package com.pal2hmnk.example.permissions.infrastructure

import com.pal2hmnk.example.permissions.domains.entities.AccessToken
import com.pal2hmnk.example.permissions.domains.values.UserId
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldContain
import java.util.Base64

class AccessTokenIssuerImplTest : FunSpec({
    test("issue") {
        val token = AccessToken(
            UserId(1000),
            "gateway",
            listOf("customers","contracts"),
        )
        val decode = { it : String ->
            Base64.getDecoder().decode(it.toByteArray()).toString(Charsets.UTF_8)
        }
        val issued = AccessTokenIssuerImpl().issue(token)
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
        }
    }
})
