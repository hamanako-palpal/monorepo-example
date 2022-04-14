package com.pal2hmnk.example.gateway.infrastructures.grpc

import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.ShopGrpcClient
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.UserGrpcClient
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlin.reflect.KProperty

class GrpcChannelFactory {
    inline operator fun <reified E : GrpcClient> getValue(clientCls: E, property: KProperty<*>): ManagedChannel {
        val (name, port) = when (clientCls) {
            is UserGrpcClient -> "localhost" to 50051
            is ShopGrpcClient -> "localhost" to 50052
            else -> "localhost" to 99999
        }
        return ManagedChannelBuilder
            .forAddress(name, port)
            .usePlaintext()
            .build()
    }
}
