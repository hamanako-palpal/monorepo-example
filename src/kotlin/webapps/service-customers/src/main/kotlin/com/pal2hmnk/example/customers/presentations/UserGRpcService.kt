package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.adapters.CustomersAdapter
import com.pal2hmnk.example.customers.domains.entities.PasswordRow
import com.pal2hmnk.example.customers.domains.usecases.FindUserByName
import com.pal2hmnk.example.customers.domains.usecases.SignUp
import com.pal2hmnk.example.customers.domains.values.Email
import com.pal2hmnk.example.generated.grpc.services.SignUpRequest
import com.pal2hmnk.example.generated.grpc.services.UserInfo
import com.pal2hmnk.example.generated.grpc.services.UserName
import com.pal2hmnk.example.generated.grpc.services.UserServiceGrpcKt
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class UserGRpcService(
    private val signUp: SignUp,
    private val findUserByName: FindUserByName,
) : UserServiceGrpcKt.UserServiceCoroutineImplBase() {

    override suspend fun findUserInfoByName(request: UserName): UserInfo =
        UseCaseRunner(
            transformer = UserName::getValue,
            useCase = findUserByName::exec,
            converter = CustomersAdapter::translate,
            exceptionHandler = { UserInfo.getDefaultInstance() }
        ).run(request)

    override suspend fun signUp(request: SignUpRequest): UserInfo {
        return UseCaseRunner(
            transformer = { req: SignUpRequest ->
                Triple(req.name.value, req.authInfo.email.value, req.authInfo.password)
            },
            useCase = { (name, email, pass) ->
                signUp.exec(name, PasswordRow(pass), Email(email))
            },
            converter = {
                UserInfo.newBuilder()
                    .setId(it.userId!!.value)
                    .setName(it.name)
                    .build()
            },
            exceptionHandler = { UserInfo.getDefaultInstance() }
        ).run(request)
    }
}
