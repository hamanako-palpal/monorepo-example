package com.pal2hmnk.example.permissions.domains.usecases

interface GetIdToken {
    fun exec(accessToken: String): String?
}
