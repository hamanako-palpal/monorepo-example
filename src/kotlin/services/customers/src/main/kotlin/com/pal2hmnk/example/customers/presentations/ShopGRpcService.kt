package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.adapters.CustomersAdapter
import com.pal2hmnk.example.customers.domains.usecases.FindShopsByIds
import com.pal2hmnk.example.generated.grpc.services.ShopIdsRequest
import com.pal2hmnk.example.generated.grpc.services.ShopInfos
import com.pal2hmnk.example.generated.grpc.services.ShopServiceGrpcKt
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class ShopGRpcService(
    scenario: FindShopsByIds,
) : ShopServiceGrpcKt.ShopServiceCoroutineImplBase() {

    private val useCaseRunner = UseCaseRunner(
        useCase = scenario::exec,
        converter = CustomersAdapter::shopInfosAsGRpc,
        exceptionHandler = { ShopInfos.getDefaultInstance() }
    )

    override suspend fun findShopInfo(request: ShopIdsRequest): ShopInfos =
        useCaseRunner
            .initial { CustomersAdapter.inputDataOf(request) }
            .run()
}
