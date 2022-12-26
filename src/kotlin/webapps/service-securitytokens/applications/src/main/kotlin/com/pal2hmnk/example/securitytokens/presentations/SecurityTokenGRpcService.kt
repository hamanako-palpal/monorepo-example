package com.pal2hmnk.example.securitytokens.presentations

import com.pal2hmnk.example.entities.SecurityToken
import com.pal2hmnk.example.generated.grpc.services.ConnectionId
import com.pal2hmnk.example.generated.grpc.services.GenerateTokenRequest
import com.pal2hmnk.example.generated.grpc.services.SecurityTokenServiceGrpcKt
import com.pal2hmnk.example.securitytokens.usecases.GenerateToken
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import com.pal2hmnk.example.values.Role
import com.pal2hmnk.example.values.ShopId
import com.pal2hmnk.example.values.UserId
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class SecurityTokenGRpcService(
    private val generateToken: GenerateToken,
) : SecurityTokenServiceGrpcKt.SecurityTokenServiceCoroutineImplBase() {

    override suspend fun generateToken(request: GenerateTokenRequest): ConnectionId =
        UseCaseRunner(
            transformer = { req: GenerateTokenRequest ->
                SecurityToken(
                    UserId(req.userId.value),
                    req.staffInfosList.map {
                        ShopId(it.shopId.value) to Role(it.role.roleKey)
                    },
                )
            },
            useCase = generateToken::exec,
            converter = {
                ConnectionId
                    .newBuilder()
                    .setValue(it)
                    .build()
            },
            exceptionHandler = { ConnectionId.getDefaultInstance() }
        ).run(request)
}
