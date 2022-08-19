package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.adapters.CustomersAdapter
import com.pal2hmnk.example.customers.usecases.FindUserByName
import com.pal2hmnk.example.generated.grpc.services.UserInfo
import com.pal2hmnk.example.generated.grpc.services.UserName
import com.pal2hmnk.example.generated.grpc.services.UserServiceGrpcKt

class UserController(
    private val scenario: FindUserByName,
) : UserServiceGrpcKt.UserServiceCoroutineImplBase() {

    override suspend fun findUserInfoByName(request: UserName): UserInfo = try {
        val outputData = scenario.exec(request.value)
        CustomersAdapter.translate(outputData)
    } catch (e: Exception) {
        println(e)
        UserInfo.getDefaultInstance()
    }
}
