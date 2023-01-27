package com.pal2hmnk.example.contracts.infrastructures

import com.pal2hmnk.example.contracts.domains.values.UserId
import com.pal2hmnk.example.contracts.infrastructures.persistence.exposed.Orders
import com.pal2hmnk.example.test.shared.PostgresContainer
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class OrderRepositoryImplTest : FunSpec({
    beforeEach {
        transaction {
            SchemaUtils.create(Orders)
        }
    }
    test("test") {
        transaction {
            Orders.insert { it[id] = 1000; it[userId] = 1000; it[shopId] = 7000; it[ordered] = LocalDateTime.now() }
        }
        val order = OrderRepositoryImpl().findOrderHistoryBy(UserId(1000))
        order.find { it.userId.value == 1000 }!!.shopId.value shouldBe 7000
    }
    afterEach {
        transaction {
            SchemaUtils.drop(Orders)
        }
    }

})
object KotestProjectConfig : AbstractProjectConfig() {
    override suspend fun beforeProject() {
        PostgresContainer.connectContainer()
    }
}
