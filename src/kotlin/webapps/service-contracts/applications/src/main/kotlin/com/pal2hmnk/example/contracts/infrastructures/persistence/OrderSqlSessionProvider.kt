package com.pal2hmnk.example.contracts.infrastructures.persistence

import com.pal2hmnk.example.generated.orm.mapper.OrdersMapper
import com.pal2hmnk.example.shared.infrastructures.persistence.SqlSessionConfigMapper
import com.pal2hmnk.example.shared.infrastructures.persistence.SqlSessionProviderImpl

class OrderSqlSessionProvider : SqlSessionProviderImpl() {
    override val mapper: SqlSessionConfigMapper = {
        addMapper(OrdersMapper::class.java)
    }
}
