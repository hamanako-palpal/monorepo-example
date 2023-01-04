package com.pal2hmnk.example.customers.infrastructures.persistence.exposed

import org.jetbrains.exposed.sql.Table

object Shops : Table("shops") {
    val id = Shops.integer("id").autoIncrement()
    val name = Shops.varchar("name", 255)
}
