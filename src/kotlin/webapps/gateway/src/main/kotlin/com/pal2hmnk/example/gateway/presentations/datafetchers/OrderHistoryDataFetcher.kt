package com.pal2hmnk.example.gateway.presentations.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.pal2hmnk.example.gateway.usecaes.OrderHistoryOutputData
import com.pal2hmnk.example.gateway.usecaes.FindOrderHistoriesByUserName
import com.pal2hmnk.example.generated.graphql.types.Order
import com.pal2hmnk.example.generated.graphql.types.OrderHistory
import com.pal2hmnk.example.generated.graphql.types.Shop
import com.pal2hmnk.example.generated.graphql.types.User

@DgsComponent
class OrderHistoryDataFetcher(
    private val scenario: FindOrderHistoriesByUserName,
) {
    @DgsQuery
    fun orderHistory(@InputArgument name: String): Order =
        scenario.exec(name).translate()

    private fun OrderHistoryOutputData.translate(): Order =
        Order(
            user = User(user.id, user.name),
            orders = orderHistory.map { OrderHistory(Shop(it.id, it.name), it.ordered) }
        )
}
