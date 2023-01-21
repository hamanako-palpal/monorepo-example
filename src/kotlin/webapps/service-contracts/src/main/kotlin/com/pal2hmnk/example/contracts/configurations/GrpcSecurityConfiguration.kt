package com.pal2hmnk.example.contracts.configurations

import com.pal2hmnk.example.contracts.domains.entities.UserIdentityRepository
import com.pal2hmnk.example.contracts.securities.ExampleIdentifiedToken
import org.lognet.springboot.grpc.security.GrpcSecurity
import org.lognet.springboot.grpc.security.GrpcSecurityConfigurerAdapter
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken


@Configuration
class GrpcSecurityConfiguration(
    private val userIdentityRepository: UserIdentityRepository,
) : GrpcSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(builder: GrpcSecurity) {
        builder.authorizeRequests()
            .anyMethod().authenticated()
            .and()
            .authenticationProvider(object : AuthenticationProvider {
                override fun authenticate(authentication: Authentication): Authentication {
                    val authToken = authentication.credentials.toString()
                    val userIdentity = userIdentityRepository.getUserIdentity(authToken)
                        .takeIf { it.isValid }
                    return userIdentity?.let { ExampleIdentifiedToken(it) }!!
                }

                override fun supports(authentication: Class<*>?): Boolean {
                    return authentication == BearerTokenAuthenticationToken::class.java
                }
            })
    }
}
