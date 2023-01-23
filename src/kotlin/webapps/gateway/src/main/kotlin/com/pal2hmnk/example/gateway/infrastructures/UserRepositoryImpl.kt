package com.pal2hmnk.example.gateway.infrastructures

import com.pal2hmnk.example.gateway.adapters.UsersAdapter
import com.pal2hmnk.example.gateway.domains.entities.User
import com.pal2hmnk.example.gateway.domains.entities.UserRepository
import com.pal2hmnk.example.gateway.infrastructures.grpc.clients.CustomersGrpcClient
import org.springframework.stereotype.Service

@Service
class UserRepositoryImpl(
    private val customersGrpcClient: CustomersGrpcClient,
) : UserRepository {
    override suspend fun findUserInfo(name: String): User =
        customersGrpcClient.findUserInfo(name)

    override suspend fun signUp(userName: String, password: String, email: String): User =
        customersGrpcClient.signUp {
            authInfo = UsersAdapter.userAuthInfoAsGRpc {
                setEmail(email)
                setPassword(password)
            }
            name = UsersAdapter.userNameAsGRpc(userName = userName)
        }
}
