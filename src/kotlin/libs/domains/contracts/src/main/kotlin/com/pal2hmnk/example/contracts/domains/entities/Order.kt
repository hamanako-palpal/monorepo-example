package com.pal2hmnk.example.contracts.domains.entities

import com.pal2hmnk.example.contracts.domains.values.ShopId
import com.pal2hmnk.example.contracts.domains.values.UserId
import java.time.LocalDate

class Order(
    val orderId: Int,
    val userId: UserId,
    val shopId: ShopId,
    val ordered: LocalDate,
) {
    fun userId() = this.userId.value
    fun shopId() = this.shopId.value
}
