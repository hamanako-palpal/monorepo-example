package com.pal2hmnk.example.gateway.adapters

import com.pal2hmnk.example.generated.grpc.services.UserAuthInfo
import com.pal2hmnk.example.generated.grpc.services.UserName

object UsersAdapter {
    fun userNameAsGRpc(userName: String): UserName =
        UserName.newBuilder().setValue(userName).build()

    fun userAuthInfoAsGRpc(cmd: UserAuthInfo.Builder.() -> Unit): UserAuthInfo =
        UserAuthInfo.newBuilder().apply(cmd).build()
}
