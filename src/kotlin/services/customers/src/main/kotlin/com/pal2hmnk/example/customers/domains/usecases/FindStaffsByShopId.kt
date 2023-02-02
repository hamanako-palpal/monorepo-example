package com.pal2hmnk.example.customers.domains.usecases

import com.pal2hmnk.example.customers.domains.entities.Staff
import com.pal2hmnk.example.customers.domains.values.ShopId

interface FindStaffsByShopId {
    fun exec(shopId: ShopId): List<Staff>
}
