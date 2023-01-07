package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.customers.domains.entities.AccessToken
import com.pal2hmnk.example.customers.domains.entities.AccessTokenRepository
import com.pal2hmnk.example.customers.domains.entities.Stuff
import com.pal2hmnk.example.customers.infrastructures.grpc.client.PermissionsClient
import com.pal2hmnk.example.generated.grpc.services.Role
import com.pal2hmnk.example.generated.grpc.services.ShopId
import com.pal2hmnk.example.generated.grpc.services.StaffInfo
import com.pal2hmnk.example.generated.grpc.services.UserId
import org.springframework.stereotype.Repository

@Repository
class AccessTokenRepositoryImpl(
    private val permissionsClient: PermissionsClient
) : AccessTokenRepository {
    override fun save(stuff: Stuff): AccessToken {
        return permissionsClient.issue {
            setUserId(UserId.newBuilder().setValue(stuff.user.userId!!.value))
            setStaffInfo(
                StaffInfo.newBuilder()
                    .setShopId(ShopId.newBuilder().setValue(stuff.shopId.value))
                    .setRole(Role.newBuilder().setRoleKey(stuff.role))
            )
        }
            .let { AccessToken(it.accessToken.value) }
    }
}
