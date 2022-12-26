package com.pal2hmnk.example.entities

import com.pal2hmnk.example.values.Permission
import com.pal2hmnk.example.values.Role

interface PermissionMapper {
    fun findByRoles(): Map<Role, List<Permission>>
}
