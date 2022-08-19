package com.pal2hmnk.example.contracts.presentations

import com.pal2hmnk.example.contracts.adapters.ContractsAdapter
import com.pal2hmnk.example.contracts.usecases.FindOrderHistory
import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.generated.grpc.services.OrderServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.UserId

class OrderHistoryController(
    private val scenario: FindOrderHistory
) : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {
    override suspend fun findOrderHistory(request: UserId): OrderHistoryList = try {
        val inputData = ContractsAdapter.inputDataOf(request)
        val outputData = scenario.exec(inputData)
        ContractsAdapter.translate(outputData)
    } catch (e: Exception) {
        println(e)
        OrderHistoryList.getDefaultInstance()
    }
}
