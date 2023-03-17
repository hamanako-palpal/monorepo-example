package com.pal2hmnk.example.gateway.configurations

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration {
    @Autowired
    private lateinit var authenticationManager: ReactiveAuthenticationManager

    @Autowired
    private lateinit var serverSecurityContextRepository: ServerSecurityContextRepository

    @Bean
    fun securityConfigure(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .exceptionHandling()
            .authenticationEntryPoint { swe, _ ->
                Mono.fromRunnable {
                    swe.response.statusCode = HttpStatus.UNAUTHORIZED
                }
            }
            .and()
            .securityContextRepository(serverSecurityContextRepository)
            .authenticationManager(authenticationManager)
            .authorizeExchange()
            .anyExchange()
            .authenticated()
            .and()
            .csrf()
            .disable()
            .cors()
            .disable()

            .build()
    }
}
