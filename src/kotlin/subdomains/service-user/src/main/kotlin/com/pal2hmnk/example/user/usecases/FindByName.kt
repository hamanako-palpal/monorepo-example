package com.pal2hmnk.example.user.usecases

import com.pal2hmnk.example.domain.user.entities.User
import com.pal2hmnk.example.shared.usecases.Scenario

interface FindByName: Scenario<User, String>
