package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.adapters.asGRpc
import com.pal2hmnk.example.customers.domains.entities.AccessToken
import com.pal2hmnk.example.customers.domains.entities.AccessTokenRepository
import com.pal2hmnk.example.customers.domains.entities.Stuff
import com.pal2hmnk.example.customers.infrastructures.grpc.client.PermissionsClient
import com.pal2hmnk.example.generated.grpc.services.Role
import com.pal2hmnk.example.generated.grpc.services.StaffInfo
import org.springframework.stereotype.Repository

@Repository
class AccessTokenRepositoryImpl(
    private val permissionsClient: PermissionsClient
) : AccessTokenRepository {
    override fun save(stuff: Stuff): AccessToken {
        return permissionsClient.issue {
            userId = stuff.userId.asGRpc()
            setStaffInfo(
                StaffInfo.newBuilder().apply {
                    stuff.shopId?.let { this.shopId = it.asGRpc() }
                    stuff.role?.let { this.setRole(Role.newBuilder().setRoleKey(it)) }
                }
            )
        }
            .let { AccessToken(it.accessToken.value) }
    }
}
