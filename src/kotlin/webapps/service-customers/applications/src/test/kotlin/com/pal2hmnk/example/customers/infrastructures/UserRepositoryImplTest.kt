package com.pal2hmnk.example.customers.infrastructures

import com.ninja_squad.dbsetup_kotlin.dbSetup
import com.pal2hmnk.example.customers.config.KotestProjectConfig
import com.pal2hmnk.example.customers.domains.values.Name
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.Users
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepositoryImplTest : FunSpec({
    beforeTest {
        dbSetup(to = KotestProjectConfig.dataSource()) {
            insertInto("users") {
                columns("id", "name")
                values(1000, "Admin")
            }
        }.launch()
    }
    test("test") {
        val user = UserRepositoryImpl().findBy(Name("Admin"))
        user?.userId shouldBe 1000
    }
    afterEach {
        transaction {
            Users.deleteAll()
        }
    }
})
