package com.pal2hmnk.example.gateway.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "grpc.client")
@ConstructorBinding
data class GrpcClientConfig(
    val user: User,
    val shop: Shop,
) {
    data class User(
        val addr: String,
        val port: Int,
    )
    data class Shop(
        val addr: String,
        val port: Int,
    )
}
