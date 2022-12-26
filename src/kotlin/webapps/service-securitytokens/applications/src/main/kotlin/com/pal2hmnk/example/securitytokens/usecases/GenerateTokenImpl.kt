package com.pal2hmnk.example.securitytokens.usecases

import com.pal2hmnk.example.entities.SecurityToken
import com.pal2hmnk.example.entities.SecurityTokenIssuer
import com.pal2hmnk.example.entities.SecurityTokenRepository
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
