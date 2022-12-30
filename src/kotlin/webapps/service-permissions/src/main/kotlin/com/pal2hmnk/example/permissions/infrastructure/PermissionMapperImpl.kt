package com.pal2hmnk.example.permissions.infrastructure

import com.pal2hmnk.example.permissions.domains.entities.PermissionMapper
import com.pal2hmnk.example.permissions.infrastructure.persistance.exposed.Permissions
import com.pal2hmnk.example.permissions.infrastructure.persistance.exposed.RolePermissions
import com.pal2hmnk.example.permissions.domains.values.Permission
import com.pal2hmnk.example.permissions.domains.values.Role
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class PermissionMapperImpl : PermissionMapper {

    @Transactional
    override fun findByRoles(): Map<Role, List<Permission>> = transaction {
        Permissions.innerJoin(otherTable = RolePermissions)
            .slice(RolePermissions.roleKey, Permissions.name)
            .selectAll().associate {
                Role(it[RolePermissions.roleKey]) to Permission(it[Permissions.name])
            }
            .entries.groupBy { it.key }
            .mapValues { it.value.map { entry -> entry.value } }
    }
}
