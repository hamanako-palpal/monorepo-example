package com.pal2hmnk.example.permissions.infrastructure

import com.ninja_squad.dbsetup_kotlin.dbSetup
import com.pal2hmnk.example.permissions.config.KotestProjectConfig
import com.pal2hmnk.example.permissions.domains.values.Role
import com.pal2hmnk.example.permissions.infrastructure.persistance.exposed.Permissions
import com.pal2hmnk.example.permissions.infrastructure.persistance.exposed.RolePermissions
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction

class PermissionMapperImplTest : FunSpec({
    beforeTest {
        dbSetup(to = KotestProjectConfig.dataSource()) {
            insertInto("permissions") {
                columns("id", "name")
                values(1000, "ReadStaffInfo")
            }
            insertInto("permissions") {
                columns("id", "name")
                values(1001, "AddStaffInfo")
            }
            insertInto("role_permissions") {
                columns("role_key", "permission_id")
                values("manager", 1000)
            }
            insertInto("role_permissions") {
                columns("role_key", "permission_id")
                values("manager", 1001)
            }
        }.launch()
    }
    test("test") {
        val roleMap = withContext(Dispatchers.IO) {
            PermissionMapperImpl().findByRoles()
        }
        val permissions = roleMap[Role("manager")]!!
        permissions.map { it.name } shouldContainExactly listOf("ReadStaffInfo", "AddStaffInfo")
    }
    afterEach {
        transaction {
            Permissions.deleteAll()
            RolePermissions.deleteAll()
        }
    }
})
