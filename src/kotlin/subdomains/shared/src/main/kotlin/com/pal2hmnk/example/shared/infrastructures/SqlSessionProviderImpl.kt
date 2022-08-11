package com.pal2hmnk.example.shared.infrastructures

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.ExecutorType
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory

abstract class SqlSessionProviderImpl: SqlSessionProvider {

    abstract val mapper: SqlSessionConfigMapper

    override fun get(): SqlSession {
        val dsConfig = DataSourceConfig()
        val ds = UnpooledDataSource(
            dsConfig.driverClassName,
            dsConfig.url,
            dsConfig.username,
            dsConfig.password,
        )
        val environment = Environment("test", JdbcTransactionFactory(), ds)
        val config = Configuration(environment).apply(mapper)
        return SqlSessionFactoryBuilder()
            .build(config)
            .openSession(ExecutorType.REUSE, true)
    }
}
