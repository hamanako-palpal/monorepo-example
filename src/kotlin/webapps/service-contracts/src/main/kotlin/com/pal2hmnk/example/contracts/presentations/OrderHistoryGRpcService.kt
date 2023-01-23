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
import org.lognet.springboot.grpc.security.GrpcSecurity
import org.springframework.security.access.prepost.PreAuthorize

@GRpcService
class OrderHistoryGRpcService(
    private val scenario: FindOrderHistory,
) : OrderServiceGrpcKt.OrderServiceCoroutineImplBase() {

    private val useCaseRunner = UseCaseRunner(
        useCase = scenario::exec,
        converter = ContractsAdapter::orderHistoriesAsGRpc,
        exceptionHandler = { OrderHistoryList.getDefaultInstance() }
    )

    @PreAuthorize("hasRole('findOrderHistory')")
    override suspend fun findOrderHistory(request: Empty): OrderHistoryList {
        val auth = GrpcSecurity.AUTHENTICATION_CONTEXT_KEY.get() as ExampleIdentifiedToken
        val userIdentity = auth.principal as UserIdentity
        return useCaseRunner
            .initial { userIdentity.userId }
            .run()
    }
}
