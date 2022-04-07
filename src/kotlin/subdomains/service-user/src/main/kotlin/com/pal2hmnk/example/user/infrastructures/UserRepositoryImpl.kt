package com.pal2hmnk.example.user.infrastructures

import com.pal2hmnk.example.generated.orm.mapper.UsersDynamicSqlSupport
import com.pal2hmnk.example.generated.orm.mapper.UsersMapper
import com.pal2hmnk.example.generated.orm.mapper.selectOne
import com.pal2hmnk.example.shared.SqlSessionProvider
import com.pal2hmnk.example.user.domains.Name
import com.pal2hmnk.example.user.domains.User
import com.pal2hmnk.example.user.domains.UserRepository
import org.mybatis.dynamic.sql.util.kotlin.elements.isEqualTo

class UserRepositoryImpl(
    private val sessionProvider: SqlSessionProvider,
) : UserRepository {
    override fun findBy(name: Name): User = sessionProvider.get().use { session ->
        session.getMapper(UsersMapper::class.java).selectOne {
            where(UsersDynamicSqlSupport.Users.name, isEqualTo(name.value))
        }?.let { User(it.id, it.name) } ?: throw Exception()
    }
}
