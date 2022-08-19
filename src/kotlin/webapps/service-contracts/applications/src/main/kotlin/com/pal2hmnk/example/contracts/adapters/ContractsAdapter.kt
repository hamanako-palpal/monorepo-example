package com.pal2hmnk.example.contracts.adapters

import com.pal2hmnk.example.contracts.usecases.OrderHistoryOutputData
import com.pal2hmnk.example.generated.grpc.services.OrderHistory
import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.generated.grpc.services.ShopId
import com.pal2hmnk.example.generated.grpc.services.UserId
import com.pal2hmnk.example.util.DateConverter
import com.pal2hmnk.example.contracts.domains.values.UserId as UserIdDomain

object ContractsAdapter {
    fun inputDataOf(request: UserId) = UserIdDomain(request.value)

    fun translate(outputData: OrderHistoryOutputData): OrderHistoryList =
        OrderHistoryList.newBuilder().also {
            it.setUserId(UserId.newBuilder().setValue(outputData.orderHistory.first().userId()))
            outputData.orderHistory.forEachIndexed { idx, history ->
                val grpcOrderHistory = OrderHistory.newBuilder().apply {
                    shopId = ShopId.newBuilder().setValue(history.shopId()).build()
                    ordered = DateConverter.localDateToStr(history.ordered)
                }.build()
                it.setOrderHistory(idx, grpcOrderHistory)
            }
        }.build()
}
