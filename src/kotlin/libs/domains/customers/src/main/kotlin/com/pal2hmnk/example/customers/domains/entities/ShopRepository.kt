package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.ShopId

interface ShopRepository {
    fun findByIds(ids: List<ShopId>): List<Shop>
}
