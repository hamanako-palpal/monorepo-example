package com.pal2hmnk.example.permissions.infrastructure.persistance.redis

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.util.Date

@Component
class RedisSecurityTokenRepository(
    private val redisTemplate: StringRedisTemplate,
) {
    fun store(key: String, value: String, expire: Date) {
        redisTemplate.opsForValue().set(key, value)
        redisTemplate.expireAt(key, expire)
    }
}
