package com.pal2hmnk.example.shop

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.pal2hmnk.example.shop.domains.OrderHistoryRepository
import com.pal2hmnk.example.shop.infrastructures.OrderHistoryRepositoryImpl
import com.pal2hmnk.example.shop.infrastructures.persistence.OrderSqlSessionProvider
import com.pal2hmnk.example.shop.presentations.OrderController
import com.pal2hmnk.example.shop.usecases.OrderInteractor
import com.pal2hmnk.example.shop.usecases.OrderScenario
import com.pal2hmnk.example.shared.GrpcServerBase
import com.pal2hmnk.example.shared.SqlSessionProvider
import io.grpc.BindableService

fun main() {
    val kodein = Kodein {
        bind<SqlSessionProvider>() with singleton { OrderSqlSessionProvider() }
        bind<OrderHistoryRepository>() with singleton { OrderHistoryRepositoryImpl(instance()) }
        bind<OrderScenario>() with singleton { OrderInteractor(instance()) }
        bind<List<BindableService>>() with singleton { listOf(OrderController(instance())) }
    }
    GrpcServerBase(kodein.instance()).runServer()
}

