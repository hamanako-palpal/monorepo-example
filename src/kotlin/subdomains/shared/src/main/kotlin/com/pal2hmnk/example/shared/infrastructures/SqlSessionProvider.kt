package com.pal2hmnk.example.shared.infrastructures

import org.apache.ibatis.session.SqlSession

interface SqlSessionProvider {
    fun get(): SqlSession
}
