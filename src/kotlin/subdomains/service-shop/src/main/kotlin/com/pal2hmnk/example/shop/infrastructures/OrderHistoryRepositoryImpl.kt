package com.pal2hmnk.example.shop.infrastructures

import com.pal2hmnk.example.generated.orm.mapper.OrdersDynamicSqlSupport.Orders
import com.pal2hmnk.example.generated.orm.mapper.OrdersMapper
import com.pal2hmnk.example.generated.orm.mapper.ShopsDynamicSqlSupport.Shops
import com.pal2hmnk.example.generated.orm.mapper.ShopsMapper
import com.pal2hmnk.example.generated.orm.mapper.select
import com.pal2hmnk.example.generated.orm.model.OrdersRecord
import com.pal2hmnk.example.generated.orm.model.ShopsRecord
import com.pal2hmnk.example.shared.SqlSessionProvider
import com.pal2hmnk.example.shop.domains.Name
import com.pal2hmnk.example.shop.domains.OrderHistory
import com.pal2hmnk.example.shop.domains.OrderHistoryRepository
import com.pal2hmnk.example.shop.domains.ShopId
import com.pal2hmnk.example.shop.domains.UserId
import org.mybatis.dynamic.sql.util.kotlin.elements.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.elements.isIn
import java.time.ZoneId

class OrderHistoryRepositoryImpl(
    private val sessionProvider: SqlSessionProvider,
) : OrderHistoryRepository {
    override fun findBy(id: UserId): List<OrderHistory> {
        val orderList = findOrdersRecordList(id)
        val shopIds = orderList.map { it.shopId }
        val shopMap = findShopsRecordListByShopIds(shopIds).associate { it.id to it.name }
        return orderList.map {
            OrderHistory(
                Name.of(shopMap[it.id]),
                ShopId(it.id),
                it.ordered.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            )
        }
    }
    private fun findOrdersRecordList(id: UserId): List<OrdersRecord> = sessionProvider.get().use { session ->
        session.getMapper(OrdersMapper::class.java).select {
            where(Orders.userId, isEqualTo(id.value))
        }
    }
    private fun findShopsRecordListByShopIds(shopIds: List<Int>): List<ShopsRecord> = sessionProvider.get().use { session ->
        session.getMapper(ShopsMapper::class.java).select {
            where(Shops.id, isIn(shopIds))
        }
    }
}
