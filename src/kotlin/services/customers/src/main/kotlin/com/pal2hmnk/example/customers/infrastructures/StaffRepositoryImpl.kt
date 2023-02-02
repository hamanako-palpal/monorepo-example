package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.entities.Staff
import com.pal2hmnk.example.customers.domains.entities.StaffRepository
import com.pal2hmnk.example.customers.domains.values.ShopId
import com.pal2hmnk.example.customers.domains.values.UserId
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.Roles
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.Staffs
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class StaffRepositoryImpl : StaffRepository {
    override fun findByShopId(shopId: ShopId): List<Staff> = transaction {
        Staffs.join(
            Roles,
            JoinType.INNER,
            additionalConstraint = { Staffs.roleId eq Roles.id })
            .select { Staffs.shopId eq shopId.value }
            .map {
                Staff(
                    userId = UserId(it[Staffs.userId]),
                    shopId = ShopId(it[Staffs.shopId]),
                    role = it[Roles.roleKey],
                )
            }
    }
}
