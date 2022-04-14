package com.pal2hmnk.example.shop.presentations

import com.pal2hmnk.example.generated.grpc.services.OrderHistory
import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.generated.grpc.services.ShopServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.UserId
import com.pal2hmnk.example.shop.usecases.FindOrderHistory
import com.pal2hmnk.example.shop.usecases.OutputData
import com.pal2hmnk.example.util.DateConverter

class ShopController(
    private val scenario: FindOrderHistory
) : ShopServiceGrpcKt.ShopServiceCoroutineImplBase() {
    override suspend fun findOrderHistory(request: UserId): OrderHistoryList =
        scenario.exec(request.id).translate()

    private fun OutputData.translate(): OrderHistoryList =
        OrderHistoryList.newBuilder().also {
            this.value.forEachIndexed { idx, history ->
                val grpcOrderHistory = OrderHistory.newBuilder().apply {
                    shopId = history.shopId.value
                    ordered = DateConverter.toStr(history.Ordered)
                }.build()
                it.setOrderHistory(idx, grpcOrderHistory)
            }
        }.build()
}
