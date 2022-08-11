package com.pal2hmnk.example.shop.usecases

import com.pal2hmnk.example.domain.shop.entities.OrderHistoryRepository
import com.pal2hmnk.example.domain.shop.entities.UserId

class FindOrderHistoryImpl(
    private val repo: OrderHistoryRepository,
) : FindOrderHistory {
    override fun exec(params: Int): OrderHistoryOutputData =
        OrderHistoryOutputData(repo.findBy(UserId(params)))
}
