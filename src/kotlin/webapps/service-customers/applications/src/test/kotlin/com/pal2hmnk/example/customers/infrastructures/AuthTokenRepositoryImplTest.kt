package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.values.Name
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.AuthTokens
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.Users
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldContain
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.Base64

class AuthTokenRepositoryImplTest : FunSpec({
    beforeEach {
        transaction {
            SchemaUtils.create(Users, AuthTokens)
            Users.insert { it[id] = 1000; it[name] = "test" }
        }
    }
    test("test") {
        val decode: (String) -> String = {
            Base64.getDecoder().decode(it.toByteArray()).toString(Charsets.UTF_8)
        }
        val user = UserRepositoryImpl().findBy(Name("test"))!!
        val authToken = AuthTokenRepositoryImpl().createToken(user)
        val jwt = authToken.token.split(".")
        decode(jwt[0]).also { header ->
            header shouldContain "\"kid\":\"keyId\""
            header shouldContain "\"typ\":\"JWT\""
            header shouldContain "\"alg\":\"HS256\""
        }
        decode(jwt[1]).also { payload ->
            payload shouldContain "\"user\":1000"
            payload shouldContain "\"iss\":\"customer\""
            payload shouldContain "\"jti\":\"${authToken.jwtId}\""
        }
    }
    afterEach {
        transaction {
            SchemaUtils.drop(Users, AuthTokens)
        }
    }
})
