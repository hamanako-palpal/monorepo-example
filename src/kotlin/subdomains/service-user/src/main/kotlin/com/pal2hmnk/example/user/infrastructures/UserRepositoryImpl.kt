package com.pal2hmnk.example.user.infrastructures

import com.pal2hmnk.example.domain.user.entities.Name
import com.pal2hmnk.example.domain.user.entities.User
import com.pal2hmnk.example.domain.user.entities.UserRepository
import com.pal2hmnk.example.generated.orm.mapper.UsersDynamicSqlSupport.Users
import com.pal2hmnk.example.generated.orm.mapper.UsersMapper
import com.pal2hmnk.example.generated.orm.mapper.selectOne
import com.pal2hmnk.example.generated.orm.model.UsersRecord
import com.pal2hmnk.example.shared.infrastructures.SqlSessionProvider
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
