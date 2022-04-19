package com.pal2hmnk.example.shop.usecases

import com.pal2hmnk.example.shop.domains.OrderHistoryRepository
import com.pal2hmnk.example.shop.domains.UserId

class FindOrderHistoryImpl(
    private val repo: OrderHistoryRepository,
): FindOrderHistory {
    override fun exec(params: Int): OrderHistoryOutputData =
        OrderHistoryOutputData(repo.findBy(UserId(params)))
}
