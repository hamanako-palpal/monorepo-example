package com.pal2hmnk.example.permissions.presentations

import com.pal2hmnk.example.generated.grpc.services.GenerateTokenRequest
import com.pal2hmnk.example.generated.grpc.services.Jwt
import com.pal2hmnk.example.generated.grpc.services.PermissionServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.TokenResult
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
    override suspend fun internalGenerateToken(request: GenerateTokenRequest): TokenResult =
        UseCaseRunner(
            transformer = { req: GenerateTokenRequest ->
                SecurityToken(
                    userId = UserId(req.userId.value),
                    stuffInfo = ShopId(req.staffInfo.shopId.value) to Role(req.staffInfo.role.roleKey),
                    "gateway",
                )
            },
            useCase = scenario::exec,
            converter = {
                TokenResult.newBuilder()
                    .setRes(TokenResult.Result.CREARTED)
                    .setAccessToken(Jwt.newBuilder().setValue(it.accessToken))
                    .setRefreshToken(Jwt.newBuilder().setValue(it.refreshToken))
                    .build()
            },
            exceptionHandler = { TokenResult.getDefaultInstance() },
        ).run(request)
}
