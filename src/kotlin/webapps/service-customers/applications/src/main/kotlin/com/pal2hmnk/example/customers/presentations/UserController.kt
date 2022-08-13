package com.pal2hmnk.example.customers.presentations

import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.customers.usecases.FindUserByName
import com.pal2hmnk.example.generated.grpc.services.UserInfo
import com.pal2hmnk.example.generated.grpc.services.UserName
import com.pal2hmnk.example.generated.grpc.services.UserServiceGrpcKt

class UserController(
    private val scenario: FindUserByName,
) : UserServiceGrpcKt.UserServiceCoroutineImplBase() {

    override suspend fun findUserInfoByName(request: UserName): UserInfo  = try {
        scenario.exec(request.value).translate()
    } catch (e: Exception) {
        println(e)
        UserInfo.getDefaultInstance()
    }
    private fun User.translate(): UserInfo =
        UserInfo.newBuilder()
            .setId(this.userId)
            .setName(this.name.value)
            .build()
}
