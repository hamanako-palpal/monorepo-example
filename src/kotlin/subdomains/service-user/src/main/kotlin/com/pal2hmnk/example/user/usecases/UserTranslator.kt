package com.pal2hmnk.example.user.usecases

import com.pal2hmnk.example.generated.grpc.services.UserInfo
import com.pal2hmnk.example.user.domains.User

class UserTranslator {
    companion object {
        val toDTO: ((User) -> UserInfo) = {
            UserInfo.newBuilder()
                .setId(it.userId)
                .setName(it.name)
                .build()
        }
    }
}
