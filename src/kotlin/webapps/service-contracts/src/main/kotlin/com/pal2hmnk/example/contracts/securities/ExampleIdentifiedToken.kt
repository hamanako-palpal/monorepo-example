package com.pal2hmnk.example.contracts.securities

import com.pal2hmnk.example.contracts.domains.entities.UserIdentity
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

class ExampleIdentifiedToken(
    private val userIdentity: UserIdentity,
) : AbstractAuthenticationToken(
    userIdentity.permissions.map { SimpleGrantedAuthority(it.name) }
) {
    override fun getCredentials(): Any? = null
    override fun getPrincipal(): Any = userIdentity
    override fun isAuthenticated(): Boolean = true

    companion object {
        private const val serialVersionUID: Long = 0L
    }
}
