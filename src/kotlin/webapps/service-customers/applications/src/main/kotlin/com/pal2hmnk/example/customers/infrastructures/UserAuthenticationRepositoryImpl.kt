package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.entities.PasswordHashed
import com.pal2hmnk.example.customers.domains.entities.UserAuthentication
import com.pal2hmnk.example.customers.domains.entities.UserAuthenticationRepository
import com.pal2hmnk.example.customers.domains.values.Email
import com.pal2hmnk.example.customers.domains.values.UserId
import com.pal2hmnk.example.customers.infrastructures.persistence.exposed.UserAuthentications
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class UserAuthenticationRepositoryImpl : UserAuthenticationRepository {
    override fun findBy(email: Email): UserAuthentication? = transaction {
        UserAuthentications
            .select { UserAuthentications.email eq email.value }
            .singleOrNull()
            ?.let {
                UserAuthentication(
                    UserId(it[UserAuthentications.userId]),
                    Email(it[UserAuthentications.email]),
                    PasswordHashed(it[UserAuthentications.password]),
                )
            }
    }
}
