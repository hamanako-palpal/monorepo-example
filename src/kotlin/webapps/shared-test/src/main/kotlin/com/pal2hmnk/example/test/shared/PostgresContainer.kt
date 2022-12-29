package com.pal2hmnk.example.test.shared

import org.jetbrains.exposed.sql.Database
import org.testcontainers.containers.PostgreSQLContainer

object PostgresContainer {
    private val container = PostgreSQLContainer("postgres:12.13-alpine")
        .apply {
            withInitScript("ddl.sql")
        }

    fun connectContainer() {
        container.start()
        Database.connect(
            container.jdbcUrl,
            container.driverClassName,
            container.username,
            container.password,
        )
    }

    fun getJdbcUrl(): String = container.jdbcUrl
    fun getUsername() = container.username
    fun getPass() = container.password
}
