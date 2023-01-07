package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.domains.usecases.Authenticate
import com.pal2hmnk.example.customers.domains.values.Email
import com.pal2hmnk.example.customers.domains.entities.PasswordRow
import com.pal2hmnk.example.generated.grpc.services.AuthServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.Jwt
import com.pal2hmnk.example.generated.grpc.services.TokenResult
import com.pal2hmnk.example.generated.grpc.services.UserAuthInfo
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class AuthGRpcService(
    scenario: Authenticate,
) : AuthServiceGrpcKt.AuthServiceCoroutineImplBase() {

    private val useCaseRunner = UseCaseRunner(
        transformer = { userAuthInfo: UserAuthInfo ->
            Email(userAuthInfo.email.value) to PasswordRow(userAuthInfo.password)
        },
        useCase = scenario::exec,
        converter = {
            TokenResult.newBuilder()
                .setAccessToken(Jwt.newBuilder().setValue(it))
                .build()
        },
        exceptionHandler = { TokenResult.getDefaultInstance() }
    )

    override suspend fun authenticate(request: UserAuthInfo): TokenResult =
        useCaseRunner.run(request)
}
