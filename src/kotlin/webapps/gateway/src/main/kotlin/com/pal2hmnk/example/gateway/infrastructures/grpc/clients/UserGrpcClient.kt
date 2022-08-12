package com.pal2hmnk.example.gateway.infrastructures.grpc.clients

import com.pal2hmnk.example.gateway.configs.GrpcClientConfig
import com.pal2hmnk.example.gateway.domains.User
import com.pal2hmnk.example.gateway.infrastructures.grpc.GrpcChannelFactory
import com.pal2hmnk.example.gateway.infrastructures.grpc.GrpcClient
import com.pal2hmnk.example.generated.grpc.services.UserRequest
import com.pal2hmnk.example.generated.grpc.services.UserServiceGrpcKt
import org.springframework.stereotype.Component

@Component
class UserGrpcClient(
    config: GrpcClientConfig,
) : GrpcClient {
    final override val channel = GrpcChannelFactory.createChannel(
        config.user.addr,
        config.user.port,
    )
    private val stub = UserServiceGrpcKt.UserServiceCoroutineStub(channel)

    suspend fun findUserInfo(name: String): User {
        val request = UserRequest.newBuilder().setName(name).build()
        return stub.findUserInfo(request).let { User(it.id, it.name) }
    }
}
