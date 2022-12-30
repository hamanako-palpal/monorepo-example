package com.pal2hmnk.example.permissions.infrastructure.persistance.exposed

import org.jetbrains.exposed.sql.Table

object Permissions : Table("permissions") {
    val id = Permissions.integer("id")
    val name = Permissions.varchar("name", 255)
}
