package com.pal2hmnk.example.customers.configurations

import com.pal2hmnk.example.test.shared.PostgresContainer
import io.kotest.core.config.AbstractProjectConfig
import org.springframework.boot.jdbc.DataSourceBuilder
import javax.sql.DataSource

object KotestProjectConfig : AbstractProjectConfig() {
    override suspend fun beforeProject() {
        PostgresContainer.connectContainer()
    }

    fun dataSource(): DataSource {
        return DataSourceBuilder.create().apply {
            driverClassName(PostgresContainer.getDriverClassName())
            url(PostgresContainer.getJdbcUrl())
            username(PostgresContainer.getUsername())
            password(PostgresContainer.getPass())
        }.build()
    }
}
