package com.pal2hmnk.example.customers.adapters

import com.pal2hmnk.example.customers.domains.entities.Shop
import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.generated.grpc.services.ShopId
import com.pal2hmnk.example.generated.grpc.services.ShopIdsRequest
import com.pal2hmnk.example.generated.grpc.services.ShopInfo
import com.pal2hmnk.example.generated.grpc.services.ShopInfos
import com.pal2hmnk.example.generated.grpc.services.UserInfo
import com.pal2hmnk.example.customers.domains.values.ShopId as ShopIdDomain

object CustomersAdapter {
    fun inputDataOf(request: ShopIdsRequest): List<ShopIdDomain> =
        request.idsList.map { ShopIdDomain(it.value) }

    fun translate(shopList: List<Shop>): ShopInfos = ShopInfos.newBuilder().also {
        shopList.forEachIndexed { idx, shop ->
            it.setShops(idx, translate(shop))
        }
    }.build()

    fun translate(user: User): UserInfo =
        UserInfo.newBuilder()
            .setId(user.userId)
            .setName(user.email.value)
            .build()

    private fun translate(shop: Shop): ShopInfo =
        ShopInfo.newBuilder()
            .setId(ShopId.newBuilder().setValue(shop.shopId()))
            .setName(shop.shopName())
            .build()
}
