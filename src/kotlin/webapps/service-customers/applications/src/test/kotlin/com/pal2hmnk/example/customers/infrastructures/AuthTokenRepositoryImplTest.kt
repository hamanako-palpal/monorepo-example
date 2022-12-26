package com.pal2hmnk.example.customers.infrastructures

import io.kotest.core.spec.style.FunSpec

class AuthTokenRepositoryImplTest : FunSpec({
//    beforeEach {
//        transaction {
//            SchemaUtils.create(Users, AuthTokens)
//            Users.insert { it[id] = 1000; it[name] = "test" }
//        }
//    }
//    test("test") {
//        val decode: (String) -> String = {
//            Base64.getDecoder().decode(it.toByteArray()).toString(Charsets.UTF_8)
//        }
//        val user = UserAuthenticationRepositoryImpl().findBy(Name("test"))!!
//        val authToken = AuthTokenRepositoryImpl().createToken(user)
//        val jwt = authToken.token.split(".")
//        decode(jwt[0]).also { header ->
//            header shouldContain "\"kid\":\"keyId\""
//            header shouldContain "\"typ\":\"JWT\""
//            header shouldContain "\"alg\":\"HS256\""
//        }
//        decode(jwt[1]).also { payload ->
//            payload shouldContain "\"user\":1000"
//            payload shouldContain "\"iss\":\"customer\""
//            payload shouldContain "\"jti\":\"${authToken.jwtId}\""
//        }
//    }
//    afterEach {
//        transaction {
//            SchemaUtils.drop(Users, AuthTokens)
//        }
//    }
})
