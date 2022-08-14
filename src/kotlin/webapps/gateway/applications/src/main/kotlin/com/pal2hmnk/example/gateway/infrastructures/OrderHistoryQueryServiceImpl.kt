package com.pal2hmnk.example.gateway.infrastructures

import com.pal2hmnk.example.gateway.domains.entities.Order
import com.pal2hmnk.example.gateway.domains.querymodels.OrderHistory
import com.pal2hmnk.example.gateway.domains.querymodels.OrderHistoryQueryService
import com.pal2hmnk.example.gateway.domains.values.ShopId
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.ContractsGrpcClient
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.CustomersGrpcClient
import com.pal2hmnk.example.util.DateConverter
import org.springframework.stereotype.Service

@Service
class OrderHistoryQueryServiceImpl(
    private val customersGrpcClient: CustomersGrpcClient,
    private val contractsGrpcClient: ContractsGrpcClient,
) : OrderHistoryQueryService {
    override suspend fun findBy(name: String): OrderHistory {
        val user = customersGrpcClient.findUserInfo(name)
        val orderHistoryMap = contractsGrpcClient.findBy(user.id).orderHistoryList
            .associateBy { ShopId(it.shopId.value) }
        val shopList = customersGrpcClient.findShopsByShopIds(orderHistoryMap.keys)

        return OrderHistory(
            user,
            orderHistoryMap.mapNotNull {
                shopList
                    .find { shop -> shop.id == it.key }
                    ?.let { shop ->
                        Order(
                            shop,
                            DateConverter.strToLocalDate(it.value.ordered)
                        )
                    }
            },
        )
    }
}
