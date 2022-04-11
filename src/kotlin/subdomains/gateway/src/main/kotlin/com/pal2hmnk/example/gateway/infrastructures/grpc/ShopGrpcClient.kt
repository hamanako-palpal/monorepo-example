package com.pal2hmnk.example.gateway.infrastructures.grpc

import com.pal2hmnk.example.gateway.domains.UserId
import com.pal2hmnk.example.gateway.infrastructures.GrpcChannelFactory
import com.pal2hmnk.example.gateway.infrastructures.GrpcClient
import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.generated.grpc.services.ShopServiceGrpcKt
import org.springframework.stereotype.Component
import com.pal2hmnk.example.generated.grpc.services.UserId as GrpcUserId

@Component
class ShopGrpcClient : GrpcClient {
    final override val channel by GrpcChannelFactory()
    private val stub = ShopServiceGrpcKt.ShopServiceCoroutineStub(channel)
    suspend fun findBy(id: UserId): OrderHistoryList {
        val request = GrpcUserId.newBuilder().apply { setId(id.value) }.build()
        return stub.findOrderHistory(request)
    }
}
