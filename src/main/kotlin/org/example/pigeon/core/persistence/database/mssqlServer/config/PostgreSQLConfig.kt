package org.example.pigeon.core.persistence.database.mssqlServer.config

import jakarta.inject.Inject
import jakarta.inject.Singleton
import org.example.pigeon.core.persistence.config.BaseDataSourceConfig
import org.example.pigeon.core.persistence.config.BaseHikariConfig

@Singleton
class PostgreSQLConfig: BaseDataSourceConfig() {
    @Inject
    lateinit var hikari: BaseHikariConfig
}
