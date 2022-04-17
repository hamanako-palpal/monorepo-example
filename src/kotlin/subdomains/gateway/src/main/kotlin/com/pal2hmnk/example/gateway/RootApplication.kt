package com.pal2hmnk.example.gateway

import com.pal2hmnk.example.gateway.configs.GrpcClientConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(GrpcClientConfig::class)
class RootApplication

fun main(args: Array<String>) {
    runApplication<RootApplication>(*args)
}
