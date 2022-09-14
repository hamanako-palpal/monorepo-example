package com.pal2hmnk.example.contracts

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.pal2hmnk.example.contracts.domains.entities.OrderRepository
import com.pal2hmnk.example.contracts.infrastructures.OrderRepositoryImpl
import com.pal2hmnk.example.contracts.presentations.OrderHistoryController
import com.pal2hmnk.example.contracts.usecases.FindOrderHistoryImpl
import com.pal2hmnk.example.contracts.usecases.FindOrderHistory
import com.pal2hmnk.example.shared.infrastructures.persistence.DataSourceConfig
import com.pal2hmnk.example.shared.presentations.GrpcServerBase
import io.grpc.BindableService
import org.jetbrains.exposed.sql.Database

fun main() {
    val kodein = Kodein {
        bind<OrderRepository>() with singleton { OrderRepositoryImpl() }
        bind<FindOrderHistory>() with singleton { FindOrderHistoryImpl(instance()) }
        bind<List<BindableService>>() with singleton { listOf(OrderHistoryController(instance())) }
    }
    val dbConfig = DataSourceConfig()
    Database.connect(
        url = dbConfig.url!!,
        driver = dbConfig.driverClassName!!,
        user = dbConfig.username!!,
        password = dbConfig.password!!,
    )
    GrpcServerBase(kodein.instance()).runServer()
}

