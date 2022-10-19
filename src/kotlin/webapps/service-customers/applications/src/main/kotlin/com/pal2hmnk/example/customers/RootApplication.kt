package com.pal2hmnk.example.customers

import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.github.salomonbrys.kodein.singleton
import com.pal2hmnk.example.customers.domains.entities.AuthTokenRepository
import com.pal2hmnk.example.customers.domains.entities.PasswordHashedRepository
import com.pal2hmnk.example.customers.domains.entities.ShopRepository
import com.pal2hmnk.example.customers.domains.entities.UserRepository
import com.pal2hmnk.example.customers.infrastructures.AuthTokenRepositoryImpl
import com.pal2hmnk.example.customers.infrastructures.PasswordHashedRepositoryImpl
import com.pal2hmnk.example.customers.infrastructures.ShopRepositoryImpl
import com.pal2hmnk.example.customers.infrastructures.UserRepositoryImpl
import com.pal2hmnk.example.customers.presentations.AuthController
import com.pal2hmnk.example.customers.presentations.ShopController
import com.pal2hmnk.example.customers.presentations.UserController
import com.pal2hmnk.example.customers.usecases.Authenticate
import com.pal2hmnk.example.customers.usecases.AuthenticateImpl
import com.pal2hmnk.example.customers.usecases.FindShopsByIds
import com.pal2hmnk.example.customers.usecases.FindShopsByIdsImpl
import com.pal2hmnk.example.customers.usecases.FindUserByName
import com.pal2hmnk.example.customers.usecases.FindUserByNameImpl
import com.pal2hmnk.example.shared.infrastructures.persistence.DataSourceConfig
import com.pal2hmnk.example.shared.presentations.GrpcServerBase
import io.grpc.BindableService
import org.jetbrains.exposed.sql.Database

fun main() {
    val kodein = Kodein {
        bind<AuthTokenRepository>() with singleton { AuthTokenRepositoryImpl() }
        bind<PasswordHashedRepository>() with singleton { PasswordHashedRepositoryImpl() }
        bind<UserRepository>() with singleton { UserRepositoryImpl() }
        bind<ShopRepository>() with singleton { ShopRepositoryImpl() }

        bind<Authenticate>() with singleton { AuthenticateImpl(instance(), instance(), instance()) }
        bind<FindUserByName>() with singleton { FindUserByNameImpl(instance()) }
        bind<FindShopsByIds>() with singleton { FindShopsByIdsImpl(instance()) }
        bind<List<BindableService>>() with singleton {
            listOf(
                UserController(instance()),
                ShopController(instance()),
                AuthController(instance()),
            )
        }
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
