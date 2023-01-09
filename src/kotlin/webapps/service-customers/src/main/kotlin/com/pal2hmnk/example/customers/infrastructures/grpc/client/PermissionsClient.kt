package com.pal2hmnk.example.customers.infrastructures.grpc.client

import com.pal2hmnk.example.generated.grpc.services.GenerateTokenRequest
import com.pal2hmnk.example.generated.grpc.services.Jwt
import com.pal2hmnk.example.generated.grpc.services.PermissionServiceGrpcKt
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import java.io.Closeable
import java.util.concurrent.TimeUnit

@Component
class PermissionsClient : Closeable {

    private val name = "localhost"

    private val port = 50053

    private val channel = ManagedChannelBuilder
        .forAddress(name, port)
        .usePlaintext()
        .build()

    private val stub = PermissionServiceGrpcKt.PermissionServiceCoroutineStub(channel)

    fun issue(request: GenerateTokenRequest.Builder.() -> Unit) = runBlocking {
        val built = GenerateTokenRequest.newBuilder().apply(request).build()
        stub.internalGenerateToken(built)
    }

    suspend fun findIdentity(token: String): String {
        val jwt = Jwt.newBuilder().setValue(token).build()
        return stub.getIdToken(jwt).value
    }

    override fun close() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }
}
