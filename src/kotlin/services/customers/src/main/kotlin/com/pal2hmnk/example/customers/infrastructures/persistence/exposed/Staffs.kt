package com.pal2hmnk.example.customers.infrastructures.persistence.exposed

import org.jetbrains.exposed.sql.Table

object Staffs : Table("staffs") {
    val id = Staffs.integer("id").autoIncrement()
    val userId = Staffs.integer("user_id")
    val shopId = Staffs.integer("shop_id")
    val roleId = Staffs.integer("role_id")
}
