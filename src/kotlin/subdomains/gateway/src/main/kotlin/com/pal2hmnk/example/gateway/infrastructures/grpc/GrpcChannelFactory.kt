package com.pal2hmnk.example.gateway.infrastructures.grpc

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

object GrpcChannelFactory {
    fun createChannel(name: String?, port: Int?): ManagedChannel =
        ManagedChannelBuilder
            .forAddress(name ?: "localhost", port ?: 99999)
            .usePlaintext()
            .build()
}
