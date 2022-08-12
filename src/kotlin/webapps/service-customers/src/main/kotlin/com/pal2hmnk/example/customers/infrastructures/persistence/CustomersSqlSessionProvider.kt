package com.pal2hmnk.example.customers.infrastructures.persistence

import com.pal2hmnk.example.generated.orm.mapper.ShopsMapper
import com.pal2hmnk.example.generated.orm.mapper.UsersMapper
import com.pal2hmnk.example.shared.infrastructures.SqlSessionConfigMapper
import com.pal2hmnk.example.shared.infrastructures.SqlSessionProviderImpl

class CustomersSqlSessionProvider: SqlSessionProviderImpl() {
    override val mapper: SqlSessionConfigMapper = {
        addMapper(ShopsMapper::class.java)
        addMapper(UsersMapper::class.java)
    }
}
