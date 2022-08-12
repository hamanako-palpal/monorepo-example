package com.pal2hmnk.example.gateway.usecaes

import com.pal2hmnk.example.gateway.domains.OrderHistoryRepository
import com.pal2hmnk.example.gateway.domains.UserId
import com.pal2hmnk.example.gateway.domains.UserRepository
import com.pal2hmnk.example.util.DateConverter
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class FindOrderHistoriesByUserNameImpl(
    private val userRepository: UserRepository,
    private val orderHistoryRepository: OrderHistoryRepository,
) : FindOrderHistoriesByUserName {
    override fun exec(params: String): OrderHistoryOutputData =
        runBlocking {
            val user = userRepository.findUserInfo(params).let { UserOutput(it.id, it.name) }
            val orderHistory = orderHistoryRepository.findBy(UserId(user.id))
                .map { OrderHistoryOutput(it.shop.id, it.shop.name, DateConverter.localDateToStr(it.Ordered)) }
            OrderHistoryOutputData(user, orderHistory)
        }
}
