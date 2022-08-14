package com.pal2hmnk.example.customers.usecases

import com.pal2hmnk.example.customers.domains.entities.Shop
import com.pal2hmnk.example.customers.domains.values.ShopId

interface FindShopsByIds {
    fun exec(shopIds: List<ShopId>): List<Shop>
}
