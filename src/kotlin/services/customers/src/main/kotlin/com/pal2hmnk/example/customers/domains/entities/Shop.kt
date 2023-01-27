package com.pal2hmnk.example.customers.domains.entities

import com.pal2hmnk.example.customers.domains.values.Name
import com.pal2hmnk.example.customers.domains.values.ShopId

class Shop(
    val id: ShopId,
    val shopName: Name,
) {
    companion object {
        fun of(id: Int,  shopName: String) =
            Shop(ShopId(id), Name(shopName))
    }
}
