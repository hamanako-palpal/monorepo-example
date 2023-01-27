package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.entities.PasswordHashed
import com.pal2hmnk.example.customers.domains.entities.PasswordHashedRepository
import com.pal2hmnk.example.customers.domains.entities.PasswordRow
import de.mkammerer.argon2.Argon2Factory
import org.springframework.stereotype.Repository

@Repository
class PasswordHashedRepositoryImpl : PasswordHashedRepository {

    private val argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id)

    override fun generate(passwordRow: PasswordRow): PasswordHashed {
        val hashed = argon2.hash(22, 65536, 1, passwordRow.value.toCharArray())
        return PasswordHashed(hashed)
    }

    override fun match(passwordHashed: PasswordHashed?, rowPassword: PasswordRow): Boolean {
        passwordHashed ?: return false
        return argon2.verify(
            passwordHashed.value,
            rowPassword.value.toCharArray()
        )
    }
}
