package com.pal2hmnk.example.gateway.infrastructures

import com.pal2hmnk.example.gateway.domains.OrderHistory
import com.pal2hmnk.example.gateway.domains.OrderHistoryRepository
import com.pal2hmnk.example.gateway.domains.Shop
import com.pal2hmnk.example.gateway.domains.UserId
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.ShopGrpcClient
import com.pal2hmnk.example.util.DateConverter
import org.springframework.stereotype.Service

@Service
class OrderHistoryRepositoryImpl(
    private val shopGrpcClient: ShopGrpcClient,
) : OrderHistoryRepository {
    override suspend fun findBy(id: UserId): List<OrderHistory> {
        val orderHistoryList = shopGrpcClient.findBy(id).orderHistoryList
        return orderHistoryList.map {
            OrderHistory(
                Shop(
                    it.shopId,
                    it.name
                ),
                DateConverter.strToLocalDate(it.ordered)
            )
        }
    }
}
