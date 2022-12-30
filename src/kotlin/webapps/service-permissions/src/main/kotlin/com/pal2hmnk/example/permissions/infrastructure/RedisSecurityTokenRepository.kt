package com.pal2hmnk.example.permissions.infrastructure

import com.pal2hmnk.example.permissions.domains.entities.SecurityToken
import com.pal2hmnk.example.permissions.domains.entities.SecurityTokenRepository
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisSecurityTokenRepository(
    private val redisTemplate : StringRedisTemplate,
) : SecurityTokenRepository {
    override fun store(securityToken: SecurityToken, issuedToken: String) {
        redisTemplate.opsForValue().set(securityToken.connectionId, issuedToken)
    }
}
