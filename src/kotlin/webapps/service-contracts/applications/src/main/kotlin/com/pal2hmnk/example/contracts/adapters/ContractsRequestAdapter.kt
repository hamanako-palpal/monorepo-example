package com.pal2hmnk.example.contracts.adapters

import com.pal2hmnk.example.generated.grpc.services.UserId

object ContractsRequestAdapter {
    fun transform(request: UserId) =
        com.pal2hmnk.example.contracts.domains.values.UserId(request.id)
}
