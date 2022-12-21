package com.pal2hmnk.example.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class RootApplication

fun main(args: Array<String>) {
    runApplication<RootApplication>(*args)
}
