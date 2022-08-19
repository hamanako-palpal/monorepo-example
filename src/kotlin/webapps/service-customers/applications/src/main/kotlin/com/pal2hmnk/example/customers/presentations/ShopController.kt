package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.adapters.CustomersAdapter
import com.pal2hmnk.example.customers.usecases.FindShopsByIds
import com.pal2hmnk.example.generated.grpc.services.ShopIdsRequest
import com.pal2hmnk.example.generated.grpc.services.ShopInfos
import com.pal2hmnk.example.generated.grpc.services.ShopServiceGrpcKt

class ShopController(
    private val scenario: FindShopsByIds,
) : ShopServiceGrpcKt.ShopServiceCoroutineImplBase() {

    override suspend fun findShopInfo(request: ShopIdsRequest): ShopInfos {
        val inputData = CustomersAdapter.inputDataOf(request)
        val outputData = scenario.exec(inputData)
        return CustomersAdapter.translate(outputData)
    }
}
