package com.pal2hmnk.example.contracts.infrastructures

import com.pal2hmnk.example.contracts.domains.entities.Order
import com.pal2hmnk.example.contracts.domains.entities.OrderRepository
import com.pal2hmnk.example.contracts.domains.values.ShopId
import com.pal2hmnk.example.contracts.domains.values.UserId
import com.pal2hmnk.example.generated.orm.mapper.OrdersDynamicSqlSupport.Orders
import com.pal2hmnk.example.generated.orm.mapper.OrdersMapper
import com.pal2hmnk.example.generated.orm.mapper.select
import com.pal2hmnk.example.generated.orm.model.OrdersRecord
import com.pal2hmnk.example.shared.infrastructures.SqlSessionProvider
import com.pal2hmnk.example.util.DateConverter
import org.mybatis.dynamic.sql.util.kotlin.elements.isEqualTo

class OrderRepositoryImpl(
    private val sessionProvider: SqlSessionProvider,
) : OrderRepository {
    override fun findOrderHistoryBy(id: UserId): List<Order> {
        return findOrdersRecordList(id).map { it.translate() }
    }

    private fun findOrdersRecordList(id: UserId): List<OrdersRecord> = sessionProvider.get().use { session ->
        session.getMapper(OrdersMapper::class.java).select {
            where(Orders.userId, isEqualTo(id.value))
        }
    }

    private fun OrdersRecord.translate() = Order(
        orderId = this.id,
        userId = UserId(this.userId),
        shopId = ShopId(this.shopId),
        ordered = DateConverter.dateToLocalDate(this.ordered),
    )
}
