package com.pal2hmnk.example.customers

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.pal2hmnk.example.customers.domains.entities.ShopRepository
import com.pal2hmnk.example.customers.domains.entities.UserRepository
import com.pal2hmnk.example.customers.infrastructures.ShopRepositoryImpl
import com.pal2hmnk.example.customers.infrastructures.UserRepositoryImpl
import com.pal2hmnk.example.customers.infrastructures.persistence.CustomersSqlSessionProvider
import com.pal2hmnk.example.customers.presentations.ShopController
import com.pal2hmnk.example.customers.presentations.UserController
import com.pal2hmnk.example.customers.usecases.FindShopsByIds
import com.pal2hmnk.example.customers.usecases.FindShopsByIdsImpl
import com.pal2hmnk.example.customers.usecases.FindUserByName
import com.pal2hmnk.example.customers.usecases.FindUserByNameImpl
import com.pal2hmnk.example.shared.infrastructures.SqlSessionProvider
import com.pal2hmnk.example.shared.presentations.GrpcServerBase
import io.grpc.BindableService

fun main() {
    val kodein = Kodein {
        bind<SqlSessionProvider>() with singleton { CustomersSqlSessionProvider() }
        bind<UserRepository>() with singleton { UserRepositoryImpl(instance()) }
        bind<ShopRepository>() with singleton { ShopRepositoryImpl(instance()) }
        bind<FindUserByName>() with singleton { FindUserByNameImpl(instance()) }
        bind<FindShopsByIds>() with singleton { FindShopsByIdsImpl(instance()) }
        bind<List<BindableService>>() with singleton {
            listOf(
                UserController(instance()),
                ShopController(instance()),
            )
        }
    }
    GrpcServerBase(kodein.instance()).runServer()
}
