package com.pal2hmnk.example.gateway.usecaes

class OrderHistoryOutputData(
    val user: UserOutput,
    val orderHistory: List<OrderHistoryOutput>,
)

class UserOutput(val id: Int, val name: String)
class OrderHistoryOutput(
    val id: Int,
    val name: String,
    val ordered: String,
)
