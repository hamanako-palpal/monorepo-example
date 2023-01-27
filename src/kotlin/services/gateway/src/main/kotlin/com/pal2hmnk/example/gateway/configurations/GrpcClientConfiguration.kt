package com.pal2hmnk.example.gateway.configurations

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "grpc.client")
@ConstructorBinding
data class GrpcClientConfiguration(
    val customers: GrpcClientProperties,
    val contracts: GrpcClientProperties,
    val permissions: GrpcClientProperties,
) {
    data class GrpcClientProperties(
        val addr: String,
        val port: Int,
    )
}
