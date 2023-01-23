package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.adapters.asGRpc
import com.pal2hmnk.example.customers.domains.entities.PasswordRow
import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.domains.usecases.FindUserByName
import com.pal2hmnk.example.customers.domains.usecases.SignUp
import com.pal2hmnk.example.customers.domains.usecases.SignUpModel
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
            useCase = findUserByName::exec,
            converter = User::asGRpc,
            exceptionHandler = { UserInfo.getDefaultInstance() }
        )
            .initial { request.value }
            .run()

    override suspend fun signUp(request: SignUpRequest): UserInfo {
        return UseCaseRunner(
            useCase = signUp::exec,
            converter = User::asGRpc,
            exceptionHandler = { UserInfo.getDefaultInstance() }
        )
            .initial {
                SignUpModel(
                    request.name.value,
                    PasswordRow(request.authInfo.password),
                    Email(request.authInfo.email))
            }
            .run()
    }
}
