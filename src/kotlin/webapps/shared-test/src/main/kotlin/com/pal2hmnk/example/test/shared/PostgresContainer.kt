package com.pal2hmnk.example.test.shared

import org.testcontainers.containers.PostgreSQLContainer
import org.jetbrains.exposed.sql.Database

object PostgresContainer {
    private val container = PostgreSQLContainer("postgres:alpine")

    fun connectContainer() {
        container.start()
        Database.connect(
            container.jdbcUrl,
            container.driverClassName,
            container.username,
            container.password,
        )
    }
}
