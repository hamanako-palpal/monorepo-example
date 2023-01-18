package com.pal2hmnk.example.contracts.presentations

import com.google.protobuf.Empty
import com.pal2hmnk.example.contracts.adapters.ContractsAdapter
import com.pal2hmnk.example.contracts.domains.entities.UserIdentity
import com.pal2hmnk.example.contracts.domains.usecases.FindOrderHistory
import com.pal2hmnk.example.contracts.securities.ExampleIdentifiedToken
import com.pal2hmnk.example.generated.grpc.services.OrderHistoryList
import com.pal2hmnk.example.generated.grpc.services.OrderServiceGrpcKt
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import org.lognet.springboot.grpc.GRpcService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder

@GRpcService
class OrderHistoryGRpcService(
    private val scenario: FindOrderHistory,
) : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {

    private val useCaseRunner = UseCaseRunner(
        useCase = scenario::exec,
        converter = ContractsAdapter::translate,
        exceptionHandler = { OrderHistoryList.getDefaultInstance() }
    )

    @PreAuthorize("hasRole('findOrderHistory')")
    override suspend fun findOrderHistory(request: Empty): OrderHistoryList {
        val idToken = SecurityContextHolder.getContext().authentication as ExampleIdentifiedToken
        return useCaseRunner
            .initial { (idToken.principal as UserIdentity).userId }
            .run()
    }
}
