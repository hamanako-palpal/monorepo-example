package com.pal2hmnk.example.permissions.infrastructure

import com.pal2hmnk.example.permissions.domains.entities.PermissionMapper
import com.pal2hmnk.example.permissions.domains.values.Permission
import com.pal2hmnk.example.permissions.domains.values.Role
import com.pal2hmnk.example.permissions.infrastructure.persistance.exposed.Permissions
import com.pal2hmnk.example.permissions.infrastructure.persistance.exposed.RolePermissions
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class PermissionMapperImpl : PermissionMapper {
    @Transactional
    override fun findByRoles(): Map<Role, List<Permission>> = transaction {
        Permissions
            .join(
                RolePermissions,
                JoinType.INNER,
                additionalConstraint = { Permissions.id eq RolePermissions.permissionId })
            .selectAll()
            .map {
                Role(it[RolePermissions.roleKey]) to Permission(it[Permissions.name])
            }
            .groupBy { it.first }
            .mapValues { (_, v) -> v.map { it.second } }
    }
}
