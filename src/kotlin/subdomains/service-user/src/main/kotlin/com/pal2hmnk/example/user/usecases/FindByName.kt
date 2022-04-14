package com.pal2hmnk.example.user.usecases

import com.pal2hmnk.example.shared.usecases.Scenario
import com.pal2hmnk.example.user.domains.User

interface FindByName: Scenario<User, String>
