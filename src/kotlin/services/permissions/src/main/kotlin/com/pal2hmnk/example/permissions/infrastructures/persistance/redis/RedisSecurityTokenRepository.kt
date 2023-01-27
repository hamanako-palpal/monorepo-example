package com.pal2hmnk.example.permissions.infrastructures.persistance.redis

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

    fun find(key: String): String? {
        return redisTemplate.opsForValue().get(key)
    }
}
