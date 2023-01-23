package com.pal2hmnk.example.gateway.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.pal2hmnk.example.gateway.domains.usecaes.FindOrderHistoriesByUserName
import com.pal2hmnk.example.gateway.domains.usecaes.OrderHistoryInputData
import com.pal2hmnk.example.gateway.domains.usecaes.OrderHistoryOutputData
import com.pal2hmnk.example.generated.graphql.types.Order
import com.pal2hmnk.example.generated.graphql.types.OrderHistory
import com.pal2hmnk.example.generated.graphql.types.Shop
import com.pal2hmnk.example.generated.graphql.types.User
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import com.pal2hmnk.example.shared.utils.DateConverter
import reactor.core.publisher.Mono

@DgsComponent
class OrderHistoryDataFetcher(
    private val scenario: FindOrderHistoriesByUserName,
) {
    private val useCaseRunner = UseCaseRunner(
        useCase = scenario::exec,
        converter = ::asGql,
        exceptionHandler = { Mono.just(OrderHistory(User(0, ""), orders = emptyList())) }
    )

    @DgsQuery
    fun orderHistory(
        @InputArgument name: String,
        dataFetchingEnvironment: DgsDataFetchingEnvironment,
    ): Mono<OrderHistory> = useCaseRunner.initial { inputDataOf(name) }.run()

    private fun inputDataOf(name: String) = OrderHistoryInputData(name)

    private fun asGql(output: Mono<OrderHistoryOutputData>): Mono<OrderHistory> =
        output.map {
            OrderHistory(
                user = User(it.orderHistory.user.id.value, it.orderHistory.user.name),
                orders = it.orderHistory.orders.map { order ->
                    Order(
                        shop = Shop(order.shop.id.value, order.shop.name),
                        ordered = DateConverter.localDateTimeToStr(order.ordered)
                    )
                }
            )
        }
}
