package com.pal2hmnk.example.contracts.infrastructures

import com.pal2hmnk.example.contracts.domains.entities.UserIdentity
import com.pal2hmnk.example.contracts.domains.entities.UserIdentityRepository
import com.pal2hmnk.example.contracts.domains.entities.UserIdentityResolver
import org.springframework.stereotype.Component

@Component
class UserIdentityRepositoryImpl(
    private val userIdentityResolver: UserIdentityResolver,
) : UserIdentityRepository {
    override fun getUserIdentity(token: String): UserIdentity =
        userIdentityResolver.resolve(token)
}
