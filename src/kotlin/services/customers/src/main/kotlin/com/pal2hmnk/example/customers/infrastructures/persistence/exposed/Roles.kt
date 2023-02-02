package com.pal2hmnk.example.customers.infrastructures.persistence.exposed

import org.jetbrains.exposed.sql.Table

object Roles : Table("roles") {
    val id = Roles.integer("id").autoIncrement()
    val roleKey = Roles.varchar("role_key", 255)
}
