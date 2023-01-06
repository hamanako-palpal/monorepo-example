package com.pal2hmnk.example.permissions.domains.usecases

import com.pal2hmnk.example.permissions.infrastructures.JwtResolver
import com.pal2hmnk.example.permissions.infrastructures.persistance.redis.RedisSecurityTokenRepository
import org.springframework.stereotype.Service

@Service
class GetIdTokenImpl(
    private val redisSecurityTokenRepository: RedisSecurityTokenRepository
) : GetIdToken {
    override fun exec(accessToken: String): String? {
        val (jti, userId) = JwtResolver().resolveAccessToken(accessToken)
        val userIdAssumed = redisSecurityTokenRepository.find("at_$jti")?.toInt()
        if (userId.value != userIdAssumed) {
            return null
        }
        return redisSecurityTokenRepository.find("it_$userIdAssumed")
    }
}
