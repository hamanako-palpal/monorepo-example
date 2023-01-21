package com.pal2hmnk.example.gateway.infrastructures

import com.pal2hmnk.example.gateway.domains.entities.IdToken
import com.pal2hmnk.example.gateway.domains.entities.Order
import com.pal2hmnk.example.gateway.domains.querymodels.OrderHistory
import com.pal2hmnk.example.gateway.domains.querymodels.OrderHistoryQueryService
import com.pal2hmnk.example.gateway.domains.values.ShopId
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.ContractsGrpcClient
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.CustomersGrpcClient
import com.pal2hmnk.example.gateway.securities.GatewayIdentifiedToken
import com.pal2hmnk.example.shared.utils.DateConverter
import kotlinx.coroutines.runBlocking
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrderHistoryQueryServiceImpl(
    private val customersGrpcClient: CustomersGrpcClient,
    private val contractsGrpcClient: ContractsGrpcClient,
) : OrderHistoryQueryService {
    override fun findBy(name: String): Mono<OrderHistory> =
        ReactiveSecurityContextHolder.getContext().map { context ->
            val idToken = (context.authentication as GatewayIdentifiedToken).principal as IdToken

            val (user, orderHistoryMap) = runBlocking {
                val user = customersGrpcClient.findUserInfo(name)// , idToken.value
                val orderHistoryMap = contractsGrpcClient.findBy(user.id, idToken.value)
                    .orderHistoryList
                    .associateBy { ShopId(it.shopId.value) }

                Pair(user, orderHistoryMap)
            }
            val shopList = runBlocking {
                customersGrpcClient.findShopsByShopIds(orderHistoryMap.keys, idToken.value)
            }

            OrderHistory(
                user,
                orderHistoryMap.mapNotNull {
                    shopList
                        .find { shop -> shop.id == it.key }
                        ?.let { shop ->
                            Order(
                                shop,
                                DateConverter.strToLocalDateTime(it.value.ordered)
                            )
                        }
                },
            )
        }
}
