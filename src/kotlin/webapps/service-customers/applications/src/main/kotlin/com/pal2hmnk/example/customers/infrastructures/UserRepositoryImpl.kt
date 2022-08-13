package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.domains.entities.UserRepository
import com.pal2hmnk.example.customers.domains.values.Name
import com.pal2hmnk.example.generated.orm.mapper.UsersDynamicSqlSupport.Users
import com.pal2hmnk.example.generated.orm.mapper.UsersMapper
import com.pal2hmnk.example.generated.orm.mapper.selectOne
import com.pal2hmnk.example.generated.orm.model.UsersRecord
import com.pal2hmnk.example.shared.infrastructures.persistence.SqlSessionProvider
import org.mybatis.dynamic.sql.util.kotlin.elements.isEqualTo

class UserRepositoryImpl(
    private val sessionProvider: SqlSessionProvider,
) : UserRepository {
    override fun findBy(name: Name): User? = sessionProvider.get().use { session ->
        session.getMapper(UsersMapper::class.java).selectOne {
            where(Users.name, isEqualTo(name.value))
        }?.translate()
    }

    private fun UsersRecord.translate() = User(id, Name(name))
}
