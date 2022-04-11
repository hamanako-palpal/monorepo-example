package com.pal2hmnk.example.gateway.presentations.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.pal2hmnk.example.gateway.usecaes.OrderHistoryOutputData
import com.pal2hmnk.example.gateway.usecaes.OrderHistoryScenario
import com.pal2hmnk.example.generated.graphql.types.Order
import com.pal2hmnk.example.generated.graphql.types.OrderHistory
import com.pal2hmnk.example.generated.graphql.types.Shop
import com.pal2hmnk.example.generated.graphql.types.User

@DgsComponent
class OrderHistoryDataFetcher(
    private val scenario: OrderHistoryScenario,
) {
    @DgsQuery
    fun orderHistory(@InputArgument name: String): Order =
        toDTO(scenario.findByName(name))

    fun toDTO(output: OrderHistoryOutputData): Order {
        return Order(
            user = User(output.user.id, output.user.name),
            orders = output.orderHistory.map { OrderHistory(Shop(it.id, it.name), it.ordered) }
        )
    }
}
