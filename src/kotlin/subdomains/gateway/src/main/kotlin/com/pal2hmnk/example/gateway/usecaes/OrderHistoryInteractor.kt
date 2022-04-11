package com.pal2hmnk.example.gateway.usecaes

import com.pal2hmnk.example.gateway.domains.OrderHistoryRepository
import com.pal2hmnk.example.gateway.domains.UserId
import com.pal2hmnk.example.gateway.domains.UserRepository
import com.pal2hmnk.example.util.DateConverter
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class OrderHistoryInteractor(
    private val userRepository: UserRepository,
    private val orderHistoryRepository: OrderHistoryRepository,
) : OrderHistoryScenario {
    override fun findByName(name: String): OrderHistoryOutputData =
        runBlocking {
            val user = userRepository.findUserInfo(name).let { UserOutput(it.id, it.name) }
            val orderHistory = orderHistoryRepository.findBy(UserId(user.id))
                .map { OrderHistoryOutput(it.shop.id, it.shop.name, DateConverter.toStr(it.Ordered)) }
            OrderHistoryOutputData(user, orderHistory)
        }
}
