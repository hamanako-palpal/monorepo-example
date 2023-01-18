package com.pal2hmnk.example.gateway.infrastructures.grpc.clients

import com.pal2hmnk.example.gateway.configurations.GrpcClientConfiguration
import com.pal2hmnk.example.generated.grpc.services.Jwt
import com.pal2hmnk.example.generated.grpc.services.PermissionServiceGrpcKt
import org.springframework.stereotype.Component

@Component
class PermissionsGrpcClient(
    grpcClientConfiguration: GrpcClientConfiguration,
) : AbstractGrpcClient(
    name = grpcClientConfiguration.permissions.addr,
    port = grpcClientConfiguration.permissions.port,
) {
    private val stub = PermissionServiceGrpcKt.PermissionServiceCoroutineStub(channel)

    suspend fun getIdentity(token: String): String {
        val jwt = Jwt.newBuilder().setValue(token).build()
        return stub.getIdToken(jwt).value
    }
}
