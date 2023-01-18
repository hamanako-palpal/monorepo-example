package com.pal2hmnk.example.gateway.infrastructures.grpc.clients

import com.pal2hmnk.example.gateway.adapters.ShopsAdapter
import com.pal2hmnk.example.gateway.configurations.GrpcClientConfiguration
import com.pal2hmnk.example.gateway.domains.entities.Shop
import com.pal2hmnk.example.gateway.domains.entities.User
import com.pal2hmnk.example.gateway.domains.values.ShopId
import com.pal2hmnk.example.gateway.domains.values.UserId
import com.pal2hmnk.example.generated.grpc.services.Email
import com.pal2hmnk.example.generated.grpc.services.ShopServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.SignUpRequest
import com.pal2hmnk.example.generated.grpc.services.UserAuthInfo
import com.pal2hmnk.example.generated.grpc.services.UserName
import com.pal2hmnk.example.generated.grpc.services.UserServiceGrpcKt
import org.springframework.stereotype.Component

@Component
class CustomersGrpcClient(
    config: GrpcClientConfiguration,
) : AbstractGrpcClient(
    name = config.contracts.addr,
    port = config.contracts.port,
) {
    private val userStub = UserServiceGrpcKt.UserServiceCoroutineStub(channel)
    private val shopStub = ShopServiceGrpcKt.ShopServiceCoroutineStub(channel)

    suspend fun signUp(userName: String, password: String, email: String): User {
        val request = SignUpRequest.newBuilder().apply {
            setAuthInfo(
                UserAuthInfo.newBuilder()
                    .setEmail(Email.newBuilder().setValue(email))
                    .setPassword(password)
            )
            setName(UserName.newBuilder().setValue(userName))
        }.build()
        return userStub.signUp(request).let { User(UserId(it.id), it.name) }
    }

    suspend fun findUserInfo(name: String): User {
        val request = UserName.newBuilder().setValue(name).build()
        return userStub.findUserInfoByName(request).let { User(UserId(it.id), it.name) }
    }

    suspend fun findShopsByShopIds(shopIds: Set<ShopId>): List<Shop> {
        val request = ShopsAdapter.transform(shopIds)
        return shopStub.findShopInfo(request).shopsList.map {
            Shop(ShopId(it.id.value), it.name)
        }
    }
}
