package com.pal2hmnk.example.contracts.adapters

import com.pal2hmnk.example.contracts.domains.usecases.OrderHistoryOutputData
import com.pal2hmnk.example.generated.grpc.services.OrderHistory
import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.shared.utils.DateConverter

object ContractsAdapter {
    fun orderHistoriesAsGRpc(outputData: OrderHistoryOutputData): OrderHistoryList =
        OrderHistoryList.newBuilder().also {
            it.userId = outputData.userId.value
            when (outputData.orderHistory.size) {
                0 -> it.setOrderHistory(1, OrderHistory.getDefaultInstance())
                else -> outputData.orderHistory.forEachIndexed { idx, history ->
                    val grpcOrderHistory = OrderHistory.newBuilder().apply {
                        shopId = history.shopId()
                        ordered = DateConverter.localDateTimeToStr(history.ordered)
                    }.build()
                    it.setOrderHistory(idx, grpcOrderHistory)
                }
            }
        }.build()
}
