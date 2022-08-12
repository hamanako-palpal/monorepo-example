package com.pal2hmnk.example.customers.usecases

import com.pal2hmnk.example.customers.domains.entities.Shop
import com.pal2hmnk.example.customers.domains.entities.ShopRepository
import com.pal2hmnk.example.customers.domains.values.ShopId

class FindShopsByIdsImpl(
    private val repo : ShopRepository,
): FindShopsByIds {
    override fun exec(params: List<ShopId>): List<Shop> =
        repo.findByIds(params.map { ShopId(it.value) })
}
