package com.pal2hmnk.example.customers.infrastructures.persistence.exposed

import org.jetbrains.exposed.sql.Table

object UserAuthentications : Table("user_authentications") {
    val userId = UserAuthentications.integer("user_id")
    val email = UserAuthentications.varchar("email", 255)
    val password = UserAuthentications.varchar("password", 255)
}
