package com.pal2hmnk.example.permissions.infrastructure.persistance.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisSecurityTokenRepository(
    private val redisTemplate: StringRedisTemplate,
) {
    fun store(key: String, value: String) {
        redisTemplate.opsForValue().set(key, value)
    }
}
