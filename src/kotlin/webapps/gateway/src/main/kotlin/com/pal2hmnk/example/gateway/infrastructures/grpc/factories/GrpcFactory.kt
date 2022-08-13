package com.pal2hmnk.example.gateway.infrastructures.grpc.factories

import com.pal2hmnk.example.generated.grpc.services.ShopId
import com.pal2hmnk.example.generated.grpc.services.UserId
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

object GrpcFactory {
    fun createChannel(name: String?, port: Int?): ManagedChannel =
        ManagedChannelBuilder
            .forAddress(name ?: "localhost", port ?: 99999)
            .usePlaintext()
            .build()
    fun userId(): UserId.Builder = UserId.newBuilder()
    fun shopId(): ShopId.Builder = ShopId.newBuilder()
}
