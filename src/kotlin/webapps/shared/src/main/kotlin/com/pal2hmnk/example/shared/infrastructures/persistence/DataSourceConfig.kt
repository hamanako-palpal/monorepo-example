package com.pal2hmnk.example.shared.infrastructures.persistence

class DataSourceConfig(
    val url: String? = System.getenv("DB_URL"),
    val username: String? = System.getenv("DB_USER"),
    val password: String? = System.getenv("DB_PASSWORD"),
    val driverClassName: String? = System.getenv("DB_DRIVER"),
)
