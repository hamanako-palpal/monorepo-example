package com.pal2hmnk.example.test.shared

import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

object RedisContainer {
    private val redis = GenericContainer(
        DockerImageName.parse("redis:6.2")
    )
        .withExposedPorts(6379)

    fun connectContainer() {
        redis.start()
    }

    fun getHost(): String = redis.host
    fun getPort(): Int = redis.getMappedPort(6379)
}
