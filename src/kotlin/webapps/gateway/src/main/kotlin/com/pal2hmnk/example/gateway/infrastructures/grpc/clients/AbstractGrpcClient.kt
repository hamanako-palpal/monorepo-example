package com.pal2hmnk.example.gateway.infrastructures.grpc.clients

import io.grpc.CallCredentials
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import java.io.Closeable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

abstract class AbstractGrpcClient(
    name: String?,
    port: Int?,
) : Closeable {
    val channel: ManagedChannel = ManagedChannelBuilder
        .forAddress(name ?: "localhost", port ?: 99999)
        .usePlaintext()
        .build()

    fun credentials(token: String) = object : CallCredentials() {
        override fun applyRequestMetadata(
            requestInfo: RequestInfo?,
            appExecutor: Executor,
            applier: MetadataApplier,
        ) {
            appExecutor.execute {
                try {
                    val headers = io.grpc.Metadata()
                    headers.put(
                        io.grpc.Metadata.Key.of("authorization", io.grpc.Metadata.ASCII_STRING_MARSHALLER),
                        "Bearer $token"
                    )
                    applier.apply(headers)
                } catch (e: Throwable) {
                    applier.fail(io.grpc.Status.UNAUTHENTICATED.withCause(e))
                }
            }
        }

        override fun thisUsesUnstableApi() {
            // yes this is unstable :(
        }
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}
