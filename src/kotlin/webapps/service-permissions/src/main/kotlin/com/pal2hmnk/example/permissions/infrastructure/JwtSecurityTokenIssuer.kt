package com.pal2hmnk.example.permissions.infrastructure

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.pal2hmnk.example.permissions.domains.entities.PermissionMapper
import com.pal2hmnk.example.permissions.domains.entities.SecurityToken
import com.pal2hmnk.example.permissions.domains.entities.SecurityTokenIssuer
import com.pal2hmnk.example.shared.utils.DateConverter
import com.pal2hmnk.example.permissions.domains.values.Permission
import org.springframework.stereotype.Component

@Component
class JwtSecurityTokenIssuer(
    private val permissionMapper: PermissionMapper,
) : SecurityTokenIssuer {
    private val privateKey = "secret"

    override fun issue(securityToken: SecurityToken): String {
        var token = ""
        try {
            val algorithm = Algorithm.HMAC256(privateKey)
            token = JWT.create()
                .withIssuer("permissions")
                .withSubject(securityToken.userId.toString())
                .withKeyId("keyId")
                .withAudience("contracts", "customers")
                .withExpiresAt(DateConverter.localDateTimeToDate(securityToken.expired))
                .apply(applyArrayClaim(securityToken))
                .sign(algorithm)

        } catch (e: JWTCreationException) {
            //Invalid Signing
        }
        return token
    }

    private fun applyArrayClaim(securityToken: SecurityToken): JWTCreator.Builder.() -> Unit = {
        val permissionMap = permissionMapper.findByRoles()
        securityToken.stuffInfos.map { (shopId, role) ->
            withArrayClaim(
                shopId.toString(),
                permissionMap[role]!!
                    .map(Permission::name)
                    .toTypedArray(),
            )
        }
    }
}
