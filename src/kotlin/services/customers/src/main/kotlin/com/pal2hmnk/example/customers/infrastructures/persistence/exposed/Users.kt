package com.pal2hmnk.example.customers.infrastructures.persistence.exposed

import org.jetbrains.exposed.sql.Table

object Users : Table("users") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
}
