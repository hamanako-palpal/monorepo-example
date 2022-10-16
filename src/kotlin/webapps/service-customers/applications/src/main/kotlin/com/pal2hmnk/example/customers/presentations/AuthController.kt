package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.domains.values.Name
import com.pal2hmnk.example.customers.domains.values.Password
import com.pal2hmnk.example.customers.usecases.Authenticate
import com.pal2hmnk.example.generated.grpc.services.AuthServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.Token
import com.pal2hmnk.example.generated.grpc.services.UserAuthInfo
import com.pal2hmnk.example.shared.presentations.UseCaseRunner

class AuthController(
    scenario: Authenticate,
) : AuthServiceGrpcKt.AuthServiceCoroutineImplBase() {

    private val useCaseRunner = UseCaseRunner(
        transformer = { userAuthInfo : UserAuthInfo ->
            Name(userAuthInfo.userName.value) to Password(userAuthInfo.password)
        },
        useCase = scenario::exec,
        converter = {
                    it?.let { Token.newBuilder().setValue(it.value).build() }
        },
        exceptionHandler = { Token.getDefaultInstance() }
    )

    override suspend fun findCredential(request: UserAuthInfo): Token = useCaseRunner.run(request)
}
