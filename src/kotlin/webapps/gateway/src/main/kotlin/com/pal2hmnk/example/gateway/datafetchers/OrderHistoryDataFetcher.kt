package com.pal2hmnk.example.gateway.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.pal2hmnk.example.gateway.domains.entities.IdToken
import com.pal2hmnk.example.gateway.domains.usecaes.FindOrderHistoriesByUserName
import com.pal2hmnk.example.gateway.domains.usecaes.OrderHistoryInputData
import com.pal2hmnk.example.gateway.domains.usecaes.OrderHistoryOutputData
import com.pal2hmnk.example.gateway.securities.GatewayIdentifiedToken
import com.pal2hmnk.example.generated.graphql.types.Order
import com.pal2hmnk.example.generated.graphql.types.OrderHistory
import com.pal2hmnk.example.generated.graphql.types.Shop
import com.pal2hmnk.example.generated.graphql.types.User
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import com.pal2hmnk.example.shared.utils.DateConverter
import com.pal2hmnk.example.shared.utils.biBypass
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import reactor.core.publisher.Mono

@DgsComponent
class OrderHistoryDataFetcher(
    private val scenario: FindOrderHistoriesByUserName,
) {
    @DgsQuery
    fun orderHistory(
        @InputArgument name: String,
        dataFetchingEnvironment: DgsDataFetchingEnvironment,
    ): Mono<OrderHistory> = ReactiveSecurityContextHolder.getContext().map {
        val idToken = (it.authentication.principal as GatewayIdentifiedToken).principal as IdToken
        val useCaseRunner = UseCaseRunner(
                transformer = { inputDataOf(name, idToken.value) },
                useCase = scenario::exec,
                converter = ::translate,
                exceptionHandler = { OrderHistory(User(0, ""), orders = emptyList()) }
            )
        useCaseRunner.run(name, idToken.value)
    }

    private fun inputDataOf(name: String, token: String) =
        OrderHistoryInputData(name, token)

    private fun translate(output: OrderHistoryOutputData): OrderHistory =
        OrderHistory(
            user = User(output.orderHistory.user.id.value, output.orderHistory.user.name),
            orders = output.orderHistory.orders.map {
                Order(
                    shop = Shop(it.shop.id.value, it.shop.name),
                    ordered = DateConverter.localDateTimeToStr(it.ordered)
                )
            }
        )
}
