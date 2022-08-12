package com.pal2hmnk.example.gateway.infrastructures

import com.pal2hmnk.example.gateway.domains.User
import com.pal2hmnk.example.gateway.domains.UserRepository
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.UserGrpcClient
import org.springframework.stereotype.Service

@Service
class UserRepositoryImpl(
    private val userGrpcClient: UserGrpcClient,
): UserRepository {
    override suspend fun findUserInfo(name: String): User =
        userGrpcClient.findUserInfo(name)
}
