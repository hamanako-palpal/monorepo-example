package com.pal2hmnk.example.gateway.securities

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

class GatewayAnonymousToken : AbstractAuthenticationToken(
    listOf(SimpleGrantedAuthority("ANONYMOUS"))
) {
    override fun getCredentials(): Any? = null
    override fun getPrincipal(): Any? = null
    override fun isAuthenticated(): Boolean = true

    companion object {
        private const val serialVersionUID: Long = 0L
    }
}
