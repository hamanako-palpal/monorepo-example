package com.pal2hmnk.example.permissions.infrastructures

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
        JwtIssuer(token).doIssue(withCustomClaim(token))

    private fun withCustomClaim(token: IdToken): JWTCreator.Builder.() -> Unit = {
        val permissionMap = permissionMapper.findByRoles()
        val (shopId, role) = token.stuffInfo
        shopId?.let { withClaim("shopId", it.value) }
        withArrayClaim(
            "permissions",
            permissionMap[role]!!.map(Permission::name).toTypedArray()
        )
        withAudience(*token.audience.toTypedArray())
    }
}
