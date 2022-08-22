package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.entities.Shop
import com.pal2hmnk.example.customers.domains.entities.ShopRepository
import com.pal2hmnk.example.customers.domains.values.ShopId
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.Shops
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class ShopRepositoryImpl : ShopRepository {
    override fun findByIds(ids: List<ShopId>): List<Shop> = transaction {
        Shops.select { Shops.id.inList(ids.map { it.value.toLong() }) }.map {
            Shop.of(it[Shops.id].toInt(), it[Shops.name])
        }
    }
}
