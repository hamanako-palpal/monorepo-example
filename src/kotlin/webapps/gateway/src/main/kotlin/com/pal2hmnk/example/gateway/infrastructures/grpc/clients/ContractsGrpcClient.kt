package com.pal2hmnk.example.gateway.infrastructures.grpc.clients

import com.google.protobuf.Empty
import com.pal2hmnk.example.gateway.configurations.GrpcClientConfiguration
import com.pal2hmnk.example.gateway.domains.values.UserId
import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.generated.grpc.services.OrderServiceGrpcKt
import org.springframework.stereotype.Component

@Component
class ContractsGrpcClient(
    config: GrpcClientConfiguration,
) : AbstractGrpcClient(
    name = config.contracts.addr,
    port = config.contracts.port,
) {
    private val orderStub = OrderServiceGrpcKt.OrderServiceCoroutineStub(channel)
    suspend fun findBy(id: UserId): OrderHistoryList {
        val empty = Empty.newBuilder().build()
        return orderStub.findOrderHistory(empty)
    }
}
