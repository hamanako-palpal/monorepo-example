package com.pal2hmnk.example.permissions.infrastructure

import com.ninja_squad.dbsetup_kotlin.dbSetup
import com.pal2hmnk.example.permissions.config.KotestProjectConfig
import com.pal2hmnk.example.permissions.domains.entities.IssuedSecurityToken
import com.pal2hmnk.example.permissions.domains.entities.SecurityToken
import com.pal2hmnk.example.permissions.domains.entities.SecurityTokenRepository
import com.pal2hmnk.example.permissions.domains.values.Role
import com.pal2hmnk.example.permissions.domains.values.ShopId
import com.pal2hmnk.example.permissions.domains.values.UserId
import com.pal2hmnk.example.permissions.infrastructure.persistance.exposed.RefreshTokens
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.StringRedisTemplate

@SpringBootTest
class SecurityTokenRepositoryImplTest(
    private val temp: StringRedisTemplate,
    private val securityTokenRepository: SecurityTokenRepository,
) : FunSpec({
    beforeTest {
        dbSetup(to = KotestProjectConfig.dataSource()) {
            insertInto("permissions") {
                columns("id", "name")
                values(1000, "ReadStaffInfo")
            }
            insertInto("role_permissions") {
                columns("role_key", "permission_id")
                values("manager", 1000)
            }
        }.launch()
    }
    test("insert test") {
        val securityToken = SecurityToken(
            userId = UserId(1000),
            stuffInfos = listOf(ShopId(1000) to Role("manager")),
            "test",
        )
        val issuedToken = IssuedSecurityToken(
            accessToken = "accessToken", idToken = "idToken", refreshToken = "refreshToken"
        )
        securityTokenRepository.store(securityToken, issuedToken)
        val cached = temp.opsForValue().get("it_1000")
        cached shouldBe "idToken"
        val refreshTokenInserted = transaction {
            RefreshTokens.selectAll().singleOrNull()!!
        }
        refreshTokenInserted[RefreshTokens.userId] shouldBe 1000
    }
})
