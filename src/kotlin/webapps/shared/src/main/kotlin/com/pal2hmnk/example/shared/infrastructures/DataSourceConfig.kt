package com.pal2hmnk.example.shared.infrastructures

class DataSourceConfig(
    address: String = System.getenv("DB_ADDRESS"),
    port: String = System.getenv("DB_PORT"),
    database: String = System.getenv("DB_DATABASE"),
    val username: String = System.getenv("DB_USER"),
    val password: String = System.getenv("DB_PASSWORD"),
    val driverClassName: String = System.getenv("DB_DRIVER"),
) {
    val url: String = "jdbc:postgresql://$address:$port/$database"
}
