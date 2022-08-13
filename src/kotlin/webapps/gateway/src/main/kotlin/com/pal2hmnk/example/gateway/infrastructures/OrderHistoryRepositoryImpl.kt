package com.pal2hmnk.example.gateway.infrastructures

import com.pal2hmnk.example.gateway.domains.Order
import com.pal2hmnk.example.gateway.domains.OrderHistory
import com.pal2hmnk.example.gateway.domains.OrderHistoryRepository
import com.pal2hmnk.example.gateway.domains.ShopId
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.ContractsGrpcClient
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.CustomersGrpcClient
import com.pal2hmnk.example.util.DateConverter
import org.springframework.stereotype.Service

@Service
class OrderHistoryRepositoryImpl(
    private val customersGrpcClient: CustomersGrpcClient,
    private val contractsGrpcClient: ContractsGrpcClient,
) : OrderHistoryRepository {
    override suspend fun findBy(name: String): OrderHistory {
        val user = customersGrpcClient.findUserInfo(name)
        val orderHistoryMap = contractsGrpcClient.findBy(user.id).orderHistoryList
            .associateBy { ShopId(it.shopId.id) }
        val spaceIdSet = orderHistoryMap.keys
        val shopList = customersGrpcClient.findShopsByShopIds(spaceIdSet)

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
