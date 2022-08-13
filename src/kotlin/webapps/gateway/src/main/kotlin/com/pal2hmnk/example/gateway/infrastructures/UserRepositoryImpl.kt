package com.pal2hmnk.example.gateway.infrastructures

import com.pal2hmnk.example.gateway.domains.User
import com.pal2hmnk.example.gateway.domains.UserRepository
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.CustomersGrpcClient
import org.springframework.stereotype.Service

@Service
class UserRepositoryImpl(
    private val customersGrpcClient: CustomersGrpcClient,
): UserRepository {
    override suspend fun findUserInfo(name: String): User =
        customersGrpcClient.findUserInfo(name)
}
