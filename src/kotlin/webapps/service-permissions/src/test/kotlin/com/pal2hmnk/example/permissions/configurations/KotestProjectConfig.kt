package com.pal2hmnk.example.permissions.configurations

import com.pal2hmnk.example.test.shared.PostgresContainer
import com.pal2hmnk.example.test.shared.RedisContainer
import io.kotest.core.config.AbstractProjectConfig
import org.springframework.boot.jdbc.DataSourceBuilder
import javax.sql.DataSource

object KotestProjectConfig : AbstractProjectConfig() {
    override suspend fun beforeProject() {
        PostgresContainer.connectContainer()
        RedisContainer.connectContainer()
        System.setProperty("spring.datasource.url", PostgresContainer.getJdbcUrl())
        System.setProperty("spring.datasource.username", PostgresContainer.getUsername())
        System.setProperty("spring.datasource.password", PostgresContainer.getPass())
        System.setProperty("spring.redis.host", RedisContainer.getHost())
        System.setProperty("spring.redis.port", RedisContainer.getPort().toString())
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
