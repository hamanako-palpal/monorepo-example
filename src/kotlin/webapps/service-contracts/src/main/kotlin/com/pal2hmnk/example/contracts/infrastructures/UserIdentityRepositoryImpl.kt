package com.pal2hmnk.example.contracts.infrastructures

import com.pal2hmnk.example.contracts.domains.entities.UserIdentity
import com.pal2hmnk.example.contracts.domains.entities.UserIdentityRepository
import com.pal2hmnk.example.contracts.domains.entities.UserIdentityResolver
import com.pal2hmnk.example.contracts.infrastructures.grpc.clients.PermissionsClient
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component

@Component
class UserIdentityRepositoryImpl(
    private val permissionsClient: PermissionsClient,
    private val userIdentityResolver: UserIdentityResolver,
) : UserIdentityRepository {
    override fun getUserIdentity(token: String): UserIdentity =
        runBlocking {
            val idToken = permissionsClient.findIdentity(token)
            userIdentityResolver.resolve(idToken)
        }
}
