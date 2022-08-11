package com.pal2hmnk.example.shared.presentations

import io.grpc.BindableService
import io.grpc.ServerBuilder

class GrpcServerBase(
    private val services: List<BindableService>,
) {
    private var port: Int? = null
    lateinit var server: io.grpc.Server

    fun runServer(overridePort: Int? = null) {
        initialize(overridePort)
        start()
        blockUntilShutdown()
    }

    private fun initialize(overridePort: Int? = null): GrpcServerBase {
        port = overridePort ?: System.getenv("PORT")?.toInt() ?: throw Exception("Can't find Server Port.")
        server = ServerBuilder.forPort(port!!).apply {
            services.forEach { this.addService(it) }
        }.build()

        return this
    }

    private fun start() {
        server.start()
        println("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** shutting down gRPC server since JVM is shutting down")
                stop()
                println("*** server shut down")
            }
        )
    }

    private fun stop() {
        server.shutdown()
    }

    private fun blockUntilShutdown() {
        server.awaitTermination()
    }
}
