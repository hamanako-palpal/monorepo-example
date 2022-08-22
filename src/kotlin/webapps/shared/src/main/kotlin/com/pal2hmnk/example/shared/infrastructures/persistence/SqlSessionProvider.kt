package com.pal2hmnk.example.shared.infrastructures.persistence

import org.apache.ibatis.session.SqlSession

interface SqlSessionProvider {
    val dataSourceConfig: DataSourceConfig
    val session: SqlSession
}
