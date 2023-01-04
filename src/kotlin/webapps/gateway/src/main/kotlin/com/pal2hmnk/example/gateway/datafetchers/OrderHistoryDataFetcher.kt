package com.pal2hmnk.example.gateway.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.pal2hmnk.example.gateway.domains.usecaes.OrderHistoryOutputData
import com.pal2hmnk.example.gateway.domains.usecaes.FindOrderHistoriesByUserName
import com.pal2hmnk.example.generated.graphql.types.Order
import com.pal2hmnk.example.generated.graphql.types.OrderHistory
import com.pal2hmnk.example.generated.graphql.types.Shop
import com.pal2hmnk.example.generated.graphql.types.User
import com.pal2hmnk.example.shared.utils.DateConverter

@DgsComponent
class OrderHistoryDataFetcher(
    private val scenario: FindOrderHistoriesByUserName,
) {
    @DgsQuery
    fun orderHistory(@InputArgument name: String): OrderHistory =
        scenario.exec(name).translate()

    private fun OrderHistoryOutputData.translate(): OrderHistory =
        OrderHistory(
            user = User(orderHistory.user.id.value, orderHistory.user.name),
            orders = orderHistory.orders.map {
                Order(
                    shop = Shop(it.shop.id.value, it.shop.name),
                    ordered = DateConverter.localDateTimeToStr(it.ordered)
                )
            }
        )
}
