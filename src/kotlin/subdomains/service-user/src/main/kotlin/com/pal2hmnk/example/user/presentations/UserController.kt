package com.pal2hmnk.example.user.presentations

import com.pal2hmnk.example.generated.grpc.services.UserInfo
import com.pal2hmnk.example.generated.grpc.services.UserRequest
import com.pal2hmnk.example.generated.grpc.services.UserServiceGrpcKt
import com.pal2hmnk.example.user.domains.User
import com.pal2hmnk.example.user.usecases.FindByName

class UserController(
    private val scenario: FindByName,
) : UserServiceGrpcKt.UserServiceCoroutineImplBase() {
    override suspend fun findUserInfo(request: UserRequest): UserInfo =
        scenario.exec(request.name).translate()

    private fun User.translate(): UserInfo =
        UserInfo.newBuilder()
            .setId(this.userId)
            .setName(this.name)
            .build()
}
