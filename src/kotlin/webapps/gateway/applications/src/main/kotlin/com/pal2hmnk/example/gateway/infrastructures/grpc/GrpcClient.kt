package com.pal2hmnk.example.gateway.infrastructures.grpc

import io.grpc.ManagedChannel
import java.io.Closeable
import java.util.concurrent.TimeUnit

interface GrpcClient: Closeable {
    val channel: ManagedChannel
    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}
