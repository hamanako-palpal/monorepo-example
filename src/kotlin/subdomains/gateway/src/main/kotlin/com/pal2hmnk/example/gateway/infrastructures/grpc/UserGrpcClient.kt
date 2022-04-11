package com.pal2hmnk.example.gateway.infrastructures.grpc

import com.pal2hmnk.example.gateway.domains.User
import com.pal2hmnk.example.gateway.infrastructures.GrpcChannelFactory
import com.pal2hmnk.example.gateway.infrastructures.GrpcClient
import com.pal2hmnk.example.generated.grpc.services.UserRequest
import com.pal2hmnk.example.generated.grpc.services.UserServiceGrpcKt
import org.springframework.stereotype.Component

@Component
class UserGrpcClient : GrpcClient {
    final override val channel by GrpcChannelFactory()
    private val stub = UserServiceGrpcKt.UserServiceCoroutineStub(channel)

    suspend fun findUserInfo(name: String): User {
        val request = UserRequest.newBuilder().setName(name).build()
        return stub.findUserInfo(request).let { User(it.id, it.name) }
    }
}
