package com.pal2hmnk.example.customers.usecases

import com.pal2hmnk.example.customers.domains.entities.User
import com.pal2hmnk.example.shared.usecases.Scenario

interface FindUserByName: Scenario<User, String>
