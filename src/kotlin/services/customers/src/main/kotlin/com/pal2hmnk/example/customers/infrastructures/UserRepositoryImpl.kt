package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.domains.entities.UserRepository
import com.pal2hmnk.example.customers.domains.values.Email
import com.pal2hmnk.example.customers.domains.values.Name
import com.pal2hmnk.example.customers.domains.values.UserId
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.UserAuthentications
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun findBy(name: Name): User? = transaction {
        Users.select { Users.name eq name.value }
            .singleOrNull()
            ?.let { User(UserId(it[Users.id]), Email(it[Users.name])) }
    }

    override fun save(user: User): User? = transaction {
        val inserted = Users.insert {
            it[name] = user.name
        }[Users.id]
        user.userId = UserId(inserted)
        UserAuthentications.insert {
            it[this.userId] = user.userId!!.value
            it[this.email] = user.email.value
            it[this.password] = user.passwordHashed!!.value
        }
        user
    }
}
