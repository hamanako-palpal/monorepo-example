package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.domains.entities.UserRepository
import com.pal2hmnk.example.customers.domains.values.Name
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.Users
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun findBy(name: Name): User? = transaction {
        Users.select { Users.name eq name.value }
            .singleOrNull()
            ?.let {
                User(it[Users.id], Name(it[Users.name]))
            }
    }
}
