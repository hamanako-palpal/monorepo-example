package com.pal2hmnk.example.customers.presentations

import com.google.protobuf.Empty
import com.pal2hmnk.example.customers.adapters.CustomersAdapter
import com.pal2hmnk.example.customers.domains.entities.UserIdentity
import com.pal2hmnk.example.customers.domains.usecases.FindShopsByIds
import com.pal2hmnk.example.customers.domains.usecases.FindStaffsByShopId
import com.pal2hmnk.example.customers.domains.values.ShopId
import com.pal2hmnk.example.customers.securities.ExampleIdentifiedToken
import com.pal2hmnk.example.generated.grpc.services.ShopIdsRequest
import com.pal2hmnk.example.generated.grpc.services.ShopInfos
import com.pal2hmnk.example.generated.grpc.services.ShopServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.StaffInfos
import com.pal2hmnk.example.shared.presentations.UseCaseRunner
import org.lognet.springboot.grpc.GRpcService
import org.lognet.springboot.grpc.security.GrpcSecurity
import org.springframework.security.access.prepost.PreAuthorize

@GRpcService
class ShopGRpcService(
    findShopsByIds: FindShopsByIds,
    findStaffsByShopId: FindStaffsByShopId,
) : ShopServiceGrpcKt.ShopServiceCoroutineImplBase() {

    private val useCaseRunner = UseCaseRunner(
        useCase = findShopsByIds::exec,
        converter = CustomersAdapter::shopInfosAsGRpc,
        exceptionHandler = { ShopInfos.getDefaultInstance() }
    )

    private val findStaffsByShopIdRunner = UseCaseRunner(
        useCase = findStaffsByShopId::exec,
        converter = CustomersAdapter::staffsAsGRpc,
        exceptionHandler = { StaffInfos.getDefaultInstance() }
    )

    override suspend fun findShopInfo(request: ShopIdsRequest): ShopInfos =
        useCaseRunner
            .initial { CustomersAdapter.inputDataOf(request) }
            .run()

    @PreAuthorize("hasRole('findStaffInfos')")
    override suspend fun findStaffInfos(request: Empty): StaffInfos =
        findStaffsByShopIdRunner
            .initial {
                val auth = GrpcSecurity.AUTHENTICATION_CONTEXT_KEY.get() as ExampleIdentifiedToken
                val userIdentity = auth.principal as UserIdentity
                ShopId(userIdentity.shopId!!.value)
            }
            .run()
}
