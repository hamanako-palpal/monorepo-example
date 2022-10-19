package com.pal2hmnk.example.customers.infrastructures

import com.pal2hmnk.example.test.shared.PostgresContainer
import io.kotest.core.config.AbstractProjectConfig

object KotestProjectConfig : AbstractProjectConfig() {
    override suspend fun beforeProject() {
        PostgresContainer.connectContainer()
    }
}
