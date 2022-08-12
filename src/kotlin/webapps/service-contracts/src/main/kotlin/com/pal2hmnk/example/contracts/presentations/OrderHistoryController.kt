package com.pal2hmnk.example.contracts.presentations

import com.pal2hmnk.example.generated.grpc.services.OrderHistory
import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.generated.grpc.services.ShopServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.UserId
import com.pal2hmnk.example.contracts.adapters.ContractsRequestAdapter
import com.pal2hmnk.example.contracts.usecases.FindOrderHistory
import com.pal2hmnk.example.contracts.usecases.OrderHistoryOutputData
import com.pal2hmnk.example.util.DateConverter

class OrderHistoryController(
    private val scenario: FindOrderHistory
) : ShopServiceGrpcKt.ShopServiceCoroutineImplBase() {
    override suspend fun findOrderHistory(request: UserId): OrderHistoryList = try {
        val userId = ContractsRequestAdapter.transform(request)
        scenario.exec(userId).translate()
    } catch (e: Exception) {
        println(e)
        OrderHistoryList.getDefaultInstance()
    }

    private fun OrderHistoryOutputData.translate(): OrderHistoryList =
        OrderHistoryList.newBuilder().also {
            this.orderHistory.forEachIndexed { idx, history ->
                val grpcOrderHistory = OrderHistory.newBuilder().apply {
                    shopId = history.shopId()
                    ordered = DateConverter.localDateToStr(history.ordered)
                }.build()
                it.setOrderHistory(idx, grpcOrderHistory)
            }
        }.build()
}
