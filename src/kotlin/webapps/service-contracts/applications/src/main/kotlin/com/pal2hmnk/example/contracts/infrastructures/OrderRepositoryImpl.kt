package com.pal2hmnk.example.contracts.infrastructures

import com.pal2hmnk.example.contracts.domains.entities.Order
import com.pal2hmnk.example.contracts.domains.entities.OrderRepository
import com.pal2hmnk.example.contracts.domains.values.ShopId
import com.pal2hmnk.example.contracts.domains.values.UserId
import com.pal2hmnk.example.contracts.infrastructures.persistence.exposed.Orders
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class OrderRepositoryImpl : OrderRepository {
    override fun findOrderHistoryBy(id: UserId): List<Order> = transaction {
        Orders.select { Orders.id eq id.value }.map {
            Order(
                orderId = it[Orders.id],
                userId = UserId(it[Orders.userId]),
                shopId = ShopId(it[Orders.shopId]),
                ordered = it[Orders.ordered],
            )
        }
    }
}
