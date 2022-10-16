package com.pal2hmnk.example.gateway.infrastructures.grpc.clients

import com.pal2hmnk.example.gateway.domains.values.UserId
import com.pal2hmnk.example.gateway.infrastructures.grpc.GrpcClient
import com.pal2hmnk.example.gateway.infrastructures.grpc.configs.GrpcClientConfig
import com.pal2hmnk.example.gateway.infrastructures.grpc.factories.GrpcFactory
import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.generated.grpc.services.OrderServiceGrpcKt
import org.springframework.stereotype.Component

@Component
class ContractsGrpcClient(
    config: GrpcClientConfig,
) : GrpcClient {
    final override val channel = GrpcFactory.createChannel(
        config.contracts.addr,
        config.contracts.port,
    )
    private val orderStub = OrderServiceGrpcKt.OrderServiceCoroutineStub(channel)
    suspend fun findBy(id: UserId): OrderHistoryList {
        val request = GrpcFactory.userId().setValue(id.value).build()
        return orderStub.findOrderHistory(request)
    }
}
