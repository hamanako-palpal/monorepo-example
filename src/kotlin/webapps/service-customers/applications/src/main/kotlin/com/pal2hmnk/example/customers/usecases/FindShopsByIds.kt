package com.pal2hmnk.example.customers.usecases

import com.pal2hmnk.example.customers.domains.entities.Shop
import com.pal2hmnk.example.customers.domains.values.ShopId
import com.pal2hmnk.example.shared.usecases.Scenario

interface FindShopsByIds : Scenario<List<Shop>, List<ShopId>>
