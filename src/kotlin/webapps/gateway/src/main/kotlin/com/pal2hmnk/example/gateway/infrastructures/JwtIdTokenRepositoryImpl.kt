package com.pal2hmnk.example.gateway.infrastructures

import com.pal2hmnk.example.gateway.domains.entities.IdToken
import com.pal2hmnk.example.gateway.domains.entities.IdTokenRepository
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.PermissionsGrpcClient
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Repository

@Repository
class JwtIdTokenRepositoryImpl(
    private val permissionsGrpcClient : PermissionsGrpcClient,
) : IdTokenRepository {
    override fun get(authToken: String): IdToken = runBlocking {
        IdToken(
            permissionsGrpcClient.getIdentity(authToken),
            isValid = true,
        )
    }
}
