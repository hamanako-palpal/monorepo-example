package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.entities.Shop
import com.pal2hmnk.example.customers.domains.entities.ShopRepository
import com.pal2hmnk.example.customers.domains.values.ShopId
import com.pal2hmnk.example.generated.orm.mapper.ShopsDynamicSqlSupport
import com.pal2hmnk.example.generated.orm.mapper.ShopsMapper
import com.pal2hmnk.example.generated.orm.mapper.select
import com.pal2hmnk.example.shared.infrastructures.persistence.SqlSessionProvider
import org.mybatis.dynamic.sql.util.kotlin.elements.isIn

class ShopRepositoryImpl(
    private val sessionProvider: SqlSessionProvider,
) : ShopRepository {
    override fun findByIds(ids: List<ShopId>): List<Shop> =
        sessionProvider.get().use { session ->
            session.getMapper(ShopsMapper::class.java).select {
                where(ShopsDynamicSqlSupport.Shops.id, isIn(ids.map { it.value }))
            }
        }.map { Shop.of(it.id, it.name) }
}
