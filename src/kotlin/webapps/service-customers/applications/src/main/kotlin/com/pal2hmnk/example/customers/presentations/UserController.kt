package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.adapters.CustomersAdapter
import com.pal2hmnk.example.customers.usecases.FindUserByName
import com.pal2hmnk.example.generated.grpc.services.UserInfo
import com.pal2hmnk.example.generated.grpc.services.UserName
import com.pal2hmnk.example.generated.grpc.services.UserServiceGrpcKt
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class UserController(
    private val scenario: FindUserByName,
) : UserServiceGrpcKt.UserServiceCoroutineImplBase() {

    private val useCaseRunner = UseCaseRunner(
        transformer = UserName::getValue,
        useCase = scenario::exec,
        converter = CustomersAdapter::translate,
        exceptionHandler = { UserInfo.getDefaultInstance() }
    )

    override suspend fun findUserInfoByName(request: UserName): UserInfo = useCaseRunner.run(request)
}
