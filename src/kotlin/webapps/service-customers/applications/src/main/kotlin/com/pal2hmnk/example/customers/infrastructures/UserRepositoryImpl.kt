package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.domains.entities.UserRepository
import com.pal2hmnk.example.customers.domains.values.Name
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepositoryImpl : UserRepository {
    override fun findBy(name: Name): User? = transaction {
//        Users.select { Users.name eq name.value }
//            .singleOrNull()
        null
    }
}
