package com.pal2hmnk.example.gateway.infrastructures.grpc.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "grpc.client")
@ConstructorBinding
data class GrpcClientConfig(
    val customers: Customers,
    val contracts: Contracts,
) {
    data class Customers(
        val addr: String,
        val port: Int,
    )
    data class Contracts(
        val addr: String,
        val port: Int,
    )
}
