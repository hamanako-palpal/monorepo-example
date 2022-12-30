package com.pal2hmnk.example.permissions.domains.entities

import com.pal2hmnk.example.permissions.domains.values.Permission
import com.pal2hmnk.example.permissions.domains.values.Role

interface PermissionMapper {
    fun findByRoles(): Map<Role, List<Permission>>
}
