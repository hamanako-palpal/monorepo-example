package com.pal2hmnk.example.shop

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.pal2hmnk.example.domain.shop.entities.OrderHistoryRepository
import com.pal2hmnk.example.shop.infrastructures.OrderHistoryRepositoryImpl
import com.pal2hmnk.example.shop.infrastructures.persistence.OrderSqlSessionProvider
import com.pal2hmnk.example.shop.presentations.ShopController
import com.pal2hmnk.example.shop.usecases.FindOrderHistoryImpl
import com.pal2hmnk.example.shop.usecases.FindOrderHistory
import com.pal2hmnk.example.shared.presentations.GrpcServerBase
import com.pal2hmnk.example.shared.infrastructures.SqlSessionProvider
import io.grpc.BindableService

fun main() {
    val kodein = Kodein {
        bind<SqlSessionProvider>() with singleton { OrderSqlSessionProvider() }
        bind<OrderHistoryRepository>() with singleton { OrderHistoryRepositoryImpl(instance()) }
        bind<FindOrderHistory>() with singleton { FindOrderHistoryImpl(instance()) }
        bind<List<BindableService>>() with singleton { listOf(ShopController(instance())) }
    }
    GrpcServerBase(kodein.instance()).runServer()
}

