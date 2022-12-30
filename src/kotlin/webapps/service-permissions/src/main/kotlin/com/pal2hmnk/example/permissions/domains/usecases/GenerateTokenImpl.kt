package com.pal2hmnk.example.permissions.domains.usecases

import com.pal2hmnk.example.permissions.domains.entities.SecurityToken
import com.pal2hmnk.example.permissions.domains.entities.SecurityTokenIssuer
import com.pal2hmnk.example.permissions.domains.entities.SecurityTokenRepository
import org.springframework.stereotype.Service

@Service
class GenerateTokenImpl(
    private val securityTokenIssuer: SecurityTokenIssuer,
    private val securityTokenRepository: SecurityTokenRepository,
) : GenerateToken {
    override fun exec(securityToken: SecurityToken): String {
        val issued = securityTokenIssuer.issue(securityToken)
        securityTokenRepository.store(securityToken, issued)
        return securityToken.connectionId
    }
}
