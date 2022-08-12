package com.pal2hmnk.example.customers.adapters

import com.pal2hmnk.example.customers.domains.values.ShopId
import com.pal2hmnk.example.generated.grpc.services.ShopIdsRequest

object CustomersRequestAdapter {
    fun transform(request: ShopIdsRequest): List<ShopId> =
        request.idsList.map { ShopId(it.id) }
}
