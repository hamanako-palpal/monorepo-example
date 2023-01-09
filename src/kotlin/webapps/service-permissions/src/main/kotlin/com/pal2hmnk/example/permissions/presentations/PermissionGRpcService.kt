package com.pal2hmnk.example.permissions.presentations

import com.pal2hmnk.example.generated.grpc.services.GenerateTokenRequest
import com.pal2hmnk.example.generated.grpc.services.Jwt
import com.pal2hmnk.example.generated.grpc.services.PermissionServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.TokenResult
import com.pal2hmnk.example.permissions.domains.entities.SecurityToken
import com.pal2hmnk.example.permissions.domains.usecases.GenerateToken
import com.pal2hmnk.example.permissions.domains.usecases.GetIdToken
import com.pal2hmnk.example.permissions.domains.values.Role
import com.pal2hmnk.example.permissions.domains.values.ShopId
import com.pal2hmnk.example.permissions.domains.values.UserId
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class PermissionGRpcService(
    private val getIdToken: GetIdToken,
    private val generateToken: GenerateToken,
) : PermissionServiceGrpcKt.PermissionServiceCoroutineImplBase() {
    override suspend fun getIdToken(request: Jwt): Jwt =
        UseCaseRunner(
            transformer = { it : Jwt -> it.value },
            useCase = getIdToken::exec,
            converter = { Jwt.newBuilder().setValue(it).build() },
            exceptionHandler = { Jwt.getDefaultInstance() },
        ).run(request)

    override suspend fun internalGenerateToken(request: GenerateTokenRequest): TokenResult =
        UseCaseRunner(
            transformer = { req: GenerateTokenRequest ->
                SecurityToken(
                    userId = UserId(req.userId.value),
                    stuffInfo = req.staffInfo.takeIf { it.hasShopId() }?.shopId?.let { ShopId(it.value) } to
                            req.staffInfo.takeIf { it.hasRole() }?.let { Role(it.role.roleKey) },
                    "example",
                )
            },
            useCase = generateToken::exec,
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
