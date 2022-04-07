package com.pal2hmnk.example.user.infrastructures.persistence

import com.pal2hmnk.example.generated.orm.mapper.UsersMapper
import com.pal2hmnk.example.shared.SqlSessionConfigMapper
import com.pal2hmnk.example.shared.SqlSessionProviderImpl

class UserSqlSessionProvider: SqlSessionProviderImpl() {
    override val mapper: SqlSessionConfigMapper = {
        addMapper(UsersMapper::class.java)
    }
}
