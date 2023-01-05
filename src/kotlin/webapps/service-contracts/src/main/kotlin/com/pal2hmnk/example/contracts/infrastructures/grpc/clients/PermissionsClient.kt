package com.pal2hmnk.example.contracts.infrastructures.grpc.clients

import com.pal2hmnk.example.generated.grpc.services.Jwt
import com.pal2hmnk.example.generated.grpc.services.PermissionServiceGrpcKt
import io.grpc.ManagedChannelBuilder
import org.springframework.stereotype.Component
import java.io.Closeable
import java.util.concurrent.TimeUnit

@Component
class PermissionsClient : Closeable {

    private val name = "permissions"

    private val port = 19092

    private val channel = ManagedChannelBuilder
        .forAddress(name, port)
        .usePlaintext()
        .build()

    private val stub = PermissionServiceGrpcKt.PermissionServiceCoroutineStub(channel)

    suspend fun findIdentity(token: String): String {
        val jwt = Jwt.newBuilder().setValue(token).build()
        return stub.getIdToken(jwt).value
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}
