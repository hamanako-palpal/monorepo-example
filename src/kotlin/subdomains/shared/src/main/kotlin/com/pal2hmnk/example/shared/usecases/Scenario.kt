package com.pal2hmnk.example.shared.usecases

interface Scenario<T, Params> {
    fun exec(params: Params): T
}
