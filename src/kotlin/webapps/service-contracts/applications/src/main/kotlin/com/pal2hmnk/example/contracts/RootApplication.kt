package com.pal2hmnk.example.contracts

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.pal2hmnk.example.contracts.domains.entities.OrderRepository
import com.pal2hmnk.example.contracts.infrastructures.OrderRepositoryImpl
import com.pal2hmnk.example.contracts.infrastructures.persistence.OrderSqlSessionProvider
import com.pal2hmnk.example.contracts.presentations.OrderHistoryController
import com.pal2hmnk.example.contracts.usecases.FindOrderHistoryImpl
import com.pal2hmnk.example.contracts.usecases.FindOrderHistory
import com.pal2hmnk.example.shared.presentations.GrpcServerBase
import com.pal2hmnk.example.shared.infrastructures.persistence.SqlSessionProvider
import io.grpc.BindableService

fun main() {
    val kodein = Kodein {
        bind<SqlSessionProvider>() with singleton { OrderSqlSessionProvider() }
        bind<OrderRepository>() with singleton { OrderRepositoryImpl(instance()) }
        bind<FindOrderHistory>() with singleton { FindOrderHistoryImpl(instance()) }
        bind<List<BindableService>>() with singleton { listOf(OrderHistoryController(instance())) }
    }
    GrpcServerBase(kodein.instance()).runServer()
}

