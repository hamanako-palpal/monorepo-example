package com.pal2hmnk.example.gateway.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono

@Configuration
class SecurityConfiguration {
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
            .anonymous()
//            .securityContextRepository(serverSecurityContextRepository)
//            .authenticationManager(authenticationManager)
//            .authorizeExchange()
//            .anyExchange()
//            .authenticated()

            .and()
            .csrf().disable()
            .build()
    }
}
