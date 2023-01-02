package com.pal2hmnk.example.permissions.infrastructure

import com.auth0.jwt.JWTCreator
import com.pal2hmnk.example.permissions.domains.entities.IdToken
import com.pal2hmnk.example.permissions.domains.entities.IdTokenIssuer
import com.pal2hmnk.example.permissions.domains.entities.PermissionMapper
import com.pal2hmnk.example.permissions.domains.values.Permission
import org.springframework.stereotype.Component

@Component
class IdTokenIssuerImpl(
    private val permissionMapper: PermissionMapper,
) : IdTokenIssuer {
    override fun issue(token: IdToken): String =
        JwtIssuer(token).doIssue(applyArrayClaim(token))

    private fun applyArrayClaim(token: IdToken): JWTCreator.Builder.() -> Unit = {
        val permissionMap = permissionMapper.findByRoles()
        token.stuffInfos.map { (shopId, role) ->
            withArrayClaim(
                shopId.toString(),
                permissionMap[role]!!
                    .map(Permission::name)
                    .toTypedArray(),
            )
        }
    }
}
