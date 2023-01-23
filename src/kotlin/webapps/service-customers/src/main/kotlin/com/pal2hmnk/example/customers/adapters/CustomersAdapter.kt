package com.pal2hmnk.example.customers.adapters

import com.pal2hmnk.example.customers.domains.entities.Shop
import com.pal2hmnk.example.customers.domains.entities.Staff
import com.pal2hmnk.example.generated.grpc.services.ShopIdsRequest
import com.pal2hmnk.example.generated.grpc.services.ShopInfo
import com.pal2hmnk.example.generated.grpc.services.ShopInfos
import com.pal2hmnk.example.generated.grpc.services.StaffInfo
import com.pal2hmnk.example.generated.grpc.services.UserInfo
import com.pal2hmnk.example.customers.domains.values.ShopId as ShopIdDomain

object CustomersAdapter {
    fun inputDataOf(request: ShopIdsRequest): List<ShopIdDomain> =
        request.idsList.map { ShopIdDomain(it) }

    fun translate(shopList: List<Shop>): ShopInfos = ShopInfos.newBuilder().also {
        shopList.forEachIndexed { idx, shop ->
            it.setShops(idx, shop.asGRpc())
        }
    }.build()
}

fun com.pal2hmnk.example.customers.domains.entities.User.asGRpc(): UserInfo =
    UserInfo.newBuilder()
        .setId(this.userId!!.value)
        .setName(this.name)
        .build()

fun Shop.asGRpc(): ShopInfo =
    ShopInfo.newBuilder()
        .setId(id.value)
        .setName(shopName.value)
        .build()

fun Staff.asGRpc(): StaffInfo =
    StaffInfo.newBuilder().also {
        this.shopId?.let { shop -> it.shopId = shop.value }
    }
        .setRole(this.getRole())
        .build()
