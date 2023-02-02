package com.pal2hmnk.example.customers.domains.usecases

import com.pal2hmnk.example.customers.domains.entities.Staff
import com.pal2hmnk.example.customers.domains.entities.StaffRepository
import com.pal2hmnk.example.customers.domains.values.ShopId
import org.springframework.stereotype.Service

@Service
class FindStaffsByShopIdImpl(
    private val staffRepository: StaffRepository,
) : FindStaffsByShopId {
    override fun exec(shopId: ShopId): List<Staff> =
        staffRepository.findByShopId(shopId)
}
