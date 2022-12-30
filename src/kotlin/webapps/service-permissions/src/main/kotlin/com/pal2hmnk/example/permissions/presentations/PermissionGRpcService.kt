package com.pal2hmnk.example.permissions.presentations

import com.pal2hmnk.example.generated.grpc.services.ConnectionId
import com.pal2hmnk.example.generated.grpc.services.GenerateTokenRequest
import com.pal2hmnk.example.generated.grpc.services.PermissionServiceGrpcKt
import com.pal2hmnk.example.permissions.domains.entities.SecurityToken
import com.pal2hmnk.example.permissions.domains.usecases.GenerateToken
import com.pal2hmnk.example.permissions.domains.values.Role
import com.pal2hmnk.example.permissions.domains.values.ShopId
import com.pal2hmnk.example.permissions.domains.values.UserId
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class PermissionGRpcService(
    private val scenario: GenerateToken
) : PermissionServiceGrpcKt.PermissionServiceCoroutineImplBase() {
    override suspend fun generateToken(request: GenerateTokenRequest): ConnectionId =
        UseCaseRunner(
            transformer = { req : GenerateTokenRequest ->
                SecurityToken(
                    userId = UserId(req.userId.value),
                    stuffInfos = req.staffInfosList.map { staffInfo ->
                        ShopId(staffInfo.shopId.value) to Role(staffInfo.role.roleKey)
                    }
                )
            },
            useCase = scenario::exec,
            converter = { ConnectionId.newBuilder().setValue(it).build() },
            exceptionHandler = { ConnectionId.getDefaultInstance() },
        ).run(request)
}
