package com.pal2hmnk.example.customers.configurations

import com.pal2hmnk.example.customers.domains.entities.UserIdentityResolver
import com.pal2hmnk.example.customers.securities.ExampleIdentifiedToken
import org.lognet.springboot.grpc.security.GrpcSecurity
import org.lognet.springboot.grpc.security.GrpcSecurityConfigurerAdapter
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken

@Configuration
class GrpcSecurityConfiguration(
    private val userIdentityResolver: UserIdentityResolver,
): GrpcSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(builder: GrpcSecurity) {
        builder.authorizeRequests()
            .anyMethod().authenticated()
            .and()
            .authenticationProvider(object : AuthenticationProvider {
                override fun authenticate(authentication: Authentication): Authentication {
                    val authToken = authentication.credentials.toString()
                    return userIdentityResolver.resolve(authToken)
                        .takeIf { it.isValid }
                        ?.let { ExampleIdentifiedToken(it) }
                        ?: authentication
                }

                override fun supports(authentication: Class<*>?): Boolean {
                    return authentication == BearerTokenAuthenticationToken::class.java
                }
            })
    }
}
