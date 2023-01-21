package com.pal2hmnk.example.contracts.infrastructures.persistence.exposed

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Orders : Table("orders") {
    val id = Orders.integer("id").autoIncrement()
    val userId = Orders.integer("user_id")
    val shopId = Orders.integer("shop_id")
    val ordered = Orders.datetime("ordered")
}
