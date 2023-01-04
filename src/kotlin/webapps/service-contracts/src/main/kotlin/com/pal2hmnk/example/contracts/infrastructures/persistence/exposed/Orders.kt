package com.pal2hmnk.example.contracts.infrastructures.persistence.exposed

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Orders : Table("orders") {
    val id = Orders.integer("id").autoIncrement()
    val userId = Orders.integer("userId")
    val shopId = Orders.integer("shopId")
    val ordered = Orders.datetime("ordered")
}
