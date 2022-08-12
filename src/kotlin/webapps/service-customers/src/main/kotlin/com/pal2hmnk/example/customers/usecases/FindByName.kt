package com.pal2hmnk.example.customers.usecases

import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.shared.usecases.Scenario

interface FindByName: Scenario<User, String>
