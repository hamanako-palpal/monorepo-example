package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.adapters.asGRpc
import com.pal2hmnk.example.customers.domains.entities.Authentication
import com.pal2hmnk.example.customers.domains.entities.AuthenticationRepository
import com.pal2hmnk.example.customers.domains.entities.IssuedToken
import com.pal2hmnk.example.customers.domains.entities.Staff
import com.pal2hmnk.example.customers.infrastructures.grpc.client.PermissionsClient
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Repository

@Repository
class AuthenticationRepositoryImpl(
    private val permissionsClient: PermissionsClient
) : AuthenticationRepository {
    override fun save(staff: Staff): Authentication {
        val response = runBlocking {
            permissionsClient.issue {
                userId = staff.userId.value
                staffInfo = staff.asGRpc()
            }
        }
        return Authentication(
            IssuedToken(response.accessToken),
            IssuedToken(response.refreshToken),
        )
    }
}
