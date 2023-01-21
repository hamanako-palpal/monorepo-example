package com.pal2hmnk.example.customers.adapters

import com.pal2hmnk.example.customers.domains.entities.Shop
import com.pal2hmnk.example.generated.grpc.services.ShopId
import com.pal2hmnk.example.generated.grpc.services.ShopIdsRequest
import com.pal2hmnk.example.generated.grpc.services.ShopInfo
import com.pal2hmnk.example.generated.grpc.services.ShopInfos
import com.pal2hmnk.example.generated.grpc.services.UserId
import com.pal2hmnk.example.generated.grpc.services.UserInfo
import com.pal2hmnk.example.customers.domains.values.ShopId as ShopIdDomain

object CustomersAdapter {
    fun inputDataOf(request: ShopIdsRequest): List<ShopIdDomain> =
        request.idsList.map { ShopIdDomain(it.value) }

    fun translate(shopList: List<Shop>): ShopInfos = ShopInfos.newBuilder().also {
        shopList.forEachIndexed { idx, shop ->
            it.setShops(idx, shop.asGRpc())
        }
    }.build()
}

fun com.pal2hmnk.example.customers.domains.values.UserId.asGRpc(): UserId =
    UserId.newBuilder().setValue(this.value).build()

fun com.pal2hmnk.example.customers.domains.values.ShopId.asGRpc(): ShopId =
    ShopId.newBuilder().setValue(this.value).build()

fun com.pal2hmnk.example.customers.domains.entities.User.asGrpc(): UserInfo =
    UserInfo.newBuilder()
        .setId(this.userId!!.value)
        .setName(this.name)
        .build()

fun Shop.asGRpc(): ShopInfo =
    ShopInfo.newBuilder()
        .setId(id.asGRpc())
        .setName(shopName.value)
        .build()
