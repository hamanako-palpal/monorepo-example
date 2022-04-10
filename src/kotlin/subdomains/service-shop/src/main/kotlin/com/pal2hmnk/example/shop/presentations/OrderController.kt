package com.pal2hmnk.example.shop.presentations

import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.generated.grpc.services.OrderServiceGrpcKt
import com.pal2hmnk.example.shop.domains.UserId
import com.pal2hmnk.example.shop.usecases.OrderScenario
import com.pal2hmnk.example.shop.usecases.OrderTranslator
import com.pal2hmnk.example.generated.grpc.services.UserId as GrpcUserId

class OrderController(
    private val scenario: OrderScenario
) : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {
    override suspend fun findOrderHistory(request: GrpcUserId): OrderHistoryList =
        scenario.findOrderHistory(UserId(request.id))
            .let { OrderTranslator.toDTO(it) }
}
