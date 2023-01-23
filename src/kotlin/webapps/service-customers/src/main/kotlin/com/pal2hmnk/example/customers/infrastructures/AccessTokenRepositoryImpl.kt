package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.adapters.asGRpc
import com.pal2hmnk.example.customers.domains.entities.AccessToken
import com.pal2hmnk.example.customers.domains.entities.AccessTokenRepository
import com.pal2hmnk.example.customers.domains.entities.Staff
import com.pal2hmnk.example.customers.infrastructures.grpc.client.PermissionsClient
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Repository

@Repository
class AccessTokenRepositoryImpl(
    private val permissionsClient: PermissionsClient
) : AccessTokenRepository {
    override fun save(staff: Staff): AccessToken {
        return runBlocking {
            permissionsClient.issue {
                userId = staff.userId.value
                staffInfo = staff.asGRpc()
            }
        }.let { AccessToken(it.accessToken) }
    }
}
