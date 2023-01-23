package com.pal2hmnk.example.gateway.adapters

import com.pal2hmnk.example.generated.grpc.services.ShopIdsRequest

object ShopsAdapter {
    fun transform(shopIds: Collection<com.pal2hmnk.example.gateway.domains.values.ShopId>): ShopIdsRequest =
        ShopIdsRequest.newBuilder().also {
            shopIds.forEachIndexed { idx, shopId ->
                it.setIds(idx, shopId.value)
            }
        }.build()
}
