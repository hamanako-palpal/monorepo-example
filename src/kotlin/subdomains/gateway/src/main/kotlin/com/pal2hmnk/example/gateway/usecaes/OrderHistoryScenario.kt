package com.pal2hmnk.example.gateway.usecaes

interface OrderHistoryScenario {
    fun findByName(name: String): OrderHistoryOutputData
}
