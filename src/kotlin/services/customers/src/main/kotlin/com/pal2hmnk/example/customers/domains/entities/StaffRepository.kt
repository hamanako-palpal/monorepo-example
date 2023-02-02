package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.ShopId

interface StaffRepository {
    fun findByShopId(shopId: ShopId): List<Staff>
}
