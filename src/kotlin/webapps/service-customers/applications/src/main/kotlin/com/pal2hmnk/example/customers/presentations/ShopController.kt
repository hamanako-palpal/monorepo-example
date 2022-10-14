package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.adapters.CustomersAdapter
import com.pal2hmnk.example.customers.usecases.FindShopsByIds
import com.pal2hmnk.example.generated.grpc.services.ShopIdsRequest
import com.pal2hmnk.example.generated.grpc.services.ShopInfos
import com.pal2hmnk.example.generated.grpc.services.ShopServiceGrpcKt
import com.pal2hmnk.example.shared.presentations.UseCaseRunner

class ShopController(
    private val scenario: FindShopsByIds,
) : ShopServiceGrpcKt.ShopServiceCoroutineImplBase() {

    private val useCaseRunner = UseCaseRunner(
        transformer = CustomersAdapter::inputDataOf,
        useCase = scenario::exec,
        converter = CustomersAdapter::translate,
        exceptionHandler = { ShopInfos.getDefaultInstance() }
    )

    override suspend fun findShopInfo(request: ShopIdsRequest): ShopInfos = useCaseRunner.run(request)
}
