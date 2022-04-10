package com.pal2hmnk.example.shop.usecases

import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.shop.domains.OrderHistory
import java.time.format.DateTimeFormatter
import com.pal2hmnk.example.generated.grpc.services.OrderHistory as GrpcOrderHistory

class OrderTranslator {
    companion object {
        val toDTO: (List<OrderHistory>) -> OrderHistoryList = {
            OrderHistoryList.newBuilder().apply {
                it.forEachIndexed { idx, history ->
                    val grpcOrderHistory = GrpcOrderHistory.newBuilder().apply {
                        shopId = history.shopId.value
                        ordered = history.Ordered.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    }.build()
                    this.setOrderHistory(idx, grpcOrderHistory)
                }
            }.build()
        }
    }
}
