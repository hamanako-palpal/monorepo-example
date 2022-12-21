package com.pal2hmnk.example.gateway.infrastructures.grpc.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "grpc.client")
@ConstructorBinding
data class GrpcClientConfig(
    val customers: GrpcClient,
    val contracts: GrpcClient,
) {
    data class GrpcClient(
        val addr: String,
        val port: Int,
    )
}
