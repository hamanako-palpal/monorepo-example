package com.pal2hmnk.example.permissions.infrastructure

import com.pal2hmnk.example.permissions.domains.entities.AccessToken
import com.pal2hmnk.example.permissions.domains.entities.IdToken
import com.pal2hmnk.example.permissions.domains.entities.IssuedSecurityToken
import com.pal2hmnk.example.permissions.domains.entities.RefreshToken
import com.pal2hmnk.example.permissions.domains.entities.SecurityToken
import com.pal2hmnk.example.permissions.domains.entities.SecurityTokenRepository
import com.pal2hmnk.example.permissions.infrastructure.persistance.exposed.RefreshTokens
import com.pal2hmnk.example.permissions.infrastructure.persistance.redis.RedisSecurityTokenRepository
import com.pal2hmnk.example.shared.utils.DateConverter
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
            accessToken.getKey(),
            accessToken.userId.value.toString(),
            DateConverter.localDateTimeToDate(accessToken.expired),
        )

    private fun doStore(idToken: IdToken, issuedToken: String) =
        redisSecurityTokenRepository.store(
            idToken.getKey(),
            issuedToken,
            DateConverter.localDateTimeToDate(idToken.expired),
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
