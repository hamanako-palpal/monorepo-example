package com.pal2hmnk.example.gateway.securities

import com.pal2hmnk.example.gateway.domains.entities.IdToken
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

class GatewayIdentifiedToken(
    private val idToken: IdToken,
) : AbstractAuthenticationToken(listOf(SimpleGrantedAuthority("AUTHENTICATED"))) {
    override fun getCredentials(): Any? = null
    override fun getPrincipal(): Any = idToken

    companion object {
        private const val serialVersionUID: Long = 0L
    }
}
