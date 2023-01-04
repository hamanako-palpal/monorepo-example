package com.pal2hmnk.example.contracts.presentations

import com.pal2hmnk.example.contracts.adapters.ContractsAdapter
import com.pal2hmnk.example.contracts.domains.usecases.FindOrderHistory
import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.generated.grpc.services.OrderServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.UserId
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class OrderHistoryController(
    private val scenario: FindOrderHistory
) : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {

    private val useCaseRunner = UseCaseRunner(
        transformer = ContractsAdapter::inputDataOf,
        useCase = scenario::exec,
        converter = ContractsAdapter::translate,
        exceptionHandler = { OrderHistoryList.getDefaultInstance() }
    )
    override suspend fun findOrderHistory(request: UserId): OrderHistoryList = useCaseRunner.run(request)
}
