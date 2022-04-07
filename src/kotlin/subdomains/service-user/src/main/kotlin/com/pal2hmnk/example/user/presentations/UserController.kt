package com.pal2hmnk.example.user.presentations

import com.pal2hmnk.example.generated.grpc.services.UserInfo
import com.pal2hmnk.example.generated.grpc.services.UserRequest
import com.pal2hmnk.example.generated.grpc.services.UserServiceGrpcKt
import com.pal2hmnk.example.user.usecases.UserScenario
import com.pal2hmnk.example.user.usecases.UserTranslator

class UserController(
    private val scenario: UserScenario,
) : UserServiceGrpcKt.UserServiceCoroutineImplBase() {
    override suspend fun findUserInfo(request: UserRequest): UserInfo =
        scenario.findByName(request.name).let { UserTranslator.toDTO(it) }
}
