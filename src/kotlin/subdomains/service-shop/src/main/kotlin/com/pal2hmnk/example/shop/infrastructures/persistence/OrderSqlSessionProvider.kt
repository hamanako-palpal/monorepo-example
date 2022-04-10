package com.pal2hmnk.example.shop.infrastructures.persistence

import com.pal2hmnk.example.generated.orm.mapper.OrdersMapper
import com.pal2hmnk.example.generated.orm.mapper.ShopsMapper
import com.pal2hmnk.example.shared.SqlSessionConfigMapper
import com.pal2hmnk.example.shared.SqlSessionProviderImpl

class OrderSqlSessionProvider : SqlSessionProviderImpl() {
    override val mapper: SqlSessionConfigMapper = {
        addMapper(OrdersMapper::class.java)
        addMapper(ShopsMapper::class.java)
    }
}
