package com.pal2hmnk.example.securitytokens.infrastructure

import com.pal2hmnk.example.entities.SecurityToken
import com.pal2hmnk.example.entities.SecurityTokenRepository
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
