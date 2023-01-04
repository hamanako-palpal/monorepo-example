package com.pal2hmnk.example.permissions.infrastructures.persistance.exposed

import org.jetbrains.exposed.sql.Table

object RolePermissions : Table("role_permissions") {
    val roleKey = RolePermissions.varchar("role_key", 255)
    val permissionId = RolePermissions.integer("permission_id").references(Permissions.id)
}
