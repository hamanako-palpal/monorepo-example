package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.domains.values.Email
import com.pal2hmnk.example.customers.domains.values.Password
import com.pal2hmnk.example.customers.domains.usecases.Authenticate
import com.pal2hmnk.example.generated.grpc.services.AuthServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.ConnectionId
import com.pal2hmnk.example.generated.grpc.services.UserAuthInfo
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class AuthGRpcService(
    scenario: Authenticate,
) : AuthServiceGrpcKt.AuthServiceCoroutineImplBase() {

    private val useCaseRunner = UseCaseRunner(
        transformer = { userAuthInfo: UserAuthInfo ->
            Email(userAuthInfo.email.value) to Password(userAuthInfo.password)
        },
        useCase = scenario::exec,
        converter = { connectionId ->
            ConnectionId.newBuilder().setValue(connectionId).build()
        },
        exceptionHandler = { ConnectionId.getDefaultInstance() }
    )

    override suspend fun authenticate(request: UserAuthInfo): ConnectionId =
        useCaseRunner.run(request)
}
