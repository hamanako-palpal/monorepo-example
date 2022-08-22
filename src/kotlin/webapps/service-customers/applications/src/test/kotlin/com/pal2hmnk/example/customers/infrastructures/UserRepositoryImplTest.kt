package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.values.Name
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.Users
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.testcontainers.containers.PostgreSQLContainer

class UserRepositoryImplTest : FunSpec({
    beforeEach {
        transaction {
            SchemaUtils.create(Users)
        }
    }
    test("test") {
        transaction { Users.insert { it[id] = 1000; it[name] = "test" } }
        val user = UserRepositoryImpl().findBy(Name("test"))
        user?.userId shouldBe 1000
    }
    afterEach {
        transaction {
            SchemaUtils.drop(Users)
        }
    }
})

object KotestProjectConfig : AbstractProjectConfig() {
    private val container = PostgreSQLContainer("postgres:alpine")

    override suspend fun beforeProject() {
        container.start()
        Database.connect(
            container.jdbcUrl,
            container.driverClassName,
            container.username,
            container.password,
        )
    }
}
