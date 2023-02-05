package com.pal2hmnk.example.customers.configurations

import com.pal2hmnk.example.customers.domains.entities.UserIdentityResolver
import com.pal2hmnk.example.customers.securities.ExampleIdentifiedToken
import com.pal2hmnk.example.generated.grpc.services.AuthServiceGrpcKt
import com.pal2hmnk.example.generated.grpc.services.UserServiceGrpcKt
import io.grpc.BindableService
import io.grpc.ServerServiceDefinition
import org.lognet.springboot.grpc.GRpcServicesRegistry
import org.lognet.springboot.grpc.security.GrpcSecurity
import org.lognet.springboot.grpc.security.GrpcSecurityConfigurerAdapter
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken

@Configuration
class GrpcSecurityConfiguration(
    private val gRpcServicesRegistry: GRpcServicesRegistry,
    private val userIdentityResolver: UserIdentityResolver,
): GrpcSecurityConfigurerAdapter() {

    private val authenticationProvider = object : AuthenticationProvider {
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
    }

    private val permissiveAuthorizationMethods = listOf(
        AuthServiceGrpcKt.authenticateMethod,
        UserServiceGrpcKt.signUpMethod,
    )

    @Throws(Exception::class)
    override fun configure(builder: GrpcSecurity) {
        val methods = gRpcServicesRegistry.beanNameToServiceBeanMap.values
            .map(BindableService::bindService)
            .map(ServerServiceDefinition::getServiceDescriptor)
            .flatMap { it.methods }
            .filterNot { permissiveAuthorizationMethods.contains(it) }
            .toTypedArray()
        builder.authorizeRequests()
            .methods(*methods)
            .authenticated()
            .and()
            .authenticationProvider(authenticationProvider)
    }
}
