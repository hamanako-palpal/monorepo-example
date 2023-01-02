package com.pal2hmnk.example.permissions.infrastructure

import com.pal2hmnk.example.permissions.domains.entities.AccessToken
import com.pal2hmnk.example.permissions.domains.entities.IdToken
import com.pal2hmnk.example.permissions.domains.entities.IssuedSecurityToken
import com.pal2hmnk.example.permissions.domains.entities.RefreshToken
import com.pal2hmnk.example.permissions.domains.entities.SecurityToken
import com.pal2hmnk.example.permissions.domains.entities.SecurityTokenRepository
import com.pal2hmnk.example.permissions.infrastructure.persistance.exposed.RefreshTokens
import com.pal2hmnk.example.permissions.infrastructure.persistance.redis.RedisSecurityTokenRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class SecurityTokenRepositoryImpl(
    private val redisSecurityTokenRepository: RedisSecurityTokenRepository,
) : SecurityTokenRepository {
    override fun store(
        securityToken: SecurityToken,
        issuedSecurityToken: IssuedSecurityToken
    ) {
        doStore(securityToken.accessToken)
        doStore(securityToken.idToken, issuedSecurityToken.idToken)
        doStore(securityToken.refreshToken)
    }

    private fun doStore(accessToken: AccessToken) =
        redisSecurityTokenRepository.store(
            "at_${accessToken.jti}",
            accessToken.userId.value.toString(),
        )

    private fun doStore(idToken: IdToken, issuedToken: String) =
        redisSecurityTokenRepository.store(
            "it_${idToken.userId.value}",
            issuedToken,
        )

    private fun doStore(refreshToken: RefreshToken) {
        transaction {
            RefreshTokens.insert {
                it[jti] = refreshToken.jti
                it[this.userId] = refreshToken.userId.value
                it[this.expired] = refreshToken.expired
                it[this.clientId] = refreshToken.clientId
            }
        }
    }
}
