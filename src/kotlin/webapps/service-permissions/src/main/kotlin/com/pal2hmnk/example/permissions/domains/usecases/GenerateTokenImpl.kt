package com.pal2hmnk.example.permissions.domains.usecases

import com.pal2hmnk.example.permissions.domains.entities.AccessTokenIssuer
import com.pal2hmnk.example.permissions.domains.entities.IdTokenIssuer
import com.pal2hmnk.example.permissions.domains.entities.IssuedSecurityToken
import com.pal2hmnk.example.permissions.domains.entities.RefreshTokenIssuer
import com.pal2hmnk.example.permissions.domains.entities.SecurityToken
import com.pal2hmnk.example.permissions.domains.entities.SecurityTokenRepository
import org.springframework.stereotype.Service

@Service
class GenerateTokenImpl(
    private val accessTokenIssuer: AccessTokenIssuer,
    private val idTokenIssuer: IdTokenIssuer,
    private val refreshTokenIssuer: RefreshTokenIssuer,
    private val securityTokenRepository: SecurityTokenRepository,
) : GenerateToken {
    override fun exec(securityToken: SecurityToken): IssuedSecurityToken {
        val issuedSecurityToken = IssuedSecurityToken(
            accessToken = accessTokenIssuer.issue(securityToken.accessToken),
            idToken = idTokenIssuer.issue(securityToken.idToken),
            refreshToken = refreshTokenIssuer.issue(securityToken.refreshToken),
        )
        securityTokenRepository.store(securityToken, issuedSecurityToken)
        return issuedSecurityToken
    }
}
