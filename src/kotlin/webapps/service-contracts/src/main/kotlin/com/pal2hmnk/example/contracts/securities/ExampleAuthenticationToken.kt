package com.pal2hmnk.example.contracts.securities

import org.springframework.security.authentication.AbstractAuthenticationToken

class ExampleAuthenticationToken(
    private val token: String,
) : AbstractAuthenticationToken(null) {
    override fun getCredentials(): Any? = null

    override fun getPrincipal(): String {
        return token
    }

    companion object {
        private const val serialVersionUID: Long = 0
    }
}
