package com.pal2hmnk.example.customers.domains.usecases

import com.pal2hmnk.example.customers.domains.entities.Shop
import com.pal2hmnk.example.customers.domains.entities.ShopRepository
import com.pal2hmnk.example.customers.domains.values.ShopId
import org.springframework.stereotype.Service

@Service
class FindShopsByIdsImpl(
    private val repo : ShopRepository,
): FindShopsByIds {
    override fun exec(shopIds: List<ShopId>): List<Shop> =
        repo.findByIds(shopIds.map { ShopId(it.value) })
}
