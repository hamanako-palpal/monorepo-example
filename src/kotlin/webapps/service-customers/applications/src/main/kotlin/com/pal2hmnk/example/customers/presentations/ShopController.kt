package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.adapters.CustomersRequestAdapter
import com.pal2hmnk.example.customers.domains.entities.Shop
import com.pal2hmnk.example.customers.usecases.FindShopsByIds
import com.pal2hmnk.example.generated.grpc.services.ShopId
import com.pal2hmnk.example.generated.grpc.services.ShopIdsRequest
import com.pal2hmnk.example.generated.grpc.services.ShopInfo
import com.pal2hmnk.example.generated.grpc.services.ShopInfos
import com.pal2hmnk.example.generated.grpc.services.ShopServiceGrpcKt

class ShopController(
    private val scenario: FindShopsByIds,
) : ShopServiceGrpcKt.ShopServiceCoroutineImplBase() {

    override suspend fun findShopInfo(request: ShopIdsRequest): ShopInfos {
        val shopList = scenario.exec(CustomersRequestAdapter.transform(request))
        return ShopInfos.newBuilder().also {
            shopList.forEachIndexed { idx, shop ->
                it.setShops(idx, shop.translate())
            }
        }.build()
    }

    private fun Shop.translate() = ShopInfo.newBuilder()
        .setId(ShopId.newBuilder().setId(this.shopId()))
        .setName(this.shopName())
}
